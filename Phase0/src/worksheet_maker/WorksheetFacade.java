package worksheet_maker;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Generate and present the worksheet based on inputs from the Userinterface.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetFacade {
    //TODO: Make these variable names
    private WorksheetGenerator wg;
    private PDFPresenter p;

    /**
     * Create the worksheet and PDF.
     *
     * @param formatDetails   How the Worksheet will be formatted. Includes equation format, title, number of rows
     *                        and number of columns of questions in a worksheet
     * @param equationDetails Number and types of equations in this Worksheet as follows: numEquations, operator,
     *                        operandRange1, operandRange2, negAllowed.
     * @return String representation of the Worksheet
     */
    //TODO: Use a map instead of hashmap??
    public String[][] generateWorksheetAndPDF(HashMap<String, Object> equationDetails, HashMap<String, Object> formatDetails) {
        Worksheet ws = new Worksheet();
        this.wg = new WorksheetGenerator(ws);
        this.p = new PDFPresenter(ws);
        generateWorksheet(equationDetails);
        return generatePDF(formatDetails);
    }

    /**
     * Add equations to the Worksheet instance currently assigned to it.
     *
     * @param equationDetails Number and types of equations in this Worksheet as follows: numEquations, operator,
     *                        operandRange1, operandRange2, negAllowed.
     */
    private void generateWorksheet(HashMap<String, Object> equationDetails) {
        wg.createWorksheet(equationDetails);
    }

    /**
     * Use the Worksheet instance currently assigned and formatting details to generate a PDF.
     *
     * @param formatDetails How the Worksheet will be formatted. Includes equation format, title, number of rows
     *                      and number of columns of questions in a worksheet
     * @return String representation of the Worksheet
     */
    private String[][] generatePDF(HashMap<String, Object> formatDetails) {
        return p.createPDF(formatDetails);
    }

    public static void main(String[] args) {
        HashMap<String, Object> myEquationDetails = new HashMap<>();
        myEquationDetails.put("numOfEquations", 5);
        myEquationDetails.put("operator", '+');
        myEquationDetails.put("operandRange1", new int[]{1, 10});
        myEquationDetails.put("operandRange2", new int[]{1, 10});
        myEquationDetails.put("negAllowed", true);

        HashMap<String, Object> myFormatDetails = new HashMap<>();
        WorksheetFacade wf = new WorksheetFacade();
        System.out.println(Arrays.deepToString(wf.generateWorksheetAndPDF(myEquationDetails, myFormatDetails)));
    }


}
