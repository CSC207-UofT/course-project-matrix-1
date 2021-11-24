package equation_builders;

import equation_entities.GCD;
import equation_parameters.EquationDetails;


/**
 * Generates GCD BEDMAS equations for fractions.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-23
 */
public class FractionGCDBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new GCD());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {
        // TODO: Implement this
    }
}
