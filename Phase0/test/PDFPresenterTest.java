import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PDFPresenterTest {
    private PDFPresenter p;


    @BeforeEach
    public void init() {
        Worksheet ws = new Worksheet();
        PDFPresenter p = new PDFPresenter(ws);
    }


    @Test
    public void testCreateWorksheetPDF() {
        String [][] equations = {{"q1", "a1"},{"q2", "a2"}};
        p.ws.setEquations(equations); //remove later
        p.createWorksheetPDF(12, "Horizontal");
        String pdfNoAnswers = p.ws.getPDFs()[0];
        String pdfWithAnswers = p.ws.getPDFs()[1];
        assertEquals(pdfNoAnswers, """
                Font size: 12 . Equation format: Horizontal.
                q1 = ___
                q2 = ___
                """);


    }

    @Test
    public void testGetPDFs() {
    }

    @Test
    public void testDownloadPDF() {
    }
}
