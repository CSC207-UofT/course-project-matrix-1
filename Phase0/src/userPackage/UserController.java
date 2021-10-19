package userPackage;

import java.util.List;
import java.util.Map;

/**
 * Accesses or updates user information based on inputs from UserInterface.
 * Collaborators: userPackage.UserManagerViewer, userPackage.UserManagerUpdater, userPackage.UserManager.
 *
 * @author Kerim
 * @version 1.0
 * @since 2021-10-13
 */
public class UserController {
    private final UserManagerUpdater updater;
    private final UserManagerViewer viewer;
    private String currentUsername;

    public UserController() {
        List<Object> managers = UserManager.createViewerAndUpdater();
        viewer = ((UserManagerViewer) managers.get(0));
        updater = ((UserManagerUpdater) managers.get(1));
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
     * @param username: Potential username for the user
     * @return true if and only if the username is registered in the system.
     */
    public Boolean verifyUsername(String username) {
        return this.viewer.verifyUsername(username);
    }

    public Map<String, Object> getUserDetails(String username) {
        return this.viewer.getUserDetails(username);  // returns an empty hashmap if the username is invalid
    }

    /**
     * Returns the names and the scores of the past worksheets.
     *
     * @param username: Username of the user
     * @return a hash map of scores for the specified user with keys: WorkSheet name, value: Score
     */
    public Map<String, Integer> getUserScores(String username) {
        return this.viewer.getUserScores(username);  // returns an empty hash map if the user is not in the system
    }

    /**
     * Returns the past actions of the user.
     *
     * @param username: Username of the user
     * @return An arraylist of hash maps.
     */
    public List<Map<String, Object>> getUserHistory(String username) {
        return this.viewer.getUserHistory(username);  // returns an arraylist if the user is not in the system
    }

    /**
     * Registers a new userPackage.User. Throws Exception if another userPackage.User exists with the same username.
     *
     * @param username // potential username
     * @param name     // name of userPackage.User
     * @param age      // age of userPackage.User
     * @param role     // role of userPackage.User (Student/Parent/Teacher)
     */
    public void createUser(String username, String name, Integer age, String role) {
        try {
            updater.createUser(username, name, age, role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores the user's score for the respective worksheet.
     *
     * @param username:     Username of the user
     * @param worksheetKey: Name of the worksheet
     * @param score:        Score of the user for that worksheet
     */
    public void storeUserScore(String username, String worksheetKey, Integer score) {
        this.updater.storeUserScore(username, worksheetKey, score);
    }


    /**
     * Stores the action of the user (such as creating a worksheet) in the user's history.
     *
     * @param username:   Username of the user
     * @param userAction: Action of the user (such as creating a worksheet)
     */
    public void storeUserAction(String username, Map<String, Object> userAction) {
        this.updater.storeUserAction(username, userAction);
    }

    /**
     * Removes the user action from the user's history.
     *
     * @param username: Username of the user
     * @param index:    Index of the action to be removed from the list of actions of the user.
     */
    public void removeUserAction(String username, Integer index) {
        this.updater.removeUserAction(username, index);
    }

}
