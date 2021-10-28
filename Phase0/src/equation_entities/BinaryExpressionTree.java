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


    public BinaryExpressionTree(Symbol term) {
        this.root = new Node(term);
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Uses the root node to solve the value of the tree recursively
     *
     * @return The answer of the expression stored in the tree.
     */
    public Value solve() {
        return root.solve();
    }

}
