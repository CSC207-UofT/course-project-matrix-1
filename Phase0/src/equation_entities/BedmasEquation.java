package equation_entities;

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

    /**
     * Sets the operator for the equation as the root of the binary expression tree
     *
     * @param operator A string of either +, -, *, / meaning add, subtract, multiply, and divide respectively.
     */
    public void setOperator(String operator) {
        question = new BinaryExpressionTree(operator);
    }

    /**
     * Sets the first operand to the left node as a string
     *
     * @param operand1 the second operand in the binary expression
     */
    public void setOperand1(int operand1) {
        question.getRoot().setLeftNode(new Node(Integer.toString(operand1)));
    }

    /**
     * Sets the second operand to the right node as a string
     *
     * @param operand2 the second operand in the binary expression
     */
    public void setOperand2(int operand2) {
        question.getRoot().setRightNode(new Node(Integer.toString(operand2)));
    }

    /**
     * @return the equation, as an array with the equation and answer.
     */
    @Override
    public String[] getEquation() {
        String q = question.getRoot().getLeftNode().getSymbol() + " " + question.getRoot().getSymbol() + " " + question.getRoot().getRightNode().getSymbol();
        String a = String.valueOf(answer);
        return new String[]{q, a};
    }

    /**
     * Solves the method using the binary expression tree's solve method.
     */
    @Override
    public void solve() {
        answer = question.solve();
    }
    @Override
    public String questionToString(){
        return this.question.toString();
    }
    @Override
    public String answerToString(){
        return String.valueOf(this.answer);
    }

    /**
     * Returns the operands and answers separately for testing purposes.
     *
     * @return the parts of the equation, as [operand1, operand2, answer]
     */
    public int[] getEquationParts() {
        return new int[]{Integer.parseInt(question.getRoot().getLeftNode().getSymbol()),
                Integer.parseInt(question.getRoot().getRightNode().getSymbol()), answer};
    }
}
