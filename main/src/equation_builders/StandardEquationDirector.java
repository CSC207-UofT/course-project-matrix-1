package equation_builders;

import equation_entities.StandardEquation;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import utilities.DistributionCalculator;
import utilities.Randomizer;

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
     * @param randomizer the instance of randomizer that randomizes the questions generated.
     * @param equationDetails the details of the equation necessary to create the corresponding StandardEquationMaker.
     * @param operandType     specifies whether operand is a whole number, decimal or fraction.
     */
    public StandardEquationDirector(Randomizer randomizer, EquationDetails equationDetails, String operandType) {
        super(equationDetails, randomizer);
        this.standardEquationMaker = new StandardEquationMaker(equationDetails.getOperator(), randomizer, operandType);
        if (operandType.equals("Fraction") && (equationDetails instanceof FractionAddSubEquationDetails)) {
            //Reweight numbers so that primes are less likely.
            DistributionCalculator.assignProbability(equationDetails);
        }
    }

    /**
     * Construct a standard equation given the following parameters.
     *
     */
    @Override
    public void constructEquation() {
        standardEquationMaker.createNewStandardEquationProduct();
        standardEquationMaker.buildOperator();
        standardEquationMaker.buildOperands(equationDetails);
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
