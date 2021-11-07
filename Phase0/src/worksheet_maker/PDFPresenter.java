package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
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
    private final EquationsToResizedImages equationsToResizedImages = new EquationsToResizedImages();
    int PDF_WIDTH = 612;
    int PDF_HEIGHT = 792;
    int MOD_WIDTH = 550;
    int MOD_HEIGHT = 710;

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
        BufferedImage[][] equationImages = equationsToResizedImages.createResizedImages(formatEquationDetails,
                worksheet.equationsToStringArray());
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
     * @param equationImages       The first list of images is for the question sheet and the second list of images is
     *                             for the answer sheet.
     * @param worksheetPDFs        The first PDF is a question sheet and the second PDF is the answer sheet.
     * @param formatArrangeDetails Hashmap showing details necessary for arranging images on a PDF. Includes title,
     *                             number of rows, and number of columns.
     * @throws IOException if images cannot be added to the PDF.
     */
    private void arrangeOnPDFs(BufferedImage[][] equationImages, PDDocument[] worksheetPDFs, Map<String, Object>
            formatArrangeDetails) throws IOException {
        PDImageXObject[][] qAndAPDImage = convertImageToPDImage(equationImages, worksheetPDFs);
        double rescaleFactor = findRescaleFactor(qAndAPDImage[1], (int) formatArrangeDetails.get("numRows"),
                (int) formatArrangeDetails.get("numColumns"));
        for (int i = 0; i < 2; i++) {
            populatePage(qAndAPDImage[i], worksheetPDFs[i], formatArrangeDetails);
        }
    }

    /**
     * Uses the contentStream of a PDF to populate that PDF with a list of equationImages for a given number of rows
     * and columns.
     *
     * @param equationImages the list of all the equation images that will enter the PDF.
     * @param contentStream  the stream associated with a PDF that will be used to populate the PDF.
     * @param numRows        the number of rows in the PDF.
     * @param numColumns     the number of columns in the PDF.
     * @throws IOException if images cannot be added to the PDF.
     */
    private void populatePage(PDImageXObject[] equationImages, PDPageContentStream contentStream, int numRows, int numColumns, double rescaleFactor) throws IOException {

        int pd_index = 0;
        for (int x = 0; x < numColumns; x++) {
            for (int y = numRows - 1; y > -1; y--) {
                int x_coord = MOD_WIDTH * x / numColumns + (PDF_WIDTH - MOD_WIDTH) / 2;
                int y_coord = MOD_HEIGHT * y / numRows + (PDF_HEIGHT - MOD_HEIGHT) / 2;
                if (pd_index < equationImages.length) {
                    contentStream.drawImage(equationImages[pd_index], x_coord, y_coord, equationImages[pd_index].getWidth() / 10, equationImages[pd_index].getHeight() / 10);
                    pd_index++;
                }
            }
        }
    }

    private void populatePage(PDImageXObject[] equationImages, PDDocument worksheetPDF, Map<String, Object> formatArrangeDetails) throws IOException {
        int PDF_WIDTH = 612;
        int PDF_HEIGHT = 792;
        int MOD_WIDTH = 550;
        int MOD_HEIGHT = 710;
        int pd_index = 0;
        int numColumns = (int) formatArrangeDetails.get("numColumns");
        int numRows = (int) formatArrangeDetails.get("numRows");
        String title = (String) formatArrangeDetails.get("title");
        for (int i = 0; i < worksheetPDF.getNumberOfPages(); i++) {
            PDPage page = worksheetPDF.getPage(i);
            PDPageContentStream contentStream = new PDPageContentStream(worksheetPDF, page);
            if (i == 0) {
                writeTitle(contentStream, (String) formatArrangeDetails.get("title"));
            }
            for (int x = 0; x < numColumns; x++) {
                for (int y = numRows - 1; y > -1; y--) {
                    int x_coord = MOD_WIDTH * x / numColumns + (PDF_WIDTH - MOD_WIDTH) / 2;
                    int y_coord = MOD_HEIGHT * y / numRows + (PDF_HEIGHT - MOD_HEIGHT) / 2;
                    if (pd_index < equationImages.length) {
                        contentStream.drawImage(equationImages[pd_index], x_coord, y_coord, equationImages[pd_index].getWidth() / 10, equationImages[pd_index].getHeight() / 10);
                        pd_index++;
                    }
                }
            }
            contentStream.close();
        }
    }

    private void writeTitle(PDPageContentStream contentStream, String title) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(31, 740);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 28);
        contentStream.showText(title);
        contentStream.endText();
    }

    /**
     * Return rescale factor that all the images need to be multiplied by to fit within a certain number of columns and
     * rows in a page.
     *
     * @param answerImages the list of the equation images that have a question and an answer in them.
     * @param numRows      the number of rows in the PDF.
     * @param numColumns   the number of columns in the PDF.
     * @return rescale factor to which all images should be multiplied by to fill in the page.
     */
    private double findRescaleFactor(PDImageXObject[] answerImages, int numRows, int numColumns) {
        return Math.max(MOD_WIDTH / numColumns / findBiggestWidth(answerImages), MOD_HEIGHT / numRows / findBiggestHeight(answerImages));
    }

    /**
     * Return the biggest height of any image within a list.
     *
     * @param answerImages the list of the equation images that have a question and an answer in them.
     * @return the biggest height of any image within a list.
     */
    private int findBiggestHeight(PDImageXObject[] answerImages) {
        int biggestHeight = 0;
        for (PDImageXObject answerImage : answerImages) {
            biggestHeight = Math.max(biggestHeight, answerImage.getHeight());
        }
        return biggestHeight;
    }

    /**
     * Return the biggest width of any image within a list.
     *
     * @param answerImages the list of the equation images that have a question and an answer in them.
     * @return the biggest width of any image within a list.
     */
    private int findBiggestWidth(PDImageXObject[] answerImages) {
        int biggestWidth = 0;
        for (PDImageXObject answerImage : answerImages) {
            biggestWidth = Math.max(biggestWidth, answerImage.getWidth());
        }
        return biggestWidth;
    }

    /**
     * Converts images inside equationsImages into a PDImageXObjects that are assigned to the appropriate worksheet.
     *
     * @param equationImages the equation images that need to be converted.
     * @param worksheetPDFs  the worksheet PDFs that the images belong to.
     * @return an array of PDImageXObject arrays representing the PDImageXObjects that belong to that specific PDF.
     * @throws IOException if images cannot be added to the PDF.
     */
    private PDImageXObject[][] convertImageToPDImage(BufferedImage[][] equationImages, PDDocument[] worksheetPDFs)
            throws IOException {
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
