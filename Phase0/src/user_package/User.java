package user_package;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User class. Holds information about an existing user.
 *      - User profile details cannot be updated currently.
 *
 * @author Stanley Hua, Kerim Saltoglu
 */
public class User implements Serializable {
    private final String username;
    private final String name;
    private final int age;
    private final String role;                                  // e.g. student / teacher / parent

    /**
     * Instantiate userPackage.User object.
     *
     * @param username // username used to access account
     * @param name     // name of userPackage.User
     * @param age      // age of userPackage.User
     * @param role     // position of user (student/teacher/parent)
     */
    protected User(String username, String name, int age, String role) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.role = role;
    }

    /**
     * @return <String> username of userPackage.User
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return <HashMap> containing userPackage.User information (name, age, role)
     */
    public Map<String, Object> getDetails() {
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", this.name);
        userDetails.put("age", this.age);
        userDetails.put("role", this.role);
        return userDetails;
    }
}
