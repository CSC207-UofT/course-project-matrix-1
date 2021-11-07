package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;

public class LatexToImage {
    /**
     * Converts a Latex TeXFormula into a BufferedImage.
     *
     * @param formula the TeXFormula for an equation.
     * @return BufferedImage of an equation.
     */
    public BufferedImage convertLatexToImage(TeXFormula formula) {
        return (BufferedImage) formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, (float) (10), null, null);
    }
}
