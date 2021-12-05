package user_package;

import exceptions.NotLoggedInException;

import java.util.List;
import java.util.Map;

/**
 * Presenter class. Acts as the presenter in the Model-View-Presenter architecture.
 * The view classes in the user interface access this class as the Presenter.
 *
 * @author Kerim Saltoglu
 * @since 2021-11-29
 */

public class UserPresenter {
    private final UserManager userManager;
    private final HistoryManager historyManager;
    private final String[] currentUsername;  // it is an alias of the currentUsername from UserController

    public UserPresenter(UserManager userManager, HistoryManager historyManager, String[] currentUsername) {
        this.userManager = userManager;
        this.historyManager = historyManager;
        this.currentUsername = currentUsername;
    }

    /**
     * @return true if a user is logged in.
     */
    public Boolean isLoggedIn() {
        return currentUsername[0] != null;
    }

    /**
     * Returns the history of worksheet generation records of a user.
     * Throws a NotLoggedInException if called when no user is logged in.
     *
     * @return list containing worksheet generation records (details)
     */
    public List<Map<String, Object>> getUserHistory() throws NotLoggedInException {
        if (isLoggedIn()) {
            return historyManager.getUserHistoryRaw(currentUsername[0]);
        }
        throw new NotLoggedInException();
    }

    public Map<String, Object> getUserDetails() throws NotLoggedInException {
        if (isLoggedIn()) {
            return userManager.getUserDetails(currentUsername[0]);
        }
        throw new NotLoggedInException();
    }


}
