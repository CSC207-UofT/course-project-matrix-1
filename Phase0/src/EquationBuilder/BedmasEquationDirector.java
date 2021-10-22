package EquationBuilder;

public class BedmasEquationDirector {
    private BedmasEquationBuilder bedmasEquationBuilder;

    public void setBedmasEquationBuilder(BedmasEquationBuilder beb){
        this.bedmasEquationBuilder = beb;
    }

    public BedmasEquation getBedmasEquation() {
        return bedmasEquationBuilder.getBedmasEquation();
    }

    public void constructBedmasEquation(int minOperand, int maxOperand, boolean negAns){
        bedmasEquationBuilder.createNewBedmasEquationProduct();
        bedmasEquationBuilder.buildOperator();
        bedmasEquationBuilder.buildAnswer(minOperand, maxOperand);
        bedmasEquationBuilder.buildOperand1(minOperand, maxOperand, negAns);
        bedmasEquationBuilder.buildOperand2(minOperand, maxOperand, negAns);
    }
}
