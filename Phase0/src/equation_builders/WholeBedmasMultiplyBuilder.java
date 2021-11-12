package equation_builders;

import equation_entities.Multiply;

/**
 * Builds whole number multiplication equations. Both operands are uniformly distributed across their specified range.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
class WholeBedmasMultiplyBuilder extends WholeBedmasBuilder {
    /**
     * Assigns the multiplication operator to the equation.
     */
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator(new Multiply());
    }
}
