package worksheet_maker;

/**
 * An interface adapter that convert UI input to call on the WorksheetGenerator.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetManager implements WorksheetManagerInput, PDFOutput {
    //TODO: get hashmap instead --> look at stan and kerims ui
    private final WorksheetGenerator WG = new WorksheetGenerator();
    private WorksheetOutput ws;

    @Override
    public void setWorksheet(int numOfEquations, char operator, int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        ws = (WorksheetOutput) WG.createWorksheet(numOfEquations, operator, operandRange1, operandRange2, negAllowed);
    }
}
