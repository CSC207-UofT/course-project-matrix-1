package userPackage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Updates user information based on inputs from UserInterface.
 * Collaborators: userPackage.UserManager.
 *
 * @author Kerim
 */

public class UserManagerUpdater {

    // when you update a user here, also update it at userPackage.UserManagerViewer
    private final List<User> users;  // This variable is aliased with userPackage.UserManagerViewer.users variable

    public UserManagerUpdater(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    /**
     * Registers a new userPackage.User. Throws Exception if another userPackage.User exists with the same username.
     *
     * @param username // potential username
     * @param name     // name of userPackage.User
     * @param age      // age of userPackage.User
     * @param role     // role of userPackage.User (Student/Parent/Teacher)
     */
    public void createUser(String username, String name, Integer age, String role) throws Exception {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                // TODO: Create new Exception class: UsernameTakenError
                throw new Exception("Username is already taken!");
            }
        }
        User newUser = new User(username, name, age, role);
        this.getUsers().add(newUser);  // adds the new user to the users list
    }


    /**
     * Serializes and saves list of all current User objects at /src/userPackage/usersData/.
     */
    public void saveUsers(){
        try {
            // TODO: Remove Phase0
            FileOutputStream usersOut = new FileOutputStream("Phase0/src/userPackage/usersData/users.ser");
            ObjectOutputStream out = new ObjectOutputStream(usersOut);
            out.writeObject(this.users);
            out.close();
            usersOut.close();
        } catch (IOException i){    // if IO exception occurs, print exception details
            i.printStackTrace();
        }
    }
}