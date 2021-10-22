package EquationBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AddBedmasEquationBuilder extends BedmasEquationBuilder {
    public void buildOperator() {
        bedmasEquation.setOperator("+");
    }

    public void buildAnswer(int minOperand, int maxOperand) {
        bedmasEquation.solve();
    }

    public void buildOperand1(int minOperand, int maxOperand, boolean negAns) {
        int operand1 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand + 1);
        if (negAns){
            bedmasEquation.setOperand1(operand1*-1);
        }else{
            bedmasEquation.setOperand1(operand1);
        }
    }

    public void buildOperand2(int minOperand, int maxOperand, boolean negAns) {
        //TODO: Redundant, how can we stop this?
        int operand2 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand + 1);
        if (negAns){
            bedmasEquation.setOperand2(operand2*-1);
        }else{
            bedmasEquation.setOperand2(operand2);
        }
    }
}
