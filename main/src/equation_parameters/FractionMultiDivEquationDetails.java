package equation_parameters;

public class FractionMultiDivEquationDetails extends EquationDetails {
    //The range that answer's denominator can appear as. Must be greater than 0.
    private int[] ansDenominatorRange;

    //The number of times a prime factor will be multiplied to the numerator and denominator. This can be cross
    // multiplied so, it doesn't change the actual answer, but ti does make the operands more complex.
    private int complexity;

    //The maximum value of the answer. Ex. 2 means that the answer could be 11/7, 1/2, 39/20.
    private int maxAnsValue;

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
}
