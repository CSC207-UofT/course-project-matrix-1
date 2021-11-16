package equation_builders;

import equation_entities.Add;


/**
 * Builds whole number addition equations. Both operands are uniformly distributed across their specified range.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumAddBuilder extends WholeNumBuilder {
    /**
     * Assigns the addition operator to the equation.
     */
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Add());
    }
}
