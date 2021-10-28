package equation_entities;

/**
 * A node that is used in the BinaryExpressionTree. Any leaves are values (such as integers, fractions, or decimals)
 * Any nodes that are not leaves are operators (+, -, *, /, ^). Every node contains an expression in the form of
 * leftNode.solve(), symbol, rightNode.solve().
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-21
 */
public class Node {
    private final Symbol symbol;
    private Node leftNode;
    private Node rightNode;
    private final String ADD = "+";
    private final String SUBTRACT = "-";
    private final String MULTIPLY = "*";
    private final String DIVIDE = "/";

    public Node(String value) {
        this.symbol = value;
        this.leftNode = this.rightNode = null;
    }

    /**
     * Determines if this node is a leaf. A leaf is a node where both the left and right node are null. Leaves only
     * contain Operator's as their symbol.
     *
     * @return true if this node is a leaf, false if otherwise.
     */
    public boolean isLeaf() {
        return leftNode == null && rightNode == null;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }

    /**
     * Uses the values stored in this node to solve the expression. If the node is a leaf, it returns the symbol
     * directly stored in the node. Otherwise, it uses the operator assigned to the instance of symbol to determine
     * which operation should be called on the left and right node. All expressions are calculated using the solution
     * of the left node and the solution of the right node from the left to right.
     *
     * @return The solution of the expression stored in the node.
     */
    public Value solve() {
        if (this.isLeaf()) {
            //Means it's a value
            return (Value) (this.symbol);
        } else {
            return ((Operator) symbol).solveBinaryExpression(leftNode.solve(), rightNode.solve());
        }
    }
}