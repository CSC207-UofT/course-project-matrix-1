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
    static ControllerInterface controllerInterface;
    static UserController userController;
    static WorksheetController worksheetController;

    public static void main(String[] args) {

        // Create an instance of user controller to keep track of the user's information
        userController = controllerInterface.makeUserController();


        // Create an instance of worksheet controller to generate the worksheet
        worksheetController = controllerInterface.makeWorksheetController();

        Screen.userController = userController;
        Screen.worksheetController = worksheetController;
        Screen.main(null);
    }
}