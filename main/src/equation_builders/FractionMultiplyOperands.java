package equation_builders;

import equation_entities.Fraction;
import equation_entities.Value;

/**
 * Directs the construction of fraction multiply equations, starting from the operator, then the operands,
 * and finally the answer.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionMultiplyOperands extends FractionMultDivOperands implements OperandConstructorInterface {
    /**
     * Assigns the numerator and denominator to the fractions normally.
     *
     * @param operandsN the numerator operands as [numerator1, numerator2].
     * @param operandsD the denominator operands as [denominator1, denominator2].
     * @return the fractions as a list of [fraction1, fraction2].
     */
    public Value[] assignNumeratorDenominator(int[] operandsN, int[] operandsD) {
        return new Value[]{new Fraction(operandsN[0], operandsD[0]), new Fraction(operandsN[1], operandsD[1])};
    }
}
