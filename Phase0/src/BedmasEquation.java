public class BedmasEquation implements Equation{
    private String question;
    private String answer;
    public BedmasEquation(int firstNum, int secondNum, String operator){
        this.question = firstNum + operator + secondNum;
    }
    @Override
    public void solve(){
        this.answer = "answer";
    }
    @Override
    public String[] getEquation(){
        String[] returnableEquation = {this.question, this.answer};
        return returnableEquation;
    }



}
