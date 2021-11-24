package equation_builders;

import equation_entities.Exponentiate;
import equation_entities.Multiply;
import equation_parameters.EquationDetails;

/**
 * Generates exponentiation BEDMAS equations for fractions.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-23
 */
public class FractionExponentiateBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Exponentiate());
    }

    @Override
    protected void buildOperands(EquationDetails fractionEquationDetails, int seed) {
        // TODO: Implement this
    }
}
