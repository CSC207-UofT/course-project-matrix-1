package worksheet_maker;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageResizer {
    /**
     * Resizes all images by the same factor so that they will all fit into the specified number of rows and columns.
     *
     * @param equationImages the first array represents the images for the questions, the second array represents the
     *                       images for the answers.
     * @param numRows        the number of rows in the PDF.
     * @param numColumns     the number of columns in the PDF.
     */
    public void resize(BufferedImage[][] equationImages, int numRows, int numColumns) {
        double largestQuestionWidth = 550.0 / numColumns;
        double largestQuestionHeight = 710.0 / numRows;

        double widthRescaleFactor = biggestImageWidthFinder(equationImages) / largestQuestionWidth;
        double heightRescaleFactor = biggestImageHeightFinder(equationImages) / largestQuestionHeight;
        for (BufferedImage[] worksheetImages : equationImages) {
            for (int i = 0; i < worksheetImages.length; i++) {
                worksheetImages[i] = resize(worksheetImages[i], (int) Math.round(worksheetImages[i].getWidth() * widthRescaleFactor), (int) Math.round(worksheetImages[i].getHeight() * heightRescaleFactor));
            }
        }
    }

    /**
     * Find the biggest height of any image in a collection of BufferedImage objects
     *
     * @param equationImages the first array represents the images for the questions, the second array represents the
     *                       images for the answers.
     * @return the height in pixels of the biggest height.
     */
    private double biggestImageHeightFinder(BufferedImage[][] equationImages) {
        double biggestImageHeight = 0;
        for (BufferedImage[] worksheetImages : equationImages) {
            for (BufferedImage questionImage : worksheetImages) {
                if (questionImage.getHeight() > biggestImageHeight) {
                    biggestImageHeight = questionImage.getHeight();
                }
            }
        }
        return biggestImageHeight;
    }

    /**
     * Find the biggest width of any image in a collection of BufferedImage objects
     *
     * @param equationImages the first array represents the images for the questions, the second array represents the
     *                       images for the answers.
     * @return the width in pixels of the biggest width.
     */
    private double biggestImageWidthFinder(BufferedImage[][] equationImages) {
        double biggestImageWidth = 0;
        for (BufferedImage[] worksheetImages : equationImages) {
            for (BufferedImage questionImage : worksheetImages) {
                if (questionImage.getWidth() > biggestImageWidth) {
                    biggestImageWidth = questionImage.getWidth();
                }
            }
        }
        return biggestImageWidth ;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
