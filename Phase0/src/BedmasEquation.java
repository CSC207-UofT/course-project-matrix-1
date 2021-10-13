/**
 * A Bedmas equation interface class. This includes any BEDMAS types of problems, and will allow for fractions as well as integers (for now just integers).
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class BedmasEquation implements Equation{
    private final String operator;
    private final int[] operands;
    private int answer;

    public BedmasEquation(int firstNum, int secondNum, String operator) {
        this.operands = new int[] {firstNum, secondNum};
        this.operator = operator;
    }
    /**
     * Solve the equation. Uses the operator and operands to calculate answer.
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

    //TODO: finish comments
    /**
     * Create a List
     * @return a List of String representation of the question and answer in this BedmasEquation.
     */
    @Override
    public String[] getEquation(){
        String questionString = operands[0] + " " + operator + " " + operands[1];
        String answerString = String.valueOf(this.answer);
        return new String[]{questionString, answerString};
    }



}
