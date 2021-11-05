package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Uses the Facade design pattern to hold instances of classes that manipulate latex and image data.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-5.
 */
public class ImageFacade {
    EquationToLatex equationToLatex = new EquationToLatex();
    LatexToImage latexToImage = new LatexToImage();
    ImageResizer imageResizer = new ImageResizer();

    /**
     * Creates images that are resized using the same proportions so that every image can fit on the page.
     *
     * @param formatEquationDetails Contains details necessary for formatting equation images on a PDF. The keys are
     *                              "equationFormat", "numRows", "numColumns".
     * @param worksheet             the worksheet output data.
     * @return A list of resized image lists. The first array represents the images for the questions, the second array
     * represents the images for the answers.
     */
    public BufferedImage[][] createResizedImages(Map<String, Object> formatEquationDetails, WorksheetOutput worksheet) {
        String[][] equations = worksheet.equationsToStringArray();
        BufferedImage[][] qAndAImages = new BufferedImage[2][equations.length];
        for (int i = 0; i < equations.length; i++) {
            for (int ans = 0; ans < 2; ans++) { //If ans = 1, add it to the answer. Otherwise, add it to question.
                TeXFormula questionFormula = equationToLatex.convertEquationStringToLatex(equations[i],
                        (String) formatEquationDetails.get("equationFormat"), ans == 1);
                qAndAImages[ans][i] = latexToImage.convertLatexToImage(questionFormula);
            }
        }
        imageResizer.resize(qAndAImages, (int) formatEquationDetails.get("numRows"),
                (int) formatEquationDetails.get("numColumns"));
        return qAndAImages;
    }
}
