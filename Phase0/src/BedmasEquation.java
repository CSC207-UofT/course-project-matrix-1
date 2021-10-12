public class BedmasEquation implements Equation{
    private String question;
    private String answer;
    public BedmasEquation(int firstNum, int secondNum, String operator){
        this.question = (String) firstNum + operator + (String) secondNum
    }
    @Override
    public void solve(){

    }
    @Override
    public String[] getEquation(Equation eq){

    }



}
