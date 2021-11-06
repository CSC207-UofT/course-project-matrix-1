package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
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
     * @param equationDetails Number and types of equations in this Worksheet as follows: numEquations, operator,
     *                        operandRange1, operandRange2, negAllowed.
     * @param formatDetails   How the Worksheet will be formatted. Includes equation format, title, number of rows
     *                        and number of columns of questions in a worksheet
     * @return An array of PDDocuments. The first PDDocument is the questions document, the second is the questions +
     * answers document. To save these documents to a file, use .save("some path.pdf"). Afterwards, close the PDFs
     * using .close().
     */
    public PDDocument[] generateWorksheetAndPDF(Map<String, Object> equationDetails, Map<String, Object> formatDetails) throws IOException {
        Worksheet ws = new Worksheet();
        WorksheetGenerator worksheetGenerator = new WorksheetGenerator(ws);
        PDFPresenter pdfPresenter = new PDFPresenter(ws);
        worksheetGenerator.createWorksheet(equationDetails);
        return pdfPresenter.createPDF(formatDetails);
    }
}
