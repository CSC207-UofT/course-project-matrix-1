package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXFormula;

import java.util.Arrays;

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
     * @param equationStringList The String representation of an equation. Each operator and value is a new element.
     * @param equationFormat     The format an equation should be displayed in. Horizontal, Vertical or Division
     *                           bracket.
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Latex formula of the equation.
     */
    public TeXFormula convertEquationStringToLatex(String[] equationStringList, String equationFormat, boolean withAnswer) {
        String latexString = switch (equationFormat) {
            case "Horizontal" -> createHorizontalLatex(equationStringList, withAnswer);
            case "Vertical" -> createVerticalLatex(equationStringList, withAnswer);
            case "Division bracket" -> createDivisionBracketLatex(equationStringList, withAnswer);
            default -> "";
        };
        //Convert string to TeXFormula
        return new TeXFormula(latexString);
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a Horizontal format.
     * Ex. 4 \div 2 = 2
     *
     * @param equationStringList The String representation of an equation. Each operator and value is a new element.
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Horizontal format of a latex equation as a string.
     */
    private String createHorizontalLatex(String[] equationStringList, boolean withAnswer) {
        StringBuilder latexStringBuilder = new StringBuilder();
        for (int i = 0; !equationStringList[i].equals("="); i++) {
            latexStringBuilder.append(equationStringList[i]);
        }
        latexStringBuilder.append("=");
        if (withAnswer) {
            latexStringBuilder.append(equationStringList[equationStringList.length - 1]);
        }
        return String.valueOf(latexStringBuilder);
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a Vertical format.
     * Ex.
     * 4
     * - 2
     * _____
     * 2
     *
     * @param equationStringList The String representation of an equation. Each operator and value is a new element.
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Vertical format of a latex equation as a string.
     */
    private String createVerticalLatex(String[] equationStringList, boolean withAnswer) {
        //TODO: Implement this
        //Temporary return statement to avoid warnings
        return Arrays.toString(equationStringList) + withAnswer;
    }

    /**
     * Converts a list of String representation of an equation into a latex String in a division bracket format.
     * Ex. 2)4 (with an overline above the 4)
     *
     * @param equationStringList The String representation of an equation. Each operator and value is a new element.
     * @param withAnswer         Determines if this equation should include the answer at the end of the Latex formula.
     * @return Division bracket format of a latex equation as a string.
     */
    private String createDivisionBracketLatex(String[] equationStringList, boolean withAnswer) {
        //TODO: Implement this
        //Temporary return statement to avoid warnings
        return Arrays.toString(equationStringList) + withAnswer;
    }
}
