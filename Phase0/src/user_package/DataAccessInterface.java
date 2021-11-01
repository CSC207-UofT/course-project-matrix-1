package user_package;

import java.util.List;
import java.util.Map;

/**
 * UserDataAccessInterface interface. Expects getUsers, storeUsers, getHistories, and storeHistories to be implemented.
 *
 * @version 1.0
 * @author Stanley
 */
public interface DataAccessInterface {
    /**
     * Retrieves list of Users.
     *
     * @return list of Users
     */
    static List<User> getUsers() {
        return null;
    }

    /**
     * Stores list of Users.
     */
    static void storeUsers(List<User> existingUsers) {}

    /**
     * Retrieves all existing users' worksheet history.
     *
     * @return mapping of username to History
     */
    static Map<String, History> getHistories() {
        return null;
    }

    /**
     * Stores all existing users' worksheet history.
     */
    static void storeHistories(Map<String, History> existingHistories) {}
}
