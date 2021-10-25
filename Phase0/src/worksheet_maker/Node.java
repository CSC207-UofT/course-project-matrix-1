package worksheet_maker;

public class Node {
    private final String value;
    private Node leftNode;
    private Node rightNode;
    private final String ADD = "+";
    private final String SUBTRACT = "-";
    private final String MULTIPLY = "*";
    private final String DIVIDE = "/";

    public Node(String value) {
        this.value = value;
        this.leftNode = this.rightNode = null;
    }

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

    public String getValue() {
        return value;
    }

    public int solve() {
        if (this.isLeaf()) {
            //Means it's a value
            return Integer.parseInt(value);
        } else {
            switch (value) {
                case ADD:
                    return leftNode.solve() + rightNode.solve();
                case SUBTRACT:
                    return leftNode.solve() - rightNode.solve();
                case DIVIDE:
                    return leftNode.solve() / rightNode.solve();
                case MULTIPLY:
                    return leftNode.solve() * rightNode.solve();
                default:
                    //TODO: Change this exception
                    throw new RuntimeException("There shouldn't be any other symbol here");
            }
        }
    }
}