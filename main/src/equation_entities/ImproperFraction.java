package equation_entities;

/**
 * Stores a fraction that has a whole number, numerator, and denominator each represented by an integer. Represented
 * visually as an improper fraction.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-30
 */
public class ImproperFraction extends Fraction {
    /**
     * Constructs an improper fraction. If the numerator is negative, the entire fraction is assumed to be negative.
     *
     * @param numerator   an int that represents the top half of the fraction. Can be negative or positive.
     * @param denominator an int that represents the bottom half of the fraction. Cannot be 0, must be positive.
     */
    public ImproperFraction(int numerator, int denominator) {
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
        Fraction newFraction = new ImproperFraction(numerator, denominator);
        newFraction.reduce();
        return newFraction;
    }

    /**
     * Return an improper latex string representation of the fraction.
     *
     * @return an improper latex string representation of the fraction. Ex. frac{7}{3} which looks like 7/3.
     */
    @Override
    public String toString() {
        boolean fractionIsNegative = isFractionNegative();
        StringBuilder latexString = new StringBuilder();
        if (fractionParts[0] != 0) {
            latexString.append("\\frac{").append(fractionParts[0]).append("}{").append(fractionParts[1]).append("}");
            if (fractionIsNegative) {
                latexString.insert(0, "-");
            }
        } else {
            latexString.append("0");
        }
        return String.valueOf(latexString);
    }
}
