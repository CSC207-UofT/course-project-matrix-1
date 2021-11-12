package user_interface;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import worksheet_maker.WorksheetController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Worksheet viewer screen class for the User Interface. The user can preview the first page of the generated
 * worksheet and they can download the questions and answers to any file path (invalid file paths are handled)
 *
 * @author Ethan Ing, Piotr pralat
 * @since 2021-11-01
 */
public class WorksheetViewerScreen extends StartScreen implements MouseListener {

    // Create buttons
    JButton downloadButton = new JButton("Download");
    JButton printPageButton = new JButton("Print");
    JButton historyButton = new JButton("History");
    JButton mainMenuButton = new JButton("Main Menu");
    JButton viewerBackButton = new JButton("Back");
    JButton[] viewerButtons = {downloadButton, printPageButton, historyButton, mainMenuButton, viewerBackButton};

    // Create Labels
    JLabel downloadLbl = new JLabel("Download to file path:", SwingConstants.CENTER);
    JLabel invalidPathLbl = new JLabel("Invalid file path", SwingConstants.CENTER);
    JLabel previewTitle = new JLabel("Preview", SwingConstants.CENTER);
    JLabel previewTitleShadow = new JLabel("Preview", SwingConstants.CENTER);

    // Create text fields
    JTextField downloadPath_tf = new JTextField(1);

    // Generate a pdf and image of the worksheet
    BufferedImage bim = null;
    static WorksheetController wc = new WorksheetController();
    PDDocument[] documents = wc.generateWorksheetAndPDF(equationDetails, formatDetails);

    public WorksheetViewerScreen() throws IOException {

        // Set Panel
        cardLayout.show(cardPanel, "ViewerScreen");

        // Store worksheet to user
//        uc.storeUserRecord(worksheetHistoryDetails);

        // Create the image of the first sheet of the generated worksheet
        try {
            PDFRenderer pdfRenderer = new PDFRenderer(documents[0]);
            bim = pdfRenderer.renderImageWithDPI(0, 400, ImageType.RGB);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create label where the image is displayed
        ImageIcon wsImage = new ImageIcon(bim);
        Image newWsImage = wsImage.getImage();
        Image wsScaledImage = newWsImage.getScaledInstance((int) (convert(0.45, 'w')*bim.getWidth()*1.0/bim.getHeight()), convert(0.55, 'h'), Image.SCALE_SMOOTH);
        wsImage = new ImageIcon(wsScaledImage);
        JLabel wsImageLbl = new JLabel(wsImage, SwingConstants.CENTER);
        updateLabel(wsImageLbl, 0.275, 0.05, 0.45, 0.65, 0, 'n');

        // Update the location of the labels
        updateLabel(previewTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(previewTitleShadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(downloadLbl, 0.1, 0.63, 0.6, 0.1, 0.02, 'd');
        updateLabel(invalidPathLbl, 0.15, 0.7, 0.7, 0.05, 0.0125, 'r');

        // Initially set the invalid file path label to not visible
        invalidPathLbl.setVisible(false);

        // Update the locationf of the text fields
        downloadPath_tf.setBounds(convert(0.55, 'w'), convert(0.655, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Update the location of each button
        updateButtonLocation(downloadButton, 0.4, 0.75, 0.2, 0.1);
        updateButtonLocation(mainMenuButton, 0.705, 0.8, 0.15, 0.05);
        updateButtonLocation(viewerBackButton, 0.145, 0.8, 0.125, 0.05);

        // Update the settings of each button
        defaultButton(viewerButtons);

        // Add MouseListener for hover and clicking features
        downloadButton.addMouseListener(this);
        printPageButton.addMouseListener(this);
        historyButton.addMouseListener(this);
        mainMenuButton.addMouseListener(this);
        viewerBackButton.addMouseListener(this);

        // Add all compoonents to the panel
        viewerPanel.add(downloadButton);
        viewerPanel.add(mainMenuButton);
        viewerPanel.add(viewerBackButton);
        viewerPanel.add(downloadLbl);
        viewerPanel.add(downloadPath_tf);
        viewerPanel.add(invalidPathLbl);
        viewerPanel.add(previewTitle);
        viewerPanel.add(previewTitleShadow);
        viewerPanel.add(wsImageLbl);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == downloadButton) {

            // Attempt to save the generated worksheet's questions and answers
            try {
                documents[0].save(downloadPath_tf.getText() + "/" + titleInput + "_questions.pdf");
                documents[1].save(downloadPath_tf.getText() + "/" + titleInput + "_answers.pdf");
                invalidPathLbl.setText("The Worksheet has been downloaded to " + downloadPath_tf.getText());
                invalidPathLbl.setVisible(true);
            } catch (IOException ex) {
                invalidPathLbl.setText("Invalid Path");     // Show invalid input label if files cannot be downloaded
            }
            invalidPathLbl.setVisible(true);
        }
        if (e.getSource() == mainMenuButton) {
            frame.setVisible(false);
            viewerPanel.setVisible(false);
            new StartScreen();
        }
        if (e.getSource() == viewerBackButton) {
            frame.setVisible(false);
            viewerPanel.setVisible(false);
            new CustomizeScreen();
        }

    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            highlightButton(downloadButton);
        }
        if (e.getSource() == printPageButton) {
            highlightButton(printPageButton);
        }
        if (e.getSource() == historyButton) {
            highlightButton(historyButton);
        }
        if (e.getSource() == mainMenuButton) {
            highlightButton(mainMenuButton);
        }
        if (e.getSource() == viewerBackButton) {
            highlightButton(viewerBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            defaultButton(downloadButton);
        }
        if (e.getSource() == printPageButton) {
            defaultButton(printPageButton);
        }
        if (e.getSource() == historyButton) {
            defaultButton(historyButton);
        }
        if (e.getSource() == mainMenuButton) {
            defaultButton(mainMenuButton);
        }
        if (e.getSource() == viewerBackButton) {
            defaultButton(viewerBackButton);
        }
    }
}

