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
    private final UserManagerUpdater  updater;
    private final UserManagerViewer viewer;
    private String currentUsername;

    public UserController(){
        List<Object> managers = UserManager.createViewerAndUpdater();
        viewer = ((UserManagerViewer) managers.get(0));
        updater = ((UserManagerUpdater) managers.get(1));
    }
    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String username){
        this.currentUsername = username;
    }
    public Boolean verifyUsername(String username){
        return this.viewer.verifyUsername(username);
    }

    public HashMap<String, Object> getUserDetails(String username){
        return this.viewer.getUserDetails(username);  // returns an empty hashmap if the username is invalid
    }

    public HashMap<String, Integer> getUserScores(String username){
        return this.viewer.getUserScores(username);
    }

    public ArrayList<HashMap<String, Object>> getUserHistory(String username){
        return this.viewer.getUserHistory(username);
    }

    /**
     * Registers a new User. Throws Exception if another User exists with the same username.
     * @param username  // potential username
     * @param name      // name of User
     * @param age       // age of User
     * @param role      // role of User (Student/Parent/Teacher)
     */
    public void createUser(String username, String name, Integer age, String role){
        try {
            updater.createUser(username, name, age, role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeUserScore(String username,String worksheetKey, Integer score){
        this.updater.storeUserScore(username, worksheetKey, score);
    }

    public void storeUserAction(String username, HashMap<String, Object> userAction){
        this.updater.storeUserAction(username, userAction);
    }

    public void removeUserAction(String username, Integer index){
        this.updater.removeUserAction(username, index);
    }

}
