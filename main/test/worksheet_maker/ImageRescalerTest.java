package worksheet_maker;

import constants.PDFDimensions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ImageRescalerTest {
    private ImageRescaler imageRescaler;

    @Before
    public void init() {
        imageRescaler = new ImageRescaler();
    }

    @Test
    public void testOneRescaleFactor() throws IOException {
        PDDocument pd = new PDDocument();
        PDImageXObject p1 = new PDImageXObject(pd);
        p1.setHeight(1000);
        p1.setWidth(2000);
        int rows = 5;
        int columns = 5;
        int column_width = PDFDimensions.PRINT_WIDTH / columns;
        int row_height = PDFDimensions.PRINT_HEIGHT / rows;
        double rescaleFactor = imageRescaler.findRescaleFactor(new PDImageXObject[]{p1}, rows, columns);
        pd.close();
        assertTrue((rescaleFactor * p1.getHeight() <= row_height)
                && (rescaleFactor * p1.getWidth() <= column_width));
    }

    @Test
    public void testMultipleRescaleFactor() throws IOException {
        PDDocument pd = new PDDocument();
        PDImageXObject p1 = new PDImageXObject(pd);
        p1.setHeight(1000);
        p1.setWidth(2000);
        PDImageXObject p2 = new PDImageXObject(pd);
        p1.setHeight(5);
        p1.setWidth(10);
        int rows = 5;
        int columns = 5;
        int column_width = PDFDimensions.PRINT_WIDTH / columns;
        int row_height = PDFDimensions.PRINT_HEIGHT / rows;
        double rescaleFactor = imageRescaler.findRescaleFactor(new PDImageXObject[]{p1, p2}, rows, columns);
        pd.close();
        assertTrue((rescaleFactor * p1.getHeight() <= row_height)
                && (rescaleFactor * p1.getWidth() <= column_width));
        assertEquals(rescaleFactor, imageRescaler.findRescaleFactor(new PDImageXObject[]{p1}, rows, columns),
                0.01);
        assertNotEquals(rescaleFactor, imageRescaler.findRescaleFactor(new PDImageXObject[]{p2}, rows, columns),
                0.01);
    }
}