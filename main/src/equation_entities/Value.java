package equation_entities;

/**
 * Stores an abstract value for a number that can appear in various forms (whole number, decimals, or fraction).
 * Standard BEDMAS operations can be applied to these values.
 *
 * @author Will Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-10-28
 */
public abstract class Value implements Symbol {
    /**
     * Returns the sum of this value and the otherValue.
     *
     * @param otherValue the other value to be added.
     * @return the sum of the values, as this + other.
     */
    public abstract Value add(Value otherValue);

    /**
     * Returns the difference of this value and the otherValue.
     *
     * @param otherValue the other value to be subtracted.
     * @return the difference of the values, as this - other.
     */
    public abstract Value subtract(Value otherValue);

    /**
     * Returns the quotient of this value and the otherValue.
     *
     * @param otherValue the other value to be divided.
     * @return the quotient of the values, as this / other.
     */
    public abstract Value divide(Value otherValue);

    /**
     * Returns the product of this value and the otherValue.
     *
     * @param otherValue the other value to be multiplied.
     * @return the product of the values, as this * other.
     */
    public abstract Value multiply(Value otherValue);


    /**
     * Return this value raised to the power of the otherValue.
     *
     * @param otherValue the power with which to raise the current value
     * @return the result of raising this value to the power of the otherValue
     */
    public abstract Value exponentiate(Value otherValue);


    /**
     * Return the least common multiple of this value and the other value.
     *
     * @param otherValue the other number.
     * @return the least common multiple of this and other value.
     */
    public abstract Value lcm(Value otherValue);


    /**
     * Return the greatest common divisor of this value and the other value.
     *
     * @param otherValue the other number.
     * @return the greatest common divisor of this and other value.
     */
    public abstract Value gcd(Value otherValue);
}
