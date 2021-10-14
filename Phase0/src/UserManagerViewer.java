import java.util.ArrayList;
import java.util.HashMap;

public class UserManagerViewer {
    // txt file to store all the users?
    // load the users that are stored in the txt
    // UserManager Class creates an arraylist of user objects. It passes that list to both viewer and Manager

    // TODO: Finish verifyUsername!
    // TODO: Test verifyUsername!
    private ArrayList<User> users;
    public UserManagerViewer(ArrayList<User> users){this.users = users;}
    public boolean verifyUsername(String username){
        //TODO: Actually implement it HERE!
        // the arraylist of users between viewer updater
        return true; // I just created this method so that my UserController would work
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public HashMap<String, Object> getUserDetails(){
        return new HashMap<>();  // I just created this method so that my UserController would work
        //getUserScores
    }

    public HashMap<String, Integer> getUserScores(){
        return new HashMap<>();  // I just created this method so that my UserController would work
    }

    public ArrayList<HashMap<String, Object>> getUserHistory(){
        // key: String Date of the action of the user
        // value: Description of the action of the user
        // Note: The return type was not specified in the CRC cards so feel free to change it
        return new ArrayList<HashMap<String, Object>>();  // I just created this method so that my UserController would work
    }

}
