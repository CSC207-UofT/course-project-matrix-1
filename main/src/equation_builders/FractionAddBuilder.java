package equation_builders;

import equation_entities.Add;

public class FractionAddBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Add());
    }
}

