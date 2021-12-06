package user_interface;

import user_package.UserController;
import user_package.UserPresenter;
import worksheet_maker.WorksheetController;

/**
 * StartUserInterface class. Instantiates user controller & user presenter for user functionality, and
 * worksheet controller for worksheet generation functionality. This allows all screens access to these
 * controllers/presenters.
 *
 * @author Ethan Ing, Stanley Hua
 * @version 2
 * @since 2021-12-04
 */
class StartUserInterface {
    public static void main(String[] args) {
        try {
            // Controller & Presenter for user functionality
            Screen.userController = new UserController();
            Screen.userPresenter = new UserPresenter(Screen.userController.getUserManager(),
                    Screen.userController.getHistoryManager(),
                    Screen.userController.getCurrentUsername());

            // Controller for worksheet generation
            Screen.worksheetController = new WorksheetController();

            // Start GUI
            Screen.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}