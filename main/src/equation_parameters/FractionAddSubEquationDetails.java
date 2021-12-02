package equation_parameters;

/**
 * Holds equation details specific to fractional addition and subtraction equations. This includes the range of the
 * first operand's denominator (operand1DenomRange), the maximum denominator for the operand2 and its answer
 * (maxOperand2AndAnswerDenom), the overall maximum value of the operands as an integer (maxOperandValue), and whether
 * the fraction is improper or proper (fractionFormat).
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-12-2
 */
public class FractionAddSubEquationDetails extends EquationDetails {
    //The range that operand1's denominator can appear as. Must be greater than 0.
    private int[] operand1DenomRange;

    //The maximum value the denominator of the answer or operand2 can appear as. Ex. if maxDenominator = 10, then the
    // answer and operand 2 cannot appear as x/11, and only x/10 at the greatest. Must be greater than 0.
    private int maxOperand2AndAnswerDenom;

    // The maximum integer value a one operand can be. Ex. if maxOperandValue = 1, the fraction cannot be greater than
    // x/x. If maxOperandValue = 2, the fraction cannot be greater than 2x/x. Must be greater than 0.
    private int maxOperandValue;

    // The format of a fraction. Either an improper or proper fraction.
    private String fractionFormat;

    public int[] getOperand1DenomRange() {
        return operand1DenomRange;
    }

    public void setOperand1DenomRange(int[] operand1DenomRange) {
        this.operand1DenomRange = operand1DenomRange;
    }

    public int getMaxOperand2AndAnswerDenom() {
        return maxOperand2AndAnswerDenom;
    }

    public void setMaxOperand2AndAnswerDenom(int maxOperand2AndAnswerDenom) {
        this.maxOperand2AndAnswerDenom = maxOperand2AndAnswerDenom;
    }

    public int getMaxOperandValue() {
        return maxOperandValue;
    }

    public void setMaxOperandValue(int maxOperandValue) {
        this.maxOperandValue = maxOperandValue;
    }

    public String getFractionFormat() {
        return fractionFormat;
    }

    public void setFractionFormat(String fractionFormat) {
        this.fractionFormat = fractionFormat;
    }
}
