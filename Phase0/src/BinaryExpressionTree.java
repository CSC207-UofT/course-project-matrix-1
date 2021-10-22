public class BinaryExpressionTree {
    Node root;
    private enum Operators {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }
    private class Node {
        private String value;
        private Node leftNode;
        private Node rightNode;

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
                if (value.equals(Operators.ADD.toString())) {
                    return leftNode.solve() + rightNode.solve();
                }else if (value.equals(Operators.SUBTRACT.toString())) {
                    return leftNode.solve() - rightNode.solve();
                }else if (value.equals(Operators.DIVIDE.toString())) {
                    return leftNode.solve()*rightNode.solve();
                }else{
                    return leftNode.solve()/rightNode.solve();
                }
            }
        }
    }

    BinaryExpressionTree(String operator) {
        this.root = new Node(operator);
    }

    public Node getRoot() {
        return root;
    }

    public int solve(){
        return root.solve();
    }
}
