package user_package;

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
    Map<String, User> getUsers();

    /**
     * Stores list of Users.
     */
    void storeUsers(Map<String, User> existingUsers);

    /**
     * Retrieves all existing users' worksheet history.
     *
     * @return mapping of username to History
     */
    Map<String, History> getHistories();

    /**
     * Stores all existing users' worksheet history.
     */
    void storeHistories(Map<String, History> existingHistories);
}
