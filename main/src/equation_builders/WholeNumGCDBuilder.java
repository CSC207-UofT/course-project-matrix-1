package equation_builders;

import equation_entities.GCD;

/**
 * Builds whole number GCD equations.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumGCDBuilder extends WholeNumBuilder {
    /**
     * Assigns the GCD operator to the equation.
     */
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new GCD());
    }
}
