/**
 * A subclass of Equation. Refers to an equation with any number of operands that use bedmas operators.
 * An example is 5 + 3 = 8. The question will be stored as a binary expression tree, while the answer will be a
 * string.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class BedmasEquation implements Equation {
    private final String question;
    private String answer;

    /**
     * Creates a bedmas equation with two numbers, one operator.
     *
     * @param firstNum  the first number in the equation
     * @param secondNum the second number in the equation
     * @param operator  the operator used to add the two numbers
     */
    //TODO: Update this to take in an expression tree
    public BedmasEquation(int firstNum, int secondNum, String operator) {
        this.question = firstNum + " " + operator + " " + secondNum;
    }

    /**
     * Uses the equation assigned in the constructor to calculate the answer and assigns it to
     * answer.
     */
    @Override
    public void solve() {
        this.answer = "answer";
    }

    /**
     * @return the equation, as an array with the equation and answer.
     */
    @Override
    public String[] getEquation() {
        return new String[]{this.question, this.answer};
    }



}
