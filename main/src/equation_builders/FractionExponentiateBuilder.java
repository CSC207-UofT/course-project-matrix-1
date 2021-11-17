package equation_builders;

import equation_entities.Multiply;
import equation_parameters.FractionEquationDetails;

public class FractionExponentiateBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Multiply());
    }

    @Override
    protected void buildOperands(FractionEquationDetails fractionEquationDetails, int seed) {

    }
}
