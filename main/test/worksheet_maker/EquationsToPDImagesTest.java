package worksheet_maker;

import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class EquationsToPDImagesTest {
    FormatDetails formatDetails = new FormatDetails();
    String[][] equations = {{"5", "+", "4", "=", "9"}, {"5", "+", "3", "=", "8"}};
    PDDocument[] worksheetPDFs = new PDDocument[2];
    EquationsToPDImages equationsToPDImages = new EquationsToPDImages();

    @Before
    public void init() {
        formatDetails.setEquationFormat("Horizontal");
        formatDetails.setTitle("Test");
        formatDetails.setNumRows(10);
        formatDetails.setNumColumns(10);

        worksheetPDFs[0] = new PDDocument();
        worksheetPDFs[1] = new PDDocument();
    }
    @Test
    public void test2PDFsExist() throws IOException {
        PDImageXObject[][] images = equationsToPDImages.createResizedImages(formatDetails, equations, worksheetPDFs);
        assertEquals(images.length, 2);
    }
    @Test
    public void testNumImages() throws IOException {
        PDImageXObject[][] images = equationsToPDImages.createResizedImages(formatDetails, equations, worksheetPDFs);
        assertEquals(images[0].length, 2);
        assertEquals(images[1].length, 2);
    }
    @Test
    public void testAnswerWidthLongerThanQuestion() throws IOException {
        PDImageXObject[][] images = equationsToPDImages.createResizedImages(formatDetails, equations, worksheetPDFs);
        for (int i = 0; i < images.length; i++){
            //First just contains the question, second contains question and answer.
            assertTrue(images[0][i].getWidth() < images[1][i].getWidth());
        }
    }
}