package equation_builders;

import equation_entities.StandardEquation;
import equation_parameters.EquationDetails;
import utilities.Randomizer;

/**
 * Directs the construction of all equations, returning the final equation. Must be coupled to a certain maker, that
 * actually creates the equation.
 *
 * @author Will Jeong
 * @version 2.0
 * @since 2021-10-30
 */
public abstract class EquationDirector {
    protected final EquationDetails equationDetails;
    protected final Randomizer randomizer;

    public EquationDirector(EquationDetails equationDetails, Randomizer randomizer) {
        this.equationDetails = equationDetails;
        this.randomizer = randomizer;
    }

    /**
     * Returns the equation held within the equation maker.
     *
     * @return the equation from the equation maker.
     */
    public abstract StandardEquation getEquation();

    /**
     * Construct a standard equation given the following parameters.
     */
    public abstract void constructEquation();

}
