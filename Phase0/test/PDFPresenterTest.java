import static org.junit.Assert.*;

/**
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class PDFPresenterTest {

    private PDFPresenter p;
    private WorksheetGenerator wg;

    @org.junit.Before
    public void init() {
        Worksheet ws = new Worksheet();
        wg = new WorksheetGenerator(ws);
        p = new PDFPresenter(ws);
        wg.generateWorksheet("standard add", 10, 3);
        //String[][] equations = {{"q1", "a1"}, {"q2", "a2"}};
        p.createWorksheetPDF("Sample Worksheet", 12, "Horizontal");
    }

    @org.junit.Test
    public void testCreateWorksheetPDF() {
        String pdfNoAnswers = p.ws.getPDFs()[0];
        String pdfWithAnswers = p.ws.getPDFs()[1];
        assertEquals("Title: Sample Worksheet. Font size: 12. Equation format: Horizontal.", pdfNoAnswers.substring(0, 68));
    }

    @org.junit.Test
    public void testDownloadPDF() {
        p.downloadPDF("path/path/path");
    }


}