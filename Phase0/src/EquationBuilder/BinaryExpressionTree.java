package EquationBuilder;

public class BinaryExpressionTree {
    private final Node root;

    BinaryExpressionTree(String operator) {
        this.root = new Node(operator);
    }

    public Node getRoot() {
        return root;
    }

    public int solve() {
        return root.solve();
    }

    public static void main(String[] args) {
        BinaryExpressionTree bet = new BinaryExpressionTree("+");
        Node n = bet.getRoot();
        n.setLeftNode(new Node("3"));
        Node r = new Node("/");
        r.setLeftNode(new Node("8"));
        r.setRightNode(new Node ("4"));
        n.setRightNode(r);
        System.out.println(bet.solve());
    }
}
