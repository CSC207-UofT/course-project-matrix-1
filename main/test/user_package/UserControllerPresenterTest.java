package user_package;

import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import exceptions.NotLoggedInException;
import exceptions.RecordDoesNotExistException;
import exceptions.UserDoesNotExistException;
import exceptions.UsernameTakenException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserControllerPresenterTest {
    /**
     * Create instance of User class to test methods.
     */
    UserController userController;
    UserPresenter userPresenter;

    @Before
    public void setUp() throws Exception {
        userController = new UserController();
        userPresenter = new UserPresenter(userController.getUserManager(),
                userController.getHistoryManager(),
                userController.getCurrentUsername());
        try {
            userController.registerUser("guest", "Guest", 20, "Student");
        } catch (UsernameTakenException i) {
            userController.deleteAccount("guest");
            userController.registerUser("guest", "Guest", 20, "Student");
        }
    }

    @After
    public void tearDown() {
        for (String s : Arrays.asList("guest", "guest2", "SCH99")) {
            try {
                userController.deleteAccount(s);
            } catch (Exception e) {
                return;
            }
        }
    }

    /**
     * Test login on dummy user "guest".
     */
    @Test
    public void testLoginDummy() {
        userController.login("guest");
    }

    @Test(expected = UserDoesNotExistException.class)
    public void testLoginUnsuccessful() throws Exception {
        UserController userController2 = new UserController();
        userController2.login(null);
    }

    @Test
    public void testGetUserDetailsSuccessful() {
        userController.login("guest");
        Map<String, Object> userDetails = userPresenter.getUserDetails();
        assert userDetails.get("name").equals("Guest");
        assert userDetails.get("age").equals(20);
        assert userDetails.get("role").equals("Student");
    }

    /**
     * Test getUserDetails when no user is logged in.
     */
    @Test(expected = NotLoggedInException.class)
    public void testGetUserDetailsEmpty() throws Exception {
        UserController userController2 = new UserController();
        UserPresenter userPresenter2 = new UserPresenter(userController2.getUserManager(),
                userController2.getHistoryManager(),
                userController2.getCurrentUsername());
        userPresenter2.getUserDetails();
    }


    /**
     * Test storing multiple worksheet records and retrieval. Recreates UserController instance to test persistence of
     * data.
     */
    @Test
    public void testStoreAndGetUserHistory() throws Exception {
        try {
            userController.registerUser("guest2", "Guest2", 22, "Student");
        } catch (UsernameTakenException e) {
            userController.deleteAccount("guest2");
            userController.registerUser("guest2", "Guest2", 22, "Student");
        }

        // Create record
        HashMap<String, Object> record1 = new HashMap<>();
        record1.put("worksheetKey", "00000000");

        HashMap<String, Object> record2 = new HashMap<>();
        record2.put("worksheetKey", "11111111");

        HashMap<String, Object> record3 = new HashMap<>();
        record3.put("worksheetKey", "22222222");

        HashMap<String, Object> record4 = new HashMap<>();
        record4.put("worksheetKey", "3333333333");

        userController.storeUserRecord(record1);
        userController.storeUserRecord(record2);
        userController.storeUserRecord(record3);
        userController.storeUserRecord(record4);

        // Test retrieval of new UserController
        UserController newUserController = new UserController();
        UserPresenter newUserPresenter = new UserPresenter(newUserController.getUserManager(),
                newUserController.getHistoryManager(),
                newUserController.getCurrentUsername());
        newUserController.login("guest2");

        List<Map<String, Object>> userHistory = newUserPresenter.getUserHistory();

        assertEquals("00000000", userHistory.get(0).get("worksheetKey"));
        assertEquals("11111111", userHistory.get(1).get("worksheetKey"));
        assertEquals("22222222", userHistory.get(2).get("worksheetKey"));
        assertEquals("3333333333", userHistory.get(3).get("worksheetKey"));
    }

    @Test
    public void testCreateUser() {
        try {
            userController.registerUser("SCH99", "Arnold", 20, "Student");
        } catch (UsernameTakenException i) {
            userController.login("SCH99");
        }
        Map<String, Object> userDetails = userPresenter.getUserDetails();
        assert userDetails.get("name").equals("Arnold");
        assert userDetails.get("age").equals(20);
        assert userDetails.get("role").equals("Student");
    }

    @Test
    public void testStoreUserAction() {
        userController.login("guest");
        Map<String, Object> record = new HashMap<>();
        record.put("worksheetKey", "760");
        userController.storeUserRecord(record);
        List<Map<String, Object>> userHistory = userPresenter.getUserHistory();
        assert userHistory.get(userHistory.size() - 1).equals(record);
    }

    @Test
    public void testRemoveUserAction() throws RecordDoesNotExistException {
        userController.login("guest");
        HashMap<String, Object> record = new HashMap<>();
        record.put("worksheetKey", "156");
        HashMap<String, Object> record2 = new HashMap<>();
        record2.put("worksheetKey", "2");

        userController.storeUserRecord(record);
        userController.storeUserRecord(record2);
        userController.removeUserRecord("2");

        List<Map<String, Object>> userHistory = userPresenter.getUserHistory();
        assertNotEquals(record2, userHistory.get(userHistory.size() - 1));
    }

    @Test
    public void testStoreUserScore() throws RecordDoesNotExistException {
        userController.login("guest");
        HashMap<String, Object> record = new HashMap<>();
        record.put("worksheetKey", "156");

        EquationDetails equationDetails = new WholeNumEquationDetails();
        equationDetails.setNumOfEquations(40);
        record.put("equationDetails", equationDetails);

        userController.storeUserRecord(record);
        userController.storeUserScore("156", 39);

        for (Map<String, Object> currRecord : userPresenter.getUserHistory()) {
            if (currRecord.get("worksheetKey") == "156") {
                assertEquals(currRecord.get("score"), 39);
            }
        }
    }
}