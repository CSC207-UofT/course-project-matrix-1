package userPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utilizes the factory design pattern and creates instances of both userPackage.UserManagerViewer and userPackage.UserManagerUpdater in its-
 * factory method createViewerAndUpdater.
 *
 * The method createViewerAndUpdater passes the same list of users for the instances of userPackage.UserManagerViewer
 * and userPackage.UserManagerUpdater. Thus, the updates to the users that we make in the userPackage.UserManagerUpdater can also be seen in the
 * userPackage.UserManagerViewer.
 *
 * Collaborators: userPackage.UserManagerViewer, userPackage.UserManagerUpdater
 *
 * @authors Kerim, Stanley
 * @version 1.0
 */
public class UserManagerFactory {
    /**
     * Deserializes and retrieves list of all existing Users that are stored locally.
     * @return list of existing Users
     */
    public static List<User> findUsers() {
        try {
            // Attempt to read from file
            FileInputStream usersIn = new FileInputStream("Phase0/src/userPackage/usersData/users.ser");
            ObjectInputStream in = new ObjectInputStream(usersIn);
            ArrayList<User> existingUsers = (ArrayList<User>) in.readObject();
            in.close();
            usersIn.close();
            return existingUsers;
        } catch (FileNotFoundException i) {
            // TODO: If no users stored, return empty list
//            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            /*
 TODO: If unexpected IO or ClassNotFound exception found, print details then return empty list.
            e.printStackTrace();
            return new ArrayList<>();
*/
        }
        // For testing purposes, return artificial User main.
        User user1 = new User("main", "MainUser", 21, "Student");  // default user for testing
        return new ArrayList<>(List.of(user1));

    }

    /**
     * Creates UserManagerViewer and UserManagerUpdater instances with references to the same list of existing users.
     * @return list of UserManagerViewer, UserManagerUpdater instances (in that order).
     */
        public static List<Object> createViewerAndUpdater() {
        List<User> users = UserManagerFactory.findUsers();
        UserManagerViewer viewer = new UserManagerViewer(users);
        UserManagerUpdater updater = new UserManagerUpdater(users);
        return new ArrayList<>(Arrays.asList(viewer, updater));
    }
}
