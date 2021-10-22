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
        bedmasEquationBuilder.buildAnswer(minOperand, maxOperand);
        bedmasEquationBuilder.buildOperand1(minOperand, maxOperand, negAnsAllowed);
        bedmasEquationBuilder.buildOperand2(minOperand, maxOperand, negAnsAllowed);
    }
}
