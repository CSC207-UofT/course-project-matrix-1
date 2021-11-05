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

    ImageFacade();

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
        BufferedImage[][] qAndAImages = imageFacade.createResizedImages(formatDetails, worksheet);
        arrangeOnPDFs(qAndAImages, qAndAPDF, formatDetails);
        return qAndAPDF;
    }

    /**
     * Arranges a list of images on a PDF given the rowNum and columnNum. Enough space will be made at the top for a
     * title.
     *
     * @param qAndAImages   The first list of images is for the question sheet and the second list of images is for the
     *                      answer sheet.
     * @param qAndAPDF      The first PDF is a question sheet and the second PDF is the answer sheet.
     * @param formatDetails Hashmap showing details necessary for formatting a PDF. Includes equation format, title,
     *                      number of rows, and number of columns.
     */
    private void arrangeOnPDFs(Image[][] qAndAImages, PDDocument[] qAndAPDF, Map<String, Object> formatDetails) {

    }

}
