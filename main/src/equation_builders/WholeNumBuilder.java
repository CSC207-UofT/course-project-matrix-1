package equation_builders;

import equation_entities.BedmasEquation;
import equation_entities.WholeNum;
import utilities.Randomizer;

import java.util.List;
import java.util.Random;

/**
 * An abstract builder for all whole number BEDMAS equation builders.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
abstract class WholeNumBuilder {
    protected BedmasEquation bedmasEquation;
    protected Randomizer rand;


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
     * Builds the bedmasEquation's operator.
     */
    protected abstract void buildOperator();

    /**
     * Builds the operands (first and second) for the bedmasEquation.
     *
     * Overridden in some child classes.
     *
     * RANDOM SEED (for fixing random number generation):
     *      First random operation uses the random seed. Succeeding operations increment the random seed by 5.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     * @param seed          random seed to fix random generation of operands
     */
    protected void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed, int seed){
        int operand1 = rand.randomize(operandRange1, seed);
        int operand2 = rand.randomize(operandRange2, seed + 5);
        if (negAllowed) {
            operand1 = rand.makeNegativeRandom(operand1, seed + 10);
            operand2 = rand.makeNegativeRandom(operand2, seed + 15);
        }
        bedmasEquation.setOperand1(new WholeNum(operand1));
        bedmasEquation.setOperand2(new WholeNum(operand2));
    }

    /**
     * Builds the bedmasEquation's answer.
     */
    protected void buildAnswer() {
        bedmasEquation.solve();
    }
}
