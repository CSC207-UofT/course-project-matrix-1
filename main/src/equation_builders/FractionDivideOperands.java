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
 * Handles the construction of operands for the fraction divide equations.
 *
 * @author Sean Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionDivideOperands extends FractionMultDivOperands implements OperandConstructorInterface {
    /**
     * Uses the maximum value of the answer, answers' denominator range, and complexity to get reasonable operands for
     * fraction multiplication.
     *
     * @param fractionEquationDetails the parameters for fraction equation generation.
     * @param randomizer              Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    @Override
    public Value[] buildOperands(EquationDetails fractionEquationDetails, Randomizer randomizer) {
        int[][] allOperandParts = buildOperandParts(fractionEquationDetails, randomizer);
        return new Value[]{new Fraction(allOperandParts[0][0], allOperandParts[1][0]), new Fraction(allOperandParts[1][1], allOperandParts[0][1])};
    }

}
