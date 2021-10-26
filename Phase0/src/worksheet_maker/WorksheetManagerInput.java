package worksheet_maker;

public interface WorksheetManagerInput {
    /**
     * Sets the worksheet using various worksheet related parameters.
     *
     * @param numOfEquations the number of equations in this worksheet.
     * @param operator       the operator in the equations of the worksheet.
     * @param operandRange1  the absolute range of values that the first operand can be.
     * @param operandRange2  the absolute range of values that the second operand can be.
     * @param negAllowed     specifies if the operands are allowed to be negative.
     */
    public void setWorksheet(int numOfEquations, char operator, int[] operandRange1, int[] operandRange2, boolean negAllowed);
}
