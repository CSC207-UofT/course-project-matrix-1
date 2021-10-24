package EquationBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SubBedmasEquationBuilder extends BedmasEquationBuilder {
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator("-");
    }

    @Override
    public void buildOperands(int [] operandRange1, int [] operandRange2, boolean negAnsAllowed) {
//        int operand1 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand + 1);
//        int operand2;
//        if (negAnsAllowed) {
//            operand1 = makeNegativeRandom(operand1);
//            operand2 = makeNegativeRandom(ThreadLocalRandom.current().nextInt(minOperand, maxOperand + 1));
//        }else{
//            operand2 = ThreadLocalRandom.current().nextInt(minOperand, operand1 + 1);
//        }
//        bedmasEquation.setOperand1(operand1);
//        bedmasEquation.setOperand2(operand2);
    }

    @Override
    public void buildAnswer() {
        bedmasEquation.solve();
    }

}
