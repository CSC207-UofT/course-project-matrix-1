package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class LatexToImage {
    public BufferedImage convertLatexToImage(TeXFormula formula) {
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        icon.paintIcon(new JLabel(), image.createGraphics(), 0, 0);
        return image;
    }
}
