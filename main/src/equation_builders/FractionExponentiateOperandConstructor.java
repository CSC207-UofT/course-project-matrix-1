package equation_builders;

import equation_entities.Value;
import equation_parameters.EquationDetails;
import utilities.Randomizer;

/**
 * Handles the construction of operands for the fraction exponentiation equations.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionExponentiateOperandConstructor implements OperandConstructorInterface {
    private Randomizer randomizer;

    /**
     * Returns array of values with a randomly generated first operand and second operand for subtraction standard
     * equation.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param rand            Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    @Override
    public Value[] buildOperands(EquationDetails equationDetails, Randomizer rand) {
        // TODO: Implement this
        return new Value[0];
    }
}
