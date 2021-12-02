package constants;

/**
 * Constants for PDF dimensions.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-11-07.
 */
public final class PDFDimensions {
    public final static int PDF_WIDTH = 612; //The full PDF width
    public final static int PDF_HEIGHT = 792; //The full PDF height
    public final static int PRINT_WIDTH = 550; //The portion of the PDF width that will be used
    public final static int PRINT_HEIGHT = 680;//The portion of the PDF height that will be used
    public final static int H_MARGIN = (PDF_HEIGHT - PRINT_HEIGHT) / 2; //The top/bottom margin for the PDF height
    public final static int TITLE_BUFFER = (int) (H_MARGIN * .3); // The distance the worksheet will be
    public final static int W_MARGIN = (PDF_WIDTH - PRINT_WIDTH) / 2; //The left/right margin for the PDF width
    // shifted down by to make space for the title. Cannot exceed H_MARGIN.
}
