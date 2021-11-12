package user_package;

import exceptions.NotLoggedInException;
import exceptions.RecordDoesNotExistException;
import exceptions.UserDoesNotExistException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserControllerTest {
    /**
     * Create instance of userPackage.User class to test methods.
     */
    UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController();
        userController.login("main");
    }

    /**
     * Test login on dummy user "main".
     */
    @Test
    public void testLoginDummy() {
        userController.login("main");           // dummy class
    }

    @Test(expected = UserDoesNotExistException.class)
    public void testLoginUnsuccessful() {
        userController.login("user2");          // not in the users list, just set as the current user
    }

    @Test
    public void testGetUserDetailsSuccessful() {
        Map<String, Object> userDetails = userController.getUserDetails();
        assert userDetails.get("name").equals("MainUser");
        assert userDetails.get("age").equals(21);
        assert userDetails.get("role").equals("Student");
    }

    /**
     * Test getUserDetails when no user is logged in.
     */
    @Test(expected = NotLoggedInException.class)
    public void testGetUserDetailsEmpty() throws Exception {
        UserController userController_2 = new UserController();
        userController_2.getUserDetails();
    }


    @Test
    public void testGetUserHistory() {
        assertEquals("1", userController.getUserHistory().get(0).get("worksheetKey"));
    }

    @Test
    public void testCreateUser() {
        userController.registerUser("SCH99", "Arnold", 20, "Student");
        Map<String, Object> userDetails = userController.getUserDetails();
        assert userDetails.get("name").equals("Arnold");
        assert userDetails.get("age").equals(20);
        assert userDetails.get("role").equals("Student");
    }

    @Test
    public void testStoreUserAction() {
        Map<String, Object> record = new HashMap<>();
        record.put("worksheetKey", "760");
        userController.storeUserRecord(record);
        userController.login("main");

        List<Map<String, Object>> userHistory = userController.getUserHistory();
        assert userHistory.get(userHistory.size() - 1).equals(record);
    }

    @Test
    public void testRemoveUserAction() throws RecordDoesNotExistException {
        HashMap<String, Object> record = new HashMap<>();
        record.put("numQuestions", 40);
        record.put("worksheetKey", "156");
        userController.storeUserRecord(record);
        userController.removeUserRecord("156");

        List<Map<String, Object>> userHistory = userController.getUserHistory();
        assertNotEquals(record, userHistory.get(userHistory.size() - 1));
    }

    @Test
    public void testStoreUserScore() throws RecordDoesNotExistException {
        HashMap<String, Object> record = new HashMap<>();
        record.put("worksheetKey", "156");

        Map<String, Object> equationDetails = new HashMap<>();
        equationDetails.put("numOfEquations", 40);
        record.put("equationDetails", equationDetails);

        userController.storeUserRecord(record);
        userController.storeUserScore("156", 39);

        for (Map<String, Object> currRecord : userController.getUserHistory()){
            if (currRecord.get("worksheetKey") == "156"){
                assertEquals(currRecord.get("score"), 39);
            }
        }
    }


}
