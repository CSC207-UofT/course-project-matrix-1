package equation_builders;

import equation_entities.Fraction;
import equation_entities.Value;


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
     * @param operandsN the numerator operands as [numerator1, numerator2].
     * @param operandsD the denominator operands as [denominator1, denominator2].
     * @return the fractions as a list of [fraction1, fraction2].
     */
    public Value[] assignNumeratorDenominator(int[] operandsN, int[] operandsD) {
        return new Value[]{new Fraction(operandsN[0], operandsD[0]), new Fraction(operandsD[1], operandsN[1])};
    }
}
