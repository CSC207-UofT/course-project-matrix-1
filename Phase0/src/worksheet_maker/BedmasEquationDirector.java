package worksheet_maker;

public class BedmasEquationDirector {
    private BedmasEquationBuilder bedmasEquationBuilder;

    public void setBedmasEquationBuilder(BedmasEquationBuilder beb){
        this.bedmasEquationBuilder = beb;
    }

    public BedmasEquation getBedmasEquation() {
        return bedmasEquationBuilder.getBedmasEquation();
    }

    public void constructBedmasEquation(int[] operandRange1, int[] operandRange2, boolean negAnsAllowed){
        bedmasEquationBuilder.createNewBedmasEquationProduct();
        bedmasEquationBuilder.buildOperator();
        bedmasEquationBuilder.buildOperands(operandRange1, operandRange2, negAnsAllowed);
        bedmasEquationBuilder.buildAnswer();
    }
}
