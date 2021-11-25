package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FactorFinder {
    //A list of factors for each number
    private final static Map<Integer, HashSet<Integer>> allFactors = new HashMap<>();
    private final static Map<Integer, ArrayList<Integer>> allPrimeFactors = new HashMap<>();

    /**
     * Finds the factors associated with a certain number recursively. This method adds to factorList as it does so,
     * increasing performance over time. Ex. 40 = [40, 20, 10, 8, 5, 4, 2, 1].
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
                    return (HashSet<Integer>) factors.clone();
                }
            }
            factors.add(number);
            factors.add(1);
            allFactors.put(number, factors);
            return (HashSet<Integer>) factors.clone();
        }
    }
    /**
     * Finds the prime factors associated with a certain number recursively. This method adds to primeFactorList as it
     * does so, increasing performance over time. Ex. 40 = [2, 2, 2, 5].
     *
     * @param number the number to find prime factors for.
     * @return  a collection of the prime factors.
     */
    public static ArrayList<Integer> primeFactorize(int number) {
        if (allPrimeFactors.containsKey(number)) {
            return (ArrayList<Integer>) allPrimeFactors.get(number).clone();
        } else {
            ArrayList<Integer> factors = new ArrayList<>();
            for (int i = 2; i < number + 1; i++) {
                if (number % i == 0) {
                    factors.addAll(primeFactorize(number / i));
                    factors.add(i); //i should be a prime number, otherwise it would have been divided earlier
                    allPrimeFactors.put(number, factors);
                    return (ArrayList<Integer>) factors.clone();
                }
            }
            return (ArrayList<Integer>) factors.clone();
        }
    }
    /**
     * Finds the prime factors associated with a certain number but returns them in a list where the same prime factors
     * are multiplied with each other and stored as one number. Ex. 40 = [8, 3].
     *
     * @param number the number to find prime factors for in an exponential form.
     * @return a collection of the prime factors in an exponential form.
     */
    public static HashSet<Integer> primeFactorizeExponent(int number) {
        ArrayList<Integer> primeFactors = primeFactorize(number);
        HashMap<Integer, Integer> primeToFactorExponent = new HashMap<>();
        for (Integer prime : primeFactors) {
            if (primeToFactorExponent.containsKey(prime)) {
                primeToFactorExponent.put(prime, primeToFactorExponent.get(prime) * prime);
            } else {
                primeToFactorExponent.put(prime, prime);
            }
        }
        return new HashSet<>(primeToFactorExponent.values());
    }
}
