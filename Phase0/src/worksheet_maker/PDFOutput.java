package worksheet_maker;

/**
 * Returns a PDF with the format details provided.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-25.
 */
public interface PDFOutput {
    /**
     * Generate a PDF representation of a worksheet
     *
     * @param title the title of a worksheet.
     * @param numRows the number of rows on a single page.
     * @param numColumns the number of columns on a single page.
     * @param equationFormat the format in which equations are displayed (ex. horzontal, vertical, division bracket).
     */
    //TODO: Return a PDF
    public String[][] presentPDF(String title, int numRows, int numColumns, String equationFormat);
}
