package worksheet_maker;

import equation_parameters.EquationDetails;
import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

/**
 * Generate and present the worksheet based on inputs from the UserInterface.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetController {
    /**
     * Creates a PDF representation of the worksheet.
     *
     * @param equationDetails Number and types of equations in this Worksheet as follows: numEquations, operator,
     *                        operandRange1, operandRange2, negAllowed.
     * @param formatDetails   How the Worksheet will be formatted. Includes equation format, title, number of rows
     *                        and number of columns of questions in a worksheet
     * @return An array of PDDocuments. The first PDDocument is the questions document, the second is the questions +
     * answers document. To save these documents to a file, use .save("some path.pdf"). Afterwards, close the PDFs
     * using .close().
     * @throws IOException if images cannot be added to the PDF.
     */
    public PDDocument[] generateWorksheetAndPDF(EquationDetails equationDetails, FormatDetails formatDetails,
                                                int seed)
            throws IOException {
        Worksheet ws = new Worksheet();
        WorksheetGenerator worksheetGenerator = new WorksheetGenerator(ws, seed);
        PDFPresenter pdfPresenter = new PDFPresenter(ws);
        worksheetGenerator.populateWorksheet(equationDetails);
        return pdfPresenter.createPDF(formatDetails);
    }
}
