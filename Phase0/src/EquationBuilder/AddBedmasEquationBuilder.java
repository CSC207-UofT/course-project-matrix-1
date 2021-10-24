package EquationBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AddBedmasEquationBuilder extends BedmasEquationBuilder {
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator("+");
    }

    @Override
    public void buildOperands(int [] operandRange1, int [] operandRange2, boolean negAnsAllowed) {
//        int operand1 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand + 1);
//        int operand2 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand + 1);
//        if (negAnsAllowed) {
//            bedmasEquation.setOperand1(makeNegativeRandom(operand1));
//            bedmasEquation.setOperand2(makeNegativeRandom(operand2));
//        } else {
//            bedmasEquation.setOperand1(operand1);
//            bedmasEquation.setOperand2(operand2);
//        }
    }

    @Override
    public void buildAnswer() {
        bedmasEquation.solve();
    }

}
