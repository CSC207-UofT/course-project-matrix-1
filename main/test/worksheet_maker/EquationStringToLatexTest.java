package worksheet_maker;

import org.junit.Test;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EquationStringToLatexTest {
    @Test
    public void testLatexCreated() {
        Map<String, String> equationStringList = new HashMap<>();
        equationStringList.put("operator", "+");
        equationStringList.put("operand1", "2");
        equationStringList.put("operand2", "3");
        equationStringList.put("answer", "5");
        EquationStringToLatex equationStringToLatex = new EquationStringToLatex();
        TeXFormula latexEquation = equationStringToLatex.convertEquationStringToLatex(equationStringList, "Horizontal", true);
        assertNotNull(latexEquation);
    }
}