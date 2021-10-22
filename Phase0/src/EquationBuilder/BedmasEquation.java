package EquationBuilder;

/**
 * A subclass of EquationBuilder.Equation. Refers to an equation with any number of operands that use bedmas operators.
 * An example is 5 + 3 = 8. The question will be stored as a binary expression tree, while the answer will be a
 * string.
 *
 * @author Sean Jeong
 * @version 2.0
 * @since 2021-10-12
 */
public class BedmasEquation implements Equation {
    private BinaryExpressionTree question;
    private int answer;

    public void setOperator(String operator){
        question = new BinaryExpressionTree(operator);
    }
    public void setAnswer(int answer){
        this.answer = answer;
    }
    public void setOperand1(int operand1){
        this.question.getRoot().setLeftNode(new Node (Integer.toString(operand1)));
    }
    public void setOperand2(int operand2){
        this.question.getRoot().setRightNode(new Node (Integer.toString(operand2)));
    }


//    /**
//     * Creates a bedmas equation with two numbers, one operator.
//     *
//     * @param firstNum  the first number in the equation
//     * @param secondNum the second number in the equation
//     * @param operator  the operator used to add the two numbers
//     */
//    public EquationBuilder.BedmasEquation(int firstNum, int secondNum, String operator) {
//        this.operands = new int[]{firstNum, secondNum};
//        this.operator = operator;
//    }

    /**
     * @return the equation, as an array with the equation and answer.
     */
    @Override
    public String[] getEquation() {
        String q = question.getRoot().getLeftNode().getValue() + " " + question.getRoot().getValue() + " " + question.getRoot().getRightNode().getValue();
        String a = String.valueOf(this.answer);
        return new String[] {q, a};
    }
    @Override
    public void solve(){
        this.answer = question.solve();
    }

}
