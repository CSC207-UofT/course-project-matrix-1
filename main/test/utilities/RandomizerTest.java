package utilities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class RandomizerTest {
    public Randomizer rand = new Randomizer(1000);

    @Test
    public void testRandomizeMinMax() {
        for (int i = 0; i < 100; i++) {
            int randInt = rand.randomize(0, 10);
            assertTrue(randInt <= 10 && randInt >= 0);
            System.out.println(randInt);
        }
    }

    @Test
    public void testRandomizeRange() {
        for (int i = 0; i < 100; i++) {
            int randInt = rand.randomize(new int[]{0, 10});
            assertTrue(randInt <= 10 && randInt >= 0);
            System.out.println(randInt);
        }
    }

    @Test
    public void testRandomizePossibleInts() {
        for (int i = 0; i < 100; i++) {
            ArrayList<Integer> possibleInts = new ArrayList<>();
            possibleInts.add(3);
            possibleInts.add(4);
            possibleInts.add(5);
            int randInt = rand.randomize(possibleInts);
            assertTrue(randInt == 3 || randInt == 4 || randInt == 5);
            System.out.println(randInt);
        }
    }
}