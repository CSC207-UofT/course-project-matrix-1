package equation_parameters;

public class FractionMultiDivEquationDetails extends EquationDetails{
    private int[] ansDenominatorRange;
    private int complexity;

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

}
