package worksheet_maker;

public class AddBedmasEquationBuilder extends BedmasEquationBuilder {
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator("+");
    }

    /**
     * Creates operands (first and second) in the addition bedmas equation's question. No limitations.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     */
    @Override
    public void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        int operand1 = randomize(operandRange1);
        int operand2 = randomize(operandRange2);
        if (negAllowed) {
            operand1 = makeNegativeRandom(operand1);
            operand2 = makeNegativeRandom(operand2);
        }
        bedmasEquation.setOperand1(operand1);
        bedmasEquation.setOperand2(operand2);
    }


}
