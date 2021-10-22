package EquationBuilder;

public class BedmasEquationDirector {
    private BedmasEquationBuilder bedmasEquationBuilder;

    public void setBedmasEquationBuilder(BedmasEquationBuilder beb){
        this.bedmasEquationBuilder = beb;
    }

    public BedmasEquation getBedmasEquation() {
        return bedmasEquationBuilder.getBedmasEquation();
    }

    public void constructBedmasEquation(int minOperand, int maxOperand, boolean negAnsAllowed){
        bedmasEquationBuilder.createNewBedmasEquationProduct();
        bedmasEquationBuilder.buildOperator();
        bedmasEquationBuilder.buildOperands(minOperand, maxOperand, negAnsAllowed);
        bedmasEquationBuilder.buildAnswer();
    }
}
