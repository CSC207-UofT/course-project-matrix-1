/**
 * A subclass of Equation. Refers to an equation with any number of operands that use bedmas operators.
 * An example is 5 + 3 = 8. The question will be stored as a binary expression tree, while the answer will be a
 * string.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class BedmasEquation implements Equation{
    private final String operator;
    private final int[] operands;
    private int answer;

    /**
     * Creates a bedmas equation with two numbers, one operator.
     *
     * @param firstNum  the first number in the equation
     * @param secondNum the second number in the equation
     * @param operator  the operator used to add the two numbers
     */
    public BedmasEquation(int firstNum, int secondNum, String operator) {
        this.operands = new int[] {firstNum, secondNum};
        this.operator = operator;
    }

    /**
     * Solve the equation. Uses the operator and operands to calculate answer.
     * Uses the equation assigned in the constructor to calculate the answer and assigns it to
     * answer.
     */
    @Override
    public void solve() throws InvalidInputException{
        String ADDITION = "+";
        if (operator.equals(ADDITION)){
            this.answer = operands[0] + operands[1];
        }else{
            throw new InvalidInputException();
        }
    }

    /**
     * @return the equation, as an array with the equation and answer.
     */
    @Override
    public String[] getEquation(){
        String questionString = operands[0] + " " + operator + " " + operands[1];
        String answerString = String.valueOf(this.answer);
        return new String[]{questionString, answerString};
    }
}
