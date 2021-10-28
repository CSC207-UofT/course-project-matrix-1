package equation_entities;

/**
 * A BinaryExpressionTree that holds a single root node. This root node can be manipulated to access the entire tree
 * recursively.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-21
 */
public class BinaryExpressionTree {
    private final Node root;

    public BinaryExpressionTree(String operator) {
        this.root = new Node(operator);
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Uses the root node to solve the value of the tree recursively
     *
     * @return The answer of the expression stored in the tree.
     */
    public int solve() {
        return root.solve();
    }

}
