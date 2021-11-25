package equation_builders;

import equation_entities.Multiply;
import equation_parameters.EquationDetails;

public class FractionExponentiateBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Multiply());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {

    }
}
