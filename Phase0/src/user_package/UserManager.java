package user_package;

import exceptions.UsernameTakenException;

import java.util.Map;

/**
 * UserManager class. Handles User entities.
 *
 * @version 2.0
 * @authors Kerim, Stanley
 */
public class UserManager {
    private final Map<String, User> users;
    private final LocalDataAccess dataSource; // TODO: should it say as variable to fix the warning?

    public UserManager(LocalDataAccess dataSource) {
        // Map<String, User> existingUsers,
        // users = existingUsers;
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
     *
     * @param username potential username
     * @param name     name of userPackage.User
     * @param age      age of userPackage.User
     * @param role     role of userPackage.User (Student/Parent/Teacher)
     */
    public void createUser(String username, String name, Integer age, String role) throws Exception {
        if (verifyUsername(username)) {
            throw new UsernameTakenException();
        }
        User newUser = new User(username, name, age, role);
        this.users.put(username, newUser);
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
