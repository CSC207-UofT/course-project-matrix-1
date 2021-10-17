import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserControllerTest {
    /**
     * Create instance of User class to test methods.
     */
    UserController newController;

    @Before
    public void setUp() {
        newController = new UserController();
    }

    @Test
    public void testVerifyUsername() {
        assert newController.verifyUsername("main");
        newController.setCurrentUsername("user2");
        assert !newController.verifyUsername("user2"); // not in the users list, just set as the current user
        assert newController.getCurrentUsername().equals("user2");
    }

    @Test
    public void testGetUserDetails() {
        Map<String, Object> userDetails = newController.getUserDetails("main");
        assert userDetails.get("name").equals("MainUser");
        assert userDetails.get("age").equals(21);
        assert userDetails.get("role").equals("Student");

        Map<String, Object> userDetails2 = newController.getUserDetails("non existent user");
        assert userDetails2.isEmpty();
    }

    @Test
    public void testGetUserScores() {
        assert newController.getUserScores("main").isEmpty();
    }

    @Test
    public void testGetUserHistory() {
        assert newController.getUserHistory("main").isEmpty();
    }

    @Test
    public void testCreateUser() {
        newController.createUser("SCH99", "Arnold", 20, "Student");
        Map<String, Object> userDetails = newController.getUserDetails("SCH99");
        assert userDetails.get("name").equals("Arnold");
        assert userDetails.get("age").equals(20);
        assert userDetails.get("role").equals("Student");
    }

    @Test
    public void testStoreUserAction() {
        Map<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "standard-add-easy");
        newController.storeUserAction("main", record);
        assert newController.getUserHistory("main").get(0).equals(record);
    }

    @Test
    public void testRemoveUserAction() {
        HashMap<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "standard-add-easy");
        newController.storeUserAction("main", record);
        newController.removeUserAction("main", 0);
        assert newController.getUserHistory("main").isEmpty();
    }

    @Test
    public void testStoreUserScore() {
        HashMap<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "standard-add-easy");
        newController.storeUserAction("main", record);
        newController.storeUserScore("main", "standard-add-easy", 39);
        assert newController.getUserScores("main").get("standard-add-easy").equals(39);
    }


}
