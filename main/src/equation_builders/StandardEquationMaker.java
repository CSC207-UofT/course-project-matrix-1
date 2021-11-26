package equation_builders;

import equation_entities.*;
import equation_parameters.EquationDetails;
import exceptions.InvalidInputException;
import utilities.Randomizer;

/**
 * An equation maker for all types of standard equations.
 * <p>
 * Implements Strategy design pattern to instantiate a specific class that implements the OperandConstructorInterface
 * based on runtime input. For example, it will instantiate a WholeNumDivideOperands class, which will be
 * called in the StandardEquationMaker.
 *
 * @author Will Jeong, Stanley Hua
 * @version 2.0
 * @since 2021-10-30
 */
public class StandardEquationMaker {
    protected StandardEquation standardEquation;
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
    protected StandardEquationMaker(String operator, String operandType) {
        this.currentOperator = operator;
        if (operandType.equals(wholeNumber)) {
            switch (operator) {
                case ADD:
                    //Proceeds to case multiply
                case MULTIPLY:
                    this.operandConstructor = new WholeNumAddMultOperands();
                    break;
                case EXPONENTIATE:
                    //TODO: Implement
                    break;
                case SUBTRACT:
                    this.operandConstructor = new WholeNumSubOperands();
                    break;
                case DIVIDE:
                    this.operandConstructor = new WholeNumDivideOperands();
                    break;
            }
        } else if (operandType.equals(fraction)) {
            switch (operator) {
                case ADD:
                    this.operandConstructor = new FractionAddOperands();
                    break;
                case SUBTRACT:
                    this.operandConstructor = new FractionSubOperands();
                    break;
                case MULTIPLY:
                    this.operandConstructor = new FractionMultiplyOperands();
                    break;
                case DIVIDE:
                    this.operandConstructor = new FractionDivideOperands();
                    break;
            }
        }

        if (operandConstructor == null) {
            throw new InvalidInputException();
        }
    }

    /**
     * Returns the instance of the standard equation.
     * @return the StandardEquation stored in this maker.
     */
    protected StandardEquation getStandardEquation() {
        return standardEquation;
    }

    /**
     * Creates a new instance of the standard equation.
     */
    protected void createNewStandardEquationProduct() {
        standardEquation = new StandardEquation();
        randomizer = new Randomizer();
    }

    /**
     * Sets the standardEquation's operator based on current operator.
     */
    protected void buildOperator() {
        switch (currentOperator) {
            case ADD:
                standardEquation.setOperator(new Add());
                break;
            case SUBTRACT:
                standardEquation.setOperator(new Subtract());
                break;
            case MULTIPLY:
                standardEquation.setOperator(new Multiply());
                break;
            case DIVIDE:
                standardEquation.setOperator(new Divide());
                break;
            case EXPONENTIATE:
                standardEquation.setOperator(new Exponentiate());
                break;
            case LCM:
                standardEquation.setOperator(new LCM());
                break;
            case GCD:
                standardEquation.setOperator(new GCD());
                break;
        }
    }

    /**
     * Builds the operands (first and second) for the standardEquation using operand constructor.
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
        standardEquation.setOperand1(operands[0]);
        standardEquation.setOperand2(operands[1]);
    }

    /**
     * Builds the standardEquation's answer.
     */
    protected void buildAnswer() {
        standardEquation.solve();
    }
}
