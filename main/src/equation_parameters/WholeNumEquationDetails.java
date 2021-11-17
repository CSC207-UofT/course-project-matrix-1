package equation_parameters;

public final class WholeNumEquationDetails extends EquationDetails {
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

    private int[] operandRange1;
    private int[] operandRange2;

    //TODO: give this class more functionality. maybe move randomize operand here? would need to pass all of equation details down though (is this unnecessary parameters?)


}
