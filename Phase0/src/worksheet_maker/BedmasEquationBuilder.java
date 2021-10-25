package worksheet_maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    public void buildAnswer() {
        bedmasEquation.solve();
    }

    protected static int makeNegativeRandom(int num) {
        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 0) {
            num = -1 * num;
        }
        return num;
    }

    /**
     * Returns a random int between two ints.
     *
     * @param min the minimum possible int.
     * @param max the maximum possible int.
     * @return a randomized int between [min, max] (inclusive).
     */
    protected static int randomize(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Returns a random int from a specified range.
     *
     * @param range the range of possible ints, as [min, max].
     * @return a randomized int between [min, max] (inclusive).
     */
    protected static int randomize(int[] range) {
        return ThreadLocalRandom.current().nextInt(range[0], range[1] + 1);
    }

}
