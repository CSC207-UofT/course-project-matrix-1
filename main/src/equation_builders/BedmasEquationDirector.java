package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.EquationDetails;
import utilities.DistributionCalculator;

/**
 * Directs the construction of BEDMAS equations, starting from the operator, then the operands,
 * and finally the answer.
 *
 * @author Will Jeong, Stanley Hua
 * @version 2.0
 * @since 2021-10-30
 */
public class BedmasEquationDirector extends EquationDirector {
    private BedmasEquationMaker bedmasEquationMaker;
    private boolean initialized = false;                // used for fractions
    private final String operandType;

    /**
     * @param operandType specifies whether operand is a whole number, decimal or fraction.
     */
    public BedmasEquationDirector(String operandType) {
        this.operandType = operandType;
    }

    /**
     * Create and store new GeneralEquationMaker.
     *
     * @param operator the char that determines which builder this director will use.
     */
    //TODO: Use Class.forName, have them assign the class directly instead.
    @Override
    public void setEquationBuilder(String operator) {
        this.bedmasEquationMaker = new BedmasEquationMaker(operator, operandType);
    }

    /**
     * Returns the BedmasEquation held within the GeneralEquationMaker.
     *
     * @return the bedmas equation from the builder.
     */
    @Override
    public BedmasEquation getEquation() {
        return bedmasEquationMaker.getBedmasEquation();
    }

    /**
     * Construct a bedmas equation given the following parameters.
     *
     * @param equationDetails the parameters for whole number equation generation.
     * @param seed            random seed to fix random generation of operands
     */
    @Override
    public void constructEquation(EquationDetails equationDetails, int seed) {
        // TODO: Change implementation of initialized.
        if (operandType.equals("Fraction") && (!initialized && (equationDetails.getOperator().equals("+")) ||
                (equationDetails.getOperator().equals("-")))) {
            DistributionCalculator.assignProbability(equationDetails);
            initialized = true;
        }

        bedmasEquationMaker.createNewBedmasEquationProduct();
        bedmasEquationMaker.buildOperator();
        bedmasEquationMaker.buildOperands(equationDetails, seed);
        bedmasEquationMaker.buildAnswer();
    }
}
