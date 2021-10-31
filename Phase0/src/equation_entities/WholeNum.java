package equation_entities;

/**
 * A whole number which is represented by a single integer.
 */
public class WholeNum extends Value {
    private final int wholeNumber;

    public WholeNum(int wholeNumber) {
        this.wholeNumber = wholeNumber;
    }

    /**
     * Returns the sum of this WholeNum and the otherValue. The otherValue must be a WholeNum.
     *
     * @param otherValue the other value to be added
     * @return the sum as a whole number
     */
    @Override
    public Value add(Value otherValue) {
        return new WholeNum(this.wholeNumber + ((WholeNum) otherValue).getValue());
    }

    /**
     * Returns the difference of this WholeNum and the otherValue. The otherValue must be a WholeNum.
     *
     * @param otherValue the other value to be subtracted
     * @return the difference as a whole number
     */
    @Override
    public Value subtract(Value otherValue) {
        return new WholeNum(this.wholeNumber - ((WholeNum) otherValue).getValue());
    }

    /**
     * Returns the quotient of this WholeNum and the otherValue. The otherValue must be a WholeNum. The WholeNum
     * returned will be instantiated with an integer version of the quotient, regardless of whether it is truly an integer.
     *
     * @param otherValue the other value to be divided
     * @return the quotient as a whole number
     */
    @Override
    public Value divide(Value otherValue) {
        return new WholeNum(this.wholeNumber / ((WholeNum) otherValue).getValue());
    }

    /**
     * Returns the product of this WholeNum and the otherValue. The otherValue must be a WholeNum.
     *
     * @param otherValue the other value to be multiplied
     * @return the product as a whole number
     */
    @Override
    public Value multiply(Value otherValue) {
        return new WholeNum(this.wholeNumber * ((WholeNum) otherValue).getValue());
    }

    /**
     * Returns the exponentiation of this WholeNum and the otherValue. The otherValue must be a WholeNum.
     *
     * @param otherValue the other value to be exponentiated
     * @return the exponentiation as a whole number
     */
    @Override
    public Value exponentiate(Value otherValue) {
        return new WholeNum((int) Math.pow (this.wholeNumber, ((WholeNum) otherValue).getValue()));
    }

    /**
     * Returns the value stored in this WholeNum, as an int
     *
     * @return the instance of wholeNumber.
     */
    public int getValue() {
        return wholeNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(wholeNumber);
    }
}
