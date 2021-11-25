package equation_builders;

import equation_entities.LCM;

/**
 * Builds whole number LCM equations.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumLCMBuilder extends WholeNumBuilder {
    /**
     * Assigns the LCM operator to the equation.
     */
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new LCM());
    }
}
