package user_interface;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * Worksheet viewer screen class for the User Interface. The user can preview the first page of the generated
 * worksheet, and they can download the questions and answers to any file path (invalid file paths are handled)
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class WorksheetViewerScreen extends Screen implements MouseListener {

    // Create buttons
    JButton downloadButton = new JButton("Download");
    JButton printPageButton = new JButton("Print");
    JButton historyButton = new JButton("History");
    JButton mainMenuButton = new JButton("Main Menu");
    JButton viewerBackButton = new JButton("Back");
    JButton[] viewerButtons = {downloadButton, printPageButton, historyButton, mainMenuButton, viewerBackButton};

    // Create invalid path JLabel
    JLabel invalidPathLbl = new JLabel("Invalid file path", SwingConstants.CENTER);

    // Create text fields
    JTextField downloadPathInput = new JTextField(1);

    // Initialize a buffered image and pd document
    BufferedImage bim = null;
    PDDocument[] documents;

    // Create a variable to hold he title of the document
    String documentTitle;

    // Create the map's to store the temporary equation and format details
    Map<String, Object> equationDetailsViewer;
    Map<String, Object> formatDetailsViewer;

    public WorksheetViewerScreen(Map<String, Object> equation_Details, Map<String, Object> format_Details,
                                 Map<String, Object> worksheet_details) throws IOException {

        previewPanel.setLayout(null);

        // Set the updated equation details and format details chosen by the user
        equationDetailsViewer = equation_Details;
        formatDetailsViewer = format_Details;

        // Set the document title
        documentTitle = formatDetailsViewer.get("title").toString();

        // Store the worksheet information to the user's history
        userController.storeUserRecord(worksheet_details);

        // Generate the documents worksheets (use temporary random seed of 0 until Phase 2)
        documents = worksheetController.generateWorksheetAndPDF(equation_Details, format_Details, 0);

        // Create an image of the documents first page to preview
        try {
            PDFRenderer pdfRenderer = new PDFRenderer(documents[0]);                    // Get the questions sheet
            bim = pdfRenderer.renderImageWithDPI(0, 400, ImageType.RGB);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create JLabels
        JLabel previewTitle = new JLabel("Preview", SwingConstants.CENTER);
        JLabel previewTitleShadow = new JLabel("Preview", SwingConstants.CENTER);
        JLabel downloadLbl = new JLabel("Download to file path:", SwingConstants.CENTER);

        // Create label where the image is displayed
        ImageIcon wsImage = new ImageIcon(bim);
        Image newWsImage = wsImage.getImage();
        Image wsScaledImage = newWsImage.getScaledInstance((int) (convert(0.55, 'h') * bim.getWidth() * 1.0 / bim.getHeight()), convert(0.55, 'h'), Image.SCALE_SMOOTH);
        wsImage = new ImageIcon(wsScaledImage);
        JLabel wsImageLbl = new JLabel(wsImage, SwingConstants.CENTER);
        updateLabel(wsImageLbl, 0.275, 0.05, 0.45, 0.65, 0, 'r');

        // Update the location of the labels
        updateLabel(previewTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'r');
        updateLabel(previewTitleShadow, 0.2025, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(downloadLbl, 0.1, 0.63, 0.6, 0.1, 0.02, 'd');
        updateLabel(invalidPathLbl, 0.15, 0.7, 0.7, 0.05, 0.0125, 'r');

        // Initially set the invalid file path label to not visible
        invalidPathLbl.setVisible(false);

        // Update the location of the text fields
        downloadPathInput.setBounds(convert(0.55, 'w'), convert(0.655, 'h'), convert(0.175, 'w'),
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

        // Add all components to the panel
        previewPanel.add(downloadButton);
        previewPanel.add(mainMenuButton);
        previewPanel.add(viewerBackButton);
        previewPanel.add(downloadLbl);
        previewPanel.add(downloadPathInput);
        previewPanel.add(invalidPathLbl);
        previewPanel.add(previewTitle);
        previewPanel.add(previewTitleShadow);
        previewPanel.add(wsImageLbl);

        changePanel(previewPanel);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == downloadButton) {

            // Attempt to save the generated worksheet's questions and answers to user's download path
            try {
                documents[0].save(downloadPathInput.getText() + "/" + documentTitle + "_questions.pdf");
                documents[1].save(downloadPathInput.getText() + "/" + documentTitle + "_answers.pdf");
                invalidPathLbl.setText("The Worksheet has been downloaded to " + downloadPathInput.getText());
                invalidPathLbl.setVisible(true);
            } catch (IOException ex) {
                invalidPathLbl.setText("Invalid Path");     // Show invalid input label if files cannot be downloaded
            }
            invalidPathLbl.setVisible(true);
        } else if (e.getSource() == mainMenuButton) {
            new OptionScreen();
        } else if (e.getSource() == viewerBackButton) {
            new CustomizeScreen(equationDetailsViewer);
        }

    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            highlightButton(downloadButton);
        } else if (e.getSource() == printPageButton) {
            highlightButton(printPageButton);
        } else if (e.getSource() == historyButton) {
            highlightButton(historyButton);
        } else if (e.getSource() == mainMenuButton) {
            highlightButton(mainMenuButton);
        } else if (e.getSource() == viewerBackButton) {
            highlightButton(viewerBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            defaultButton(downloadButton);
        } else if (e.getSource() == printPageButton) {
            defaultButton(printPageButton);
        } else if (e.getSource() == historyButton) {
            defaultButton(historyButton);
        } else if (e.getSource() == mainMenuButton) {
            defaultButton(mainMenuButton);
        } else if (e.getSource() == viewerBackButton) {
            defaultButton(viewerBackButton);
        }
    }
}

