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
        System.out.println("resized");

        int largestQuestionWidth = 550 / numColumns;
        int largestQuestionHeight = 710 / numRows;

        float widthRescaleFactor = biggestImageWidthFinder(equationImages) / largestQuestionWidth;
        float heightRescaleFactor = biggestImageHeightFinder(equationImages) / largestQuestionHeight;
        for (BufferedImage[] worksheetImages : equationImages) {
            for (BufferedImage questionImage : worksheetImages) {
                questionImage = resize(questionImage, Math.round(questionImage.getWidth() * widthRescaleFactor), Math.round(questionImage.getHeight() * heightRescaleFactor));
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
    private int biggestImageHeightFinder(BufferedImage[][] equationImages) {
        int biggestImageHeight = 0;
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
    private int biggestImageWidthFinder(BufferedImage[][] equationImages) {
        int biggestImageWidth = 0;
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
