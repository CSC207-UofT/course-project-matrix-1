package worksheet_maker;

import equation_entities.Add;
import equation_entities.BedmasEquation;
import equation_entities.WholeNum;
import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
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