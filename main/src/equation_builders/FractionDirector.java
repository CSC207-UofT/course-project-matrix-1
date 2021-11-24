package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import exceptions.InvalidInputException;
import utilities.DistributionCalculator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

    @Override
    public BedmasEquation getEquation() {
        return fractionBuilder.getBedmasEquation();
    }

    /**
     * Construct a bedmas equation given the following parameters. If this class has not been initialized and this
     * is a subtraction or addition question, a distribution for the denominator will be calculated.
     *
     * @param equationDetails the parameters for fraction equation generation.
     * @param seed            random seed to fix random generation of operands
     */
    @Override
    public void constructEquation(EquationDetails equationDetails, int seed) {
        //TODO: make this more flexible.
        if (!initialized && (equationDetails.getOperator()=='+')||(equationDetails.getOperator()=='-')) {
            DistributionCalculator.assignProbability(equationDetails);
            initialized = true;
        }
        fractionBuilder.createNewBedmasEquationProduct();
        fractionBuilder.buildOperator();
        fractionBuilder.buildOperands(equationDetails, seed);
        fractionBuilder.buildAnswer();
    }


}
