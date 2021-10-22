public class Node {
    private final String value;
    private Node leftNode;
    private Node rightNode;
    private final String ADD = "+";
    private final String SUBTRACT = "-";
    private final String MULTIPLY = "*";
    private final String DIVIDE = "/";
    Node(String value) {
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

    public Node getLeftNode(){
        return leftNode;
    }
    public Node getRightNode(){
        return rightNode;
    }
    public String getValue() {
        return value;
    }

    public int solve(){
        if (this.isLeaf()) {
            //Means it's a value
            return Integer.parseInt(value);
        }else{
            if (value.equals(ADD)) {
                return leftNode.solve() + rightNode.solve();
            }else if (value.equals(SUBTRACT)) {
                return leftNode.solve() - rightNode.solve();
            }else if (value.equals(DIVIDE)) {
                return leftNode.solve()/rightNode.solve();
            }else if (value.equals(MULTIPLY)){
                return leftNode.solve()*rightNode.solve();
            }else{
                //TODO: Change this exception
                throw new RuntimeException("There shouldn't be any other symbol here");
            }
        }
    }
}