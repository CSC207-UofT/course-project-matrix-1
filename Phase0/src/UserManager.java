import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utilizes the factory design pattern and creates instances of both UserManagerViewer and UserManagerUpdater in its-
 * factory method createViewerAndUpdater.
 * The method createViewerAndUpdater passes the same list of users for the instances of UserManagerViewer
 * and UserManagerUpdater. Thus, the updates to the users that we make in the UserManagerUpdater can also be seen in the
 * UserManagerViewer.
 * Collaborators: UserManagerViewer, UserManagerUpdater
 *
 * @author Kerim
 * @version 1.0
 */
public class UserManager {
    // TODO: Treat this as a factory method --> Factory Design Pattern
    // TODO: Refactor the name of UserManager to UserManagerFactory in the future
    // This one needs to get the real users list from the txt file ()
    // A function named getUsers, to get the

    // Create an Arraylist of User objects
    // Pass the same Arraylist to both viewer and manager

    // TODO: Retrieve serialized Users from directory
    public static List<User> findUsers() {
        // In the future findUsers will read from the text file and get the users
        // Input Source Interface will read the text file and allow us achieve dependency inversion
        // TODO: implement Input Source Interface
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
