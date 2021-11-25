package equation_builders;

import equation_entities.LCM;
import equation_parameters.EquationDetails;

/**
 * Generates LCM BEDMAS equations for fractions.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-23
 */
public class FractionLCMBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new LCM());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {
        // TODO: Implement this
    }
}
