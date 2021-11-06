package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;

public class LatexToImage {
    public BufferedImage convertLatexToImage(TeXFormula formula) {
        return (BufferedImage) formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, (float) (10), null, null);
    }
}
