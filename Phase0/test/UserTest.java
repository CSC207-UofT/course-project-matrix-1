import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.*;

import static org.junit.Assert.*;

public class UserTest {
    /**
     * Create instance of User class to test methods.
     */
    User newUser;

    @Before
    public void setUp() {
        newUser = new User("haddock99", "Sarah", 19, "Student");
    }

    @Test
    public void testGetUsername() {
        assertEquals(newUser.getUsername(), "haddock99");
    }

    @Test
    public void testGetDetails() {
        Map<String, Object> userDetails = newUser.getDetails();
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
        Map<String, Integer> scores = newUser2.getScores();
        assertTrue(scores.isEmpty());
    }

    /**
     * Tests getHistory on newly initialized User instance.
     */
    @Test
    public void testGetHistoryEmpty() {
        User newUser2 = new User("catfish22", "Angel", 44, "Teacher");
        List<Map<String, Object>> scores = newUser2.getHistory();
        assertTrue(scores.isEmpty());
    }

    /**
     * Tests setWorksheetScore if stored scores is updated.
     */
    @Test
    public void testSetWorksheetScore() {
        HashMap<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("worksheetKey", "standard-add-easy");
        worksheetDetails.put("numQuestions", 40);
        // Add details to history
        newUser.addToHistory(worksheetDetails);
        // Set worksheet score
        newUser.setWorksheetScore("standard-add-easy", 40);
        // Check if scores were saved properly
        Map<String, Integer> scores = newUser.getScores();
        assertFalse(scores.isEmpty());
    }

    /**
     * Tests testAddToHistory. Checks if history is updated after calling method.
     */
    @Test
    public void testAddToHistory() {
        // Create mock worksheetDetails map
        HashMap<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("worksheetKey", "standard-add-easy");
        worksheetDetails.put("numQuestions", 40);
        // Add details to history
        newUser.addToHistory(worksheetDetails);

        // Check if history was modified properly
        List<Map<String, Object>> currHistory = newUser.getHistory();
        assertFalse(currHistory.isEmpty());
    }


    /**
     * Tests findWorksheetInHistory. Checks if an item added to history can be found via its worksheetKey.
     */
    @Test
    public void testFindWorksheetInHistory() {
        // Create mock worksheetDetails map
        HashMap<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("worksheetKey", "standard-add-easy");
        worksheetDetails.put("numQuestions", 40);
        // Add details to history
        newUser.addToHistory(worksheetDetails);

        // Check if worksheet is in history
        Optional<Map<String, Object>> detailsOptional = newUser.findWorksheetInHistory("standard-add-easy");
        assertTrue(detailsOptional.isPresent());
    }

    /**
     * Tests removeFromHistory. Verifies if history is empty after removing only item in history.
     */
    @Test
    public void testRemoveFromHistory() {
        // Create dummy User
        User newUser2 = new User("catfish22", "Angel", 44, "Teacher");
        // Create mock worksheetDetails map
        HashMap<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("worksheetKey", "standard-add-easy");
        worksheetDetails.put("numQuestions", 40);
        // Add details to history
        newUser2.addToHistory(worksheetDetails);

        // Remove item from history
        newUser2.removeFromHistory(0);

        // Verify history is empty
        assertTrue(newUser2.getHistory().isEmpty());
    }
}
