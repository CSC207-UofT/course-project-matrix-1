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
    static ControllerFactory controllerFactory;
    static UserController userController;
    static WorksheetController worksheetController;

    public static void main(String[] args) {

        // Create an instance of user controller to keep track of the user's information
        userController = controllerFactory.makeUserController();


        // Create an instance of worksheet controller to generate the worksheet
        worksheetController = controllerFactory.makeWorksheetController();

        Screen.userController = userController;
        Screen.worksheetController = worksheetController;
        Screen.main(null);
    }
}