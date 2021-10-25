package EquationBuilder;

public class DivideBedmasEquationBuilder extends BedmasEquationBuilder {

    @Override
    public void buildOperator() {
        bedmasEquation.setOperator("/");
    }

    @Override
    public void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        //TODO: Zeros are not allowed for operator 2. At UI level, restrict this.
        if (operandRange2[0] == 0) {
            operandRange2[0] = 1;
        }
        int operand2 = randomize(operandRange2);
        //Operand1 must be divisible by operand2
        int operand1 = randomize(operandRange1[0] / operand2, operandRange1[1] / operand2) * operand2;
        //TODO: Will break if operand2 is greater than operand1. At UI level, make sure operand2 max <= operand1 max.
        if (negAllowed) {
            operand1 = makeNegativeRandom(operand1);
            operand2 = makeNegativeRandom(operand2);
        }
        bedmasEquation.setOperand1(operand1);
        bedmasEquation.setOperand2(operand2);
    }

}

