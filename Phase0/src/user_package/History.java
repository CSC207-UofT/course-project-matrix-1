package user_package;

import exceptions.InvalidInputException;
import exceptions.RecordDoesNotExistException;

import java.io.Serializable;
import java.util.*;

/**
 * History class. Holds a user's history of records for each worksheet generated and score (if given)
 * - Worksheet details include parameters to regenerate worksheet
 * - Each worksheet generated (+ score) is called a record in the user's history.
 * - Is serializable.
 *
 * @author Stanley Hua
 * @since 2021-10-26
 */
public class History implements Serializable {
    private final List<Map<String, Object>> history;

    /**
     * Instantiate userPackage.History object.
     */
    protected History() {
        this.history = new ArrayList<>();
    }

    // GETTER METHODS

    /**
     * @return copy ArrayList of all worksheets generated in history
     */
    public List<Map<String, Object>> getWorksheetHistory() {
        List<Map<String, Object>> historyCopy = new ArrayList<>();
        for (Map<String, Object> worksheet : history) {
            Map<String, Object> worksheetCopy = new HashMap<>(worksheet);
            historyCopy.add(worksheetCopy);
        }
        return historyCopy;
    }

    /**
     * Returns details for generated worksheet with specified worksheetKey. Raise exception if worksheet not found.
     *
     * @param worksheetKey specifies worksheet type and difficulty
     * @return optional HashMap of worksheet details
     */
    public Map<String, Object> findWorksheetRecord(String worksheetKey) throws RecordDoesNotExistException {
        for (Map<String, Object> worksheet : this.history) {
            if (worksheet.get("worksheetKey").equals(worksheetKey)) {
                return worksheet;
            }
        }
        throw new RecordDoesNotExistException();
    }

    // SETTER METHODS

    /**
     * Add details of newly generated worksheet to history.
     * Precondition:
     * - worksheetDetails identifier worksheetKey must be unique.
     *
     * @param worksheetDetails HashMap of details for some generated worksheet
     */
    public void addWorksheetRecord(Map<String, Object> worksheetDetails) {
        this.history.add(worksheetDetails);
    }

    /**
     * Removes specified record from history.
     * - Finds specific worksheet details and deletes it.
     * - Exception is raised if worksheet is not found.
     *
     * @param worksheetKey unique worksheet generated identifier
     */
    public void removeWorksheetRecord(String worksheetKey) throws RecordDoesNotExistException {
        this.history.remove(this.findWorksheetRecord(worksheetKey));
    }


    /**
     * Update score for a worksheet record. Verifies that score is not greater than the number of questions
     */
    @SuppressWarnings("unchecked")
    public void setScore(String worksheetKey, int score) throws RecordDoesNotExistException {
        Map<String, Object> worksheet = this.findWorksheetRecord(worksheetKey);
        Map<String, Object> equationDetails = (Map<String, Object>) worksheet.get("equationDetails");

        if ((int) equationDetails.get("numOfEquations") >= score & score >= 0) {
            worksheet.put("score", score);
        } else {
            throw new InvalidInputException();
        }

    }
}
