package equation_builders;

import equation_entities.StandardEquation;
import equation_parameters.EquationDetails;

/**
 * Directs the construction of all equations, returning the final equation. Must be coupled to a certain maker, that
 * actually creates the equation.
 *
 * @author Will Jeong
 * @version 2.0
 * @since 2021-10-30
 */
public abstract class EquationDirector {
    /**
     * Returns the equation held within the equation maker.
     *
     * @return the equation from the equation maker.
     */
    public abstract StandardEquation getEquation();
    /**
     * Construct a standard equation given the following parameters.
     *
     * @param equationDetails the parameters for whole number equation generation.
     * @param seed            random seed to fix random generation of operands.
     */
    public abstract void constructEquation(EquationDetails equationDetails, int seed);

}
