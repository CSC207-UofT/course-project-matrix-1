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
        n.setRightNode(new Node("10"));
        System.out.println(bet.solve());
    }
}
