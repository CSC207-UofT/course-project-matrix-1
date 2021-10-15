import org.junit.Test;

import java.util.List;

public class UserManagerTest {
    /**
     * Create instance of User class to test methods.
     */


    @Test
    public void testCreateViewerAndUpdater(){
        List<Object> managers = UserManager.createViewerAndUpdater();
        UserManagerViewer viewer = (UserManagerViewer) managers.get(0);
        UserManagerUpdater updater = (UserManagerUpdater) managers.get(1);
        assert viewer.verifyUsername("main");
        assert updater.getUsers().get(0).getUsername().equals("main");
    }
}
