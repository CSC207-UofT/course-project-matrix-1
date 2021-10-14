import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManagerUpdaterTest {
    /**
     * Create instance of User class to test methods.
     */
    UserManagerUpdater newUpdater;
    UserManagerViewer newViewer;

    @Before
    public void setUp(){
        ArrayList<User> userArray = new ArrayList<>();
        newUpdater = new UserManagerUpdater(userArray);
        newViewer  = new UserManagerViewer(userArray);}

    @Test
    public void testCreateUser(){
        newUpdater.createUser("SCH99", "Arnold", 20, "Student");
        HashMap<String, Object> userDetails = newUpdater.getUsers().get(0).getDetails();
        assert userDetails.get("name").equals("Arnold");
        assert userDetails.get("age").equals(20);
        assert userDetails.get("role").equals("Student");
        // delete this test
        HashMap<String, Object> userDetails2 = newViewer.getUsers().get(0).getDetails();
        assert userDetails2.get("name").equals("Arnold");
        assert userDetails2.get("age").equals(20);
        assert userDetails2.get("role").equals("Student");

    }

}
