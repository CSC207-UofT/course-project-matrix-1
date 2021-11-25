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

    public static void main(String[] args){
        controllerInterface = new ControllerInterface();
        controllerInterface.makeUserController();
        // Create an instance of user controller to keep track of the user's information

        try {
            userController = new UserController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        userController = controllerInterface.getUserController();

        // Create an instance of worksheet controller to generate the worksheet
        worksheetController = new WorksheetController();
        worksheetController = controllerInterface.makeWorksheetController();

        Screen.userController = userController;
        Screen.worksheetController = worksheetController;
        Screen.main(null);
    }
}