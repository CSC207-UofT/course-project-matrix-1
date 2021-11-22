package equation_builders;

import equation_entities.Subtract;
import equation_parameters.EquationDetails;


public class FractionSubBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Subtract());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {

    }
}
