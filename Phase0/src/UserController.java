import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserController {
    /*
    * Collaborators:
    * UserManagerViewer
    * UserManagerUpdater
    *
    */
    private final UserManagerUpdater updater;
    private final UserManagerViewer viewer;
    private String currentUsername;

    public UserController(){
        List<Object> managers = UserManager.createViewerAndUpdater();
        viewer = ((UserManagerViewer) managers.get(0));
        updater = ((UserManagerUpdater) managers.get(1));
    }

    /**
     * Registers a new User. Throws Exception if another User exists with the same username.
     * @param username  // potential username
     * @param name      // name of User
     * @param age       // age of User
     * @param role      // role of User (Student/Parent/Teacher)
     */
    public void createUser(String username, String name, Integer age, String role) throws Exception {
        // Attempt to create new User
        updater.createUser(username, name, age, role);
        // Stores username for future method calls. "Logs in" to User.
        currentUsername = username;
    }


    /**
     * If username is valid, user "logs into" User account by storing the username.
     * @param username  // username input by user
     * @return true if username valid
     */
    public Boolean verifyUsername(String username){
        Boolean verified = this.viewer.verifyUsername(username);
        if (verified) {
            // Stores username for future method calls. "Logs in" to User.
            currentUsername = username;
        }
        return verified;
    }

    public HashMap<String, Object> getUserDetails() throws Exception {
        checkLoggedIn();
        return this.viewer.getUserDetails(this.currentUsername);
    }

    public HashMap<String, Integer> getUserScores() throws Exception {
        checkLoggedIn();
        return this.viewer.getUserScores(this.currentUsername);
    }

    public ArrayList<HashMap<String, Object>> getUserHistory() throws Exception {
        checkLoggedIn();
        return this.viewer.getUserHistory(currentUsername);
    }

    public void storeUserScore(String worksheetKey, Integer score) throws Exception {
        checkLoggedIn();
        this.updater.storeUserScore(currentUsername, worksheetKey, score);
    }

    public void storeUserAction(HashMap<String, Object> userAction) throws Exception {
        checkLoggedIn();
        this.updater.storeUserAction(currentUsername, userAction);
    }

    public void removeUserAction(String worksheetKey, Integer index) throws Exception {
        checkLoggedIn();
        this.updater.removeUserAction(currentUsername, index, worksheetKey);
    }


    /**
     * Checks if a User has been successfully logged in.
     * NOTE: Registering a new account logs the user into the new account.
     * @throws Exception if no current used logged in. (i.e. no valid user verified or created).
     */
    private void checkLoggedIn() throws Exception {
        // Throw exceptions if no user logged in and method is called.
        // TODO: Create new exception class NoUserLoggedInError
        if (currentUsername == null) throw new Exception("No user logged in!");
    }
}
