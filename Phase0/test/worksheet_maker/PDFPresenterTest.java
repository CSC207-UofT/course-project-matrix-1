package worksheet_maker;

import equation_entities.Add;
import equation_entities.BedmasEquation;
import equation_entities.WholeNum;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PDFPresenterTest {
    Worksheet worksheet;
    PDFPresenter pdfPresenter;

    @Before
    public void init() {
        worksheet = new Worksheet();
        BedmasEquation bedmasEquation = new BedmasEquation();
        bedmasEquation.setOperator(new Add());
        bedmasEquation.setOperand1(new WholeNum(1));
        bedmasEquation.setOperand2(new WholeNum(2));
        bedmasEquation.solve();
        worksheet.addEquation(bedmasEquation);
        pdfPresenter = new PDFPresenter(worksheet);
    }

    @Test
    public void testCreatePDF() {
        HashMap<String, Object> formatDetails = new HashMap<>();
        formatDetails.put("equationFormat", "Horizontal");
        formatDetails.put("title", "Test");
        formatDetails.put("numRows", 10);
        formatDetails.put("numColumns", 10);
        String[][] pdf = pdfPresenter.createPDF(formatDetails);
        assertEquals(pdf[0][0], "1+2");
        assertEquals(pdf[0][1], "3");
    }
}