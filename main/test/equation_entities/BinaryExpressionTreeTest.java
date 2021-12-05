package equation_entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryExpressionTreeTest {
    BinaryExpressionTree bet;

    @Test
    public void testSolveAddBET() {
        bet = new BinaryExpressionTree(new Add());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-3)));
        n.setRightNode(new Node(new WholeNum(209)));
        assertEquals(bet.solve().toString(), "206");
    }

    @Test
    public void testSolveSubBET() {
        bet = new BinaryExpressionTree(new Subtract());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-3)));
        n.setRightNode(new Node(new WholeNum(-20)));
        assertEquals(bet.solve().toString(), "17");
    }

    @Test
    public void testSolveMultiplyBET() {
        bet = new BinaryExpressionTree(new Multiply());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-3)));
        n.setRightNode(new Node(new WholeNum(-20)));
        assertEquals(bet.solve().toString(), "60");
    }

    @Test
    public void testSolveDivBET() {
        bet = new BinaryExpressionTree(new Divide());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-20)));
        n.setRightNode(new Node(new WholeNum(-4)));
        assertEquals(bet.solve().toString(), "5");
    }

    @Test
    public void testSolveLongBET() {
        bet = new BinaryExpressionTree(new Multiply());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(3)));
        Node r = new Node(new Divide());
        r.setLeftNode(new Node(new WholeNum(-8)));
        r.setRightNode(new Node(new WholeNum(-4)));
        n.setRightNode(r);
        assertEquals(bet.solve().toString(), "6");
    }

    @Test
    public void testAddToArrayList() {
        bet = new BinaryExpressionTree(new Add());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-3)));
        n.setRightNode(new Node(new WholeNum(-20)));
        List<String> expected = new ArrayList<>(List.of(new String[]{"-3", "+", "-20"}));
        assertEquals(bet.toStringArrayList(), expected);
    }

    @Test
    public void testSubToArrayList() {
        bet = new BinaryExpressionTree(new Subtract());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-3)));
        n.setRightNode(new Node(new WholeNum(-20)));
        List<String> expected = new ArrayList<>(List.of(new String[]{"-3", "-", "-20"}));
        assertEquals(bet.toStringArrayList(), expected);
    }

    @Test
    public void testMultiplyToArrayList() {
        bet = new BinaryExpressionTree(new Multiply());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-3)));
        n.setRightNode(new Node(new WholeNum(-20)));
        List<String> expected = new ArrayList<>(List.of(new String[]{"-3", "\\times", "-20"}));
        assertEquals(bet.toStringArrayList(), expected);
    }

    @Test
    public void testDivideToArrayList() {
        bet = new BinaryExpressionTree(new Divide());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(-3)));
        n.setRightNode(new Node(new WholeNum(-20)));
        List<String> expected = new ArrayList<>(List.of(new String[]{"-3", "\\div", "-20"}));
        assertEquals(bet.toStringArrayList(), expected);
    }
}