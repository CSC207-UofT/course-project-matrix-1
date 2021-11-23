package equation_builders;

import equation_entities.Divide;
import equation_parameters.EquationDetails;

/**
 * Directs the construction of fraction divide equations, starting from the operator, then the operands,
 * and finally the answer.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionDivideBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Divide());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {

    }
}
