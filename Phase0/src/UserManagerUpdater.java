import java.util.ArrayList;
import java.util.HashMap;

public class UserManagerUpdater {
    // when you update a user here, also update it at UserManagerViewer
    private final ArrayList<User> users;
    public UserManagerUpdater(ArrayList<User> users){
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void createUser(String username, String name, Integer age, String role){
        User newUser = new User(username, name, age, role);
        this.getUsers().add(newUser);  // adds the new user to the users list
    }

}
