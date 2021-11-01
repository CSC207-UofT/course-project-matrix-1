package user_package;

import exceptions.RecordDoesNotExistException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HistoryManagerTest {
    HistoryManager exampleHistoryManager;
    LocalDataAccess dataAccess;
    @Before
    public void setUp() {
        LocalDataAccess dataAccess = new LocalDataAccess();
        exampleHistoryManager = new HistoryManager(dataAccess);
    }

    /**
     * Tests if beginUserHistory and getUserHistory both work.
     */
    @Test
    public void testBeginAndGetUserHistory() {
        exampleHistoryManager.beginUserHistory("exampleUsername");
        assertNotNull(exampleHistoryManager.getUserHistory("exampleUsername"));
    }

    /**
     * Tests if worksheet record can be stored for a specific User, and if added record can be retrieved.
     */
    @Test
    public void testStoreUserRecord() {
        exampleHistoryManager.beginUserHistory("newUser1");
        Map<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("numQuestions", 100);
        worksheetDetails.put("worksheetKey", "1");
        exampleHistoryManager.storeUserRecord("newUser1", worksheetDetails);

        try {
            assertNotNull(exampleHistoryManager.getUserHistory("newUser1").findWorksheetRecord("1"));
        } catch (RecordDoesNotExistException e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Tests removeUserAction method. Simulate User and worksheet record. Removes added worksheet record. Another call
     * to remove the already removed record should throw a RecordDoesNotExistException.
     * @throws RecordDoesNotExistException since record does not exist after deleting it
     */
    @Test(expected = RecordDoesNotExistException.class)
    public void testRemoveUserAction() throws RecordDoesNotExistException {
        exampleHistoryManager.beginUserHistory("newUser2");
        Map<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("numQuestions", 70);
        worksheetDetails.put("worksheetKey", "3");
        exampleHistoryManager.storeUserRecord("newUser2", worksheetDetails);

        exampleHistoryManager.removeUserRecord("newUser2", "3");
        exampleHistoryManager.removeUserRecord("newUser2", "3");
    }

    /**
     * Tests setUserScoreForRecord method. Checks if worksheet record contains stored score.
     */
    @Test
    public void testSetUserScoreForRecord() throws RecordDoesNotExistException {
        exampleHistoryManager.beginUserHistory("newUser3");
        Map<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("numQuestions", 100);
        worksheetDetails.put("worksheetKey", "4");
        exampleHistoryManager.storeUserRecord("newUser3", worksheetDetails);
        exampleHistoryManager.setUserScoreForRecord("newUser3", "4", 99);

        assertEquals(99, exampleHistoryManager.getUserHistory("newUser3").findWorksheetRecord("4").get("score"));
    }
}