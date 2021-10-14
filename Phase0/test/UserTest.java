import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {
    /**
     * Create instance of User class to test methods.
     */
    User newUser;

    @Before
    public void setUp(){
        newUser = new User("haddock99", "Sarah", 19, "Student");
    }

    @Test
    public void testGetUsername() {
        assertEquals(newUser.getUsername(), "haddock99");
    }

    @Test
    public void testGetDetails() {
        HashMap userDetails = newUser.getDetails();
        assertEquals(userDetails.get("name"), "Sarah");
        assertEquals(userDetails.get("age"), 19);
        assertEquals(userDetails.get("role"), "Student");
    }

    /**
     * Tests getScores on newly initialized User instance.
     */
    @Test
    public void testGetScoresEmpty() {
        User newUser2 = new User("catfish22", "Angel", 44, "Teacher");
        HashMap scores = newUser2.getScores();
        assertTrue(scores.isEmpty());
    }

    /**
     * Tests getHistory on newly initialized User instance.
     */
    @Test
    public void testGetHistoryEmpty() {
        User newUser2 = new User("catfish22", "Angel", 44, "Teacher");
        ArrayList<HashMap> scores = newUser2.getHistory();
        assertTrue(scores.isEmpty());
    }

    /**
     * Tests setWorksheetScore if stored scores is updated.
     */
    @Test
    public void testSetWorksheetScore() {
        // Set worksheet score
        newUser.setWorksheetScore("standard-add-easy", 40);
        // Check if scores were saved properly
        HashMap scores = newUser.getScores();
        assertFalse(scores.isEmpty());
    }

    /**
     * Tests testAddToHistory if history is updated.
     */
    @Test
    public void testAddToHistory() {
        // Create mock worksheetDetails map
        HashMap<String, Object> worksheetDetails = new HashMap();
        worksheetDetails.put("worksheetKey", "standard-add-easy");
        worksheetDetails.put("numQuestions", 40);
        // Add details to history
        newUser.addToHistory(worksheetDetails);

        // Check if history was modified properly
        ArrayList currHistory = newUser.getHistory();
        assertFalse(currHistory.isEmpty());
    }


    // TODO: Create test for findWorksheetInHistory
    @Test
    public void testFindWorksheetInHistory(){

    }
}