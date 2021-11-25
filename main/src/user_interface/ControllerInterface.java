package user_interface;

import user_package.UserController;
import worksheet_maker.WorksheetController;


public class ControllerInterface {
    static UserController userController;

    public ControllerInterface(){}
    public void makeUserController(){
        // Create an instance of user controller to keep track of the user's information
        try {
             userController = new UserController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserController getUserController(){
        return userController;
    }
    WorksheetController makeWorksheetController(){
        // Create an instance of worksheet controller to generate the worksheet
        return new WorksheetController();
    }
}
