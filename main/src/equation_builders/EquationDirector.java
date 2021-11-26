package equation_builders;

import equation_entities.StandardEquation;
import equation_parameters.EquationDetails;

/**
 * Directs the construction of all equations, returning the final equation.
 *
 * @author Will Jeong
 * @version 2.0
 * @since 2021-10-30
 */
public abstract class EquationDirector {

    public abstract StandardEquation getEquation();

    public abstract void constructEquation(EquationDetails equationDetails, int seed);

}
