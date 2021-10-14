import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManagerViewer {
    // txt file to store all the users?
    // load the users that are stored in the txt
    // UserManager Class creates an arraylist of user objects. It passes that list to both viewer and Manager


    private final List<User> users;  // This variable is aliased with UserManagerUpdater.users variable
    public UserManagerViewer(List<User> users){this.users = users;}
    public boolean verifyUsername(String username){
        for(User u: this.getUsers()){
            if(u.getUsername().equals(username)){return true;}
        }
        return false;
    }

    public List<User> getUsers() {
        return users;
    }

    public HashMap<String, Object> getUserDetails(String username){
        for(User u: this.users){
            if(u.getUsername().equals(username)){return u.getDetails();}
        }
        return new HashMap<>();  //Return empty hash map if the username is invalid
    }

    public HashMap<String, Integer> getUserScores(String username){
        for(User u: this.users){
            if(u.getUsername().equals(username)){return u.getScores();}
        }
        return new HashMap<>();  //Return empty hash map if the username is invalid
    }

    public ArrayList<HashMap> getUserHistory(String username){
        for(User u: this.users){
            if(u.getUsername().equals(username)){return u.getHistory();}
        }
        // Note: The return type was not specified in the CRC cards so feel free to change it
        return new ArrayList<>(); //Return empty arraylist if the username is invalid
    }

}
