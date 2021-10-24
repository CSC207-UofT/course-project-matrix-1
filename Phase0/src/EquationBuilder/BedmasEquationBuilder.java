package EquationBuilder;

import java.util.Random;

abstract class BedmasEquationBuilder {
    protected BedmasEquation bedmasEquation;

    public BedmasEquation getBedmasEquation() {
        return bedmasEquation;
    }

    public void createNewBedmasEquationProduct() {
        bedmasEquation = new BedmasEquation();
    }

    public abstract void buildOperator();
    public abstract void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAns);
    public abstract void buildAnswer();

    protected static int makeNegativeRandom(int num) {
        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 0) {
            num = -1 * num;
        }
        return num;
    }
}
