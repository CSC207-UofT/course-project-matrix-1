package worksheet_maker;

import equation_entities.Add;
import equation_entities.StandardEquation;
import equation_entities.WholeNum;
import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PDFPresenterTest {
    Worksheet worksheet;
    PDFPresenter pdfPresenter;

    @Before
    public void init() {
        worksheet = new Worksheet();
        StandardEquation standardEquation = new StandardEquation();
        standardEquation.setOperator(new Add());
        standardEquation.setOperand1(new WholeNum(1));
        standardEquation.setOperand2(new WholeNum(2));
        standardEquation.solve();
        worksheet.addEquation(standardEquation);
        pdfPresenter = new PDFPresenter(worksheet);
    }

    @Test
    public void testCreatePDF() throws IOException {
        FormatDetails formatDetails = new FormatDetails();
        formatDetails.setEquationFormat("Horizontal");
        formatDetails.setTitle("Test");
        formatDetails.setNumRows(10);
        formatDetails.setNumColumns(10);
        PDDocument[] pdf = pdfPresenter.createPDF(formatDetails);
        assertEquals(2, pdf.length);

    }
}