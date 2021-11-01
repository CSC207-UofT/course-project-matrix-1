package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;

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

    public PDFPresenter(WorksheetOutput worksheet) {
        this.worksheet = worksheet;
    }

    /**
     * Creates all the equations in the worksheet using various equation related parameters found in equationDetails.
     *
     * @param formatDetails Hashmap showing details necessary for formatting a PDF. Includes equation format, title,
     *                      number of rows, and number of columns.
     * @return String[][] displaying all the equations in worksheet.
     */
    public String[][] createPDF(Map<String, Object> formatDetails) {
        return worksheet.equationsToStringArray();
    }



}
