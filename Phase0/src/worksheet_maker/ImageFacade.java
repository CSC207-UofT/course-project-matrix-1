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

    public BufferedImage[][] createResizedImages(Map<String, Object> formatDetails, WorksheetOutput worksheet) {
        String[][] equations = worksheet.equationsToStringArray();
        BufferedImage[][] qAndAImages = new BufferedImage[2][equations.length];
        for (String[] equation : equations) {
            //equationToLatex.convertEquationStringToLatex(equation, formatDetails, false){
        }
    }
}
