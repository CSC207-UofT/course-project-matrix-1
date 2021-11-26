package equation_entities;

import exceptions.NotImplementedException;


/**
 * Stores a decimal represented as a double.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-16
 */
public class Decimal extends Value {
    private final double decimalValue;

    /**
     * Constructs the decimal by storing the integer in this instance of Decimal.
     *
     * @param decimalValue a decimal number that represents the value to be stored in this instance of Decimal.
     */
    public Decimal(double decimalValue) {
        this.decimalValue = decimalValue;
    }

    /**
     * Returns the sum of this Decimal and the otherValue. The otherValue must be a Decimal.
     *
     * @param otherValue the other value to be added.
     * @return the sum as a decimal.
     */
    @Override
    public Value add(Value otherValue) {
        return new Decimal(this.decimalValue + ((Decimal) otherValue).getValue());
    }

    /**
     * Returns the difference of this Decimal and the otherValue. The otherValue must be a Decimal.
     *
     * @param otherValue the other value to be subtracted.
     * @return the difference as a decimal.
     */
    @Override
    public Value subtract(Value otherValue) {
        return new Decimal(this.decimalValue - ((Decimal) otherValue).getValue());
    }

    /**
     * Returns the quotient of this Decimal and the otherValue. The otherValue must be a Decimal. The Decimal
     * returned will be instantiated with an integer version of the quotient, regardless of whether it is truly an
     * integer.
     *
     * @param otherValue the other value to be divided.
     * @return the quotient as a decimal.
     */
    @Override
    public Value divide(Value otherValue) {
        return new Decimal(this.decimalValue / ((Decimal) otherValue).getValue());
    }

    /**
     * Returns the product of this Decimal and the otherValue. The otherValue must be a Decimal.
     *
     * @param otherValue the other value to be multiplied.
     * @return the product as a decimal.
     */
    @Override
    public Value multiply(Value otherValue) {
        return new Decimal(this.decimalValue * ((Decimal) otherValue).getValue());
    }

    /**
     * Return this value raised to the power of the otherValue.
     *
     * @param otherValue the power with which to raise the current value
     * @return the result of raising this value to the power of the otherValue
     */
    @Override
    public Value exponentiate(Value otherValue) {
        return new WholeNum((int) Math.pow(this.decimalValue, ((WholeNum) otherValue).getValue()));
    }

    /**
     * Decimal LCM is not implemented! Throw an exception if called.
     */
    @Override
    public Value lcm(Value otherValue) {
        throw new NotImplementedException();
    }

    /**
     * Decimal GCD is not implemented! Throw an exception if called.
     */
    @Override
    public Value gcd(Value otherValue) {
        throw new NotImplementedException();
    }

    /**
     * Returns the value stored in this Decimal, as an int.
     *
     * @return the instance of wholeNumber.
     */
    public double getValue() {
        return decimalValue;
    }

    /**
     * Creates a string representation of the decimal by converting the int stored in wholeNumber into a string.
     *
     * @return the string representation of the int wholeNumber stored in this string.
     */
    @Override
    public String toString() {
        return String.valueOf(decimalValue);
    }
}
