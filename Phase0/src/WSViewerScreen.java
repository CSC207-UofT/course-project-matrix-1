import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WSViewerScreen extends StartScreen implements MouseListener {

    // Create Buttons for the Worksheet Viewer Screen
    JButton downloadButton = new JButton("Download");
    JButton printPageButton = new JButton("Print");
    JButton historyButton = new JButton("History");
    JButton mainMenuButton = new JButton("Main Menu");
    JButton viewerBackButton = new JButton("Previous");

    JButton[] viewerButtons = {downloadButton, printPageButton, historyButton, mainMenuButton, viewerBackButton};

    public WSViewerScreen() {

        System.out.println(equationDetails);
        System.out.println(formatDetails);

        // Set Panel
        cardLayout.show(cardPanel, "ViewerScreen");
        viewerPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        viewerPanel.setLayout(null);


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
            System.out.println("Main Menu screen");
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

