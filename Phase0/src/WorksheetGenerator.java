/**
 * An interface adapter that can generate and retrieve a PDF.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class WorksheetGenerator {
    private Worksheet ws;
    private EquationGenerator eg;

    /**
     * Creates an instance of WorksheetGenerator that shares a worksheet with PDFPresenter.
     *
     * @param ws an instance of a worksheet that is shared with the PDFPresenter.
     */
    public WorksheetGenerator(Worksheet ws){
        this.ws = ws;
        this.eg = new EquationGenerator();
    }

    /**
     * Create equations for Worksheet.
     * @param equationType The type of equations in a worksheet.
     * @param numEquation Number of equations in a Worksheet.
     * @param difficulty Difficulty level of the question, either 1, 2, or 3 (Easy, Medium, or Hard respectively).
     */
    public static void generateWorksheet(String equationType, int numEquation, int difficulty){
        String[][] WorksheetEquationArray = new String[numEquation][2];
        for (int i = 1; i <= numEquation; i++){
            WorksheetEquationArray.push(eg.createEquation(equationType, difficulty));
        }
        this.ws.setEquations(WorksheetEquationArray);

    }
}
