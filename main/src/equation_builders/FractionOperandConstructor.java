package equation_builders;

import equation_entities.ImproperFraction;
import equation_entities.MixedFraction;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import exceptions.NotImplementedException;
import utilities.Randomizer;

import static constants.FractionFormats.IMPROPER;
import static constants.FractionFormats.MIXED;

public abstract class FractionOperandConstructor implements OperandConstructorInterface {
    protected Randomizer randomizer;

    public abstract Value[] buildOperands(EquationDetails fractionEquationDetails, Randomizer randomizer);

    /**
     * Create a different type of fraction depending on the fraction format.
     * @param operandsN the numerator operands as [numerator1, numerator2].
     * @param operandsD the denominator operands as [denominator1, denominator2].
     * @param fractionFormat the fraction format for the operand. Mixed or improper (note: they only differ in how they
     *                       are represented, they have the exact same functionality otherwise)
     * @return array of first operand and second operand values as Fractions.
     */
    protected Value[] createFractions(int[] operandsN, int[] operandsD, String fractionFormat) {
        if (fractionFormat.equals(IMPROPER)){
            return new Value[]{new ImproperFraction(operandsN[0], operandsD[0]), new ImproperFraction(operandsN[1], operandsD[1])};
        } else if (fractionFormat.equals(MIXED)){
            return new Value[]{new MixedFraction(operandsN[0], operandsD[0]), new MixedFraction(operandsN[1], operandsD[1])};
        } else{
            throw new NotImplementedException();
        }
    }

    /**
     * If negative is allowed, the operands have a 50% chance of becoming negative.
     *
     * @param isNegAllowed whether negatives are allowed for this equation.
     * @param operandsN    the numerator of the operands as a list of 2 ints.
     */
    protected void makeOperandsNegative(boolean isNegAllowed, int[] operandsN) {
        if (isNegAllowed) {
            operandsN[0] = randomizer.makeNegativeRandom(operandsN[0]);
            operandsN[1] = randomizer.makeNegativeRandom(operandsN[1]);
        }
    }
}
