package user_package;

import exceptions.RecordDoesNotExistException;
import exceptions.UsernameTakenException;

import java.util.List;
import java.util.Map;

/**
 * Accesses or updates user information based on inputs from UserInterface.
 * <p>
 * Using dependency injection, existing users and their histories are retrieved and injected into UserManager and
 * HistoryManager.
 * <p>
 * Collaborators: userPackage.UserManager, userPackage.HistoryManager
 *
 * @author Kerim, Stanley
 * @version 2.0
 * @since 2021-10-26
 */
public class UserController {
    private final UserManager userManager;
    private final HistoryManager historyManager;
    private String currentUsername;

    public UserController() {
        DataAccessInterface dataSource = new LocalDataAccess();
        userManager = new UserManager(dataSource);
        historyManager = new HistoryManager(dataSource);
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    /**
     * Verifies whether the user is in the system or not.
     *
     * @param username username for the user
     * @return true iff the username corresponds to existing user
     */
    public Boolean login(String username) {
        return userManager.verifyUsername(username);
    }

    public Map<String, Object> getUserDetails(String username) {
        return userManager.getUserDetails(username);
    }

    /**
     * Returns the history of worksheet generation records of a user.
     *
     * @param username of user
     * @return list containing worksheet generation records (details)
     */
    public List<Map<String, Object>> getUserHistory(String username) {
        return historyManager.getUserHistoryRaw(username);
    }

    /**
     * Registers a new userPackage.User. Throws Exception if another userPackage.User exists with the same username.
     *
     * @param username potential username
     * @param name     name of userPackage.User
     * @param age      age of userPackage.User
     * @param role     role of userPackage.User (Student/Parent/Teacher)
     */
    public void registerUser(String username, String name, Integer age, String role) throws UsernameTakenException {
        userManager.createUser(username, name, age, role);
    }

    /**
     * Stores the user's score for the respective worksheet.
     *
     * @param username:     Username of the user
     * @param worksheetKey: Name of the worksheet
     * @param score:        Score of the user for that worksheet
     */
    public void storeUserScore(String username, String worksheetKey, Integer score) throws RecordDoesNotExistException {
        historyManager.setUserScoreForRecord(username, worksheetKey, score);
    }


    /**
     * Stores the action of the user (such as creating a worksheet) in the user's history.
     *
     * @param username:         Username of the user
     * @param worksheetDetails: details for worksheet generation
     */
    public void storeUserRecord(String username, Map<String, Object> worksheetDetails) {
        historyManager.storeUserRecord(username, worksheetDetails);
    }

    /**
     * Removes the specified worksheet generation record from the user's history.
     *
     * @param username: Username of the user
     * @param worksheetKey:    Index of the action to be removed from the list of actions of the user.
     */
    public void removeUserRecord(String username, String worksheetKey) throws RecordDoesNotExistException {
        historyManager.removeUserRecord(username, worksheetKey);
    }

}
