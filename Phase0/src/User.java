import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private final String username;
    private final String name;
    private final int age;
    private final String role;                                  // e.g. student / teacher / parent
    private final Map<String, Integer> scores;                  // worksheet name : scores
    private final List<Map<String, Object>> history;            // list of Worksheet details stored in a HashMap

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
    public Map<String, Object> getDetails() {
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", this.name);
        userDetails.put("age", this.age);
        userDetails.put("role", this.role);
        return userDetails;
    }

    /**
     * @return <HashMap> of worksheet identifier (key) to recorded score (value)
     */
    public Map<String, Integer> getScores() {
        return scores;
    }

    /**
     * @return <HashMap> of action identifier (key) to worksheet details (value)
     */
    public List<Map<String, Object>> getHistory() {
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
        Optional<Map<String, Object>> detailsOptional = findWorksheetInHistory(worksheetKey);
        if (detailsOptional.isEmpty()) {
            // TODO: If no worksheet of specified type was generated, throw an exception
            throw new IllegalArgumentException("Worksheet type has not been generated yet!");
        } else {
            // Get worksheet details
            Map<String, Object> details = detailsOptional.get();
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
    public Optional<Map<String, Object>> findWorksheetInHistory(String worksheetKey) {
        // Loop from the end of history to find a worksheet of specified worksheetKey.
        int i = this.history.size();
        while (i != 0) {
            i = i - 1;
            Map<String, Object> currMap = this.history.get(i);
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
    public void addToHistory(Map<String, Object> worksheetDetails) {
        this.history.add(worksheetDetails);
    }


    /**
     * Removes item in history at specified position (each item contains worksheet generation details and score).
     * If score of item was the best so far for
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
        // Temporarily store details of history item, then remove from history.
        Map<String, Object> worksheetDetails = this.history.get(elementIndex);
        Object worksheetKey = worksheetDetails.get("worksheetKey");
        Object recordScore = worksheetDetails.get("score");

        this.history.remove(elementIndex);

        // If recorded score to be removed is the best so far, look for replacement, or set to null.
        if (this.scores.get((String) worksheetKey) == recordScore){

            int bestScore = -1;
            // Loop through remaining records for the best score
            for (Map<String, Object> currRecord : this.history){
                // Access record
                Object currKey = currRecord.get("worksheetKey");
                Object currScore= currRecord.get("score");

                // If same exact worksheet type, record score if better than current best score
                if (currKey.equals(worksheetKey) & ((int) currScore > bestScore)) bestScore = (int) currScore;
            }

            // Update score
            if (bestScore != -1){
                // Update to next best score if replacement found
                this.scores.put((String) worksheetKey, bestScore);
            } else {
                // If not, remove worksheetKey from map
                this.scores.remove(worksheetKey);
            }
        }

    }
}
