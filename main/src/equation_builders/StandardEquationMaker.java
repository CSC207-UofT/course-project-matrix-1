package equation_builders;

import equation_entities.*;

import static constants.OperatorRep.*;

import equation_parameters.EquationDetails;
import exceptions.InvalidInputException;
import utilities.Randomizer;

import static constants.EquationType.*;

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
    protected OperandConstructorInterface operandConstructor;
    protected final Randomizer randomizer;
    protected final String currentOperator;


    /**
     * Instantiates the proper OperandConstructor strategy based on input, and stores current equation operand.
     *
     * @param operandType specifies whether operand is a whole number, decimal or fraction.
     * @param randomizer  the instance of randomizer that randomizes the questions generated.
     * @param operator    the char that determines which builder this director will use.
     */
    protected StandardEquationMaker(String operator, Randomizer randomizer, String operandType) {
        this.randomizer = randomizer;
        this.currentOperator = operator;
        if (operandType.equals(WHOLE_NUMBER)) {
            switch (operator) {
                case ADD:
                    //Proceeds to case multiply. Uses WholeNumIncreaseOperands();
                case MULT:
                    //Proceeds to case exponentiate. Uses WholeNumIncreaseOperands();
                case EXP:
                    this.operandConstructor = new WholeNumIncreaseOperands();
                    break;
                case SUB:
                    this.operandConstructor = new WholeNumSubOperands();
                    break;
                case DIV:
                    this.operandConstructor = new WholeNumDivideOperands();
                    break;
            }
        } else if (operandType.equals(FRACTION)) {
            switch (operator) {
                case ADD:
                    this.operandConstructor = new FractionAddOperands();
                    break;
                case SUB:
                    this.operandConstructor = new FractionSubOperands();
                    break;
                case MULT:
                    this.operandConstructor = new FractionMultiplyOperands();
                    break;
                case DIV:
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
     *
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
    }

    /**
     * Sets the standardEquation's operator based on current operator.
     */
    protected void buildOperator() {
        switch (currentOperator) {
            case ADD:
                standardEquation.setOperator(new Add());
                break;
            case SUB:
                standardEquation.setOperator(new Subtract());
                break;
            case MULT:
                standardEquation.setOperator(new Multiply());
                break;
            case DIV:
                standardEquation.setOperator(new Divide());
                break;
            case EXP:
                standardEquation.setOperator(new Exponentiate());
                break;
            case LCM:
                standardEquation.setOperator(new LCM());
                break;
            case GCF:
                standardEquation.setOperator(new GCD());
                break;
        }
    }

    /**
     * Builds the operands (first and second) for the standardEquation using operand constructor.
     *
     * @param equationDetails contains necessary parameters for equation generation.
     */
    public void buildOperands(EquationDetails equationDetails) {
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
