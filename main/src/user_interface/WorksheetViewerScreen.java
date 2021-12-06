package user_interface;

import equation_parameters.EquationDetails;
import equation_parameters.FormatDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Worksheet viewer screen class for the User Interface. The user can preview the first page of the generated
 * worksheet, and they can download the questions and answers to any file path (invalid file paths are handled)
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class WorksheetViewerScreen extends Screen implements MouseListener, KeyListener {

    // Create buttons
    JButton downloadButton = new JButton("Download");
    JButton printPageButton = new JButton("Print");
    JButton historyButton = new JButton("History");
    JButton mainMenuButton = new JButton("Main Menu");
    JButton viewerBackButton = new JButton("Back");
    JButton nextPreviewPage = new JButton("<");
    JButton previousPreviewPage = new JButton(">");
    JButton[] viewerButtons = {printPageButton, historyButton, mainMenuButton, viewerBackButton,
            nextPreviewPage, previousPreviewPage};

    // Create invalid path JLabel
    JLabel invalidPathLbl = new JLabel("Invalid file path", SwingConstants.CENTER);
    JLabel downloadSuccess = new JLabel("", SwingConstants.CENTER);

    // Create text fields
    JTextField downloadPathInput = new JTextField(1);

    // Initialize a buffered image and pd document
    BufferedImage bim = null;
    PDDocument[] documents;

    // Create a variable to hold he title of the document
    String documentTitle;

    // Create equation and format details
    EquationDetails equationDetails;
    FormatDetails formatDetails;
    Map<String, Object> worksheetDetails;

    public WorksheetViewerScreen(Map<String, Object> worksheetDetails, Boolean regenerateExact) throws IOException {

        // Update the default panel settings
        updatePanel(previewPanel);

        // Get the equation and format details passed in
        this.worksheetDetails = worksheetDetails;
        equationDetails = (EquationDetails) worksheetDetails.get("equationDetails");
        formatDetails = (FormatDetails) worksheetDetails.get("formatDetails");

        // Set the document title
        documentTitle = formatDetails.getTitle();

        // Generate worksheet random seed if new worksheet, and store for later regeneration.
        if (!this.worksheetDetails.containsKey("seed") | !regenerateExact) {
            this.worksheetDetails.put("seed", new Random().nextInt(1000000000));
        }

        // Store the worksheet information to the user's history
        userController.storeUserRecord(worksheetDetails);

        // Generate the documents worksheets
        documents = worksheetController.generateWorksheetAndPDF(equationDetails, formatDetails, (int) this.worksheetDetails.get("seed"));
        int numPages = documents[0].getNumberOfPages();

        fillScreen(numPages);
    }

    /**
     * Overloaded constructor allows later specifying if worksheet should be generated exactly or identically.
     */
    public WorksheetViewerScreen(Map<String, Object> worksheetDetails) throws IOException {
        new WorksheetViewerScreen(worksheetDetails, false);
    }

    /**
     * Adds all necessary parts of WorksheetViewerScreen and displays a preview of the worksheet.
     *
     * @param numPages number of pages
     */
    private void fillScreen(int numPages) {
        ArrayList<JLabel> labels = new ArrayList<>();
        // Create an image of the documents first page to preview
        try {
            PDFRenderer pdfRenderer = new PDFRenderer(documents[0]);                    // Get the questions sheet

            for (int i = 0; i < numPages; i++) {
                bim = pdfRenderer.renderImageWithDPI(i, 400, ImageType.RGB);
                ImageIcon wsImage = new ImageIcon(bim);
                Image newWsImage = wsImage.getImage();

                // Scale the image to the Panel
                Image wsScaledImage = newWsImage.getScaledInstance((int) (convert(0.575, 'h') * bim.getWidth() * 1.0 / bim.getHeight()), convert(0.45, 'h'), Image.SCALE_SMOOTH);
                wsImage = new ImageIcon(wsScaledImage);
                JLabel wsImageLblTemp = new JLabel(wsImage, SwingConstants.CENTER);
                updateLabel(wsImageLblTemp, 0.275, 0.255, 0.45, 0.45, 0, 'r');
                labels.add(wsImageLblTemp);
            }
        } catch (IOException ex) {
            invalidPathLbl.setVisible(true);
        }
        // Create a panel to display the preview of the worksheets as a scroll pane
        JPanel images = new JPanel();
        images.setLayout(new BoxLayout(images, BoxLayout.Y_AXIS));
        for (int i = 0; i < labels.toArray().length; i++) {
            images.add(labels.get(i));
            images.add(new JSeparator());
        }

        // Update the location of the scroll pane
        JScrollPane scrollPane = new JScrollPane(images);
        scrollPane.setBounds(convert(0.35, 'w'), convert(0.25, 'h'), convert(0.3, 'w'),
                convert(0.45, 'h'));

        // Create JLabels
        JLabel previewTitle = new JLabel("Preview", SwingConstants.CENTER);
        JLabel previewTitleShadow = new JLabel("Preview", SwingConstants.CENTER);
        JLabel downloadLbl = new JLabel("Download to file path:", SwingConstants.CENTER);

        // Update the location of the labels
        updateLabel(previewTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(previewTitleShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(downloadLbl, 0.1, 0.7, 0.6, 0.1, 0.02, 'd');
        updateLabel(invalidPathLbl, 0.4, 0.78, 0.2, 0.05, 0.0125, 'w');
        updateLabel(downloadSuccess, 0.2, 0.78, 0.6, 0.05, 0.0125, 'w');

        // Initially set the invalid file path label to not visible and update its settings
        invalidPathLbl.setOpaque(true);
        invalidPathLbl.setBackground(new Color(217, 207, 131, 252));
        invalidPathLbl.setVisible(false);
        downloadSuccess.setOpaque(true);
        downloadSuccess.setBackground(new Color(217, 207, 131, 252));
        downloadSuccess.setVisible(false);

        // Update the location of the text fields
        downloadPathInput.setBounds(convert(0.55, 'w'), convert(0.725, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        downloadPathInput.setOpaque(true);
        downloadPathInput.setBackground(new Color(220, 220, 220));
        downloadPathInput.setBorder(BorderFactory.createMatteBorder(2, 2, 2,
                2, Color.DARK_GRAY));

        // Update the location of each button
        updateButtonLocation(downloadButton, 0.4, 0.845, 0.2, 0.1);
        updateButtonLocation(mainMenuButton, 0.705, 0.845, 0.15, 0.05);
        updateButtonLocation(viewerBackButton, 0.145, 0.845, 0.125, 0.05);
        updateButtonLocation(nextPreviewPage, 0.466, 0.675, 0.066, 0.025);
        updateButtonLocation(previousPreviewPage, 0.4, 0.675, 0.066, 0.025);

        // Update the settings of each button
        defaultButton(viewerButtons);
        defaultButton(downloadButton, 'b');

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
        previewPanel.add(downloadSuccess);
        previewPanel.add(scrollPane);

        // Change the panel to the preview panel
        changePanel(previewPanel);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            // Attempt to save the generated worksheet's questions and answers to user's download path
            downloadDocument();
        } else if (e.getSource() == mainMenuButton) {
            closeFiles();
            new OptionScreen();
        } else if (e.getSource() == viewerBackButton) {
            closeFiles();
            new CustomizeScreen(worksheetDetails);
        }

    }

    private void closeFiles() {
        try {
            documents[0].close();
            documents[1].close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Download the questions and answer worksheets as pdfs to the specified download path
     */
    private void downloadDocument() {
        try {
            documents[0].save(downloadPathInput.getText() + "/" + documentTitle + "_questions.pdf");
            documents[1].save(downloadPathInput.getText() + "/" + documentTitle + "_answers.pdf");
            // Inform user that the download was successful
            downloadSuccess.setText("The Worksheet has been downloaded to " + downloadPathInput.getText());
            invalidPathLbl.setVisible(false);
            downloadSuccess.setVisible(true);
        } catch (IOException ex) {
            downloadSuccess.setVisible(false);
            invalidPathLbl.setVisible(true);
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            highlightButton(downloadButton, 'b');
        } else if (e.getSource() == printPageButton) {
            highlightButton(printPageButton, 'd');
        } else if (e.getSource() == historyButton) {
            highlightButton(historyButton, 'd');
        } else if (e.getSource() == mainMenuButton) {
            highlightButton(mainMenuButton, 'd');
        } else if (e.getSource() == viewerBackButton) {
            highlightButton(viewerBackButton, 'd');
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            defaultButton(downloadButton, 'b');
        } else if (e.getSource() == printPageButton) {
            defaultButton(printPageButton, 'd');
        } else if (e.getSource() == historyButton) {
            defaultButton(historyButton, 'd');
        } else if (e.getSource() == mainMenuButton) {
            defaultButton(mainMenuButton, 'd');
        } else if (e.getSource() == viewerBackButton) {
            defaultButton(viewerBackButton, 'd');
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            downloadDocument();
        }
    }

}

