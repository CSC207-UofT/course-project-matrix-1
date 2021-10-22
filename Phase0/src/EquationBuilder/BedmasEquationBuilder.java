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
    public abstract void buildAnswer();
    public abstract void buildOperand1();
    public abstract void buildOperand2();
}
