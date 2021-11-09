package worksheet_maker;

import org.junit.Test;
import org.scilab.forge.jlatexmath.TeXFormula;

import static org.junit.Assert.*;

public class EquationStringToLatexTest {
    @Test
    public void testLatexCreated() {
        String[] equationStringList = {"2", "+", "3", "=", "5"};
        EquationStringToLatex equationStringToLatex = new EquationStringToLatex();
        TeXFormula latexEquation = equationStringToLatex.convertEquationStringToLatex(equationStringList, "Horizontal", true);
        assertNotNull(latexEquation);
    }
}