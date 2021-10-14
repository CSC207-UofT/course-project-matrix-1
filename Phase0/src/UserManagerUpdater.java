import java.util.ArrayList;
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

}
