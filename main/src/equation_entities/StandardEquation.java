package equation_entities;

import java.util.HashMap;
import java.util.Map;

import static constants.EquationParts.*;

/**
 * Refers to an equation with any number of operands that use standard operators (BEDMAS + LCM + GCF). An example is
 * 5 + 3 = 8. The question will be stored as a binary expression tree, while the answer will be a string.
 *
 * @author Sean Jeong
 * @version 2.0
 * @since 2021-10-12
 */
public class StandardEquation implements Equation {
    private BinaryExpressionTree question;
    private Value answer;

    /**
     * Sets the operator for the equation as the root of the binary expression tree.
     *
     * @param operator A string of either +, -, *, / meaning add, subtract, multiply, and divide respectively.
     */
    public void setOperator(Operator operator) {
        question = new BinaryExpressionTree(operator);
    }

    /**
     * Sets the first operand to the left node as a string.
     *
     * @param operand1 the second operand in the binary expression.
     */
    public void setOperand1(Value operand1) {
        question.getRoot().setLeftNode(new Node(operand1));
    }

    /**
     * Sets the second operand to the right node as a string.
     *
     * @param operand2 the second operand in the binary expression.
     */
    public void setOperand2(Value operand2) {
        question.getRoot().setRightNode(new Node(operand2));
    }

    /**
     * Returns a Hashmap of String representation of an Equation. Key is operator, operand1, operand2, and answer.
     * Values are a String representation of each of those for a given equation.
     *
     * @return Hashmap of String representation of a Worksheet.
     * Ex. {"operator"="\div",operand1="10", "operand2"="5", "answer"="2" }
     */
    public Map<String, String> equationToHashMap() {
        Map<String, String> equationHashMap = new HashMap<>();
        equationHashMap.put(OPERATOR, question.getRoot().getSymbol().toString());
        equationHashMap.put(OPERAND1, question.getRoot().getLeftNode().getSymbol().toString());
        equationHashMap.put(OPERAND2, question.getRoot().getRightNode().getSymbol().toString());
        equationHashMap.put(ANSWER, answer.toString());
        return equationHashMap;
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
