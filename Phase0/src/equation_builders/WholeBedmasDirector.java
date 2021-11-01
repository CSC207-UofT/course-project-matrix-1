package equation_builders;

import equation_entities.BedmasEquation;

/**
 * Directs the construction of whole number BEDMAS equations, starting from the operator, then the operands, and finally
 * the answer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class WholeBedmasDirector {
    private WholeBedmasBuilder bedmasEquationBuilder;

    /**
     * Set the BedmasEquationBuilder.
     *
     * @param beb the BedmasEquationBuilder which this director will use.
     */
    public void setBedmasEquationBuilder(WholeBedmasBuilder beb) {
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
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     */
    public void constructBedmasEquation(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        bedmasEquationBuilder.createNewBedmasEquationProduct();
        bedmasEquationBuilder.buildOperator();
        bedmasEquationBuilder.buildOperands(operandRange1, operandRange2, negAllowed);
        bedmasEquationBuilder.buildAnswer();
    }
}
