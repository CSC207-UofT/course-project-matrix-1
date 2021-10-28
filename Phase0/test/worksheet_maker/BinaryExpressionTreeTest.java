package worksheet_maker;

import equation_entities.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-21
 */
public class BinaryExpressionTreeTest {
    BinaryExpressionTree bet;

    @Before
    public void init() {
        bet = new BinaryExpressionTree(new Add());
        Node n = bet.getRoot();
        n.setLeftNode(new Node(new WholeNum(3)));
        Node r = new Node(new Divide());
        r.setLeftNode(new Node(new WholeNum(8)));
        r.setRightNode(new Node(new WholeNum(4)));
        n.setRightNode(r);
    }

    @Test
    public void testSolveLongBET() {
        assertEquals(bet.solve().toString(), "5");
    }
}