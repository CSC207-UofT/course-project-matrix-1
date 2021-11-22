package user_interface;

import user_package.UserController;
import worksheet_maker.WorksheetController;

public class ControllerFactory {
    //TODO: Refactor the of this class
    UserController makeUserController() {
        // Create an instance of user controller to keep track of the user's information
        UserController userController = null;
        try {
            userController = new UserController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userController;
    }
    WorksheetController makeWorksheetController(){
        // Create an instance of worksheet controller to generate the worksheet
        return new WorksheetController();
    }
}
