package utilities;

import java.util.List;
import java.util.Random;

public class Randomizer {
    public Random rand;

    public Randomizer(){
        rand = new Random();
    }

    /**
     * Randomly returns either a positive or negative version of the number given (50:50 chance).
     *
     * @param num the original number.
     * @param seed the random seed for reproducibility
     * @return a negative or positive version of num.
     */
    public int makeNegativeRandom(int num, int seed) {
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
     * @param min the minimum possible int.
     * @param max the maximum possible int.
     * @param seed the random seed for reproducibility
     * @return a randomized int between [min, max] (inclusive).
     */
    public int randomize(int min, int max, int seed) {
        rand.setSeed(seed);
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns a random int from a specified range.
     *
     * @param range the range of possible ints, as [min, max].
     * @param seed the random seed for reproducibility
     * @return a randomized int between [min, max] (inclusive).
     */
    public int randomize(int[] range, int seed) {
        return randomize(range[0], range[1], seed);
    }

    /**
     * Returns a random int from a specified list of numbers.
     *
     * @param possibleInts the possible ints in no particular order.
     * @param seed the random seed for reproducibility
     * @return a randomized int from the possibleInts.
     */
    public int randomize(List<Integer> possibleInts, int seed) {
        return possibleInts.get(randomize(0, possibleInts.size() - 1, seed));
    }
}
