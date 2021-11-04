package user_package;

import exceptions.RecordDoesNotExistException;

import java.util.List;
import java.util.Map;

/**
 * HistoryManager class. Holds user-specific history of worksheets generated.
 * TODO: Allow finding of scores for User records of the same topics.
 *
 * @author Stanley Hua
 * @since 2021-10-26
 */
public class HistoryManager {
    private final Map<String, History> userHistoryMapping;        // stores username to history mapping
    /**
     * Instantiates new HistoryManager with histories.
     * @param dataSource: The source of the user data.
     *
     */
    public HistoryManager(DataAccessInterface dataSource) {
        userHistoryMapping = dataSource.getHistories();
    }

    /**
     * Creates history to hold future records for a newly registered User.
     *
     * @param username of User
     */
    public void beginUserHistory(String username) {
        userHistoryMapping.put(username, new History());
    }

    /**
     * Get user history in Java language classes.
     * @param username of User
     * @return List of worksheet generation records for specified User.
     */
    public List<Map<String, Object>> getUserHistoryRaw(String username) {
        return userHistoryMapping.get(username).getWorksheetHistory();
    }

    /**
     * Get user history in History class.
     * @param username of User
     * @return History containing worksheet generation records for specified User.
     */
    public History getUserHistory(String username) {
        return userHistoryMapping.get(username);
    }

    /**
     * For some User specified by username, stores a record of the latest worksheet generation details.
     *
     * @param username         of User
     * @param worksheetDetails for latest worksheet generated
     */
    public void storeUserRecord(String username, Map<String, Object> worksheetDetails) {
        userHistoryMapping.get(username).addWorksheetRecord(worksheetDetails);
    }

    /**
     * For some User specified by username, removes a specified record (identified by its unique worksheetKey) from
     * User's history.
     *
     * @param username     of User
     * @param worksheetKey unique string identifier for worksheet record
     */
    public void removeUserRecord(String username, String worksheetKey) throws RecordDoesNotExistException {
        userHistoryMapping.get(username).removeWorksheetRecord(worksheetKey);
    }

    /**
     * Stores the score the user received on a specific worksheet record.
     *
     * @param username     of User
     * @param worksheetKey unique string identifier for worksheet record
     * @param score:       that the user received on the specified worksheet generated
     */
    public void setUserScoreForRecord(String username, String worksheetKey, int score) throws RecordDoesNotExistException {
        userHistoryMapping.get(username).setScore(worksheetKey, score);
    }
}
