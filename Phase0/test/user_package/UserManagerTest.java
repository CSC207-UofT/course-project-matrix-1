package user_package;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserManagerTest {
    UserManager userManager;

    @Before
    public void setUp() {
        Map<String, User> users = new HashMap<>();
        LocalDataAccess dataAccess = new LocalDataAccess();
        users.put("main", new User("main", "MainUser", 21, "Student"));
        dataAccess.storeUsers(users);
        userManager = new UserManager(dataAccess);
    }

    @Test
    public void testVerifyUsername() {
        assertTrue(userManager.verifyUsername("main"));
        assertFalse(userManager.verifyUsername("random"));
    }

    @Test
    public void testCreateUser() {
        userManager.createUser("SCH99", "Arnold", 20, "Student");
        assertTrue(userManager.verifyUsername("SCH99"));
    }

    /**
     * Tests getUserDetails on a non-existent user.
     */
    @Test(expected = NullPointerException.class)
    public void testGetUserDetails() {
        Map<String, Object> userDetails = userManager.getUserDetails("main");
        assertEquals("MainUser", userDetails.get("name"));
        assertEquals(21, userDetails.get("age"));
        assertEquals("Student", userDetails.get("role"));
        assertNull(userManager.getUserDetails("NonExistent"));
    }
}