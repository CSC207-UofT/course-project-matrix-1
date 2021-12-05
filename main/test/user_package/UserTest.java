package user_package;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

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
        assertEquals("haddock99", newUser.getUsername());
    }

    @Test
    public void testGetDetails() {
        Map<String, Object> userDetails = newUser.getDetails();
        assertEquals("Sarah", userDetails.get("name"));
        assertEquals(19, userDetails.get("age"));
        assertEquals("Student", userDetails.get("role"));
    }
}
