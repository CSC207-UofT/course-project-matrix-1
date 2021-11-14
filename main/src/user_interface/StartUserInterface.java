package user_interface;
import user_package.UserController;
import worksheet_maker.WorksheetController;

/**
 * User Interface class. Create instances of user controller and worksheet controller
 * for screen (start screen).
 *
 * @author Ethan Ing
 * @since 2021-11-01
 */
class StartUserInterface {

    static UserController user_controller;
    static WorksheetController worksheet_controller;

    public static void main(String[] args) {

        // Create an instance of user controller to keep track of the user's information
        try {
            user_controller = new UserController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create an instance of worksheet controller to generate the worksheet
        worksheet_controller = new WorksheetController();

        Screen.userController = user_controller;
        Screen.worksheetController = worksheet_controller;
        Screen.main(null);
    }
}