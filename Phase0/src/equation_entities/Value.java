package equation_entities;

/**
 * A value such as integers, decimals, or fractions.
 *
 * @author Will Jeong
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
     * Returns the exponentiation of this value and the otherValue.
     *
     * @param otherValue the other value to be exponentiated.
     * @return the exponentiation of the values, as this ^ other.
     */
    public abstract Value exponentiate(Value otherValue);
}
