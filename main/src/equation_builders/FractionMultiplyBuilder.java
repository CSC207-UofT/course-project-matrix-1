package equation_builders;

import equation_entities.Multiply;

public class FractionMultiplyBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Multiply());
    }
}
