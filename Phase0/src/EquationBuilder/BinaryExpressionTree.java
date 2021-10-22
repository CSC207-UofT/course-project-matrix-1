package EquationBuilder;

public class BinaryExpressionTree {
    private final Node root;

    public BinaryExpressionTree(String operator) {
        this.root = new Node(operator);
    }

    public Node getRoot() {
        return root;
    }

    public int solve() {
        return root.solve();
    }

}
