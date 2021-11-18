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
     * RANDOM SEED (for fixing random number generation): First random operation uses the random seed. Succeeding
     * operations increment the random seed by 5.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param seed random seed to fix randomness in generating of operands.
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    Value[] buildOperands(EquationDetails equationDetails, int seed, Randomizer rand);
}