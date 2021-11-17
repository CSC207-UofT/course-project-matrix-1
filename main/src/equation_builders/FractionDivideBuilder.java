package equation_builders;

import equation_entities.Divide;
import equation_parameters.FractionEquationDetails;

public class FractionDivideBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Divide());
    }

    @Override
    protected void buildOperands(FractionEquationDetails fractionEquationDetails, int seed) {

    }
}
