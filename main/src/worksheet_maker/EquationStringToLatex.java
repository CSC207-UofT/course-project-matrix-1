package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXFormula;

import java.util.Arrays;
import java.util.Map;

/**
 * Converts an Equation as a String into a TexFormula.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-05.
 */
public class EquationStringToLatex {
    /**
     * Converts a list of String representation of an equation into a TexFormula.
     *
     * @param equationStringMap The String representation of an equation. Each operator and value is a new element.
     * @param equationFormat     The format an equation should be displayed in. Horizontal, Vertical or Division
     *                           bracket.
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Latex formula of the equation.
     */
    public TeXFormula convertEquationStringToLatex(Map<String, String> equationStringMap, String equationFormat, boolean withAnswer) {
        String latexString;
        //DO NOT UPDATE THIS FOR HIGHER VERSIONS OF JAVA
        switch (equationFormat) {
            case "Horizontal":
                latexString = createHorizontalLatex(equationStringMap, withAnswer);
                break;
            case "Vertical":
                latexString = createVerticalLatex(equationStringMap, withAnswer);
                break;
            case "Division bracket":
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
     * Ex. 4 \div 2 = 2
     *
     * @param equationStringMap
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Horizontal format of a latex equation as a string.
     */
    private String createHorizontalLatex(Map<String, String> equationStringMap, boolean withAnswer) {
        if (withAnswer){
            return equationStringMap.get("operator1") + equationStringMap.get("operand") + equationStringMap.get("operator2") + "=" + equationStringMap.get("answer");
        }else{
            return equationStringMap.get("operator1") + equationStringMap.get("operand") + equationStringMap.get("operator2");
        }
//      StringBuilder latexStringBuilder = new StringBuilder();
//        for (int i = 0; !equationStringMap[i].equals("="); i++) {
//            latexStringBuilder.append(equationStringMap[i]);
//        }
//        latexStringBuilder.append("=");
//        if (withAnswer) {
//            latexStringBuilder.append(equationStringMap[equationStringMap.length - 1]);
//        }
//        return String.valueOf(latexStringBuilder);
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a Vertical format.
     * Ex.
     * 4
     * - 2
     * _____
     * 2
     *
     * @param equationStringMap
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Vertical format of a latex equation as a string.
     */
    private String createVerticalLatex(Map<String, String> equationStringMap, boolean withAnswer) {
        return "";
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a division bracket format.
     * Ex. 2)4 (with an overline above the 4)
     *
     * @param equationStringMap
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Division bracket format of a latex equation as a string.
     */
    private String createDivisionBracketLatex(Map<String, String> equationStringMap, boolean withAnswer) {
        return "";
    }
}
