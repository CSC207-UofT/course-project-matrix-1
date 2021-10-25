package worksheet_maker;


/**
 * An interface adapter that generates, retrieves, and downloads a PDF to the users computer.
 *
 * @author Will Jeong, Sean Jeong
 * @version 2.0
 * @since 2021-10-12
 */
public class PDFPresenter {

    /**
     * Uses the worksheet information stored in ws and formattign details from UI to create a new formatted PDF version of the worksheet. This will also dispaly the PDF.
     *
     * @param title          the title of the PDF, displayed at the top
     * @param numRows        the number of question rows on the PDF
     * @param numColumns     the number of question columns on the PDF
     * @param equationFormat how the equation should be displayed. For example, "horizontal" refers to 4 + 5, whereas
     *                       vertical refers to 4 + 5 stacked on top of each other
     */
    public void createWorksheetPDF(String title, int numRows, int numColumns, String equationFormat) {
//        String[][] equations = Worksheet.equationsToStringArray();
//        StringBuilder pdfNoAnswers = new StringBuilder();
//        StringBuilder pdfWithAnswers = new StringBuilder();
//
//        pdfNoAnswers.append("Title: ").append(title).append(". Font size: ").append(fontSize).
//                append(". EquationBuilder.Equation format: ").append(equationFormat).append(".").append("\n");
//        pdfWithAnswers.append("Title: ").append(title).append(". Font size: ").append(fontSize).
//                append(". EquationBuilder.Equation format: ").append(equationFormat).append(".").append("\n");
//        for (String[] equation : equations) {
//            pdfNoAnswers.append(equation[0]).append(" = ___").append("\n");
//            pdfWithAnswers.append(equation[0]).append(" = ").append(equation[1]).append("\n");
//        }
//
//        System.out.println(pdfNoAnswers);
//        System.out.println(pdfWithAnswers);
//        //String[] pdfs = {pdfNoAnswers.toString().strip(), pdfWithAnswers.toString().strip()};

    }


    /**
     * Downloads the PDF stored in old_worksheet_maker.Worksheet to the specified path.
     *
     * @param path the file path specifying where the file should be downloaded
     */
    public void downloadPDF(String path) {
        System.out.println("You downloaded these PDF's to " + path);
    }

}