package worksheet_maker;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ImageRescaler {

    /**
     * Return rescale factor that all the images need to be multiplied by to fit within a certain number of columns and
     * rows in a page.
     *
     * @param answerImages the list of the equation images that have a question and an answer in them.
     * @param numRows      the number of rows in the PDF.
     * @param numColumns   the number of columns in the PDF.
     * @return rescale factor to which all images should be multiplied by to fill in the page.
     */
    public double findRescaleFactor(PDImageXObject[] answerImages, int numRows, int numColumns) {
        return Math.min(1.0 * PDFDimensions.PRINT_WIDTH / numColumns / findBiggestWidth(answerImages), 1.0 * PDFDimensions.PRINT_HEIGHT / numRows / findBiggestHeight(answerImages));
    }

    /**
     * Return the biggest height of any image within a list.
     *
     * @param answerImages the list of the equation images that have a question and an answer in them.
     * @return the biggest height of any image within a list.
     */
    private int findBiggestHeight(PDImageXObject[] answerImages) {
        int biggestHeight = 0;
        for (PDImageXObject answerImage : answerImages) {
            biggestHeight = Math.max(biggestHeight, answerImage.getHeight());
        }
        return biggestHeight;
    }

    /**
     * Return the biggest width of any image within a list.
     *
     * @param answerImages the list of the equation images that have a question and an answer in them.
     * @return the biggest width of any image within a list.
     */
    private int findBiggestWidth(PDImageXObject[] answerImages) {
        int biggestWidth = 0;
        for (PDImageXObject answerImage : answerImages) {
            biggestWidth = Math.max(biggestWidth, answerImage.getWidth());
        }
        return biggestWidth;
    }
}
