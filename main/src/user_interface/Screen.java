package user_interface;

import user_package.UserController;
import worksheet_maker.WorksheetController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * Screen class for the User Interface. Superclass that stores panels and each method to update buttons and labels.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-09
 */
public class Screen extends JFrame implements MouseListener {

    // Screen size Dimensions are set to full screen
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = screenSize.width;
    static int height = screenSize.height;

    // Create all Panels and Frames
    static JFrame frame = new JFrame();

    JPanel loginPanel = new JPanel();
    JPanel optionPanel = new JPanel();
    JPanel topicPanel = new JPanel();
    JPanel customizePanel = new JPanel();
    JPanel previewPanel = new JPanel();
    JPanel historyPanel = new JPanel();
    JPanel newUserPanel = new JPanel();
    JPanel userProfilePanel = new JPanel();

    // Create a user controller and worksheet controller instance
    public static UserController userController;
    public static WorksheetController worksheetController;

    ImageIcon profileIconImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("userProfileIcon.png")));
    Image profileImage = profileIconImage.getImage();
    Image profileScaledImage = profileImage.getScaledInstance(150,150, Image.SCALE_SMOOTH);

    ImageIcon historyIconImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("userHistoryIcon.png")));
    Image historyImage = historyIconImage.getImage();
    Image historyScaledImage = historyImage.getScaledInstance(67,67, Image.SCALE_SMOOTH);

    static String userName = "";

    public Screen() {
    }

    public void changePanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
    }

    public void updatePanel(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(new Color(177, 203, 187));
        panel.setBorder(BorderFactory.createMatteBorder(convert(0.15, 'h'), 2, 2,
                2, new Color(142, 202, 234, 255)));

        JLabel matrixTitle = new JLabel("Matrix", SwingConstants.CENTER);
        JLabel matrixTitleShadow = new JLabel("Matrix", SwingConstants.CENTER);
        updateLabel(matrixTitle, 0.35, 0.03, 0.3, 0.1, 0.03, 'r');
        updateLabel(matrixTitleShadow, 0.3525, 0.0325, 0.3, 0.1, 0.03, 'd');
        panel.add(matrixTitle);
        panel.add(matrixTitleShadow);
    }

    /**
     * Update the location of each button.
     * Precondition:
     * - Each parameter is less than 1
     *
     * @param x the constant multiplied by the screen width to get the starting x-location of the button
     * @param y the constant multiplied by the screen height to get the starting y-location of the button
     * @param width the constant multiplied by the screen width to get the button width
     * @param height the constant multiplied by the screen height to get the button height
     */
    public void updateButtonLocation(JButton b, double x, double y, double width, double height) {
        int startX = convert(x, 'w');
        int startY = convert(y, 'y');
        int buttonWidth = convert(width, 'w');
        int buttonHeight = convert(height, 'h');
        b.setBounds(startX, startY, buttonWidth, buttonHeight);
    }

    /**
     * Update the location of each label.
     * Precondition:
     * - Each parameter is less than 1
     *
     * @param x the constant multiplied by the screen width to get the starting x-location of the label
     * @param y the constant multiplied by the screen height to get the starting y-location of the label
     * @param w the constant multiplied by the screen width to get the label width
     * @param h the constant multiplied by the screen height to get the label height
     * @param textSize the constant used to get the text size for the label
     * @param r the color of the font (r being red, anything else being dark gray)
     */
    public void updateLabel(JLabel l, double x, double y, double w, double h, double textSize, char r) {
        l.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * textSize)));
        l.setBounds(convert(x, 'w'), convert(y, 'h'), convert(w, 'w'),
                convert(h, 'h'));
        if (r == 'r') {
            l.setForeground(new Color(255, 55, 51));
        }
        else if (r == 'd'){
            l.setForeground(Color.DARK_GRAY);
        }
        else if (r == 'w'){
            l.setForeground(Color.WHITE);
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
     * @param buttons an array list of JButtons that will be updated to the default settings
     */
    public void defaultButton(JButton[] buttons) {
        for (JButton button: buttons) {
            button.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
            button.setOpaque(true);
            button.setForeground(Color.DARK_GRAY);
            button.setBorder(BorderFactory.createMatteBorder(4, 4, 4,
                    4, Color.DARK_GRAY));
            button.setBackground(new Color(220, 220, 220, 255));
        }
    }

    /**
     * Update the settings of each text field
     *
     * @param textFields an array list of JTextFields that will be updated to the default settings
     */
    public void updateTextFields(JTextField[] textFields) {
        for (JTextField textField: textFields) {
            textField.setBorder(BorderFactory.createMatteBorder(2, 2, 2,
                    2, Color.DARK_GRAY));
        }
    }


    /**
     * Update the settings of the button to the default (font, color, and border). Overloaded method with one button.
     *
     * @param button a JButton that will be updated to the default settings
     */
    public void defaultButton(JButton button) {
        button.setOpaque(true);
        button.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
        button.setForeground(Color.DARK_GRAY);
        button.setBorder(BorderFactory.createMatteBorder(4, 4, 4,
                4, Color.DARK_GRAY));
        button.setBackground(new Color(220, 220, 220, 255));
    }

    /**
     * Update the button to be highlighted red and a bigger font.
     *
     * @param button a JButton that will be highlighted
     */
    public void highlightButton(JButton button) {
        button.setForeground(new Color(255,55,51));
        button.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
    }

    /**
     * Convert the constant, num, multiplied by either the width or height ot an integer
     * Precondition:
     * - num must be less than 1
     *
     * @param num the constant to be multiplied by the width or height
     * @param c the width or height to be multiplied by num (w for width, h for height)
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
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new LoginScreen();
    }
}

