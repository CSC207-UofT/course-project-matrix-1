package equation_parameters;

/**
 * Holds equation details specific to whole number equations. This includes the range of operand1 and operand2.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-12-2
 */
public final class WholeNumEquationDetails extends EquationDetails {
    private int[] operandRange1;
    private int[] operandRange2;

    public int[] getOperandRange1() {
        return operandRange1;
    }

    public void setOperandRange1(int[] operandRange1) {
        this.operandRange1 = operandRange1;
    }

    public int[] getOperandRange2() {
        return operandRange2;
    }

    public void setOperandRange2(int[] operandRange2) {
        this.operandRange2 = operandRange2;
    }
}
