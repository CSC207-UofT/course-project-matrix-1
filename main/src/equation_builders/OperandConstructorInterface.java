package equation_builders;

import equation_entities.Value;
import equation_parameters.EquationDetails;
import utilities.Randomizer;

/**
 * Classes that implement this interface must provide a buildOperands method that returns an array of two Value objects
 * corresponding to the first and second operand.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-17
 */
public interface OperandConstructorInterface {
    /**
     * Returns array of values with a randomly generated first operand and second operand.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param rand            Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    Value[] buildOperands(EquationDetails equationDetails, Randomizer rand);
}