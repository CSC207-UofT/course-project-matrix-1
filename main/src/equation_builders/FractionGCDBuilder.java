package equation_builders;

import equation_entities.GCD;
import equation_parameters.FractionEquationDetails;

public class FractionGCDBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new GCD());
    }

    @Override
    protected void buildOperands(FractionEquationDetails fractionEquationDetails, int seed) {
        // TODO: Implement this
    }
}
