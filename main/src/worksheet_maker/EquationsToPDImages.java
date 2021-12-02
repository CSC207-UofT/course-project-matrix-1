package worksheet_maker;

import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Uses the Facade design pattern to hold instances of classes that convert equations to images suitable for PDF
 * representation.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-5.
 */
public class EquationsToPDImages {
    private final EquationStringToLatex equationStringToLatex = new EquationStringToLatex();
    private final LatexToImage latexToImage = new LatexToImage();

    /**
     * /**
     * Creates images that are resized using the same proportions so that every image can fit on the page.
     *
     * @param formatDetails Contains details necessary for formatting equation images on a PDF. The keys are
     *                      "equationFormat", "numRows", "numColumns".
     * @param equations     A list of maps containing the operands, operator and answer of each equation as Strings.
     * @param worksheetPDFs Two PDFs that equations will be added to. The first represents a PDF with questions
     *                      only, the second represents a PDF with questions and answers.
     * @return A list of high resolution PD images. The first array represents the images for the questions, the second
     * array represents the images for the answers.
     * @throws IOException if the images cannot be added to the file properly.
     */
    public PDImageXObject[][] createResizedImages(FormatDetails formatDetails, List<Map<String, String>> equations,
                                                  PDDocument[] worksheetPDFs) throws IOException {
        PDImageXObject[][] qAndAImages = new PDImageXObject[2][equations.size()];
        for (int i = 0; i < equations.size(); i++) {
            for (int ans = 0; ans < 2; ans++) { //If ans = 1, add it to the answer. Otherwise, add it to question.
                TeXFormula latexEquation = equationStringToLatex.convertEquationStringToLatex(equations.get(i),
                        formatDetails.getEquationFormat(), ans == 1);
                TeXFormula latexEquationWithNumber = new TeXFormula((i + 1) + ")\\;\\;\\;").add(latexEquation);
                qAndAImages[ans][i] = latexToImage.convertLatexToImage(latexEquationWithNumber, worksheetPDFs[ans]);
            }
        }
        return qAndAImages;
    }
}
