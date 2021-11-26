package equation_builders;

import equation_entities.*;
import equation_parameters.EquationDetails;
import exceptions.InvalidInputException;
import utilities.Randomizer;

/**
 * An equation maker for all types of BEDMAS equations.
 * <p>
 * Implements Strategy design pattern to instantiate a specific class that implements the OperandConstructorInterface
 * based on runtime input. For example, it will instantiate a WholeNumDivideOperandConstructor class, which will be
 * called in the BedmasEquationMaker.
 *
 * @author Will Jeong, Stanley Hua
 * @version 2.0
 * @since 2021-10-30
 */
public class BedmasEquationMaker {
    protected BedmasEquation bedmasEquation;
    protected Randomizer randomizer;
    protected OperandConstructorInterface operandConstructor;
    protected String currentOperator;

    protected final String ADD = "+";
    protected final String SUBTRACT = "-";
    protected final String MULTIPLY = "*";
    protected final String DIVIDE = "/";
    protected final String EXPONENTIATE = "^";
    protected final String LCM = "LCM";
    protected final String GCD = "GCD";

    protected final String wholeNumber = "Whole Number";
    protected final String decimal = "Decimal";
    protected final String fraction = "Fraction";

    /**
     * Instantiates the proper OperandConstructor strategy based on input, and stores current equation operand.
     *
     * @param operandType specifies whether operand is a whole number, decimal or fraction.
     * @param operator    the char that determines which builder this director will use.
     */
    protected BedmasEquationMaker(String operator, String operandType) {
        this.currentOperator = operator;

        if (operandType.equals(wholeNumber)) {
            switch (operator) {
                case ADD:
                    this.operandConstructor = new WholeNumAddOperandConstructor();
                case EXPONENTIATE:
                    //TODO: Implement
                    break;
                case MULTIPLY:
                    // Uses the same strategy - rename
                    this.operandConstructor = new WholeNumAddOperandConstructor();
                    break;
                case SUBTRACT:
                    this.operandConstructor = new WholeNumSubOperandConstructor();
                    break;
                case DIVIDE:
                    this.operandConstructor = new WholeNumDivideOperandConstructor();
                    break;
            }
        } else if (operandType.equals(fraction)) {
            switch (operator) {
                case ADD:
                    this.operandConstructor = new FractionAddOperandConstructor();
                    break;
                case SUBTRACT:
                    this.operandConstructor = new FractionSubOperandConstructor();
                    break;
                case MULTIPLY:
                    this.operandConstructor = new FractionMultiplyOperandConstructor();
                    break;
                case DIVIDE:
                    this.operandConstructor = new FractionDivideOperandConstructor();
                    break;
            }
        }

        if (operandConstructor == null) {
            throw new InvalidInputException();
        }
    }

    protected BedmasEquation getBedmasEquation() {
        return bedmasEquation;
    }

    /**
     * Creates a new instance of the bedmas equation.
     */
    protected void createNewBedmasEquationProduct() {
        bedmasEquation = new BedmasEquation();
        randomizer = new Randomizer();
    }

    /**
     * Sets the bedmasEquation's operator based on current operator.
     */
    protected void buildOperator() {
        switch (currentOperator) {
            case ADD:
                bedmasEquation.setOperator(new Add());
                break;
            case SUBTRACT:
                bedmasEquation.setOperator(new Subtract());
                break;
            case MULTIPLY:
                bedmasEquation.setOperator(new Multiply());
                break;
            case DIVIDE:
                bedmasEquation.setOperator(new Divide());
                break;
            case EXPONENTIATE:
                bedmasEquation.setOperator(new Exponentiate());
                break;
            case LCM:
                bedmasEquation.setOperator(new LCM());
                break;
            case GCD:
                bedmasEquation.setOperator(new GCD());
                break;
        }
    }

    /**
     * Builds the operands (first and second) for the bedmasEquation using operand constructor.
     * <p>
     * RANDOM SEED (for fixing random number generation):
     * First random operation uses the random seed. Succeeding operations increment the random seed by 5.
     *
     * @param equationDetails contains necessary parameters for equation generation.
     * @param seed            random seed to fix random generation of operands
     */
    public void buildOperands(EquationDetails equationDetails, int seed) {
        this.randomizer.setSeed(seed);
        Value[] operands = this.operandConstructor.buildOperands(equationDetails, randomizer);
        bedmasEquation.setOperand1(operands[0]);
        bedmasEquation.setOperand2(operands[1]);
    }

    /**
     * Builds the bedmasEquation's answer.
     */
    protected void buildAnswer() {
        bedmasEquation.solve();
    }
}
