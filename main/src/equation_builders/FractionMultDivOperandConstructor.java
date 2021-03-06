package equation_builders;

import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import utilities.FactorFinder;
import utilities.Randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constants.OperatorRep.DIV;

/**
 * Contains helper methods used by both fraction multiplication and division of operands.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-28
 */

public class FractionMultDivOperandConstructor extends FractionOperandConstructor {
    // Arbitrary prime numbers that are used to add complexity to a fraction.
    final int[] PRIMES = {2, 3, 5, 7, 11};

    /**
     * Uses the maximum value of the answer, answers' denominator range, and complexity to get reasonable operands for
     * fraction multiplication.
     *
     * @param fractionEquationDetails the parameters for fraction equation generation.
     * @param randomizer              the randomizer for a given equation
     * @return an array of first operand and second operand values.
     */
    public Value[] buildOperands(EquationDetails fractionEquationDetails, Randomizer randomizer) {
        this.randomizer = randomizer;
        FractionMultiDivEquationDetails fracMultiDivEqnDetails = (FractionMultiDivEquationDetails) fractionEquationDetails;

        // Create a random answer's numerator and denominator by using the answer's denominator range and maximum value.
        // Ex. 15/10
        int unreducedAnsD = randomizer.randomize(fracMultiDivEqnDetails.getAnsDenominatorRange());
        int unreducedAnsN = randomizer.randomize(0, unreducedAnsD) + unreducedAnsD * (fracMultiDivEqnDetails.getMaxAnsValue() - 1);

        // Split the answer's numerator and denominator into factors
        // Ex. (5*3)/(5*2)
        List<Integer> unreducedAnsDFactors = FactorFinder.primeFactorize(unreducedAnsD);
        List<Integer> unreducedAnsNFactors = FactorFinder.primeFactorize(unreducedAnsN);

        // Add complexity to the answer by multiplying the numerator and denominator by a random prime factor a certain
        // number of time. This can be useful for practicing cross multiplication skills, while not changing the
        // answer's value.
        // Ex. (5*3*3)/(5*2*3)
        addComplexity(fracMultiDivEqnDetails.getComplexity(), unreducedAnsDFactors, unreducedAnsNFactors);

        // Randomly segregate the answer's factors into two operands and multiply them together. operandsN[0] is the
        // numerator for operand1, operandsN[1] is the numerator for operand2. Same thing for denominator.
        // Ex. (5)/(2*3) * (3*3)/(5) --> 5/6 * 9/5
        int[] operandsN = splitFactorsIntoTwoOperands(unreducedAnsNFactors);
        int[] operandsD = splitFactorsIntoTwoOperands(unreducedAnsDFactors);

        // Randomly make operands negative by changing the sign of the numerator (if negatives are allowed).
        // Ex. (-5)/6 * 9/5
        makeOperandsNegative(fracMultiDivEqnDetails.isNegAllowed(), operandsN);

        //Flip operand 2 if it is division
        //TODO: extract method?
        if (fractionEquationDetails.getOperator().equals(DIV)) {
            int temp = operandsN[1];
            operandsN[1] = operandsD[1];
            operandsD[1] = temp;
        }
        // Use operand 1 and 2's numerator and denominator to create a Fraction.
        return createFractions(operandsN, operandsD, ((FractionMultiDivEquationDetails) fractionEquationDetails).getFractionFormat());
    }

    /**
     * Split the factors in a given list into two operands somewhat evenly. Ex. [2, 3, 2, 5] makes [6, 10].
     *
     * @param factors a list of factors that can be multiplied together.
     * @return a list of 2 numbers that together are the product of all the factors.
     */
    private int[] splitFactorsIntoTwoOperands(List<Integer> factors) {
        List<Integer> operand1Factors = new ArrayList<>();
        List<Integer> operand2Factors = new ArrayList<>();
        operand1Factors.add(1);
        operand2Factors.add(1);
        Collections.sort(factors);
        if (factors.size() >= 3) {
            operand1Factors.add(factors.remove(0));
            operand1Factors.add(factors.remove(0));
            operand2Factors.add(factors.remove(factors.size() - 1));
        }
        while (!factors.isEmpty()) {
            if (factors.size() % 2 == 0) {
                operand1Factors.add(factors.remove(0));
            } else {
                operand2Factors.add(factors.remove(0));
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
     * @param complexity           how many additional factors will be added to the numerator adn denominator to make
     *                             the fraction more complex.
     * @param unreducedAnsDFactors a list of all the factors in the numerator of the answer.
     * @param unreducedAnsNFactors a list of all the factors in the denominator of the answer.
     */
    private void addComplexity(int complexity, List<Integer> unreducedAnsDFactors,
                               List<Integer> unreducedAnsNFactors) {
        for (int i = 0; i < complexity; i++) {
            int prime = biasedSelectNumber(PRIMES);
            unreducedAnsDFactors.add(prime);
            unreducedAnsNFactors.add(prime);
        }
    }

    /**
     * Randomly select a number from a given set of numbers. Be biased towards the ones on the left of the list.
     *
     * @param numbers the possible number values to choose from.
     * @return a randomly selected number (biased towards smaller ones).
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
}
