import static org.junit.Assert.*;

public class PDFPresenterTest {

    private PDFPresenter p;

    @org.junit.Before
    public void init() {
        Worksheet ws = new Worksheet();
        p = new PDFPresenter(ws);
        String[][] equations = {{"q1", "a1"}, {"q2", "a2"}};
        p.ws.setEquations(equations); //remove later
        p.createWorksheetPDF("Sample Worksheet",12, "Horizontal");
    }

    @org.junit.Test
    public void testCreateWorksheetPDF() {
        String pdfNoAnswers = p.ws.getPDFs()[0];
        String pdfWithAnswers = p.ws.getPDFs()[1];
        assertEquals("Title: Sample Worksheet. Font size: 12. Equation format: Horizontal." + "\n" + "q1 = ___" + "\n" + "q2 = ___" , pdfNoAnswers);
    }

    @org.junit.Test
    public void testDownloadPDF() {
        p.downloadPDF("path/path/path");
    }


}