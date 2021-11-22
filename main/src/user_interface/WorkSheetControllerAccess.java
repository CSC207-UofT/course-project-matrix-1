package user_interface;

import worksheet_maker.WorksheetController;

public class WorkSheetControllerAccess implements ControllerAccessInterface{
    static WorksheetController worksheetController;

    public WorksheetController returnController(){
        return new WorksheetController();
    }
}
