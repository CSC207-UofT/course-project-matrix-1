package userPackage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManagerViewerTest {
    UserManagerViewer newViewer;

    @Test
    public void testVerifyUsername() {
        assert newViewer.verifyUsername("main");
        assert !newViewer.verifyUsername("Random");
    }

    @Test
    public void testGetUserDetails() {
        Map<String, Object> userDetails = newViewer.getUserDetails("main");
        assert userDetails.get("name").equals("MainUser");
        assert userDetails.get("age").equals(21);
        assert userDetails.get("role").equals("Student");
        Map<String, Object> userDetails2 = newViewer.getUserDetails("NonExistent"); // not a user
        assert userDetails2.isEmpty();
    }

    @Test
    public void testGetUserScores() {
        assert newViewer.getUserScores("main").equals(new HashMap<>());
    }


}
