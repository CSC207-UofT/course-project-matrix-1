package equation_builders;

import equation_entities.BedmasEquation;
import exceptions.InvalidInputException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Directs the construction of whole number BEDMAS equations, starting from the operator, then the operands,
 * and finally the answer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class FractionDirector {
    private FractionBuilder fractionBuilder;
    //A list of factors for each number
    private final static Map<Integer, HashSet<Integer>> allFactors = new HashMap<>();

    /**
     * Set the FractionBuilder.
     *
     * @param operator the char that determines which builder this director will use.
     */
    public void setEquationBuilder(char operator) {
        char ADD = '+';
        char SUBTRACT = '-';
        char MULTIPLY = '*';
        char DIVIDE = '/';

        if (operator == ADD) {
            this.fractionBuilder = new FractionAddBuilder();
        } else if (operator == SUBTRACT) {
            this.fractionBuilder = new FractionSubBuilder();
        } else if (operator == MULTIPLY) {
            this.fractionBuilder = new FractionMultiplyBuilder();
        } else if (operator == DIVIDE) {
            this.fractionBuilder = new FractionDivideBuilder();
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Returns the BedmasEquation held within the BedmasEquationBuilder.
     *
     * @return the bedmas equation from the builder.
     */
    public BedmasEquation getBedmasEquation() {
        return fractionBuilder.getBedmasEquation();
    }

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
                    HashSet<Integer> tempFactors = findFactors(number/i);
                    for (Object factor : tempFactors.toArray()) {
                        factors.add(((Integer) factor) * i);
                    }
                    factors.addAll(tempFactors);
                    factors.add(i); //i should be a prime number, otherwise it would have been divided earlier
                    //Every number is divisible by itself and 1
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
