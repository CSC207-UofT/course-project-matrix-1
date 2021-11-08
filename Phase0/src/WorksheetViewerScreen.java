import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WorksheetViewerScreen extends StartScreen implements MouseListener {

    JPanel panel4 = new JPanel();

    public WorksheetViewerScreen(){
        // Set Frame


        // Set Panel and Border
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        panel3.setLayout(null);
        frame.add(panel3);
        frame.setVisible(true);
        frame.setVisible(false);
    }
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == downloadButton) {
            System.out.println("Download");
        }
        if (e.getSource() == printPageButton) {
            System.out.println("Print Page");
        }
        if (e.getSource() == historyButton) {
            System.out.println("user History");
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
