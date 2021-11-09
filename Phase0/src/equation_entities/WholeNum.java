package equation_entities;

/**
 * Stores a whole number which is represented by a single integer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class WholeNum extends Value {
    private final int wholeNumber;

    /**
     * Constructs the whole number by storing the integer in this instance of WholeNum.
     *
     * @param wholeNumber an int that represents the value to be stored in this instance of WholeNum.
     */
    public WholeNum(int wholeNumber) {
        this.wholeNumber = wholeNumber;
    }

    /**
     * Returns the sum of this WholeNum and the otherValue. The otherValue must be a WholeNum.
     *
     * @param otherValue the other value to be added.
     * @return the sum as a whole number.
     */
    @Override
    public Value add(Value otherValue) {
        return new WholeNum(this.wholeNumber +
                ((WholeNum) otherValue).getValue());
    }

    /**
     * Returns the difference of this WholeNum and the otherValue. The otherValue must be a WholeNum.
     *
     * @param otherValue the other value to be subtracted.
     * @return the difference as a whole number.
     */
    @Override
    public Value subtract(Value otherValue) {
        return new WholeNum(this.wholeNumber - ((WholeNum) otherValue).getValue());
    }

    /**
     * Returns the quotient of this WholeNum and the otherValue. The otherValue must be a WholeNum. The WholeNum
     * returned will be instantiated with an integer version of the quotient, regardless of whether it is truly an
     * integer.
     *
     * @param otherValue the other value to be divided.
     * @return the quotient as a whole number.
     */
    @Override
    public Value divide(Value otherValue) {
        return new WholeNum(this.wholeNumber / ((WholeNum) otherValue).getValue());
    }

    /**
     * Returns the product of this WholeNum and the otherValue. The otherValue must be a WholeNum.
     *
     * @param otherValue the other value to be multiplied.
     * @return the product as a whole number.
     */
    @Override
    public Value multiply(Value otherValue) {
        return new WholeNum(this.wholeNumber * ((WholeNum) otherValue).getValue());
    }



    /**
     * Returns the value stored in this WholeNum, as an int.
     *
     * @return the instance of wholeNumber.
     */
    public int getValue() {
        return wholeNumber;
    }

    /**
     * Creates a string representation of the whole number by converting the int stored in wholeNumber into a string.
     *
     * @return the string representation of the int wholeNumber stored in this string.
     */
    @Override
    public String toString() {
        return String.valueOf(wholeNumber);
    }
}
