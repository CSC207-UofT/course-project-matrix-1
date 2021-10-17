import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagerViewer {
    // UserManager Class creates an arraylist of user objects. It passes that list to both viewer and Manager


    private final List<User> users;  // This variable is aliased with UserManagerUpdater.users variable

    public UserManagerViewer(List<User> users) {
        this.users = users;
    }

    /**
     * @param username Potential Username of the user
     * @return true if and only if the user is in the system.
     */
    public boolean verifyUsername(String username) {
        // search for the username amongst the registered users in the system
        for (User u : this.getUsers()) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers() {
        return users;
    }

    /**
     * @param username: Username of the user
     * @return HashMap representing the details of the
     */
    public Map<String, Object> getUserDetails(String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                return u.getDetails();
            }
        }
        return new HashMap<>();  // Return empty hash map if the username is invalid
    }

    /**
     * @param username Username of the user
     * @return Hash map representing User scores
     */
    public Map<String, Integer> getUserScores(String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                return u.getScores();
            }
        }
        return new HashMap<>();  // Return empty hash map if the username is invalid
    }

    /**
     * @param username: Username of the user
     * @return Arraylists of hash maps representing past actions of the user.
     */
    public List<Map<String, Object>> getUserHistory(String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                return u.getHistory();
            }
        }
        return new ArrayList<>(); //Return empty arraylist if the username is invalid
    }

}
