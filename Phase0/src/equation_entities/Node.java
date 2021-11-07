package equation_entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores a node which holds a left and right leaf. Any leaves are values (such as integers, fractions, or decimals)
 * Any nodes that are not leaves are operators (+, -, *, /, ^). All nodes with operators must contain a left leaf and
 * right leaf. Every node contains an expression in the form of leftNode.solve(), symbol, rightNode.solve().
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-21
 */
public class Node {
    private final Symbol symbol;
    private Node leftNode;
    private Node rightNode;

    /**
     * Instantiates the left node and right node as null, while assigning the symbol provided in the constructor to this
     * node's instance of symbol.
     *
     * @param symbol The symbol that this node directly contains.
     */
    public Node(Symbol symbol) {
        this.symbol = symbol;
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

    /**
     * Sets the left Node.
     *
     * @param leftNode the left node, representing the left operand.
     */
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Sets the right Node.
     *
     * @param rightNode the right node, representing the right operand.
     */
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * Returns the left Node.
     *
     * @return the left node, representing the left operand.
     */
    public Node getLeftNode() {
        return leftNode;
    }

    /**
     * Returns the right Node.
     *
     * @return the right node, representing the left operand.
     */
    public Node getRightNode() {
        return rightNode;
    }

    /**
     * Returns the symbol that is stored at this equation. If it is an Operator, this node has a left and right child.
     * If it is a Value, this node is a leaf.
     *
     * @return the symbol stored in this Node.
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Returns the string representation of the symbol, ignoring the children.
     *
     * @return the string representation of the symbol.
     */
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

    /**
     * Recursively adds all elements in the tree to the array list in the correct order.
     *
     * @return the string list of equations.
     */
    public List<String> toArrayList() {
        List<String> equationList = new ArrayList<>();
        if (this.isLeaf()) {
            equationList.add(this.getSymbol().toString());
        } else {
            equationList.addAll(this.getLeftNode().toArrayList());
            equationList.add(this.getSymbol().toString());
            equationList.addAll(this.getRightNode().toArrayList());
        }
        return equationList;
    }
}