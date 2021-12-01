package equation_entities;

/**
 * Stores a fraction that has a whole number, numerator, and denominator each represented by an integer. Represented
 * visually as a mixed fraction.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-30
 */
public class MixedFraction extends Fraction{
    /**
     * Constructs a mixed fraction. If the numerator is negative, the entire fraction is assumed to be negative.
     *
     * @param numerator   an int that represents the top half of the fraction. Can be negative or positive.
     * @param denominator an int that represents the bottom half of the fraction. Cannot be 0, must be positive.
     */
    public MixedFraction(int numerator, int denominator) {
        super(numerator, denominator);
    }

    /**
     * Return a mixed latex string representation of the fraction.
     *
     * @return a mixed latex string representation of the fraction. Ex. 2\frac{1}{3} which looks like 2 1/3.
     */
    @Override
    public String toString() {
        int wholeNumber;
        boolean fractionIsNegative = isFractionNegative();
        StringBuilder latexString = new StringBuilder();
        if (fractionParts[0] != 0) {
            wholeNumber = fractionParts[0] / fractionParts[1];
            if (wholeNumber == 0){
                latexString.append("\\frac{").append(fractionParts[0]).append("}{").append(fractionParts[1]).append("}");
            } else {
                latexString.append(wholeNumber).append("\\frac{").append(fractionParts[0] % fractionParts[1]).append("}{").append(fractionParts[1]).append("}");
            }
            if (fractionIsNegative){
                latexString.insert(0, "-");
            }
        } else {
            latexString.append("0");
        }
        return String.valueOf(latexString);
    }
}
