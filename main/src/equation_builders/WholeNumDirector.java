package equation_builders;

import equation_entities.BedmasEquation;
import exceptions.InvalidInputException;
import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;

/**
 * Directs the construction of whole number BEDMAS equations, starting from the operator, then the operands,
 * and finally the answer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class WholeNumDirector extends EquationDirector {
    private WholeNumBuilder wholeBedmasBuilder;

    /**
     * Set the BedmasEquationBuilder.
     *
     * @param operator the char that determines which builder this director will use.
     */
    //TODO: Use Class.forName, have them assign the class directly instead.
    @Override
    public void setEquationBuilder(char operator) {
        char ADD = '+';
        char SUBTRACT = '-';
        char MULTIPLY = '*';
        char DIVIDE = '/';
        char EXPONENTIATE = '^';

        if (operator == ADD) {
            this.wholeBedmasBuilder = new WholeNumAddBuilder();
        } else if (operator == SUBTRACT) {
            this.wholeBedmasBuilder = new WholeNumSubBuilder();
        } else if (operator == MULTIPLY) {
            this.wholeBedmasBuilder = new WholeNumMultiplyBuilder();
        } else if (operator == DIVIDE) {
            this.wholeBedmasBuilder = new WholeNumDivideBuilder();
        } else if (operator == EXPONENTIATE) {
            this.wholeBedmasBuilder = new WholeNumExponentiateBuilder();
        } else {
            throw new InvalidInputException();
        }
    }
    
    /**
     * Returns the BedmasEquation held within the BedmasEquationBuilder.
     *
     * @return the bedmas equation from the builder.
     */
    @Override
    public BedmasEquation getEquation() {
        return wholeBedmasBuilder.getBedmasEquation();
    }

    /**
     * Construct a bedmas equation given the following parameters.
     *
     * @param equationDetails the parameters for whole number equation generation.
     * @param seed          random seed to fix random generation of operands
     */
    @Override
    public void constructEquation(EquationDetails equationDetails, int seed) {
        WholeNumEquationDetails wholeNumEquationDetails = (WholeNumEquationDetails) equationDetails;
        wholeBedmasBuilder.createNewBedmasEquationProduct();
        wholeBedmasBuilder.buildOperator();
        //TODO: Refactor so that the wholeNumEquationDetails is directly sent in
        wholeBedmasBuilder.buildOperands(wholeNumEquationDetails.getOperandRange1(), wholeNumEquationDetails.getOperandRange2(), wholeNumEquationDetails.isNegAllowed(), seed);
        wholeBedmasBuilder.buildAnswer();
    }
}
