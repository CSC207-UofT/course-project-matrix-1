package equation_builders;

import equation_entities.Divide;
import equation_parameters.EquationDetails;

public class FractionDivideBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Divide());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {

    }
}
