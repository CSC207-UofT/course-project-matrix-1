import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class User {
    private final String username;
    private final String name;
    private final int age;
    private final String role;                  // e.g. student / teacher / parent
    private final HashMap<String, Integer> scores;    // worksheet name : scores
    private final ArrayList<HashMap<String, Object>> history;         // list of Worksheet details stored in a HashMap

    /**
     * Instantiate User object.
     *
     * @param username // username used to access account
     * @param name     // name of User
     * @param age      // age of User
     * @param role     // position of user (student/teacher/parent)
     */
    public User(String username, String name, int age, String role) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.role = role;
        this.scores = new HashMap<>();
        this.history = new ArrayList<>();
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
    public HashMap<String, Object> getDetails() {
        HashMap<String, Object> userDetails = new HashMap<>();
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
    public ArrayList<HashMap<String, Object>> getHistory() {
        return history;
    }

    // SETTER METHODS

    /**
     * Record score on specific worksheet type (with difficulty).
     * Checks if input score exceeds number of questions IFF a worksheet was recently generated of specified type.
     * Overrides existing values.
     *
     * @param worksheetKey // unique identifier for worksheet
     * @param score        // integer score on worksheet
     */
    public void setWorksheetScore(String worksheetKey, int score) {
        // Verify score is >= 0
        if (score < 0) {
            throw new IllegalArgumentException("Negative score input!");
        }

        // Retrieve worksheet generated details
        Optional<HashMap<String, Object>> detailsOptional = findWorksheetInHistory(worksheetKey);
        if (detailsOptional.isEmpty()) {
            // TODO: If no worksheet of specified type was generated, throw an exception
             throw new IllegalArgumentException("Worksheet type has not been generated yet!");
        } else {
            // Get worksheet details
            HashMap<String, Object> details = detailsOptional.get();
            // If details available, check if input score is within the max score.
            Object numQuestions = details.get("numQuestions");
            if (score > (int) numQuestions) {
                throw new IllegalArgumentException("Score input exceeds maximum possible score!");
            }
        }
        // Record score
        this.scores.put(worksheetKey, score);
    }


    /**
     * Returns details for latest worksheet generated of specified type.
     *
     * @param worksheetKey // specifies worksheet type and difficulty
     * @return optional HashMap of worksheet details
     */
    public Optional<HashMap<String, Object>> findWorksheetInHistory(String worksheetKey) {
        // Loop from the end of history to find a worksheet of specified worksheetKey.
        int i = this.history.size();
        while (i != 0) {
            i = i - 1;
            HashMap<String, Object> currMap = this.history.get(i);
            if (currMap.get("worksheetKey") == worksheetKey) {
                // Wraps object in Optional object
                return Optional.of(currMap);
            }
        }
        return Optional.empty();
    }


    /**
     * Add details of worksheet generation to history.
     *
     * @param worksheetDetails // HashMap of details for some generated worksheet
     */
    public void addToHistory(HashMap<String, Object> worksheetDetails) {
        this.history.add(worksheetDetails);
    }


    /**
     * Removes action in history at specified position.
     * Precondition:
     * - index must be within a valid range.
     *
     * @param index // index of action in history to remove
     */
    public void removeFromHistory(int index) {
        // Check if index exceeds current number of items in history.
        if (Math.abs(index) >= this.history.size()) {
            throw new ArrayIndexOutOfBoundsException("Index provided exceeds length of list of user history.");
        }

        // If negative index given, convert to its equivalent positive index.
        int elementIndex;
        if (index < 0) {
            elementIndex = this.history.size() + index;
        } else {
            elementIndex = index;
        }
        this.history.remove(elementIndex);
    }
}
