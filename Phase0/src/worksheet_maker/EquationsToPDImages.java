package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.io.IOException;
import java.util.Map;

/**
 * Uses the Facade design pattern to hold instances of classes that manipulate latex and image data.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-5.
 */
public class EquationsToPDImages {
    private final EquationStringToLatex equationStringToLatex = new EquationStringToLatex();
    private final LatexToImage latexToImage = new LatexToImage();

    /**
     * Creates images that are resized using the same proportions so that every image can fit on the page.
     *
     * @param formatEquationDetails Contains details necessary for formatting equation images on a PDF. The keys are
     *                              "equationFormat", "numRows", "numColumns".
     * @param equations             An array containing equations represented as an array of strings.
     * @return A list of resized image lists. The first array represents the images for the questions, the second array
     * represents the images for the answers.
     */
    public PDImageXObject[][] createResizedImages(Map<String, Object> formatEquationDetails, String[][] equations, PDDocument[] worksheetPDFs) throws IOException {
        PDImageXObject[][] qAndAImages = new PDImageXObject[2][equations.length];
        for (int i = 0; i < equations.length; i++) {
            for (int ans = 0; ans < 2; ans++) { //If ans = 1, add it to the answer. Otherwise, add it to question.
                TeXFormula questionFormula = equationStringToLatex.convertEquationStringToLatex(equations[i],
                        (String) formatEquationDetails.get("equationFormat"), ans == 1);
                qAndAImages[ans][i] = latexToImage.convertLatexToImage((new TeXFormula((i+1) + ")\\;\\;\\;")).add(questionFormula), worksheetPDFs[ans]);
            }
        }
        return qAndAImages;
    }
}
