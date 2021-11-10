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
import java.util.Objects;

public class WSViewerScreen extends StartScreen implements MouseListener {

    // Create Buttons for the Worksheet Viewer Screen
    JButton downloadButton = new JButton("Download");
    JButton printPageButton = new JButton("Print");
    JButton historyButton = new JButton("History");
    JButton mainMenuButton = new JButton("Main Menu");
    JButton viewerBackButton = new JButton("Previous");

    JButton[] viewerButtons = {downloadButton, printPageButton, historyButton, mainMenuButton, viewerBackButton};

    public WSViewerScreen() {

        // Set Panel
        cardLayout.show(cardPanel, "ViewerScreen");

        viewerPanel.setSize(width, height);
        viewerPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        viewerPanel.setLayout(null);

        // Generate an image of the worksheet
        BufferedImage bim = null;
        WorksheetController ws = new WorksheetController();
        try {
            PDDocument[] documents = ws.generateWorksheetAndPDF(equationDetails, formatDetails);

            PDFRenderer pdfRenderer = new PDFRenderer(documents[0]);

            for (int page = 0; page < documents[0].getNumberOfPages(); ++page)
            {
                bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create JLabel for the image
        ImageIcon wsImage = new ImageIcon(bim);
        Image newWsImage = wsImage.getImage();
        Image wsScaledImage = newWsImage.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        wsImage = new ImageIcon(wsScaledImage);
        JLabel wsImageLbl = new JLabel(wsImage, JLabel.CENTER);
        updateLabel(wsImageLbl, 0.725, 0.21, 0.2, 0.2, 0, 'n');

        // Update each buttons location
        updateButtonLocation(downloadButton, 0.15, 0.1, 0.2, 0.1);
        updateButtonLocation(printPageButton, 0.4, 0.1, 0.2, 0.1);
        updateButtonLocation(historyButton, 0.65, 0.1, 0.2, 0.1);
        updateButtonLocation(mainMenuButton, 0.275, 0.75, 0.2, 0.1);
        updateButtonLocation(viewerBackButton, 0.525, 0.75, 0.2, 0.1);

        defaultButton(viewerButtons);

        // Add Mouse Listener for hover features
        downloadButton.addMouseListener(this);
        printPageButton.addMouseListener(this);
        historyButton.addMouseListener(this);
        mainMenuButton.addMouseListener(this);
        viewerBackButton.addMouseListener(this);

        // Add Buttons to the panel
        viewerPanel.add(downloadButton);
        viewerPanel.add(printPageButton);
        viewerPanel.add(historyButton);
        viewerPanel.add(mainMenuButton);
        viewerPanel.add(viewerBackButton);
        viewerPanel.add(wsImageLbl);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            System.out.println("Download");
        }
        if (e.getSource() == printPageButton) {
            System.out.println("Print Page");
        }
        if (e.getSource() == historyButton) {
            frame.setVisible(false);
            viewerPanel.setVisible(false);
            new WorksheetHistoryScreen();
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

