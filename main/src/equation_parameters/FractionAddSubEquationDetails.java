package equation_parameters;

public class FractionAddSubEquationDetails extends FractionEquationDetails {
    private int[] denominatorRange;
    private int maxDenominator;

    public int[] getDenominatorRange() {
        return denominatorRange;
    }

    public void setDenominatorRange(int[] denominatorRange) {
        this.denominatorRange = denominatorRange;
    }

    public int getMaxDenominator() {
        return maxDenominator;
    }

    public void setMaxDenominator(int maxDenominator) {
        this.maxDenominator = maxDenominator;
    }

}
