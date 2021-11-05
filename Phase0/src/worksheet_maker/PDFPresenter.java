package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
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
    private final ImageFacade imageFacade = new ImageFacade();

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
     */
    public PDDocument[] createPDF(Map<String, Object> formatDetails) {
        PDDocument[] qAndAPDF = new PDDocument[]{new PDDocument(), new PDDocument()};
        Map<String, Object> formatEquationDetails = restrictToEquationDetails(formatDetails);
        Map<String, Object> formatArrangeDetails = restrictToArrangeDetails(formatDetails);
        BufferedImage[][] qAndAImages = imageFacade.createResizedImages(formatEquationDetails, worksheet);
        arrangeOnPDFs(qAndAImages, qAndAPDF, formatArrangeDetails);
        return qAndAPDF;
    }

    /**
     * Arranges a list of images on a PDF given the rowNum and columnNum. Enough space will be made at the top for a
     * title.
     *
     * @param qAndAImages          The first list of images is for the question sheet and the second list of images is
     *                             for the answer sheet.
     * @param qAndAPDF             The first PDF is a question sheet and the second PDF is the answer sheet.
     * @param formatArrangeDetails Hashmap showing details necessary for arranging images on a PDF. Includes title,
     *                             number of rows, and number of columns.
     */
    private void arrangeOnPDFs(Image[][] qAndAImages, PDDocument[] qAndAPDF, Map<String, Object> formatArrangeDetails) {

    }

    /**
     * Restricts the Map to only the details necessary for image arrangement.
     *
     * @param formatDetails a Map with all the details.
     * @return the restricted map. Contains the values associated with "title", "numRows", "numColumns" in the
     * formatDetails map, assigned to the same key.
     */
    private Map<String, Object> restrictToArrangeDetails(Map<String, Object> formatDetails) {
        Map<String, Object> formatArrangeDetails = new HashMap<>();
        formatArrangeDetails.put("title", formatDetails.get("title"));
        formatArrangeDetails.put("numRows", formatDetails.get("numRows"));
        formatArrangeDetails.put("numColumns", formatDetails.get("numColumns"));
        return formatArrangeDetails;
    }

    /**
     * Restricts the Map to only the details necessary for equation formatting.
     *
     * @param formatDetails a Map with all the details.
     * @return the restricted map. Contains the values associated with "equationFormat", "numRows", "numColumns" in the
     * formatDetails map, assigned to the same key.
     */
    private Map<String, Object> restrictToEquationDetails(Map<String, Object> formatDetails) {
        Map<String, Object> formatEquationDetails = new HashMap<>();
        formatEquationDetails.put("equationFormat", formatDetails.get("equationFormat"));
        formatEquationDetails.put("numRows", formatDetails.get("numRows"));
        formatEquationDetails.put("numColumns", formatDetails.get("numColumns"));
        return formatEquationDetails;
    }
}
