package equation_builders;

import equation_entities.BedmasEquation;
import exceptions.InvalidInputException;

/**
 * Directs the construction of whole number BEDMAS equations, starting from the operator, then the operands,
 * and finally the answer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class WholeBedmasDirector {
    private WholeBedmasBuilder wholeBedmasBuilder;

    /**
     * Set the BedmasEquationBuilder.
     *
     * @param operator the char that determines which builder this director will use.
     */
    //TODO: Use Class.forName, have them assign the class directly instead.
    public void setBedmasEquationBuilder(char operator) {
        char ADD = '+';
        char SUBTRACT = '-';
        char MULTIPLY = '*';
        char DIVIDE = '/';

        if (operator == ADD) {
            this.wholeBedmasBuilder = new WholeBedmasAddBuilder();
        } else if (operator == SUBTRACT) {
            this.wholeBedmasBuilder = new WholeBedmasSubBuilder();
        } else if (operator == MULTIPLY) {
            this.wholeBedmasBuilder = new WholeBedmasMultiplyBuilder();
        } else if (operator == DIVIDE) {
            this.wholeBedmasBuilder = new WholeBedmasDivideBuilder();
        } else {
            throw new InvalidInputException();
        }
    }
    
    /**
     * Returns the BedmasEquation held within the BedmasEquationBuilder.
     *
     * @return the bedmas equation from the builder.
     */
    public BedmasEquation getBedmasEquation() {
        return wholeBedmasBuilder.getBedmasEquation();
    }

    /**
     * Construct a bedmas equation given the following parameters.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     * @param seed          random seed to fix random generation of operands
     */
    public void constructBedmasEquation(int[] operandRange1, int[] operandRange2, boolean negAllowed, int seed) {
        wholeBedmasBuilder.createNewBedmasEquationProduct();
        wholeBedmasBuilder.buildOperator();
        wholeBedmasBuilder.buildOperands(operandRange1, operandRange2, negAllowed, seed);
        wholeBedmasBuilder.buildAnswer();
    }
}
