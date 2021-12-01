package equation_builders;

import equation_entities.ImproperFraction;
import equation_entities.MixedFraction;
import equation_entities.Value;
import exceptions.NotImplementedException;

import static constants.FractionFormats.IMPROPER;
import static constants.FractionFormats.MIXED;


/**
 * Handles the construction of operands for the fraction divide equations.
 *
 * @author Sean Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionDivideOperands extends FractionMultDivOperands implements OperandConstructorInterface {
    /**
     * Assigns the numerator and denominator to the fractions, where the second fraction has the numerator and
     * denominator flipped.
     *
     * @param operandsN      the numerator operands as [numerator1, numerator2].
     * @param operandsD      the denominator operands as [denominator1, denominator2].
     * @param fractionFormat the fraction format for the operand. Mixed or improper (note: they only differ in how they
     *                       are represented, they have the exact same functionality otherwise)
     * @return the fractions as a list of [fraction1, fraction2].
     */
    public Value[] createFractions(int[] operandsN, int[] operandsD, String fractionFormat) {
        if (fractionFormat.equals(IMPROPER)){
            return new Value[]{new ImproperFraction(operandsN[0], operandsD[0]), new ImproperFraction(operandsD[1], operandsN[1])};
        } else if (fractionFormat.equals(MIXED)){
            return new Value[]{new MixedFraction(operandsN[0], operandsD[0]), new MixedFraction(operandsD[1], operandsN[1])};
        } else{
            throw new NotImplementedException();
        }
    }
}
