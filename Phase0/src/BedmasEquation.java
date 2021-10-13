public class BedmasEquation implements Equation{
    private final String question;
    private String answer;

    public BedmasEquation(int firstNum, int secondNum, String operator) {
        this.question = firstNum + " " + operator + " " + secondNum;
    }
    @Override
    public void solve(){
        this.answer = "answer";
    }

    @Override
    public String[] getEquation(){
        return new String[]{this.question, this.answer};
    }



}
