import java.util.ArrayList;
import java.util.Arrays;

public class UserManager {
    // Create an Arraylist of User objects
    // Pass the same Arraylist to both viewer and manager
    // create subbranch to stan-kerim and merge it
    public ArrayList<Object> createViewerAndUpdater(ArrayList<User> users){
        UserManagerViewer viewer = new UserManagerViewer();
        UserManagerUpdater updater = new UserManagerUpdater();
        viewer.setUsers(users);
        updater.setUsers(users);
        /* same users array is set to both viewer and updater so that when we update user objects in updater,
        * the same user objects will be also updated in the viewer
         */
        return new ArrayList<>(Arrays.asList(viewer, updater));
    }
}
