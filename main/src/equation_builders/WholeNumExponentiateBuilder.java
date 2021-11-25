package equation_builders;

import equation_entities.Exponentiate;

/**
 * Builds whole number exponentiation equations.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumExponentiateBuilder extends WholeNumBuilder {
    /**
     * Assigns the multiplication operator to the equation.
     */
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Exponentiate());
    }


}
