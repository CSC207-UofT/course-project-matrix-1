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

    /**
     * Constructs a binary expression tree with a single node. This node is the root and contains the term assigned to
     * this tree.
     *
     * @param term the symbol that will be stored in the root node of this binary expression tree.
     */
    public BinaryExpressionTree(Symbol term) {
        this.root = new Node(term);
    }

    /**
     * Returns the root node.
     *
     * @return the root of the tree. This node can be used to access the other elements in the tree.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Uses the root node to solve the value of the tree recursively.
     *
     * @return the answer of the expression stored in the tree.
     */
    public Value solve() {
        return root.solve();
    }


    /**
     * Uses the root node to convert the values of the tree into a list of strings recursively.
     *
     * @return the string list of equations
     */
    public List<String> toStringArrayList() {
        return root.toArrayList();
    }
}
