package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.EquationDetails;
import equation_parameters.FractionEquationDetails;
import exceptions.InvalidInputException;

/**
 * Directs the construction of whole number BEDMAS equations, starting from the operator, then the operands,
 * and finally the answer. You must create a new FractionDirector everytime you use a new set of parameters.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class FractionDirector extends EquationDirector {
    private FractionBuilder fractionBuilder;
    private boolean initialized = false;

    /**
     * Set the FractionBuilder.
     *
     * @param operator the char that determines which builder this director will use.
     */
    public void setEquationBuilder(char operator, String operandType) {
        char ADD = '+';
        char SUBTRACT = '-';
        char MULTIPLY = '*';
        char DIVIDE = '/';
        char EXPONENTIATE = '^';

        if (operator == ADD) {
            this.fractionBuilder = new FractionAddOperandConstructor();
        } else if (operator == SUBTRACT) {
            this.fractionBuilder = new FractionSubOperandConstructor();
        } else if (operator == MULTIPLY) {
            this.fractionBuilder = new FractionMultiplyOperandConstructor();
        } else if (operator == DIVIDE) {
            this.fractionBuilder = new FractionDivideOperandConstructor();
        } else if (operator == EXPONENTIATE) {
            this.fractionBuilder = new FractionExponentiateOperandConstructor();
        } else {
            throw new InvalidInputException();
        }
    }

    public void setEquationBuilder(char operator) {
        setEquationBuilder(operator, "Fraction");
    }

    @Override
    public BedmasEquation getEquation() {
        return fractionBuilder.getBedmasEquation();
    }

    /**
     * Construct a bedmas equation given the following parameters.
     *
     * @param equationDetails the parameters for fraction equation generation.
     * @param seed random seed to fix randomness in generating of operands
     */
    @Override
    public void constructEquation(EquationDetails equationDetails, int seed) {
        if (!initialized) {
            fractionBuilder.assignProbability((FractionEquationDetails) equationDetails);
            initialized = true;
        }
        FractionEquationDetails fractionEquationDetails = (FractionEquationDetails) equationDetails;
        fractionBuilder.createNewBedmasEquationProduct();
        fractionBuilder.buildOperator();
        fractionBuilder.buildOperands(fractionEquationDetails, seed);
        fractionBuilder.buildAnswer();
    }


}
