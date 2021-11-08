package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class LatexToImage {
    /**
     * Converts a Latex TeXFormula into a BufferedImage.
     *
     * @param formula the TeXFormula for an equation.
     * @return BufferedImage of an equation.
     */
    public PDImageXObject convertLatexToImage(TeXFormula formula, PDDocument worksheet) throws IOException {
        BufferedImage equationImage = (BufferedImage) formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, (float) (100), null, null);
        return JPEGFactory.createFromImage(worksheet, equationImage);
    }
}
