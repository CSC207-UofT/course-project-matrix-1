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

    EquationDetails equationDetails;
    FormatDetails formatDetails;

    public WorksheetViewerScreen(Map<String, Object> worksheetDetails) throws IOException {

        updatePanel(previewPanel);

        equationDetails = (EquationDetails) worksheetDetails.get("equationDetails");
        formatDetails = (FormatDetails) worksheetDetails.get("formatDetails");

        // Set the document title
        documentTitle = formatDetails.getTitle();

        // Generate worksheet random seed, and store for later regeneration.
        Random r = new Random();
        int randomSeed = r.nextInt(1000000000);
        worksheetDetails.put("seed", randomSeed);

        ArrayList<JLabel> labels = new ArrayList<>();

        // Store the worksheet information to the user's history
        userController.storeUserRecord(worksheetDetails);
//        //TODO: this method call sends in a map containing EquationDetails and FormatDetails, currently does not work

        // Generate the documents worksheets (use temporary random seed of 0 until Phase 2)
        documents = worksheetController.generateWorksheetAndPDF(equationDetails, formatDetails, (int) worksheetDetails.get("seed"));
        int numPages = documents[0].getNumberOfPages();

        // Create an image of the documents first page to preview
        try {
            PDFRenderer pdfRenderer = new PDFRenderer(documents[0]);                    // Get the questions sheet

            for (int i = 0; i < numPages; i++) {
                bim = pdfRenderer.renderImageWithDPI(i, 400, ImageType.RGB);
                ImageIcon wsImage = new ImageIcon(bim);
                Image newWsImage = wsImage.getImage();
                Image wsScaledImage = newWsImage.getScaledInstance((int) (convert(0.575, 'h') * bim.getWidth() * 1.0 / bim.getHeight()), convert(0.45, 'h'), Image.SCALE_SMOOTH);
                wsImage = new ImageIcon(wsScaledImage);
                JLabel wsImageLblTemp = new JLabel(wsImage, SwingConstants.CENTER);
                updateLabel(wsImageLblTemp, 0.275, 0.255, 0.45, 0.45, 0, 'r');
                labels.add(wsImageLblTemp);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        documents[0].close();
        JPanel images = new JPanel();
        images.setLayout(new BoxLayout(images, BoxLayout.Y_AXIS));
        for (int i = 0; i < labels.toArray().length; i++) {
            images.add(labels.get(i));
            images.add(new JSeparator());
        }
        JScrollPane scrollPane = new JScrollPane(images);
        scrollPane.setBounds(convert(0.35, 'w'), convert(0.25,'h'), convert(0.3, 'w'),
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

        // Initially set the invalid file path label to not visible
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

        changePanel(previewPanel);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            // Attempt to save the generated worksheet's questions and answers to user's download path
            downloadDocument();
        } else if (e.getSource() == mainMenuButton) {
            new OptionScreen();
        } else if (e.getSource() == viewerBackButton) {
            new CustomizeScreen(equationDetails, formatDetails);
        }

    }

    private void downloadDocument() {
        try {
            documents[0].save(downloadPathInput.getText() + "/" + documentTitle + "_questions.pdf");
            documents[1].save(downloadPathInput.getText() + "/" + documentTitle + "_answers.pdf");
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Attempt to save the generated worksheet's questions and answers to user's download path
        downloadDocument();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

