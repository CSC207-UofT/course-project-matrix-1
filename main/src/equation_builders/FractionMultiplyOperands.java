package equation_builders;

import equation_entities.Fraction;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import utilities.FactorFinder;
import utilities.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Directs the construction of fraction multiply equations, starting from the operator, then the operands,
 * and finally the answer.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionMultiplyOperands extends FractionMultDivOperands implements OperandConstructorInterface {

    /**
     * Uses the maximum value of the answer, answers' denominator range, and complexity to get reasonable operands for
     * fraction multiplication.
     *
     * @param fractionEquationDetails the parameters for fraction equation generation.
     */
    @Override
    public Value[] buildOperands(EquationDetails fractionEquationDetails, Randomizer randomizer) {
        // Construct all operands as if it was a multiplication question.
        int[][] allOperandParts = buildOperandParts(fractionEquationDetails, randomizer);
        // Assign the appropriate numerators and denominators to create two Fractions.
        return new Value[]{new Fraction(allOperandParts[0][0], allOperandParts[1][0]), new Fraction(allOperandParts[0][1], allOperandParts[1][1])};
    }
}
