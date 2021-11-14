package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Converts Latex equations to PDImageXObjects. These PDImageXObjects can be directly arranged on their designated PDF.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-7.
 */
public class LatexToImage {
    /**
     * Converts a Latex TeXFormula into a BufferedImage.
     *
     * @param formula   the TeXFormula for a single equation.
     * @param worksheet the worksheet to which this equation belongs.
     * @return a PDImageXObject representation of the latex equation.
     * @throws IOException if the formula cannot be properly converted into an image.
     */
    public PDImageXObject convertLatexToImage(TeXFormula formula, PDDocument worksheet) throws IOException {
        BufferedImage equationImage = (BufferedImage) formula.createBufferedImage(TeXConstants.STYLE_DISPLAY,
                (float) (100), null, null);
        return JPEGFactory.createFromImage(worksheet, equationImage);
    }
}
