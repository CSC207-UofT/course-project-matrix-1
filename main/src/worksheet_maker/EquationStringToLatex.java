package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXFormula;

import java.util.Map;

import static constants.EquationFormats.*;
import static constants.EquationParts.*;
import static constants.OperatorRep.GCD;
import static constants.OperatorRep.LCM;

/**
 * Converts an Equation as a String into a TexFormula.
 * Note that the helper methods createHorizontalLatex, createVerticalLatex, createDivisionBracketLatex are public, this
 * is so that we are able to test the String value intermediates since latex itself cannot be easily tested.
 *
 * @author Sean Jeong
 * @version 2.0
 * @since 2021-12-02.
 */
public class EquationStringToLatex {
    /**
     * Converts a Map of String representation of an equation into a TexFormula.
     *
     * @param equationStringMap A Map containing the operands, operator and answer of an equation as Strings.
     * @param equationFormat    The format an equation should be displayed in. Horizontal, Vertical or Division
     *                          bracket.
     * @param withAnswer        Determines if this equation should include the answer at the end of the Latex formula.
     * @return Latex formula of the equation.
     */
    public TeXFormula convertEquationStringToLatex(Map<String, String> equationStringMap, String equationFormat, boolean withAnswer) {
        String latexString;
        //DO NOT UPDATE THIS FOR HIGHER VERSIONS OF JAVA
        switch (equationFormat) {
            case HORIZONTAL:
                latexString = createHorizontalLatex(equationStringMap, withAnswer);
                break;
            case VERTICAL:
                latexString = createVerticalLatex(equationStringMap, withAnswer);
                break;
            case DIVISION_BRACKET:
                latexString = createDivisionBracketLatex(equationStringMap, withAnswer);
                break;
            default:
                latexString = "";
                break;
        }
        //Convert string to TeXFormula
        return new TeXFormula(latexString);
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a Horizontal format.
     * Ex. 4 \div 2 = 2, or LCM(2,20) =
     *
     * @param equationStringMap A Map containing the operands, operator and answer of an equation as Strings.
     * @param withAnswer        Determines if this equation should include the answer at the end of the Latex formula.
     * @return Horizontal format of a latex equation as a string.
     */
    public String createHorizontalLatex(Map<String, String> equationStringMap, boolean withAnswer) {
        String horizontalLatex;
        if (equationStringMap.get(OPERATOR).equals(LCM) || equationStringMap.get(OPERATOR).equals(GCD)) {
            horizontalLatex = equationStringMap.get(OPERATOR) + "(" + equationStringMap.get(OPERAND1) + "," + equationStringMap.get(OPERAND2) + ")" + "=";
        } else {
            horizontalLatex = equationStringMap.get(OPERAND1) + equationStringMap.get(OPERATOR) + equationStringMap.get(OPERAND2) + "=";
        }

        if (withAnswer) {
            horizontalLatex += equationStringMap.get(ANSWER);
        }
        return horizontalLatex;
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a Vertical format.
     * Ex.
     * 4
     * - 2
     * _____
     * 2
     *
     * @param equationStringMap A Map containing the operands, operator and answer of an equation as Strings.
     * @param withAnswer        Determines if this equation should include the answer at the end of the Latex formula.
     * @return Vertical format of a latex equation as a string.
     */
    public String createVerticalLatex(Map<String, String> equationStringMap, boolean withAnswer) {
        String verticalLatex = "\\begin{array}{r@{\\,}r@{\\,}}";
        verticalLatex += "&" + equationStringMap.get(OPERAND1) + "\\\\";
        verticalLatex += equationStringMap.get(OPERATOR) + "&" + equationStringMap.get(OPERAND2) + "\\\\";
        verticalLatex += "\\hline";
        if (withAnswer) {
            verticalLatex += "&" + equationStringMap.get(ANSWER) + "\\\\";
        }
        verticalLatex += "\\end{array}";
        return verticalLatex;
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a division bracket format.
     * Ex. 2)4 (with an overline above the 4)
     *
     * @param equationStringMap A Map containing the operands, operator and answer of an equation as Strings.
     * @param withAnswer        Determines if this equation should include the answer at the end of the Latex formula.
     * @return Division bracket format of a latex equation as a string.
     */
    public String createDivisionBracketLatex(Map<String, String> equationStringMap, boolean withAnswer) {
        String divisionBracketLatex = "\\begin{array}{r}";
        if (withAnswer) {
            divisionBracketLatex += equationStringMap.get(ANSWER) + "\\\\";
        }
        divisionBracketLatex += equationStringMap.get(OPERAND2) + "\\overline{)" + equationStringMap.get(OPERAND1) + "}\\\\";
        divisionBracketLatex += "\\end{array}";
        return divisionBracketLatex;
    }
}
