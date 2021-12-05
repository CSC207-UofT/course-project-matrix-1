package equation_entities;

/**
 * Stores a fraction that has a whole number, numerator, and denominator each represented by an integer. Represented
 * visually as a mixed fraction.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-30
 */
public class MixedFraction extends Fraction {
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
     * Uses a numerator and denominator to create an instance of a reduced Fraction.
     *
     * @param numerator   the unreduced numerator.
     * @param denominator the unreduced denominator. Cannot be 0.
     * @return the reduced Fraction.
     */
    @Override
    public Fraction createReducedFraction(int numerator, int denominator) {
        Fraction newFraction = new MixedFraction(numerator, denominator);
        newFraction.reduce();
        return newFraction;
    }

    /**
     * Return a mixed latex string representation of the fraction.
     *
     * @return a mixed latex string representation of the fraction. Ex. 2\frac{1}{3} which looks like 2 1/3.
     */
    @Override
    public String toString() {
        if (fractionParts[0] == 0) {
            return "0";
        }
        StringBuilder latexString = new StringBuilder();

        //Find the whole number part (ex. 2), update the numerator, and correct the negative signs.
        boolean fractionIsNegative = isFractionNegative();
        int wholeNumber = fractionParts[0] / fractionParts[1];
        fractionParts[0] = fractionParts[0] % fractionParts[1];

        if (fractionParts[0] != 0) {
            if (wholeNumber == 0) {
                latexString.append("\\frac{").append(fractionParts[0]).append("}{").append(fractionParts[1]).append("}");
            } else {
                latexString.append(wholeNumber).append("\\frac{").append(fractionParts[0] % fractionParts[1]).append("}{").append(fractionParts[1]).append("}");
            }
        } else {
            latexString.append(wholeNumber);
        }
        if (fractionIsNegative) {
            latexString.insert(0, "-");
        }
        return String.valueOf(latexString);
    }
}
