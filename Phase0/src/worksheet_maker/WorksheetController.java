package worksheet_maker;

/**
 * An interface adapter that convert UI input to call on the WorksheetGenerator.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetController {
    public void setWorksheet(int numOfEquations, char operator, int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        IWorksheetGenerator wg = new WorksheetGenerator();
        wg.createWorksheet(numOfEquations, operator, operandRange1, operandRange2, negAllowed);

    }
}
