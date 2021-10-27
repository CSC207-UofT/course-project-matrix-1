package worksheet_maker;

public class SubBedmasEquationBuilder extends BedmasEquationBuilder {
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator("-");
    }

    /**
     * Creates operands (first and second) in the subtraction bedmas equation's question. If not negAllowed, prevent
     * operand 2 from being greater than operand 1.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     */
    @Override
    public void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        //TODO: Fix bad inputs (ex. operand2 range is greater than operand1)
        int operand1 = randomize(operandRange1);
        int operand2;
        if (negAllowed) {
            operand1 = makeNegativeRandom(operand1);
            operand2 = makeNegativeRandom(randomize(operandRange2));
        } else {
            operand2 = randomize(operandRange2[0], operand1);
        }
        bedmasEquation.setOperand1(operand1);
        bedmasEquation.setOperand2(operand2);
    }


}
