package equation_builders;

import equation_entities.BedmasEquation;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class BedmasEquationBuilder {
    protected BedmasEquation bedmasEquation;

    public BedmasEquation getBedmasEquation() {
        return bedmasEquation;
    }

    public void createNewBedmasEquationProduct() {
        bedmasEquation = new BedmasEquation();
    }

    /**
     * Builds the bedmasEquation's operator.
     */
    public abstract void buildOperator();

    /**
     * Builds the operands (first and second) for the bedmasEquation.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     */
    public abstract void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed);

    /**
     * Builds the bedmasEquation's answer.
     */
    public void buildAnswer() {
        bedmasEquation.solve();
    }

    /**
     * Randomly returns either a positive or negative version of the number given (50:50 chance).
     *
     * @param num the original number
     * @return a negative or positive version of num
     */
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

    /**
     * Returns a random int from a specified list of numbers.
     *
     * @param possibleInts the possible ints in no particular order.
     * @return a randomized int from the possibleInts.
     */
    protected static int randomize(ArrayList<Integer> possibleInts) {
        return possibleInts.get(randomize(0, possibleInts.size() - 1));
    }

}
