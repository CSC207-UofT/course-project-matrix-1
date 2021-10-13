import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private final String username;
    private final String name;
    private final int age;
    private final String role;                  // e.g. student / teacher / parent
    private HashMap<String, Integer> scores;    // worksheet name : scores
    private ArrayList<HashMap> history;         // list of Worksheet details stored in a HashMap

    /** Instantiate User object.
     * @param username  // username used to access account
     * @param name      // name of User
     * @param age       // age of User
     * @param role      // position of user (student/teacher/parent)
     */
    public User(String username, String name, int age, String role){
        this.username = username;
        this.name = name;
        this.age = age;
        this.role = role;
        this.scores = new HashMap<>();
        this.history = new ArrayList();
    }


    // GETTER METHODS
    /**
     * @return <String> username of User
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return <HashMap> containing User information (name, age, role)
     */
    public HashMap<String, Object> getDetails(){
        HashMap<String, Object> userDetails = new HashMap<String, Object>();
        userDetails.put("name", this.name);
        userDetails.put("age", this.age);
        userDetails.put("role", this.role);
        return userDetails;
    }

    /**
     * @return <HashMap> of worksheet identifier (key) to recorded score (value)
     */
    public HashMap<String, Integer> getScores() {
        return scores;
    }

    /**
     * @return <HashMap> of action identifier (key) to worksheet details (value)
     */
    public ArrayList<HashMap> getHistory() {
        return history;
    }

    // SETTER METHODS
    /** Record score on worksheet (with difficulty). Overrides existing values
     * @param worksheetKey  // unique identifier for worksheet
     * @param score         // integer score on worksheet
     */
    public void setWorksheetScore(String worksheetKey, int score){
        // TODO: Verify score is >= 0 and <= number of questions on worksheet
        this.scores.put(worksheetKey, score);
    }

    public void addToHistory(HashMap worksheetDetails){
        this.history.add(worksheetDetails);
    }
}
