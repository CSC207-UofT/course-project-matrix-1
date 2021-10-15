import java.util.HashMap;
import java.util.List;

public class UserManagerUpdater {

    // when you update a user here, also update it at UserManagerViewer
    private final List<User> users;  // This variable is aliased with UserManagerViewer.users variable
    public UserManagerUpdater(List<User> users){
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void createUser(String username, String name, Integer age, String role){
        User newUser = new User(username, name, age, role);
        this.getUsers().add(newUser);  // adds the new user to the users list
    }

    /**
     * @param username: Username
     * @param worksheetDetails: WorksheetDetails
     */
    public void storeUserAction(String username, HashMap<String, Object> worksheetDetails){
        for(User u: this.users){
            if(u.getUsername().equals(username)){u.addToHistory(worksheetDetails);}
        }
    }

    /**
     * @param username: UserName
     * @param index: Index of the worksheet
     */
    public void removeUserAction(String username, Integer index, String worksheetKey){
        for(User u: this.users){
            if(u.getUsername().equals(username)){
                u.removeFromHistory(index);}
        }
    }

    public void storeUserScore(String username, String worksheetKey, int score){
        for(User u: this.users){
            if(u.getUsername().equals(username)){u.setWorksheetScore(worksheetKey, score);}
        }
    }

}
