package equation_builders;

import equation_entities.BedmasEquation;
import equation_entities.WholeNum;

import java.util.*;

/**
 * An abstract builder for all fraction equation builders.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
abstract class FractionBuilder {
    protected BedmasEquation bedmasEquation;
    private Random rand;

    protected BedmasEquation getBedmasEquation() {
        return bedmasEquation;
    }

    /**
     * Creates a new instance of the bedmas equation.
     */
    protected void createNewBedmasEquationProduct() {
        bedmasEquation = new BedmasEquation();
        rand = new Random();
    }

    /**
     * Builds the bedmasEquation's operator.
     */
    protected abstract void buildOperator();

    /**
     * Builds the operands (first and second) for the bedmasEquation.
     * <p>
     * Overridden in some child classes.
     * <p>
     * RANDOM SEED (for fixing random number generation):
     * First random operation uses the random seed. Succeeding operations increment the random seed by 5.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     * @param seed          random seed to fix random generation of operands
     */
    protected void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed, int seed) {
        int operand1 = randomize(operandRange1, seed);
        int operand2 = randomize(operandRange2, seed + 5);
        if (negAllowed) {
            operand1 = makeNegativeRandom(operand1, seed + 10);
            operand2 = makeNegativeRandom(operand2, seed + 15);
        }
        bedmasEquation.setOperand1(new WholeNum(operand1));
        bedmasEquation.setOperand2(new WholeNum(operand2));
    }

    /**
     * Builds the bedmasEquation's answer.
     */
    protected void buildAnswer() {
        bedmasEquation.solve();
    }

    /**
     * Randomly returns either a positive or negative version of the number given (50:50 chance).
     *
     * @param num  the original number.
     * @param seed the random seed for reproducibility
     * @return a negative or positive version of num.
     */
    protected int makeNegativeRandom(int num, int seed) {
        rand.setSeed(seed);
        int x = rand.nextInt(2);
        if (x == 0) {
            num = -1 * num;
        }
        return num;
    }

    /**
     * Returns a random int between two ints.
     *
     * @param min  the minimum possible int.
     * @param max  the maximum possible int.
     * @param seed the random seed for reproducibility
     * @return a randomized int between [min, max] (inclusive).
     */
    protected int randomize(int min, int max, int seed) {
        rand.setSeed(seed);
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns a random int from a specified range.
     *
     * @param range the range of possible ints, as [min, max].
     * @param seed  the random seed for reproducibility
     * @return a randomized int between [min, max] (inclusive).
     */
    protected int randomize(int[] range, int seed) {
        return randomize(range[0], range[1], seed);
    }

    /**
     * Returns a random int from a specified list of numbers.
     *
     * @param possibleInts the possible ints in no particular order.
     * @param seed         the random seed for reproducibility
     * @return a randomized int from the possibleInts.
     */
    protected int randomize(List<Integer> possibleInts, int seed) {
        return possibleInts.get(randomize(0, possibleInts.size() - 1, seed));
    }


}
