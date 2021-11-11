package user_package;

import exceptions.NotLoggedInException;
import exceptions.RecordDoesNotExistException;
import exceptions.UserDoesNotExistException;
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

    /**
     * @return true if a user is logged in.
     */
    public Boolean isLoggedIn() {
        return currentUsername.length() > 0;
    }

    /**
     * Verifies whether the user is in the system or not.
     * Throws an UserDoesNotExistException if the username does not match a user in the system.
     *
     * @param username username for the user
     */
    public void login(String username) throws UserDoesNotExistException{
        if (userManager.verifyUsername(username)) {
            this.currentUsername = username;
        } else {
            throw new UserDoesNotExistException();
        }
    }

    public Map<String, Object> getUserDetails() throws NotLoggedInException{
        if (isLoggedIn()) {
            return userManager.getUserDetails(currentUsername);
        }
        throw new NotLoggedInException();
    }

    /**
     * Returns the history of worksheet generation records of a user.
     * Throws a NotLoggedInException if called when no user is logged in.
     *
     * @return list containing worksheet generation records (details)
     */
    public List<Map<String, Object>> getUserHistory() throws NotLoggedInException{
        if (isLoggedIn()) {
            return historyManager.getUserHistoryRaw(currentUsername);
        }
        throw new NotLoggedInException();
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
        this.currentUsername = username;
    }

    /**
     * Stores the user's score for the respective worksheet.
     * Throws a RecordDoesNotExistException if worksheet specified does not exist
     * Throws a NotLoggedInException if called when no user is logged in.
     *
     * @param worksheetKey: Name of the worksheet
     * @param score:        Score of the user for that worksheet
     */
    public void storeUserScore(String worksheetKey, Integer score) throws RecordDoesNotExistException, NotLoggedInException {
        if (isLoggedIn()) {
            historyManager.setUserScoreForRecord(currentUsername, worksheetKey, score);
        }
        throw new NotLoggedInException();
    }


    /**
     * Stores the action of the user (such as creating a worksheet) in the user's history.
     * Throws a NotLoggedInException if called when no user is logged in.
     *
     * @param worksheetDetails: details for worksheet generation
     */
    public void storeUserRecord(Map<String, Object> worksheetDetails) throws NotLoggedInException {
        if (isLoggedIn()) {
            historyManager.storeUserRecord(currentUsername, worksheetDetails);
        }
        else {
            throw new NotLoggedInException();
        }
    }

    /**
     * Removes the specified worksheet generation record from the user's history.
     * Throws a NotLoggedInException if called when no user is logged in.
     *
     * @param worksheetKey:    Index of the action to be removed from the list of actions of the user.
     */
    public void removeUserRecord(String worksheetKey) throws RecordDoesNotExistException, NotLoggedInException {

        if (isLoggedIn()) {
            historyManager.removeUserRecord(currentUsername, worksheetKey);
        }
        throw new NotLoggedInException();
    }

}
