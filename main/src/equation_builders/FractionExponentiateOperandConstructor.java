package equation_builders;

import equation_entities.Multiply;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionEquationDetails;

public class FractionExponentiateOperandConstructor implements OperandConstructorInterface {
    /**
     * Creates fraction operands (first and second) for the subtraction bedmas equation's question.
     *
     * RANDOM SEED (for fixing random number generation): First random operation uses the random seed. Succeeding
     * operations increment the random seed by 5.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param seed random seed to fix randomness in generating of operands
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails equationDetails, int seed, utilities.Randomizer rand) {
        FractionEquationDetails fractionEquationDetails = (FractionEquationDetails) equationDetails;
        // TODO: Implement this
        return new Value[]{};
    }
}
