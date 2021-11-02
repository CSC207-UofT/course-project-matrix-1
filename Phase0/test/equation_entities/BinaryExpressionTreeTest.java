package equation_entities;

import equation_entities.*;
import org.junit.Before;
import org.junit.Test;

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
    public void testSolveMultBET() {
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
}