import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    /*
    * Collaborators:
    * UserManagerViewer
    * UserManagerUpdater
    *
    */
    private UserManagerUpdater updater;
    private UserManagerViewer viewer;
    private String currentUsername;


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

    public void createUser(String username, String name, Integer age, String role){
        this.updater.createUser(username, name, age, role);
    }

    public void storeUserScore(String username,String worksheetKey, Integer score){
        this.updater.storeUserScore(username, worksheetKey, score);
    }

    public void storeUserAction(String username, HashMap<String, Object> userAction){
        this.updater.storeUserAction(username, userAction);
    }

    public void removeUserAction(String username, String worksheetKey, Integer index){
        this.updater.removeUserAction(username, index, worksheetKey);
    }

}
