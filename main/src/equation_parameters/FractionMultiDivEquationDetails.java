package equation_parameters;

public class FractionMultiDivEquationDetails extends EquationDetails{
    private int[] ansDenominatorRange;
    private int complexity;
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
