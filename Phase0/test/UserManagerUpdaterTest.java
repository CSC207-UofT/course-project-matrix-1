import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class UserManagerUpdaterTest {
    /**
     * Create instance of User class to test methods.
     */
    UserManagerUpdater newUpdater;

    @Before
    public void setUp() {

        newUpdater = (UserManagerUpdater) UserManager.createViewerAndUpdater().get(1);
    }

    @Test
    public void testCreateUser() throws Exception {
        newUpdater.createUser("SCH99", "Arnold", 20, "Student");
        HashMap<String, Object> userDetails = newUpdater.getUsers().get(0).getDetails();
        assert userDetails.get("name").equals("MainUser");
        assert userDetails.get("age").equals(21);
        assert userDetails.get("role").equals("Student");

        HashMap<String, Object> userDetails2 = newUpdater.getUsers().get(1).getDetails();
        assert userDetails2.get("name").equals("Arnold");
        assert userDetails2.get("age").equals(20);
        assert userDetails2.get("role").equals("Student");
    }

    @Test
    public void testStoreUserAction() {
        HashMap<String, Object> action = new HashMap<>();
        action.put("Some action", 1);
        newUpdater.storeUserAction("main", action);
        assert newUpdater.getUsers().get(0).getHistory().get(0).equals(action);
    }

    @Test
    public void testRemoveUserAction() {
        HashMap<String, Object> action = new HashMap<>();
        action.put("worksheetKey", "some action");
        newUpdater.storeUserAction("main", action);
        newUpdater.removeUserAction("main", 0);
        assert newUpdater.getUsers().get(0).getHistory().size() == 0;
    }

    @Test
    public void testStoreUserScore() {
        HashMap<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "standard-add-easy");
        newUpdater.storeUserAction("main", record);
        newUpdater.storeUserScore("main", "standard-add-easy", 39);
        assert newUpdater.getUsers().get(0).getScores().get("standard-add-easy").equals(39);
    }

    @Test
    public void testStoreUserScores() {
        HashMap<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "standard-add-easy");
        newUpdater.storeUserAction("main", record);
        newUpdater.storeUserScore("main", "standard-add-easy", 39);
        assert newUpdater.getUsers().get(0).getScores().get("standard-add-easy").equals(39);
    }

}
