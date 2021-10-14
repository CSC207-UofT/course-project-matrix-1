import java.util.HashMap;

public class UserController {
    /*
    * Collaborators:
    * UserManagerViewer
    * UserManagerUpdater
    * User?
    */

    private String currentUsername;

    public String getCurrentUsername() {
        return currentUsername;
    }
    public String verifyUsername(String username){
        UserManagerViewer viewer = new UserManagerViewer();
        if(viewer.verifyUsername(username)){
            HashMap<String, Object> userDetails = viewer.getUserDetails();
        }
        return "Invalid user name, please try again";
    }
}
