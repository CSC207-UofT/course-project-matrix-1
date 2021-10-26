package worksheet_maker;

public class BedmasEquationDirector {
    private BedmasEquationBuilder bedmasEquationBuilder;

    /**
     * Set the BedmasEquationBuilder.
     *
     * @param beb the BedmasEquationBuilder which this director will use.
     */
    public void setBedmasEquationBuilder(BedmasEquationBuilder beb) {
        this.bedmasEquationBuilder = beb;
    }

    /**
     * Returns the BedmasEquation held within the BedmasEquationBuilder.
     *
     * @return the bedmas equation from the builder.
     */
    public BedmasEquation getBedmasEquation() {
        return bedmasEquationBuilder.getBedmasEquation();
    }

    /**
     * Construct a bedmas equation given the following parameters.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands are allowed to be negative.
     */
    public void constructBedmasEquation(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        bedmasEquationBuilder.createNewBedmasEquationProduct();
        bedmasEquationBuilder.buildOperator();
        bedmasEquationBuilder.buildOperands(operandRange1, operandRange2, negAllowed);
        bedmasEquationBuilder.buildAnswer();
    }
}
