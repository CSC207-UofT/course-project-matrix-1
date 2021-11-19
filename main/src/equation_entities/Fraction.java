package equation_entities;

/**
 * Stores a fraction that has a whole number, numerator, and denominator each represented by an integer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-6
 */
public class Fraction extends Value {
    // Represents the fraction as an improper fraction consisting of [numerator, denominator]. If the numerator is
    //negative, the enitre fraction is considered to be negative.
    private final int[] fractionParts = new int[2];


    /**
     * Constructs the fraction, adding the value associated with the wholeNumber to the numerator.
     *
     * @param numerator   represents the top half of the fraction. Must be positive.
     * @param denominator represents the bottom half of the fraction. Cannot be 0, must be positive.
     * @param negative    if true, the number is negative.
     * @param wholeNumber represents the whole number associated with the fraction.
     */
    public Fraction(int numerator, int denominator, boolean negative, int wholeNumber) {
        fractionParts[0] = wholeNumber * denominator + numerator;
        if (negative) {
            fractionParts[0] = -1 * fractionParts[0];
        }
        fractionParts[1] = denominator;
    }


    /**
     * Constructs the fraction. If the numerator is negative, the entire fraction is assumed to be negative.
     *
     * @param numerator   an int that represents the top half of the fraction. Can be negative or positive.
     * @param denominator an int that represents the bottom half of the fraction. Cannot be 0, must be positive.
     */
    public Fraction(int numerator, int denominator) {
        fractionParts[0] = numerator;
        fractionParts[1] = denominator;
    }

    /**
     * Adds the two improper fractions together.
     *
     * @param otherValue the other fraction to be added.
     * @return the reduced sum of the fractions.
     */
    @Override
    public Value add(Value otherValue) {
        int[] otherParts = ((Fraction) otherValue).getImproperFraction();
        return createReducedFraction(fractionParts[0] * otherParts[1] + fractionParts[1] * otherParts[0], fractionParts[1] * otherParts[1]);
    }

    /**
     * Subtracts the two improper fractions together.
     *
     * @param otherValue the other fraction to be subtracted.
     * @return the reduced difference of the fractions.
     */
    @Override
    public Value subtract(Value otherValue) {
        int[] otherParts = ((Fraction) otherValue).getImproperFraction();
        return createReducedFraction(fractionParts[0] * otherParts[1] - fractionParts[1] * otherParts[0], fractionParts[1] * otherParts[1]);
    }

    /**
     * Divides the two improper fractions together.
     *
     * @param otherValue the other fraction to be divided. This cannot be 0.
     * @return the reduced quotient of the fractions.
     */
    @Override
    public Value divide(Value otherValue) {
        int[] otherParts = ((Fraction) otherValue).getImproperFraction();
        return createReducedFraction(fractionParts[0] * otherParts[1], fractionParts[1] * otherParts[0]);
    }

    /**
     * Multiplies the two improper fractions together.
     *
     * @param otherValue the other fraction to be multiplied.
     * @return the reduced product of the fractions.
     */
    @Override
    public Value multiply(Value otherValue) {
        int[] otherParts = ((Fraction) otherValue).getImproperFraction();
        return createReducedFraction(fractionParts[0] * otherParts[0], fractionParts[1] * otherParts[1]);
    }
    /**
     * As this operation is not relevant to fractions, this method will not be available for fractions.
     *
     * @param otherValue the power with which to raise the current value
     * @return the result of raising this value to the power of the otherValue
     */
    @Override
    public Value exponentiate(Value otherValue){
        return null;
    }

    /**
     * Return this value raised to the power of the otherValue.
     *
     * @param otherValue the power with which to raise the current value
     * @return the result of raising this value to the power of the otherValue
     */
    @Override
    public Value exponentiate(Value otherValue) {
        // TODO: Implement this or throw exception for usage.
        return null;
    }

    /**
     * Return the least common multiple of this value and the other value.
     *
     * @param otherValue the other number.
     * @return the least common multiple of this and other value.
     */
    @Override
    public Value lcm(Value otherValue) {
        // TODO: Implement this or throw exception for usage.
        return null;
    }

    /**
     * Return the greatest common divisor of this value and the other value.
     *
     * @param otherValue the other number.
     * @return the greatest common divisor of this and other value.
     */
    @Override
    public Value gcd(Value otherValue) {
        // TODO: Implement this or throw exception for usage.
        return null;
    }

    /**
     * Uses a numerator and denominator to create an instance of a reduced Fraction.
     *
     * @param numerator   the unreduced numerator.
     * @param denominator the unreduced denominator. Cannot be 0.
     * @return the reduced Fraction.
     */
    public Fraction createReducedFraction(int numerator, int denominator) {
        Fraction newFraction = new Fraction(numerator, denominator);
        newFraction.reduce();
        return newFraction;
    }

    /**
     * Returns the fraction as a mixed fraction, where the numerator is smaller than the denominator.
     *
     * @return the list of fraction parts, as [numerator, denominator, wholeNumber, negative], where negative = -1
     * if the fraction is negative, and +1 if the fraction is positive.
     */
    public int[] getMixedFraction() {
        return null;
    }

    /**
     * Returns the fraction as an improper fraction, where the whole number is 0.
     *
     * @return the list of fraction parts, as [numerator, denominator]
     */
    public int[] getImproperFraction() {
        return fractionParts;
    }

    /**
     * Reduces the numerator and denominator to their smallest possible values.
     */
    public void reduce() {
        for (int i = 2; i < Math.min(fractionParts[0], fractionParts[1]) + 1; i++) {
            int limit = Math.min(fractionParts[0], fractionParts[1]) + 1;
            if (fractionParts[0] % i == 0 && fractionParts[1] % i == 0) {
                fractionParts[0] = fractionParts[0] / i;
                fractionParts[1] = fractionParts[1] / i;
                i = 1;
            }
        }
    }

    @Override
    public String toString() {
        return (fractionParts[0]+"/"+fractionParts[1]);
    }
}
