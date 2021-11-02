package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

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

//    public static void test() {
//        PDDocument doc = new PDDocument();
//        PDPage page = new PDPage();
//        try {
//            doc.addPage(page);
//            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//            //----
//            contentStream.beginText();
//            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
//            contentStream.newLineAtOffset(25, 500);
//            contentStream.setLeading(14.5f);
//            String text = "This is the sample document and we are adding content to it.";
//            contentStream.showText(text);
//            contentStream.newLine();
//            contentStream.showText(text);
//            contentStream.endText();
//            contentStream.close();
//            //----
//            doc.save("C:/Users/willj/OneDrive - University of Toronto/Desktop/my_doc.pdf");
//            doc.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        PDFPresenter.test();
//    }

}
