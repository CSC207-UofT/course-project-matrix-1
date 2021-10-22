/**
 * An interface adapter that can generate and retrieve a PDF.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class WorksheetGenerator {
    private final Worksheet ws;
    private final EquationGenerator eg;

    /**
     * Creates an instance of WorksheetGenerator that shares a worksheet with PDFPresenter.
     *
     * @param ws an instance of a worksheet that is shared with the PDFPresenter.
     */
    public WorksheetGenerator(Worksheet ws) {
        this.ws = ws;
        this.eg = new EquationGenerator();
    }

    /**
     * Create equations for Worksheet.
     *
     * @param equationType The type of Equations in ws.
     * @param numEquation  Number of Equations in ws.
     * @param difficulty   Difficulty level of an EquationBuilder.Equation in ws, either 1, 2, or 3 (Easy, Medium, or Hard).
     */
    public void generateWorksheet(String equationType, int numEquation, String difficulty) {
        String[][] WorksheetEquationArray = new String[numEquation][2];
        int difficultyInt;
        if (difficulty.equals("Easy")) {
            difficultyInt = 1;
        } else if (difficulty.equals("Medium")) {
            difficultyInt = 2;
        } else {
            difficultyInt = 3;
        }
        for (int i = 0; i < numEquation; i++) {
            WorksheetEquationArray[i] = eg.createEquation(equationType, difficultyInt);
        }
        this.ws.setEquations(WorksheetEquationArray);

    }
}
