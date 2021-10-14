import java.util.HashMap;
import java.util.Map;

public class UserManagerViewer {
    public UserManagerViewer(){

    }
    public boolean verifyUsername(String username){
        return true; // I just created this method so that my UserController would work
    }

    public HashMap<String, String> getUserDetails(){
        return new HashMap<>();  // I just created this method so that my UserController would work
        //getUserScores
    }

    public HashMap<String, String> getUserScores(){
        return new HashMap<>();  // I just created this method so that my UserController would work
    }

    public HashMap<String, String> getUserHistory(){
        // key: String Date of the action of the user
        // value: Description of the action of the user
        // Note: The return type was not specified in the CRC cards so feel free to change it
        return new HashMap<>();  // I just created this method so that my UserController would work
    }

}
