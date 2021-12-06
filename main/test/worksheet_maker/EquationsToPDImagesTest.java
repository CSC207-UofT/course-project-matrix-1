package worksheet_maker;

import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EquationsToPDImagesTest {
    FormatDetails formatDetails = new FormatDetails();
    List<Map<String, String>> equations = new ArrayList<>();
    //    String[][] equations = {{"5", "+", "4", "=", "9"}, {"5", "+", "3", "=", "8"}};
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
        Map<String, String> q1 = new HashMap<>();
        q1.put("operator", "+");
        q1.put("operand1", "5");
        q1.put("operand2", "4");
        q1.put("answer", "9");
        equations.add(q1);
        Map<String, String> q2 = new HashMap<>();
        q2.put("operator", "+");
        q2.put("operand1", "5");
        q2.put("operand2", "3");
        q2.put("answer", "8");
        equations.add(q2);
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
        for (int i = 0; i < images.length; i++) {
            //First just contains the question, second contains question and answer.
            assertTrue(images[0][i].getWidth() < images[1][i].getWidth());
        }
    }
    @After
    public void teardown() throws IOException {
        worksheetPDFs[0].close();
        worksheetPDFs[1].close();
    }
}