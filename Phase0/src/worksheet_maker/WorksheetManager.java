package worksheet_maker;

/**
 * Manages worksheet input and output, storing a worksheet during this process.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-25.
 */
public class WorksheetManager implements WorksheetManagerInput, PDFOutput {
    //TODO: get hashmap instead --> look at stan and kerims ui
    private final WorksheetGenerator WG = new WorksheetGenerator();
    private WorksheetOutput ws;

    @Override
    public void setWorksheet(int numOfEquations, char operator, int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        ws = (WorksheetOutput) WG.createWorksheet(numOfEquations, operator, operandRange1, operandRange2, negAllowed);
    }

    @Override
    //TODO: makes this return a PDF for the UI
    public String[][] presentPDF(String title, int numRows, int numColumns, String equationFormat) {
        return ws.equationsToStringArray();
    }
}
