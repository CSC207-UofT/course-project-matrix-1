package equation_builders;

import equation_entities.BedmasEquation;

/**
 * Director of all BedmasEquationBuilders. Contains the BedmasEquationBuilder to be used by the client and specifies
 * which order the build methods should be done in.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class BedmasEquationDirector {
    private BedmasEquationBuilder bedmasEquationBuilder;

    /**
     * Set the BedmasEquationBuilder.
     *
     * @param bedmasEquationBuilder the BedmasEquationBuilder which this director will use.
     */
    public void setBedmasEquationBuilder(BedmasEquationBuilder bedmasEquationBuilder) {
        this.bedmasEquationBuilder = bedmasEquationBuilder;
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
