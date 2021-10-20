package userPackageTester;

import org.junit.Test;
import userPackage.UserManagerFactory;
import userPackage.UserManagerUpdater;
import userPackage.UserManagerViewer;

import java.util.List;

public class UserManagerFactoryTest {
    /**
     * Create instance of userPackage.User class to test methods.
     */


    @Test
    public void testCreateViewerAndUpdater(){
        List<Object> managers = UserManagerFactory.createViewerAndUpdater();
        UserManagerViewer viewer = (UserManagerViewer) managers.get(0);
        UserManagerUpdater updater = (UserManagerUpdater) managers.get(1);
        assert viewer.verifyUsername("main");
        assert updater.getUsers().get(0).getUsername().equals("main");
    }
}
