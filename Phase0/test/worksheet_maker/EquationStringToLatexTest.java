package worksheet_maker;

import equation_entities.Add;
import equation_entities.BedmasEquation;
import equation_entities.WholeNum;
import org.junit.Before;
import org.junit.Test;
import org.scilab.forge.jlatexmath.TeXFormula;

import static org.junit.Assert.*;

public class EquationStringToLatexTest {
    @Before
    public void init() {
        BedmasEquation bedmasEquation = new BedmasEquation();
        bedmasEquation.setOperator(new Add());
        bedmasEquation.setOperand1(new WholeNum(1));
        bedmasEquation.setOperand2(new WholeNum(2));
        bedmasEquation.solve();

    }
    @Test
    public void testWholeNumHorizontal() {
        String[] equationStringList = {"2", "+", "3", "=", "5"};
        EquationStringToLatex equationStringToLatex = new EquationStringToLatex();
        TeXFormula latexEquation = equationStringToLatex.convertEquationStringToLatex(equationStringList, "Horizontal", true);
        assertEquals(latexEquation);
    }

}