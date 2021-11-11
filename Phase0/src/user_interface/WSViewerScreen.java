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

public class WSViewerScreen extends StartScreen implements MouseListener {

    // Create Buttons for the Worksheet Viewer Screen
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

    // Generate an image of the worksheet
    BufferedImage bim = null;
    static WorksheetController wc = new WorksheetController();
    PDDocument[] documents = wc.generateWorksheetAndPDF(equationDetails, formatDetails);

    public WSViewerScreen() throws IOException {

        // Set Panel
        cardLayout.show(cardPanel, "ViewerScreen");

        // Store worksheet to user
        uc.storeUserRecord(equationDetails);

        viewerPanel.setSize(width, height);
        viewerPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        viewerPanel.setLayout(null);

        try {
            PDFRenderer pdfRenderer = new PDFRenderer(documents[0]);
            bim = pdfRenderer.renderImageWithDPI(0, 400, ImageType.RGB);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create JLabel for the image
        ImageIcon wsImage = new ImageIcon(bim);
        Image newWsImage = wsImage.getImage();
        Image wsScaledImage = newWsImage.getScaledInstance((int) (convert(0.45, 'w')*bim.getWidth()*1.0/bim.getHeight()), convert(0.55, 'h'), Image.SCALE_SMOOTH);
        wsImage = new ImageIcon(wsScaledImage);
        JLabel wsImageLbl = new JLabel(wsImage, SwingConstants.CENTER);
        updateLabel(wsImageLbl, 0.275, 0.05, 0.45, 0.65, 0, 'n');

        // Update location of JLabels and texfield
        updateLabel(previewTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(previewTitleShadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(downloadLbl, 0.1, 0.63, 0.6, 0.1, 0.02, 'd');
        updateLabel(invalidPathLbl, 0.15, 0.7, 0.7, 0.05, 0.0125, 'n');
        downloadPath_tf.setBounds(convert(0.55, 'w'), convert(0.655, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Update each buttons location
        updateButtonLocation(downloadButton, 0.4, 0.75, 0.2, 0.1);
        updateButtonLocation(mainMenuButton, 0.705, 0.8, 0.15, 0.05);
        updateButtonLocation(viewerBackButton, 0.145, 0.8, 0.125, 0.05);
        defaultButton(viewerButtons);

        // Add Mouse Listener for hover features
        downloadButton.addMouseListener(this);
        printPageButton.addMouseListener(this);
        historyButton.addMouseListener(this);
        mainMenuButton.addMouseListener(this);
        viewerBackButton.addMouseListener(this);

        // Add Buttons to the panel
        viewerPanel.add(downloadButton);
        viewerPanel.add(mainMenuButton);
        viewerPanel.add(viewerBackButton);
        viewerPanel.add(downloadLbl);
        viewerPanel.add(downloadPath_tf);
        viewerPanel.add(invalidPathLbl);
        viewerPanel.add(previewTitle);
        viewerPanel.add(previewTitleShadow);
        invalidPathLbl.setVisible(false);
        viewerPanel.add(wsImageLbl);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            try {
                documents[0].save(downloadPath_tf.getText() + "/" + titleInput + "_questions.pdf");
                documents[1].save(downloadPath_tf.getText() + "/" + titleInput + "_answers.pdf");
                invalidPathLbl.setText("The Worksheet has been downloaded to " + downloadPath_tf.getText());
                invalidPathLbl.setVisible(true);
            } catch (IOException ex) {
                invalidPathLbl.setText("Invalid Path");
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

