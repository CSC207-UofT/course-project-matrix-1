package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.Map;

public class PDFArranger {


    private final ImageRescaler imageRescaler = new ImageRescaler();

    /**
     * Arranges a list of images on a PDF given the rowNum and columnNum. Enough space will be made at the top for a
     * title. Every PDF has a width of 612 pixels by 792 pixels.
     *
     * @param qAndAPDImage         The first list of images is for the question sheet and the second list of images is
     *                             for the answer sheet.
     * @param worksheetPDFs        The first PDF is a question sheet and the second PDF is the answer sheet.
     * @param formatArrangeDetails Hashmap showing details necessary for arranging images on a PDF. Includes title,
     *                             number of rows, and number of columns.
     * @throws IOException if images cannot be added to the PDF.
     */
    public void arrangeOnPDFs(PDImageXObject[][] qAndAPDImage, PDDocument[] worksheetPDFs, Map<String, Object>
            formatArrangeDetails) throws IOException {

        double rescaleFactor = imageRescaler.findRescaleFactor(qAndAPDImage[1], (int) formatArrangeDetails.get("numRows"),
                (int) formatArrangeDetails.get("numColumns"));
        for (int i = 0; i < 2; i++) {
            populatePages(qAndAPDImage[i], worksheetPDFs[i], formatArrangeDetails, rescaleFactor);
        }
    }

    /**
     * Uses the equationImages of a PDF to populate that PDF with a list of equationImages for a given number of rows
     * and columns.
     *
     * @param equationImages       the list of all the equation images that will enter the PDF.
     * @param worksheetPDF         the pdf that will be modified.
     * @param formatArrangeDetails the arrangement details.
     * @param rescaleFactor        the factor by which to rescale the equationImages.
     * @throws IOException if images cannot be added to the PDF.
     */

    private void populatePages(PDImageXObject[] equationImages, PDDocument worksheetPDF, Map<String, Object> formatArrangeDetails, double rescaleFactor) throws IOException {
        int pd_index = 0;
        int numColumns = (int) formatArrangeDetails.get("numColumns");
        int numRows = (int) formatArrangeDetails.get("numRows");
        for (int i = 0; i < worksheetPDF.getNumberOfPages(); i++) {
            PDPage page = worksheetPDF.getPage(i);
            PDPageContentStream contentStream = new PDPageContentStream(worksheetPDF, page);
            if (i == 0) {
                writeTitle(contentStream, (String) formatArrangeDetails.get("title"));
            }
            for (int x = 0; x < numColumns; x++) {
                for (int y = numRows; y > 0; y--) {
                    if (pd_index < equationImages.length) {
                        int x_coord = PDFDimensions.PRINT_WIDTH * x / numColumns + (PDFDimensions.PDF_WIDTH - PDFDimensions.PRINT_WIDTH) / 2;
                        int y_coord = (int) (PDFDimensions.PRINT_HEIGHT * y / numRows + (PDFDimensions.PDF_HEIGHT - PDFDimensions.PRINT_HEIGHT) / 2 - Math.round(equationImages[pd_index].getHeight() * rescaleFactor)) - PDFDimensions.TITLE_BUFFER;
                        contentStream.drawImage(equationImages[pd_index], x_coord, y_coord,
                                Math.round(equationImages[pd_index].getWidth() * rescaleFactor),
                                Math.round(equationImages[pd_index].getHeight() * rescaleFactor));
                        pd_index++;
                    }
                }
            }
            contentStream.close();
        }
    }

    private void writeTitle(PDPageContentStream contentStream, String title) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset((float) ((PDFDimensions.PDF_WIDTH - PDFDimensions.PRINT_WIDTH) / 2), (float) (PDFDimensions.PRINT_HEIGHT + (PDFDimensions.PDF_HEIGHT - PDFDimensions.PRINT_HEIGHT) / 2));
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 28);
        contentStream.showText(title);
        contentStream.endText();
    }
}
