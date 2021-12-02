package user_interface;

import user_package.UserController;
import user_package.UserPresenter;
import worksheet_maker.WorksheetController;

import java.util.Arrays;

/**
 * User Interface class. Create instances of user controller and presenter for the user interface.
 *
 * @author Kerim Saltoglu
 * @since 2021-11-29
 */

public class UserAccessInterface {
    // This class creates an instance of UserController and UserPresenter
    // The view classes such as the WorkSheetViewerScreen use the UserPresenter as a Presenter in the MVP architecture.
    static UserController userController;
    static UserPresenter userPresenter;

    public UserAccessInterface() {
    }

    public void makeControllerAndPresenter() {
        // Create an instance of user controller to keep track of the user's information
        // Create an instance of user presenter to display user's information
        try {
            userController = new UserController();
            userPresenter = new UserPresenter(userController.getUserManager(), userController.getHistoryManager(),
                    userController.getCurrentUsername());  // current UserName is aliased
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserController getUserController() {
        return userController;
    }

    public WorksheetController makeWorksheetController() {
        // Create an instance of worksheet controller to generate the worksheet
        return new WorksheetController();
    }

    public UserPresenter getUserPresenter() {
        return userPresenter;
    }
}
