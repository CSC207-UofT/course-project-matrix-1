package worksheet_maker;

import java.util.Map;

/**
 * Generate and present the worksheet based on inputs from the Userinterface.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetController {
    /**
     * Create the worksheet and PDF.
     *
     * @param formatDetails   How the Worksheet will be formatted. Includes equation format, title, number of rows
     *                        and number of columns of questions in a worksheet
     * @param equationDetails Number and types of equations in this Worksheet as follows: numEquations, operator,
     *                        operandRange1, operandRange2, negAllowed.
     * @return String representation of the Worksheet
     */
    public String[][] generateWorksheetAndPDF(Map<String, Object> equationDetails, Map<String, Object> formatDetails) {
        Worksheet ws = new Worksheet();
        WorksheetGenerator worksheetGenerator = new WorksheetGenerator(ws);
        PDFPresenter pdfPresenter = new PDFPresenter(ws);
        //TODO: learn facade design, is this really how we do it? No helper code?
        worksheetGenerator.createWorksheet(equationDetails);
        return pdfPresenter.createPDF(formatDetails);
    }
}
