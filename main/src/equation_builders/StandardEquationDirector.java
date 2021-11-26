package equation_builders;

import equation_entities.StandardEquation;
import equation_parameters.EquationDetails;
import utilities.DistributionCalculator;

/**
 * Directs the construction of standard equations, starting from the operator, then the operands,
 * and finally the answer. Only one StandardEquationDirector should only be made for a certain operandType and operator.
 * If you need a new one for a new worksheet, make a new instance of this class.
 *
 * @author Will Jeong, Stanley Hua
 * @version 2.0
 * @since 2021-10-30
 */
public class StandardEquationDirector extends EquationDirector {
    private final StandardEquationMaker standardEquationMaker;

    /**
     * Creates an instance of StandardEquationDirector and its associated StandardEquationMaker.
     *
     * @param operandType     specifies whether operand is a whole number, decimal or fraction.
     * @param equationDetails the details of the equation necessary to create the corresponding StandardEquationMaker.
     */
    public StandardEquationDirector(String operandType, EquationDetails equationDetails) {
        this.standardEquationMaker = new StandardEquationMaker(equationDetails.getOperator(), operandType);
        if (operandType.equals("Fraction")) {
            //If the equation is a fraction, reweight numbers so that primes are less likely.
            DistributionCalculator.assignProbability(equationDetails);
        }
    }

    /**
     * Construct a standard equation given the following parameters.
     *
     * @param equationDetails the parameters for whole number equation generation.
     * @param seed            random seed to fix random generation of operands.
     */
    @Override
    public void constructEquation(EquationDetails equationDetails, int seed) {
        standardEquationMaker.createNewStandardEquationProduct();
        standardEquationMaker.buildOperator();
        standardEquationMaker.buildOperands(equationDetails, seed);
        standardEquationMaker.buildAnswer();
    }

    /**
     * Returns the StandardEquation held within the StandardEquationMaker.
     *
     * @return the standard equation from the StandardEquationMaker.
     */
    @Override
    public StandardEquation getEquation() {
        return standardEquationMaker.getStandardEquation();
    }


}
