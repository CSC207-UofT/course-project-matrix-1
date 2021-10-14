import java.util.HashMap;

public class UserController {
    /*
    * Collaborators:
    * UserManagerViewer
    * UserManagerUpdater
    */

    private String currentUsername;

    public String getCurrentUsername() {
        return currentUsername;
    }
    public Boolean verifyUsername(String username){
        /*
        UserManagerViewer viewer = new UserManagerViewer();
         if(viewer.verifyUsername(username)){
            HashMap<String, Object> userDetails = viewer.getUserDetails();
        }
         */
        return true;
    }
}
