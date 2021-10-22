package EquationBuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-21
 */
public class BinaryExpressionTreeTest {
    BinaryExpressionTree bet;

    @Before
    public void init() {
        bet = new BinaryExpressionTree("+");
        Node n = bet.getRoot();
        n.setLeftNode(new Node("3"));
        Node r = new Node("/");
        r.setLeftNode(new Node("8"));
        r.setRightNode(new Node("4"));
        n.setRightNode(r);
    }

    @Test
    public void testSolveLongBET() {
        assertEquals(bet.solve(), 5);
    }
}