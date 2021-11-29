package worksheet_maker;

import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.Map;

/**
 * Arranges a list of images onto a PDF.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-07.
 */
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
    public void arrangeOnPDFs(PDImageXObject[][] qAndAPDImage, PDDocument[] worksheetPDFs, FormatDetails
            formatArrangeDetails) throws IOException {
        double rescaleFactor = imageRescaler.findRescaleFactor(qAndAPDImage[1], formatArrangeDetails.getNumRows(),
                formatArrangeDetails.getNumColumns());
        for (int i = 0; i < 2; i++) {
            populatePages(qAndAPDImage[i], worksheetPDFs[i], formatArrangeDetails, rescaleFactor);
        }
    }

    /**
     * Uses the equationImages of a PDF to populate that PDF with a title and a list of equationImages for a given
     * number of rows and columns.
     *
     * @param equationImages       the list of all the equation images that will enter the PDF.
     * @param worksheetPDF         the pdf that will be modified.
     * @param formatArrangeDetails the arrangement details.
     * @param rescaleFactor        the factor by which to rescale the equationImages.
     * @throws IOException if images cannot be added to the PDF.
     */
    private void populatePages(PDImageXObject[] equationImages, PDDocument worksheetPDF,
                               FormatDetails formatArrangeDetails, double rescaleFactor) throws IOException {
        int qNumber = 0;
        for (int i = 0; i < worksheetPDF.getNumberOfPages(); i++) {
            PDPageContentStream contentStream = new PDPageContentStream(worksheetPDF, worksheetPDF.getPage(i));
            if (i == 0) {
                addTitle(contentStream, formatArrangeDetails.getTitle());
            }
            qNumber = addQuestions(equationImages, rescaleFactor, qNumber, formatArrangeDetails, contentStream);
            contentStream.close();
        }
    }

    /**
     * Uses the equationImages of a PDF to populate that PDF with a list of equationImages for a given number of rows
     * and columns.
     *
     * @param equationImages       the list of all the equation images that will enter the PDF.
     * @param rescaleFactor        the factor by which to rescale the equationImages.
     * @param qNumber              the question number for the first question to add to this page.
     * @param formatArrangeDetails the arrangement details.
     * @param contentStream        the stream associated with the relevant page in the PDF.
     * @throws IOException if images cannot be added to the PDF.
     */
    private int addQuestions(PDImageXObject[] equationImages, double rescaleFactor, int qNumber,
                             FormatDetails formatArrangeDetails, PDPageContentStream contentStream)
            throws IOException {
        int numColumns = formatArrangeDetails.getNumColumns();
        int numRows = formatArrangeDetails.getNumRows();
        for (int x = 0; x < numColumns; x++) {
            for (int y = numRows; y > 0; y--) {
                if (qNumber < equationImages.length) {
                    int xCoord = PDFDimensions.PRINT_WIDTH * x / numColumns + PDFDimensions.W_MARGIN;
                    int yCoord = (int) (PDFDimensions.PRINT_HEIGHT * y / numRows + PDFDimensions.H_MARGIN - Math.round(equationImages[qNumber].getHeight() * rescaleFactor));
                    contentStream.drawImage(equationImages[qNumber], xCoord, yCoord,
                            Math.round(equationImages[qNumber].getWidth() * rescaleFactor),
                            Math.round(equationImages[qNumber].getHeight() * rescaleFactor));
                    qNumber++;
                }
            }
        }
        return qNumber;
    }


    /**
     * Adds the title to the top of the first page in the PDF.
     *
     * @param contentStream the stream associated with the first page in the PDF.
     * @param title         the title of a given worksheet.
     * @throws IOException if text cannot be added to the PDF.
     */
    private void addTitle(PDPageContentStream contentStream, String title) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset((float) (PDFDimensions.W_MARGIN),
                (float) (PDFDimensions.PRINT_HEIGHT + PDFDimensions.H_MARGIN + PDFDimensions.TITLE_BUFFER));
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 28);
        contentStream.showText(title);
        contentStream.endText();
    }
}
