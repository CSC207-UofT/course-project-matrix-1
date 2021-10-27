package userPackage;

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
        users.put("main", new User("main", "MainUser", 21, "Student"));
        userManager = new UserManager(users);
    }

    @Test
    public void testVerifyUsername() {
        assertTrue(userManager.verifyUsername("main"));
        assertFalse(userManager.verifyUsername("random"));
    }

    @Test
    public void testCreateUser() throws Exception {
        userManager.createUser("SCH99", "Arnold", 20, "Student");
        assertTrue(userManager.verifyUsername("SCH99"));
    }

    @Test
    public void testGetUserDetails() {
        Map<String, Object> userDetails = userManager.getUserDetails("main");
        assertEquals("MainUser", userDetails.get("name"));
        assertEquals(21, userDetails.get("age"));
        assertEquals("Student", userDetails.get("role"));
        Map<String, Object> userDetails2 = userManager.getUserDetails("NonExistent"); // not a user
        assertTrue(userDetails2.isEmpty());
    }
}