package EquationBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SubBedmasEquationBuilder extends BedmasEquationBuilder{
    public void buildOperator(){
        bedmasEquation.setOperator("-");
    }
    public void buildAnswer(int minOperand, int maxOperand, Boolean negAnsAllowed){
        bedmasEquation.solve()
    }
    public void buildOperand1(int minOperand, int maxOperand, Boolean negAnsAllowed){
        int operand1 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand);
        if (negAnsAllowed){
            operand1 = makeNegativeRnadom(operand1);
        }
        bedmasEquation.setOperand1(operand1);
    }

    private int makeNegativeRnadom(int operand1) {
        Random rand=new Random();
        int x = rand.nextInt(2);
        if (x == 0) {
            operand1 = -1 * operand1;
        }
        return operand1;
    }

    public void buildOperand2(int minOperand, int maxOperand){
        int operand2 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand);
        if (negAnsAllowed){
            operand2 = ThreadLocalRandom.current().nextInt(minOperand, maxOperand);
            operand2 = -1 * operand2;
        } else {

        }
    }
}
