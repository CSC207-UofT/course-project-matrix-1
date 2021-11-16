package equation_builders;

import equation_entities.Subtract;


public class FractionSubBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Subtract());
    }
}
