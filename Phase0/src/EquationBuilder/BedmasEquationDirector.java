package EquationBuilder;

public class BedmasEquationDirector {
    private BedmasEquationBuilder bedmasEquationBuilder;

    public void setBedmasEquationBuilder(BedmasEquationBuilder beb){
        this.bedmasEquationBuilder = beb;
    }

    public BedmasEquation getBedmasEquation() {
        return bedmasEquationBuilder.getBedmasEquation();
    }

    public void constructBedmasEquation(int minOperand, int maxOperand){
        bedmasEquationBuilder.createNewBedmasEquationProduct();
        bedmasEquationBuilder.buildOperator();
        bedmasEquationBuilder.buildAnswer(minOperand, maxOperand);
        bedmasEquationBuilder.buildOperand1(minOperand, maxOperand);
        bedmasEquationBuilder.buildOperand2(minOperand, maxOperand);
    }
}
