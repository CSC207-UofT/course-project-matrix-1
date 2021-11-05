package equation_entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Refers to an equation with any number of operands that use BEDMAS operators. An example is 5 + 3 = 8. The question
 * will be stored as a binary expression tree, while the answer will be a string.
 *
 * @author Sean Jeong
 * @version 2.0
 * @since 2021-10-12
 */
public class BedmasEquation implements Equation {
    private BinaryExpressionTree question;
    private Value answer;

    /**
     * Sets the operator for the equation as the root of the binary expression tree
     *
     * @param operator A string of either +, -, *, / meaning add, subtract, multiply, and divide respectively.
     */
    public void setOperator(Operator operator) {
        question = new BinaryExpressionTree(operator);
    }

    /**
     * Sets the first operand to the left node as a string
     *
     * @param operand1 the second operand in the binary expression
     */
    public void setOperand1(Value operand1) {
        question.getRoot().setLeftNode(new Node(operand1));
    }

    /**
     * Sets the second operand to the right node as a string
     *
     * @param operand2 the second operand in the binary expression
     */
    public void setOperand2(Value operand2) {
        question.getRoot().setRightNode(new Node(operand2));
    }

    /**
     * Returns the array representation of the equation.
     *
     * @return a List representation of the equation, where every symbol is a separate item in the array.
     */
    @Override
    public List<String> getEquation() {
        List<String> equationList = new ArrayList<>(question.toStringArrayList());
        equationList.add("=");
        equationList.add(answer.toString());
        return equationList;
    }

    /**
     * Returns the parts of the equation as a list of symbols. Used for testing purposes.
     *
     * @return the equation parts, as an array where the operands, operator, and answer are seperated.
     */
    public Symbol[] getEquationParts() {
        return new Symbol[]{question.getRoot().getLeftNode().getSymbol(), question.getRoot().getSymbol(),
                question.getRoot().getRightNode().getSymbol(), answer};
    }


    /**
     * Solves the method using the binary expression tree's solve method.
     */
    @Override
    public void solve() {
        answer = question.solve();
    }

}
