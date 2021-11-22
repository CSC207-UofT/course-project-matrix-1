package equation_builders;

import equation_entities.LCM;
import equation_parameters.FractionEquationDetails;

public class FractionLCMBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new LCM());
    }

    @Override
    protected void buildOperands(FractionEquationDetails fractionEquationDetails, int seed) {
        // TODO: Implement this
    }
}
