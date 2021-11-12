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
     * Retrieves a map of username to User objects.
     * @return list of Users
     */
    Map<String, User> getUsers() throws Exception;

    /**
     * Stores a map of username to User objects.
     */
    void storeUsers(Map<String, User> existingUsers);

    /**
     * Retrieves map of username to user's worksheet history.
     *
     * @return mapping of username to History
     */
    Map<String, History> getHistories() throws ClassNotFoundException;

    /**
     * Stores map of username to user's worksheet history.
     */
    void storeHistories(Map<String, History> existingHistories);
}
