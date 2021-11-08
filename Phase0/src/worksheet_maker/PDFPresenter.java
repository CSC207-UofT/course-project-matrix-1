package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.Map;

/**
 * Generates a PDF through the WorksheetOutput interface.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class PDFPresenter {
    //Worksheet output rather than Worksheet
    private final WorksheetOutput worksheet;
    private final EquationsToPDImages equationsToPD = new EquationsToPDImages();
    private final PDFArranger pdfArranger = new PDFArranger();


    public PDFPresenter(WorksheetOutput worksheet) {
        this.worksheet = worksheet;
    }

    /**
     * Creates all the equations in the worksheet using various equation related parameters found in equationDetails.
     *
     * @param formatDetails Hashmap showing details necessary for formatting a PDF. Includes equation format, title,
     *                      number of rows, and number of columns.
     * @return A list of PDF's, where the first is a question sheet and the second is the answer sheet. Both pdfs can
     * be saved to a path using .save().
     * @throws IOException if images cannot be added to the PDF.
     */

    public PDDocument[] createPDF(Map<String, Object> formatDetails) throws IOException {
        PDDocument[] worksheetPDFs = instantiatePDFs((int) formatDetails.get("numRows"),
                (int) formatDetails.get("numColumns"), worksheet.getQuestionNumber());
        PDImageXObject[][] equationImages = equationsToPD.createResizedImages(formatDetails,
                worksheet.equationsToStringArray(), worksheetPDFs);
        pdfArranger.arrangeOnPDFs(equationImages, worksheetPDFs, formatDetails);
        return worksheetPDFs;
    }

    /**
     * Instantiates the question and answer PDF with the correct number of pages.
     *
     * @param numRows        the number of rows in the PDF.
     * @param numColumns     the number of columns in the PDF.
     * @param questionNumber the number of questions in the PDF.
     * @return an array of the instantiated PDFs, as [questionPDF, answerPDF].
     */
    private PDDocument[] instantiatePDFs(int numRows, int numColumns, int questionNumber) {
        int questionsPerPage = numColumns * numRows;
        int pagesRequired = (int) (Math.ceil(1.0 * questionNumber / questionsPerPage));
        PDDocument[] worksheetPDFs = new PDDocument[]{new PDDocument(), new PDDocument()};
        for (PDDocument pdDocument : worksheetPDFs) {
            for (int j = 0; j < pagesRequired; j++) {
                pdDocument.addPage(new PDPage());
            }
        }
        return worksheetPDFs;
    }


}
