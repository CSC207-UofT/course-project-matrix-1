package EquationBuilder;

abstract class BedmasEquationBuilder {
    protected BedmasEquation bedmasEquation;

    public BedmasEquation getBedmasEquation() {
        return bedmasEquation;
    }

    public void createNewBedmasEquationProduct() {
        bedmasEquation = new BedmasEquation();
    }

    public abstract void buildOperator();
    public abstract void buildAnswer(int minOperand, int maxOperand);
    public abstract void buildOperand1(int minOperand, int maxOperand, boolean negAns);
    public abstract void buildOperand2(int minOperand, int maxOperand, boolean negAns);
}
