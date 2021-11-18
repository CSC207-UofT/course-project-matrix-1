package equation_builders;

import equation_entities.*;
import equation_parameters.EquationDetails;
import exceptions.InvalidInputException;
import utilities.Randomizer;

/**
 * An equation maker for all types of BEDMAS equations.
 *
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
    protected Randomizer rand;
    protected OperandConstructorInterface operandConstructor;
    protected char currentOperator;

    protected final char ADD = '+';
    protected final char SUBTRACT = '-';
    protected final char MULTIPLY = '*';
    protected final char DIVIDE = '/';
    protected final char EXPONENTIATE = '^';

    protected final String wholeNumber = "Whole Number";
    protected final String decimal = "Decimal";
    protected final String fraction = "Fraction";

    /**
     * Instantiates the proper OperandConstructor strategy based on input, and stores current equation operand.
     *
     * @param operandType specifies whether operand is a whole number, decimal or fraction.
     * @param operator the char that determines which builder this director will use.
     */
    protected BedmasEquationMaker(char operator, String operandType) {
        this.currentOperator = operator;

        if (operandType.equals(wholeNumber)) {
            if (operator == ADD) {
                this.operandConstructor = new WholeNumAddOperandConstructor();
            } else if (operator == SUBTRACT) {
                this.operandConstructor = new WholeNumSubOperandConstructor();
            } else if (operator == MULTIPLY) {
                this.operandConstructor = new WholeNumAddOperandConstructor(); // same way of constructing operands
            } else if (operator == DIVIDE) {
                this.operandConstructor = new WholeNumDivideOperandConstructor();
            } else if (operator == EXPONENTIATE) {
                this.operandConstructor = new WholeNumAddOperandConstructor(); // same way of constructing operands
            }
        } else if (operandType.equals(fraction)) {
            if (operator == ADD) {
                this.operandConstructor = new FractionAddOperandConstructor();
            } else if (operator == SUBTRACT) {
                this.operandConstructor = new FractionSubOperandConstructor();
            } else if (operator == MULTIPLY) {
                this.operandConstructor = new FractionMultiplyOperandConstructor();
            } else if (operator == DIVIDE) {
                this.operandConstructor = new FractionDivideOperandConstructor();
            } else if (operator == EXPONENTIATE) {
                this.operandConstructor = new FractionExponentiateOperandConstructor();
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
        rand = new Randomizer();
    }

    /**
     * Sets the bedmasEquation's operator based on current operator.
     */
    protected void buildOperator() {
        switch (currentOperator) {
            case ADD: bedmasEquation.setOperator(new Add());
            case SUBTRACT: bedmasEquation.setOperator(new Subtract());
            case MULTIPLY: bedmasEquation.setOperator(new Multiply());
            case DIVIDE: bedmasEquation.setOperator(new Divide());
            case EXPONENTIATE: bedmasEquation.setOperator(new Exponentiate());
        }
    }

    /**
     * Builds the operands (first and second) for the bedmasEquation using operand constructor.
     *
     * RANDOM SEED (for fixing random number generation):
     *      First random operation uses the random seed. Succeeding operations increment the random seed by 5.
     *
     * @param equationDetails contains necessary parameters for equation generation.
     * @param seed random seed to fix random generation of operands
     */
    public void buildOperands(EquationDetails equationDetails, int seed){
        Value[] operands = this.operandConstructor.buildOperands(equationDetails, seed, rand);
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
