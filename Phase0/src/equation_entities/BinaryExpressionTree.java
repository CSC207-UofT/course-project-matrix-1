package equation_entities;

import java.util.List;

/**
 * Stores a single root Node. This root node can be manipulated to access a binary tree recursively.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-21
 */
public class BinaryExpressionTree {
    private final Node root;


    public BinaryExpressionTree(Symbol term) {
        this.root = new Node(term);
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Uses the root node to solve the value of the tree recursively.
     *
     * @return The answer of the expression stored in the tree.
     */
    public Value solve() {
        return root.solve();
    }


    /**
     * Uses the root node to convert the values of the tree into a List of Strings recursively.
     *
     * @return  the string list of equations
     */
    public List<String> toStringArrayList() {
        return root.toArrayList();
    }
}
