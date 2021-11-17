package equation_builders;

import equation_entities.Divide;

public class FractionDivideBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Divide());
    }
}
