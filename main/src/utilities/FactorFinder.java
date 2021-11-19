package utilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FactorFinder {
    //A list of factors for each number
    private final static Map<Integer, HashSet<Integer>> allFactors = new HashMap<>();

    /**
     * Finds the factors associated with a certain number recursively. This method adds to factorList as it does so,
     * increasing performance over time.
     *
     * @param number the number to find factors for.
     * @return a collection of the factors, unordered and without duplicates.
     */
    public static HashSet<Integer> findFactors(int number) {
        if (allFactors.containsKey(number)) {
            return allFactors.get(number);
        } else {
            //Need to find the factors for the number, do so recursively. Only search through half the range.
            HashSet<Integer> factors = new HashSet<>();
            for (int i = 2; i < number / 2 + 1; i++) {
                if (number % i == 0) {
                    HashSet<Integer> tempFactors = findFactors(number / i);
                    for (Object factor : tempFactors.toArray()) {
                        factors.add(((Integer) factor) * i);
                    }
                    factors.addAll(tempFactors);
                    factors.add(i); //i should be a prime number, otherwise it would have been divided earlier
                    factors.add(number);
                    allFactors.put(number, factors);
                    return factors;
                }
            }
            factors.add(number);
            factors.add(1);
            allFactors.put(number, factors);
            return factors;
        }
    }
}
