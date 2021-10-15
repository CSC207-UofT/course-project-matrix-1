import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserManager {
    // TODO: Treat this as a factory method --> Factory Design Pattern
    // TODO: Refactor the name of UserManager to UserManagerFactory in the future
    // This one needs to get the real users list from the txt file
    // A function named getUsers, to get the

    // Create an Arraylist of User objects
    // Pass the same Arraylist to both viewer and manager
    // TODO: create random users for findUsers

    public static List<User> findUsers() {
        // In the future findUsers will read from the text file and get the users
        // Input Source Interface will read the text file and allow us achieve dependency inversion
        // TODO: create random users for findUsers.
        User user1 = new User("main", "MainUser", 21, "Student");  // default user for testing
        return new ArrayList<>(List.of(user1));
    }

    public static List<Object> createViewerAndUpdater() {
        List<User> users = UserManager.findUsers();
        UserManagerViewer viewer = new UserManagerViewer(users);
        UserManagerUpdater updater = new UserManagerUpdater(users);
        /* same users array is set to both viewer and updater so that when we update user objects in updater,
         * the same user objects will be also updated in the viewer
         */
        return new ArrayList<>(Arrays.asList(viewer, updater));
    }
}
