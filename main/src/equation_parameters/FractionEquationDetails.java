package equation_parameters;

public final class FractionEquationDetails extends EquationDetails{
    public FractionEquationDetails(int[] denominatorRange, int maxDenominator, int maxValue) {
        this.denominatorRange = denominatorRange;
        this.maxDenominator = maxDenominator;
        this.maxValue = maxValue;
    }

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

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    private int[] denominatorRange;
    private int maxDenominator;
    private int maxValue;

}
