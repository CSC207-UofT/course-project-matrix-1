import java.util.ArrayList;
import java.util.Arrays;

public class UserManager {
    // TODO: Treat this as a factory method --> Factory Design Pattern
    // This one needs to get the real users list from the txt file
    // A function named getUsers, to get the

    // Create an Arraylist of User objects
    // Pass the same Arraylist to both viewer and manager
    // create subbranch to stan-kerim and merge it
    // TODO: create random users for findUsers
    public static ArrayList<User> findUsers(){
        // In the future findUsers will read from the text file and get the users
        // TODO: create random users for findUsers
        User user1 = new User("main", "MainUser", 21, "Student");
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user1));
        return users;
    }

    public static ArrayList<Object> createViewerAndUpdater(){
        ArrayList<User> users = UserManager.findUsers();
        UserManagerViewer viewer = new UserManagerViewer(users);
        UserManagerUpdater updater = new UserManagerUpdater(users);
        /* same users array is set to both viewer and updater so that when we update user objects in updater,
        * the same user objects will be also updated in the viewer
         */
        return new ArrayList<>(Arrays.asList(viewer, updater));
    }
}
