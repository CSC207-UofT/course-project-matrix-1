package userPackage;

import java.io.FileOutputStream;
import java.io.IOException;
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
     * Stores the action of the user.
     *
     * @param username:         Username
     * @param worksheetDetails: WorksheetDetails
     */
    public void storeUserAction(String username, Map<String, Object> worksheetDetails) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                u.addToHistory(worksheetDetails);
            }
        }
    }

    /**
     * Removes the action of the user at the specified index.
     *
     * @param username: UserName
     * @param index:    Index of the worksheet
     */
    public void removeUserAction(String username, Integer index) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                // Index out of bounds exception is handled in removeFromHistory method
                u.removeFromHistory(index);
            }
        }
    }

    /**
     * Stores the score the user received on the specified worksheet.
     *
     * @param username:     UserName
     * @param worksheetKey: Name of the worksheet
     * @param score:        Score the user received on the specified worksheet
     */
    public void storeUserScore(String username, String worksheetKey, int score) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {  // Check whether the user is valid.
                u.setWorksheetScore(worksheetKey, score); // Invalid worksheet keys are handled in setWorksheetScore.
            }
        }
    }

    // TODO: Save serialized Users to files
    public static void saveUsers(){
        try {
            FileOutputStream usersOut = new FileOutputStream("");
        } catch (IOException i){
            i.printStackTrace();
        }



    }
}
