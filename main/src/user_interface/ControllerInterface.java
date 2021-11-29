package user_interface;

import user_package.UserController;
import user_package.UserPresenter;
import worksheet_maker.WorksheetController;
/**
 * User Interface class. Create instances of user controller and presenter for the user interface.
 * @author Kerim Saltoglu
 * @since 2021-11-29
 */

public class ControllerInterface {
    // TODO: Rename this class
    static UserController userController;
    static UserPresenter userPresenter;

    public ControllerInterface(){}
    public void makeUserController(){
        // TODO: Rename this method
        // Create an instance of user controller to keep track of the user's information
        try {
             userController = new UserController();
             userPresenter = new UserPresenter(userController.getUserManager(), userController.getHistoryManager(),
                     userController.getCurrentUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserController getUserController(){
        return userController;
    }

    public WorksheetController makeWorksheetController(){
        // Create an instance of worksheet controller to generate the worksheet
        return new WorksheetController();
    }

    public UserPresenter getUserPresenter(){return userPresenter;}
}
