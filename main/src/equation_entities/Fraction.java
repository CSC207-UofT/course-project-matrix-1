package equation_entities;

import exceptions.IllegalOperatorForOperandTypeException;
import exceptions.NotImplementedException;

/**
 * Stores a fraction that has a whole number, numerator, and denominator each represented by an integer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-6
 */
public class Fraction extends Value {
    // Represents the fraction as an improper fraction consisting of [numerator, denominator]. If the numerator is
    //negative, the entire fraction is considered to be negative.
    protected final int[] fractionParts = new int[2];

    /**
     * Constructs the fraction. If the numerator or denominator is negative (cannot both be negative), the entire
     * fraction is assumed to be negative.
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
        int[] otherParts = ((Fraction) otherValue).getFraction();
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
        int[] otherParts = ((Fraction) otherValue).getFraction();
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
        int[] otherParts = ((Fraction) otherValue).getFraction();
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
        int[] otherParts = ((Fraction) otherValue).getFraction();
        return createReducedFraction(fractionParts[0] * otherParts[0], fractionParts[1] * otherParts[1]);
    }

    /**
     * Fraction exponentiate is not implemented since it's not a good question!
     */
    @Override
    public Value exponentiate(Value otherValue) {
        throw new IllegalOperatorForOperandTypeException();
    }

    /**
     * Fraction LCM is not implemented since it's not a good question!
     */
    @Override
    public Value lcm(Value otherValue) {
        throw new IllegalOperatorForOperandTypeException();
    }

    /**
     * Fraction GCD is not implemented since it's not a good question!
     */
    @Override
    public Value gcd(Value otherValue) {
        throw new IllegalOperatorForOperandTypeException();
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
    public int[] getFraction() {
        return fractionParts;
    }

    /**
     * Reduces the numerator and denominator to their smallest possible values.
     */
    public void reduce() {
        for (int i = 2; i < Math.min(Math.abs(fractionParts[0]), fractionParts[1]) + 1; i++) {
            int limit = Math.min(Math.abs(fractionParts[0]), fractionParts[1]) + 1;
            if (fractionParts[0] % i == 0 && fractionParts[1] % i == 0) {
                fractionParts[0] = fractionParts[0] / i;
                fractionParts[1] = fractionParts[1] / i;
                i = 1;
            }
        }
    }

    /**
     * Modify a fraction so that the numerator and denominator don't contain negatives. If they do, remove the negative
     * and return True.
     *
     * @return if the fraction contains any negative values.
     */
    protected boolean isFractionNegative() {
        boolean fractionIsNegative = false;
        if (fractionParts[0] < 0){
            fractionIsNegative = true;
            fractionParts[0] = -fractionParts[0];
        } else if (fractionParts[1] < 0){
            fractionIsNegative = true;
            fractionParts[1] = -fractionParts[1];
        }
        return fractionIsNegative;
    }


}
