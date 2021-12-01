package utilities;

import java.util.List;
import java.util.Random;

/**
 * Randomizer class. Used to generate random numbers for equation generation.
 */
public class Randomizer {
    private final Random rand;
    private int seed;

    public Randomizer() {
        rand = new Random();
    }

    /**
     * @param seed random seed for reproducibility
     */
    public Randomizer(int seed) {
        this.seed = seed;
        rand = new Random();
        rand.setSeed(seed);
    }

    /**
     * Manually set seed for randomizer.
     *
     * @param seed seed to fix randomness
     */
    public void setSeed(int seed) {
        rand.setSeed(seed);
    }

    /**
     * Increment current random seed by 500.
     */
    public void incrementSeed() {
        this.seed += 500;
        rand.setSeed(this.seed);
    }

    /**
     * Randomly returns either a positive or negative version of the number given (50:50 chance).
     *
     * @param num the original number.
     * @return a negative or positive version of num.
     */
    public int makeNegativeRandom(int num) {
        int x = rand.nextInt(50);
        if (x >= 25) {
            num = -1 * num;
        }
        incrementSeed();
        return num;
    }

    /**
     * Returns a random int between two ints.
     *
     * @param min the minimum possible int.
     * @param max the maximum possible int.
     * @return a randomized int between [min, max] (inclusive).
     */
    public int randomize(int min, int max) {
        int randomInteger = rand.nextInt((max - min) + 1) + min;
        incrementSeed();
        return randomInteger;
    }

    /**
     * Returns a random int from a specified range.
     *
     * @param range the range of possible ints, as [min, max].
     * @return a randomized int between [min, max] (inclusive).
     */
    public int randomize(int[] range) {
        return randomize(range[0], range[1]);
    }

    /**
     * Returns a random int from a specified list of numbers.
     *
     * @param possibleInts the possible ints in no particular order.
     * @return a randomized int from the possibleInts.
     */
    public int randomize(List<Integer> possibleInts) {
        return possibleInts.get(randomize(0, possibleInts.size() - 1));
    }
}
