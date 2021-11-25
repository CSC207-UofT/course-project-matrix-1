package equation_builders;

import equation_entities.Fraction;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import utilities.FactorFinder;
import utilities.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class FractionDivideOperandConstructor implements OperandConstructorInterface {
    // Arbitrary prime numbers that are used to add complexity to a fraction.
    int[] PRIMES = {2, 3, 5, 7, 11};

    /**
     * Uses the maximum value of the answer, answers' denominator range, and complexity to get reasonable operands for
     * fraction multiplication.
     *
     * @param fractionEquationDetails the parameters for fraction equation generation.
     * @param seed random seed to fix randomness in generating of operands
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    @Override
    public Value[] buildOperands(EquationDetails fractionEquationDetails, int seed, Randomizer rand) {
        FractionMultiDivEquationDetails fracMultiDivEqnDetails = (FractionMultiDivEquationDetails) fractionEquationDetails;
        int unreducedAnsD = rand.randomize(fracMultiDivEqnDetails.getAnsDenominatorRange(), seed);
        int unreducedAnsN = rand.randomize(0, unreducedAnsD, seed) + unreducedAnsD * (fracMultiDivEqnDetails.getMaxAnsValue() - 1);
        List<Integer> unreducedAnsDFactors = FactorFinder.primeFactorize(unreducedAnsD);
        List<Integer> unreducedAnsNFactors = FactorFinder.primeFactorize(unreducedAnsN);
        addComplexity(fracMultiDivEqnDetails.getComplexity(), unreducedAnsDFactors, unreducedAnsNFactors, seed, rand);
        int[] operandsN = splitFactorsIntoTwoOperands(unreducedAnsNFactors, seed, rand);
        int[] operandsD = splitFactorsIntoTwoOperands(unreducedAnsDFactors, seed, rand);
        makeOperandsNegative(fracMultiDivEqnDetails.isNegAllowed(), operandsN, seed, rand);

        return new Value[]{new Fraction(operandsN[0], operandsD[0]), new Fraction(operandsD[1], operandsN[1])};
    }

    /**
     * @param isNegAllowed whether negatives are allowed for this equation.
     * @param operandsN    the numerator of the operands as a list of 2 ints.
     * @param seed         random seed to fix random generation of operands
     */
    private void makeOperandsNegative(boolean isNegAllowed, int[] operandsN, int seed, Randomizer rand) {
        if (isNegAllowed) {
            operandsN[0] = rand.makeNegativeRandom(operandsN[0], seed);
            operandsN[1] = rand.makeNegativeRandom(operandsN[1], seed);
        }
    }

    /**
     * Split the factors in a given list into two operands somewhat evenly. Ex. [2, 3, 2, 5] makes [6, 10].
     *
     * @param factors a list of factors that can be multiplied together.
     * @param seed    random seed to fix random generation of operands.
     * @return a list of 2 numbers that together are the product of all the factors.
     */
    private int[] splitFactorsIntoTwoOperands(List<Integer> factors, int seed, Randomizer rand) {
        List<Integer> operand1Factors = new ArrayList<>();
        List<Integer> operand2Factors = new ArrayList<>();
        operand1Factors.add(1);
        operand2Factors.add(1);
        while (!factors.isEmpty()){
            if (factors.size() % 2 == 0) {
                operand1Factors.add(factors.remove(rand.randomize(0, factors.size() - 1, seed)));
            } else {
                operand2Factors.add(factors.remove(rand.randomize(0, factors.size() - 1, seed)));
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
     * @param seed                 random seed to fix random generation of operands.
     */
    private void addComplexity(int complexity, List<Integer> unreducedAnsDFactors, List<Integer> unreducedAnsNFactors, int seed, Randomizer rand) {
        for (int i = 0; i < complexity; i++) {
            int prime = biasedSelectNumber(PRIMES, seed, rand);
            unreducedAnsDFactors.add(prime);
            unreducedAnsNFactors.add(prime);
        }
    }

    /**
     * Randomly select a number from a given set of numbers. Be biased towards the ones on the left of the list.
     *
     * @param numbers the possible number values to choose from
     * @param seed    random seed to fix random generation of operands.
     * @return a randomly selected number (biased towards smaller ones)
     */
    private int biasedSelectNumber(int[] numbers, int seed, Randomizer rand) {
        for (int number : numbers) {
            if (rand.randomize(0, 50, seed+=5) >= 25) {
                return number;
            }
        }
        // If all the numbers are passed through and still hasn't returned anything, just return the last number.
        return numbers[numbers.length - 1];
    }
}
