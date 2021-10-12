/**
 * An interface adapter that can generate, retrieve, and download a PDF to your computer.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class PDFPresenter {
    private Worksheet ws;

    public static void main(String[] args) {
        System.out.println("ss");
    }
    /**
     * Creates an instance of PDFPresenter that shares a worksheet with WorksheetGenerator.
     *
     * @param ws an instance of a worksheet that is shared with the WorksheetGenerator
     */
    public PDFPresenter(Worksheet ws){
        this.ws = ws;
    }

    /**
     * Uses the worksheet information stored in ws to create a new formatted PDF version of the worksheet, and stores
     * that inside ws.
     *
     * @param fontSize the font size of the PDF
     * @param equationFormat how the equation should be displayed. For example, "horizontal" refers to 4 + 5, whereas
     *                       vertical refers to 4 + 5 stacked on top of each other
     */
    public void createWorksheetPDF(int fontSize, String equationFormat){

    }

    /**
     * Accesses the PDF stored in Worksheet using getWorksheetPDF.
     *
     * @return the formatted PDF
     */
    public String getPDF(){
        return "";
    }

    /**
     * Downloads the PDF stored in Worksheet to the specified path.
     *
     * @param path the file path specifying where the file should be downloaded
     */
    public void downloadPDF(String path){

    }

}
