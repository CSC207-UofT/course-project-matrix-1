package equation_builders;

import equation_entities.Multiply;
import equation_parameters.EquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;

public class FractionMultiplyBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Multiply());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {
        FractionMultiDivEquationDetails fracMultiDivEqnDetails = (FractionMultiDivEquationDetails) fractionEquationDetails;

    }


}
