package user_interface;

import user_package.UserController;
import worksheet_maker.WorksheetController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Screen class for the User Interface. Superclass that stores panels and each method to update buttons and labels.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-09
 */
public class Screen extends JFrame implements MouseListener {

    // Screen size Dimensions are set to full screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    // Create all Panels and Frames
    JFrame frame = new JFrame();
    JPanel cardPanel = new JPanel();
    JPanel startPanel = new JPanel();
    JPanel optionPanel = new JPanel();
    JPanel topicPanel = new JPanel();
    JPanel customizeWSPanel = new JPanel();
    JPanel viewerPanel = new JPanel();
    JPanel historyPanel = new JPanel();
    JPanel newUserPanel = new JPanel();
    JPanel userProfilePanel = new JPanel();

    JPanel[] panels = {startPanel, optionPanel, topicPanel, customizeWSPanel, viewerPanel, historyPanel,
            newUserPanel, userProfilePanel};

    // Card Layout for the Panels to switch between them
    CardLayout cardLayout = new CardLayout();

    // Create a user controller and worksheet controller instance
    public static UserController userController;
    public static WorksheetController worksheetController;

    public Screen() {
        System.out.println("test");

        // Set Frame size to full screen
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Update each panel to the default panel settings
        updatePanels(panels);

        // Adding each panel to the card layout
        frame.add(cardPanel);
        cardPanel.setLayout(cardLayout);
        cardPanel.add(startPanel, "StartScreen");
        cardPanel.add(optionPanel, "OptionScreen");
        cardPanel.add(topicPanel, "TopicScreen");
        cardPanel.add(customizeWSPanel, "CustomizeScreen");
        cardPanel.add(viewerPanel, "ViewerScreen");
        cardPanel.add(historyPanel, "WorksheetHistoryScreen");
        cardPanel.add(newUserPanel, "NewUserScreen");
        cardPanel.add(userProfilePanel, "UserProfileScreen");
    }

    /**
     * Update the border and layout settings of each panel
     *
     * @param panels an array list of JPanels that will be updated
     */
    public void updatePanels(JPanel[] panels) {
        for (JPanel p : panels) {
            p.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                    convert(0.1, 'w'), Color.BLACK));
            p.setLayout(null);
        }
    }

    /**
     * Update the location of each button.
     * Precondition:
     * - Each parameter is less than 1
     *
     * @param x the constant multiplied by the screen width to get the starting x-location of the button
     * @param y the constant multiplied by the screen height to get the starting y-location of the button
     * @param w the constant multiplied by the screen width to get the button width
     * @param h the constant multiplied by the screen height to get the button height
     */
    public void updateButtonLocation(JButton b, double x, double y, double w, double h) {
        int start_x = convert(x, 'w');
        int start_y = convert(y, 'y');
        int b_width = convert(w, 'w');
        int b_height = convert(h, 'h');
        b.setBounds(start_x, start_y, b_width, b_height);
    }

    /**
     * Update the location of each label.
     * Precondition:
     * - Each parameter is less than 1
     *
     * @param x        the constant multiplied by the screen width to get the starting x-location of the label
     * @param y        the constant multiplied by the screen height to get the starting y-location of the label
     * @param w        the constant multiplied by the screen width to get the label width
     * @param h        the constant multiplied by the screen height to get the label height
     * @param textSize the constant used to get the text size for the label
     * @param r        the color of the font (r being red, anything else being dark gray)
     */
    public void updateLabel(JLabel l, double x, double y, double w, double h, double textSize, char r) {
        l.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * textSize)));
        l.setBounds(convert(x, 'w'), convert(y, 'h'), convert(w, 'w'),
                convert(h, 'h'));
        if (r == 'r') {
            l.setForeground(new Color(255, 55, 51));
        } else {
            l.setForeground(Color.DARK_GRAY);
        }
    }

    /**
     * Update the location of each scroll panel.
     * Precondition:
     * - Each parameter is less than 1
     *
     * @param x the constant multiplied by the screen width to get the starting x-location of the scroll panel
     * @param y the constant multiplied by the screen height to get the starting y-location of the scroll panel
     * @param w the constant multiplied by the screen width to get the scroll panel width
     * @param h the constant multiplied by the screen height to get the scroll panel height
     */
    public void updateList(JScrollPane l, double x, double y, double w, double h) {
        l.setBounds(convert(x, 'w'), convert(y, 'h'), convert(w, 'w'),
                convert(h, 'h'));
    }

    /**
     * Update the settings of each button to the default (font, color, and border).
     *
     * @param b an array list of JButtons that will be updated to the default settings
     */
    public void defaultButton(JButton[] b) {
        for (JButton button : b) {
            button.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
            button.setForeground(Color.DARK_GRAY);
            button.setBorder(new LineBorder(Color.DARK_GRAY));
        }
    }

    /**
     * Update the settings of the button to the default (font, color, and border). Overloaded method with one button.
     *
     * @param b a JButton that will be updated to the default settings
     */
    public void defaultButton(JButton b) {
        b.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
        b.setForeground(Color.DARK_GRAY);
        b.setBorder(new LineBorder(Color.DARK_GRAY));
    }

    /**
     * Update the button to be highlighted red and a bigger font.
     *
     * @param b a JButton that will be highlighted
     */
    public void highlightButton(JButton b) {
        b.setForeground(new Color(255, 55, 51));
        b.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
    }

    /**
     * Convert the constant, num, multiplied by either the width or height ot an integer
     * Precondition:
     * - num must be less than 1
     *
     * @param num the constant to be multiplied by the width or height
     * @param c   the width or height to be multiplied by num (w for width, h for height)
     */
    public int convert(double num, char c) {
        if (c == 'w')
            return (int) Math.round(num * width);
        else {
            return (int) Math.round(num * height);
        }
    }

    /**
     * Attempt to parse a String to an integer
     *
     * @param input the String input that will be parsed
     */
    public static Integer tryToParse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}

