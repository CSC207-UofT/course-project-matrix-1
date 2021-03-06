package equation_parameters;

/**
 * Holds equation details specific to fractional multiplication and division equations. This includes the range of the
 * answer's denominator, the complexity of the question, the maximum value of the answer, and the format of the
 * fraction.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-12-2
 */
public class FractionMultiDivEquationDetails extends EquationDetails {
    //The range that answer's denominator can appear as. Must be greater than 0.
    private int[] ansDenominatorRange;

    //The number of times a prime factor will be multiplied to the numerator and denominator. This can be cross
    // multiplied so, it doesn't change the actual answer, but it does make the operands more complex.
    private int complexity;

    //The maximum value of the answer. Ex. 2 means that the answer could be 11/7, 1/2, 39/20.
    private int maxAnsValue;

    // The format of a fraction. Either an improper or proper fraction.
    private String fractionFormat;

    public int[] getAnsDenominatorRange() {
        return ansDenominatorRange;
    }

    public void setAnsDenominatorRange(int[] ansDenominatorRange) {
        this.ansDenominatorRange = ansDenominatorRange;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getMaxAnsValue() {
        return maxAnsValue;
    }

    public void setMaxAnsValue(int maxAnsValue) {
        this.maxAnsValue = maxAnsValue;
    }

    public String getFractionFormat() {
        return fractionFormat;
    }

    public void setFractionFormat(String fractionFormat) {
        this.fractionFormat = fractionFormat;
    }
}
