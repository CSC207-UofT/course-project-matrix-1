package user_package;

import exceptions.RecordDoesNotExistException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class UserControllerTest {
    /**
     * Create instance of userPackage.User class to test methods.
     */
    UserController userController;

    @Before
    public void setUp() {
        userController = new UserController();
    }

    @Test
    public void testVerifyUsername() throws Exception {
        assert userController.login("main");
        userController.setCurrentUsername("user2");
        assert !userController.login("user2"); // not in the users list, just set as the current user
        assert userController.getCurrentUsername().equals("user2");
    }

    @Test
    public void testGetUserDetails() {
        // You Have to register users first!
        Map<String, Object> userDetails = userController.getUserDetails("main");
        assert userDetails.get("name").equals("MainUser");
        assert userDetails.get("age").equals(21);
        assert userDetails.get("role").equals("Student");

        Map<String, Object> userDetails2 = userController.getUserDetails("non existent user");
        assert userDetails2.isEmpty();
    }

    @Test
    public void testGetUserHistory() {
        assert userController.getUserHistory("main").isEmpty();
    }

    @Test
    public void testCreateUser() throws Exception {
        userController.registerUser("SCH99", "Arnold", 20, "Student");
        Map<String, Object> userDetails = userController.getUserDetails("SCH99");
        assert userDetails.get("name").equals("Arnold");
        assert userDetails.get("age").equals(20);
        assert userDetails.get("role").equals("Student");
    }

    @Test
    public void testStoreUserAction() {
        Map<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "standard-add-easy");
        userController.storeUserRecord("main", record);
        assert userController.getUserHistory("main").get(0).equals(record);
    }

    @Test
    public void testRemoveUserAction() throws RecordDoesNotExistException {
        HashMap<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "156");
        userController.storeUserRecord("main", record);
        userController.removeUserRecord("main", "156");
        assert userController.getUserHistory("main").isEmpty();
    }

    @Test
    public void testStoreUserScore() throws RecordDoesNotExistException {
        HashMap<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "156");
        userController.storeUserRecord("main", record);
        userController.storeUserScore("main", "156", 39);

        for (Map<String, Object> currRecord : userController.getUserHistory("main")){
            if (currRecord.get("worksheetKey") == "156"){
                assertEquals(currRecord.get("score"), 39);
            }
        }
    }


}
