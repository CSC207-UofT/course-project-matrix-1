package worksheet_maker;

import org.junit.Before;
import org.junit.Test;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.util.HashMap;
import java.util.Map;

import static constants.EquationParts.*;
import static org.junit.Assert.*;

public class EquationStringToLatexTest {
    private final Map<String, String> wholeNumEquationMap = new HashMap<>();
    private final Map<String, String> fractionEquationMap = new HashMap<>();
    private final EquationStringToLatex equationStringToLatex = new EquationStringToLatex();
    @Before
    public void init() {
        wholeNumEquationMap.put(OPERATOR, "\\div");
        wholeNumEquationMap.put(OPERAND1, "6");
        wholeNumEquationMap.put(OPERAND2, "3");
        wholeNumEquationMap.put(ANSWER, "2");

        fractionEquationMap.put(OPERATOR, "\\times");
        fractionEquationMap.put(OPERAND1, "-2\\frac{1}{2}");
        fractionEquationMap.put(OPERAND2, "\\frac{3}{4}");
        fractionEquationMap.put(ANSWER, "-1\\frac{7}{8}");
    }

    @Test
    public void testLatexCreated() {
        TeXFormula latexEquation = equationStringToLatex.convertEquationStringToLatex(wholeNumEquationMap, "Horizontal", true);
        assertNotNull(latexEquation);
    }

    @Test
    public void testWholeNumCreateHorizontalLatex() {
        assertEquals("6\\div3=2", equationStringToLatex.createHorizontalLatex(wholeNumEquationMap, true));
    }

    @Test
    public void testFractionCreateHorizontalLatex() {
        assertEquals("-2\\frac{1}{2}\\times\\frac{3}{4}=-1\\frac{7}{8}", equationStringToLatex.createHorizontalLatex(fractionEquationMap, true));
    }

    @Test
    public void testCreateVerticalLatex() {
        assertEquals("\\begin{array}{r@{\\,}r@{\\,}}&6\\\\\\div&3\\\\\\hline&2\\\\\\end{array}", equationStringToLatex.createVerticalLatex(wholeNumEquationMap, true));
    }

    @Test
    public void testCreateDivisionBracketLatex() {
        assertEquals("\\begin{array}{r}2\\\\3\\overline{)6}\\\\\\end{array}", equationStringToLatex.createVerticalLatex(wholeNumEquationMap, true));
    }

    @Test
    public void testWholeNumLCMCreateHorizontalLatex() {
        wholeNumEquationMap.put(OPERATOR, "LCM");
        wholeNumEquationMap.put(OPERAND1, "6");
        wholeNumEquationMap.put(OPERAND2, "8");
        wholeNumEquationMap.put(ANSWER, "24");
        assertEquals("LCM(6,8)=24", equationStringToLatex.createHorizontalLatex(wholeNumEquationMap, true));
    }
}