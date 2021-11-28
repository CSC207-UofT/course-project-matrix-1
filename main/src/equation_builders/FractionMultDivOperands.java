package equation_builders;

import equation_parameters.EquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import utilities.FactorFinder;
import utilities.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class FractionMultDivOperands {
    protected Randomizer randomizer;
    // Arbitrary prime numbers that are used to add complexity to a fraction.
    int[] PRIMES = {2, 3, 5, 7, 11};

    /**
     * @param isNegAllowed whether negatives are allowed for this equation.
     * @param operandsN    the numerator of the operands as a list of 2 ints.
     */
    protected void makeOperandsNegative(boolean isNegAllowed, int[] operandsN) {
        if (isNegAllowed) {
            operandsN[0] = randomizer.makeNegativeRandom(operandsN[0]);
            operandsN[1] = randomizer.makeNegativeRandom(operandsN[1]);
        }
    }

    /**
     * Split the factors in a given list into two operands somewhat evenly. Ex. [2, 3, 2, 5] makes [6, 10].
     *
     * @param factors a list of factors that can be multiplied together.
     * @return a list of 2 numbers that together are the product of all the factors.
     */
    protected int[] splitFactorsIntoTwoOperands(List<Integer> factors) {
        List<Integer> operand1Factors = new ArrayList<>();
        List<Integer> operand2Factors = new ArrayList<>();
        operand1Factors.add(1);
        operand2Factors.add(1);
        while (!factors.isEmpty()) {
            if (factors.size() % 2 == 0) {
                operand1Factors.add(factors.remove(randomizer.randomize(0, factors.size() - 1)));
            } else {
                operand2Factors.add(factors.remove(randomizer.randomize(0, factors.size() - 1)));
            }
        }
        return new int[]{multiplyFactors(operand1Factors), multiplyFactors(operand2Factors)};
    }

    /**
     * Return the multiplied product of all the factors.
     *
     * @param factors a list of factors that can be multiplied together to get a full number.
     * @return the value of all the factors multiplied together.
     */
    private int multiplyFactors(List<Integer> factors) {
        int fullNumber = 1;
        for (int factor : factors) {
            fullNumber = factor * fullNumber;
        }
        return fullNumber;
    }

    /**
     * Randomly add more complexity by adding a random (prime) factor to the numerator and denominator of the answer.
     *
     * @param complexity           how many additional factors will be added to the numerator adn denominator to make the fraction more complex.
     * @param unreducedAnsDFactors a list of all the factors in the numerator of the answer.
     * @param unreducedAnsNFactors a list of all the factors in the denominator of the answer.
     */
    protected void addComplexity(int complexity, List<Integer> unreducedAnsDFactors, List<Integer> unreducedAnsNFactors) {
        for (int i = 0; i < complexity; i++) {
            int prime = biasedSelectNumber(PRIMES);
            unreducedAnsDFactors.add(prime);
            unreducedAnsNFactors.add(prime);
        }
    }

    /**
     * Randomly select a number from a given set of numbers. Be biased towards the ones on the left of the list.
     *
     * @param numbers the possible number values to choose from
     * @return a randomly selected number (biased towards smaller ones)
     */
    private int biasedSelectNumber(int[] numbers) {
        for (int number : numbers) {
            if (randomizer.randomize(0, 50) >= 25) {
                return number;
            }
        }
        // If all the numbers are passed through and still hasn't returned anything, just return the last number.
        return numbers[numbers.length - 1];
    }

    public int[][] buildOperandParts(EquationDetails fractionEquationDetails, Randomizer randomizer) {
        this.randomizer = randomizer;
        FractionMultiDivEquationDetails fracMultiDivEqnDetails = (FractionMultiDivEquationDetails) fractionEquationDetails;
        int unreducedAnsD = randomizer.randomize(fracMultiDivEqnDetails.getAnsDenominatorRange());
        int unreducedAnsN = randomizer.randomize(0, unreducedAnsD) + unreducedAnsD * (fracMultiDivEqnDetails.getMaxAnsValue() - 1);
        List<Integer> unreducedAnsDFactors = FactorFinder.primeFactorize(unreducedAnsD);
        List<Integer> unreducedAnsNFactors = FactorFinder.primeFactorize(unreducedAnsN);
        addComplexity(fracMultiDivEqnDetails.getComplexity(), unreducedAnsDFactors, unreducedAnsNFactors);
        int[] operandsN = splitFactorsIntoTwoOperands(unreducedAnsNFactors);
        int[] operandsD = splitFactorsIntoTwoOperands(unreducedAnsDFactors);
        makeOperandsNegative(fracMultiDivEqnDetails.isNegAllowed(), operandsN);
        return new int[][]{operandsN, operandsD};
    }
}
