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
public class FractionDirector {
    private FractionBuilder fractionBuilder;

    /**
     * Set the FractionBuilder.
     *
     * @param operator the char that determines which builder this director will use.
     */
    public void setEquationBuilder(char operator) {
        char ADD = '+';
        char SUBTRACT = '-';
        char MULTIPLY = '*';
        char DIVIDE = '/';

        if (operator == ADD) {
            this.fractionBuilder = new FractionAddBuilder();
        } else if (operator == SUBTRACT) {
            this.fractionBuilder = new FractionSubBuilder();
        } else if (operator == MULTIPLY) {
            this.fractionBuilder = new FractionMultiplyBuilder();
        } else if (operator == DIVIDE) {
            this.fractionBuilder = new FractionDivideBuilder();
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
        return fractionBuilder.getBedmasEquation();
    }

}
