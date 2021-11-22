package user_interface;

import user_package.UserController;

public class UserControllerAccess implements ControllerAccessInterface{
    private UserController userController;

    @Override
    public UserController getController() {
        return userController;
    }
}
