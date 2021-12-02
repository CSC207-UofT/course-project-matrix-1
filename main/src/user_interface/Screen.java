package user_interface;

import user_package.UserController;
import worksheet_maker.WorksheetController;

import javax.swing.*;
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
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = screenSize.width;
    static int height = screenSize.height;

    // Create all Panels and Frames
    static JFrame frame = new JFrame();
    // Create a user controller and worksheet controller instance
    static UserController userController;
    static WorksheetController worksheetController;
    static String username;
    JPanel loginPanel = new JPanel();
    JPanel optionPanel = new JPanel();
    JPanel topicPanel = new JPanel();
    JPanel customizePanel = new JPanel();
    JPanel previewPanel = new JPanel();
    JPanel historyPanel = new JPanel();
    JPanel newUserPanel = new JPanel();
    JPanel userProfilePanel = new JPanel();
    Font titleFont = new Font("Monospaced", Font.BOLD, convert(0.03, 'w'));

    public Screen() {
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

    public static void main(String[] args) {
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new LoginScreen();
    }

    public void changePanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
    }

    public void updatePanel(JPanel panel) {
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createMatteBorder(convert(0.15, 'h'), 2, 2,
                2, new Color(142, 202, 234, 255)));

        JLabel matrixTitle = new JLabel("Matrix", SwingConstants.CENTER);
        JLabel matrixTitleShadow = new JLabel("Matrix", SwingConstants.CENTER);
        updateLabel(matrixTitle, 0.35, 0.03, 0.3, 0.1, 0.04, 'd');
        updateLabel(matrixTitleShadow, 0.3525, 0.0325, 0.3, 0.1, 0.04, 'w');
        panel.add(matrixTitle);
        panel.add(matrixTitleShadow);
    }

    /**
     * Update the location of each button.
     * Precondition:
     * - Each parameter is less than 1
     *
     * @param x      the constant multiplied by the screen width to get the starting x-location of the button
     * @param y      the constant multiplied by the screen height to get the starting y-location of the button
     * @param width  the constant multiplied by the screen width to get the button width
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
     * @param x         the constant multiplied by the screen width to get the starting x-location of the label
     * @param y         the constant multiplied by the screen height to get the starting y-location of the label
     * @param w         the constant multiplied by the screen width to get the label width
     * @param h         the constant multiplied by the screen height to get the label height
     * @param textSize  the constant used to get the text size for the label
     * @param fontColor the color of the font (r being red, anything else being dark gray)
     */
    public void updateLabel(JLabel l, double x, double y, double w, double h, double textSize, char fontColor) {
        l.setFont(new Font("Monospaced", Font.BOLD, (int) Math.round((width * 0.5 + height) * textSize)));
        l.setBounds(convert(x, 'w'), convert(y, 'h'), convert(w, 'w'),
                convert(h, 'h'));
        if (fontColor == 'd') {
            l.setForeground(Color.DARK_GRAY);
        } else if (fontColor == 'w') {
            l.setForeground(Color.WHITE);
        } else if (fontColor == 'g') {
            l.setForeground(new Color(177, 203, 187));
        } else if (fontColor == 'b') {
            l.setForeground(new Color(142, 202, 234, 255));
        }
    }

    /**
     * Update the settings of each button to the default (font, color, and border).
     *
     * @param buttons an array list of JButtons that will be updated to the default settings
     */
    public void defaultButton(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setFont(new Font("Monospaced", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
            button.setOpaque(true);
            button.setForeground(Color.DARK_GRAY);
            button.setBorder(BorderFactory.createMatteBorder(4, 4, 4,
                    4, Color.DARK_GRAY));
            button.setBackground(new Color(220, 220, 220));
        }
    }

    /**
     * Update the settings of each text field
     *
     * @param textFields an array list of JTextFields that will be updated to the default settings
     */
    public void updateTextFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            textField.setBorder(BorderFactory.createMatteBorder(2, 2, 2,
                    2, Color.DARK_GRAY));
            textField.setOpaque(true);
            textField.setBackground(new Color(220, 220, 220));
        }
    }

    /**
     * Update the settings of the button to the default (font, color, and border). Overloaded method with one button.
     *
     * @param button a JButton that will be updated to the default settings
     */
    public void defaultButton(JButton button, char borderColor) {
        button.setOpaque(true);
        button.setFont(new Font("Monospaced", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
        button.setForeground(Color.DARK_GRAY);
        button.setBackground(new Color(220, 220, 220));

        if (borderColor == 'd') {
            button.setBorder(BorderFactory.createMatteBorder(4, 4, 4,
                    4, Color.DARK_GRAY));
        } else if (borderColor == 'b') {
            button.setBorder(BorderFactory.createMatteBorder(4, 4, 4,
                    4, new Color(142, 202, 234, 255)));
        }

    }

    /**
     * Update the button to be highlighted red and a bigger font.
     *
     * @param button a JButton that will be highlighted
     */
    public void highlightButton(JButton button, char borderColor) {
        button.setForeground(new Color(142, 202, 234, 255));
        button.setOpaque(true);
        button.setFont(new Font("Monospaced", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));

        if (borderColor == 'd') {
            button.setBorder(BorderFactory.createMatteBorder(4, 4, 4,
                    4, Color.DARK_GRAY));
        } else if (borderColor == 'b') {
            button.setBorder(BorderFactory.createMatteBorder(4, 4, 4,
                    4, new Color(142, 202, 234, 255)));
        }

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
}

