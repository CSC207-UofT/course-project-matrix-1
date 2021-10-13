/**
 * A Bedmas equation interface class. This includes any BEDMAS types of problems, and will allow for fractions as well as integers (for now just integers).
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class BedmasEquation implements Equation{
    private final String question;
    private String answer;

    public BedmasEquation(int firstNum, int secondNum, String operator) {
        this.question = firstNum + " " + operator + " " + secondNum;
    }
    /**
     * Solve the equation. It takes the question String and calculates an answer String and saves it under answer.
     */
    @Override
    public void solve(){
        this.answer = "answer";
    }

    //TODO: finish comments
    /**
     * Create a List
     * @return a List of String representation of the question and answer in this BedmasEquation.
     */
    @Override
    public String[] getEquation(){
        return new String[]{this.question, this.answer};
    }



}
