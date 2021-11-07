package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

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
     * @throws IOException if images cannot be added to the PDF.
     */

    public PDDocument[] createPDF(Map<String, Object> formatDetails) throws IOException {
        PDDocument[] worksheetPDFs = instantiatePDFs((int) formatDetails.get("numRows"),
                (int) formatDetails.get("numColumns"), worksheet.getQuestionNumber());
        Map<String, Object> formatEquationDetails = restrictToEquationDetails(formatDetails);
        Map<String, Object> formatArrangeDetails = restrictToArrangeDetails(formatDetails);
        BufferedImage[][] equationImages = imageFacade.createResizedImages(formatEquationDetails, worksheet);
        arrangeOnPDFs(equationImages, worksheetPDFs, formatArrangeDetails);
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

    /**
     * Arranges a list of images on a PDF given the rowNum and columnNum. Enough space will be made at the top for a
     * title. Every PDF has a width of 612 pixels by 792 pixels.
     *
     * @param equationImages          The first list of images is for the question sheet and the second list of images is
     *                             for the answer sheet.
     * @param worksheetPDFs             The first PDF is a question sheet and the second PDF is the answer sheet.
     * @param formatArrangeDetails Hashmap showing details necessary for arranging images on a PDF. Includes title,
     *                             number of rows, and number of columns.
     * @throws IOException if images cannot be added to the PDF.
     */
    private void arrangeOnPDFs(BufferedImage[][] equationImages, PDDocument[] worksheetPDFs, Map<String, Object> formatArrangeDetails) throws IOException {
        PDImageXObject[][] qAndAPDImage = convertImageToPDImage(equationImages, worksheetPDFs);
        for (int i = 0; i < 2; i++) {
            PDPage page = worksheetPDFs[i].getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(worksheetPDFs[i], page);
            for (int j = 0; j < qAndAPDImage[0].length; j++) {
                contentStream.drawImage(qAndAPDImage[i][j], 20, (j+1) * 30);
            }
            contentStream.close();
        }
    }

    private PDImageXObject[][] convertImageToPDImage(BufferedImage[][] equationImages, PDDocument[] worksheetPDFs) throws IOException {
        PDImageXObject[][] pdImageXObjects = new PDImageXObject[equationImages.length][equationImages[0].length];
        for (int i = 0; i < equationImages.length; i++) {
            for (int j = 0; j < equationImages[0].length; j++) {
                pdImageXObjects[i][j] = JPEGFactory.createFromImage(worksheetPDFs[i], equationImages[i][j]);
            }
        }
        return pdImageXObjects;
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