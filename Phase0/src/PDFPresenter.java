/**
 * An interface adapter that generates, retrieves, and downloads a PDF to the users computer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class PDFPresenter {
    public Worksheet ws; //TODO: Change to private once you can use WorksheetGenerator

    /**
     * Creates an instance of PDFPresenter that shares a worksheet with WorksheetGenerator.
     *
     * @param ws an instance of a worksheet that is shared with the WorksheetGenerator.
     */
    public PDFPresenter(Worksheet ws) {
        this.ws = ws;
    }

    /**
     * Uses the worksheet information stored in ws to create a new formatted PDF version of the worksheet, and stores
     * that inside ws.
     *
     * @param title          the title of the PDF, displayed at the top
     * @param fontSize       the font size of the PDF
     * @param equationFormat how the equation should be displayed. For example, "horizontal" refers to 4 + 5, whereas
     *                       vertical refers to 4 + 5 stacked on top of each other
     */
    public void createWorksheetPDF(String title, int fontSize, String equationFormat) {
        String[][] equations = ws.getEquations();
        StringBuilder pdfNoAnswers = new StringBuilder();
        StringBuilder pdfWithAnswers = new StringBuilder();

        pdfNoAnswers.append("Title: ").append(title).append(". Font size: ").append(fontSize).
                append(". EquationBuilder.Equation format: ").append(equationFormat).append(".").append("\n");
        pdfWithAnswers.append("Title: ").append(title).append(". Font size: ").append(fontSize).
                append(". EquationBuilder.Equation format: ").append(equationFormat).append(".").append("\n");
        for (String[] equation : equations) {
            pdfNoAnswers.append(equation[0]).append(" = ___").append("\n");
            pdfWithAnswers.append(equation[0]).append(" = ").append(equation[1]).append("\n");
        }
        String[] pdfs = {pdfNoAnswers.toString().strip(), pdfWithAnswers.toString().strip()};
        ws.setPDFs(pdfs);
    }

    /**
     * Accesses the PDFs stored in Worksheet using getPdfs.
     *
     * @return formatted PDFs. The first has no answers, the second has answers.
     */
    public String[] getPDFs() {
        return ws.getPDFs();
    }

    /**
     * Downloads the PDF stored in Worksheet to the specified path.
     *
     * @param path the file path specifying where the file should be downloaded
     */
    public void downloadPDF(String path) {
        System.out.println("You downloaded these PDF's to " + path);
    }

}
