package EquationBuilder;

public class AddBedmasEquationBuilder extends BedmasEquationBuilder {
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator("+");
    }

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
