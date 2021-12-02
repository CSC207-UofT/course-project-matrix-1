package user_package;

import exceptions.UserDoesNotExistException;
import exceptions.UsernameTakenException;

import java.util.Map;

/**
 * UserManager class. Handles User entities.
 *
 * @author Kerim, Stanley
 * @version 2.0
 */
public class UserManager {
    private final Map<String, User> users;
    private final DataAccessInterface dataSource;

    protected UserManager(DataAccessInterface dataSource) throws Exception {
        this.dataSource = dataSource;
        users = this.dataSource.getUsers();
    }

    /**
     * Verifies username.
     *
     * @param username attempted username
     * @return true iff the username corresponds to an existing User.
     */
    public boolean verifyUsername(String username) {
        return users.containsKey(username);
    }


    /**
     * Registers a new userPackage.User. Throws Exception if another userPackage.User exists with the same username.
     * Afterwards, stores new user in data storage/retrieval location.
     *
     * @param username potential username
     * @param name     name of userPackage.User
     * @param age      age of userPackage.User
     * @param role     role of userPackage.User (Student/Parent/Teacher)
     */
    public void createUser(String username, String name, Integer age, String role) throws UsernameTakenException {
        if (verifyUsername(username)) {
            throw new UsernameTakenException();
        }
        User newUser = new User(username, name, age, role);
        this.users.put(username, newUser);
        this.dataSource.storeUsers(this.users);
    }

    /**
     * Deletes user from existing users.
     *
     * @param username username of User
     * @throws UserDoesNotExistException thrown if specified user does not exist
     */
    public void deleteUser(String username) throws UserDoesNotExistException {
        if (!this.users.containsKey(username)) {
            throw new UserDoesNotExistException();
        }
        this.users.remove(username);
        this.dataSource.storeUsers(this.users);
    }

    /**
     * Returns map of user details.
     *
     * @param username username of user
     * @return map holding the user's details
     */
    public Map<String, Object> getUserDetails(String username) {
        return users.get(username).getDetails();
    }
}
