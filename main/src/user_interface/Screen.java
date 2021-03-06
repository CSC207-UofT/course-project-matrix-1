package user_interface;

import user_package.UserController;
import user_package.UserPresenter;
import worksheet_maker.WorksheetController;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Screen class for the User Interface. Superclass that stores panels and each method to update buttons and labels.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-09
 */
public class Screen extends JFrame implements MouseListener, KeyListener {

    // Screen size Dimensions are set to full screen
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final int width = screenSize.width;
    static final int height = screenSize.height;

    // Create all Panels and Frames
    static final JFrame frame = new JFrame();
    // Create a user controller and worksheet controller instance
    static UserController userController;
    static UserPresenter userPresenter;
    static WorksheetController worksheetController;
    // Keep track of the username inputted
    static String username;
    final JPanel loginPanel = new JPanel();
    final JPanel optionPanel = new JPanel();
    final JPanel topicPanel = new JPanel();
    final JPanel customizePanel = new JPanel();
    final JPanel previewPanel = new JPanel();
    final JPanel historyPanel = new JPanel();
    final JPanel newUserPanel = new JPanel();
    final JPanel userProfilePanel = new JPanel();
    // Create the fonts
    final Font defaultButtonFont = new Font("Monospaced", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02));
    final Font highlightButtonFont = new Font("Monospaced", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225));

    // Create the Colors used
    final Color lightBlue = new Color(142, 202, 234, 255);
    final Color lightGray = new Color(220, 220, 220);
    final Color lightYellow = new Color(217, 207, 131, 252);
    final Color red = new Color(196, 67, 67);
    final Color darkGray = Color.DARK_GRAY;

    // Create the borders to be used (for panels, buttons, and text fields)
    final MatteBorder panelBorder = new MatteBorder(convert(0.15, 'h'), 2, 2, 2, lightBlue);
    final MatteBorder loginPanelBorder = new MatteBorder(convert(0.35, 'h'), 2, 2, 2, lightBlue);
    final MatteBorder defaultButtonBorder = new MatteBorder(4, 4, 4, 4, darkGray);
    final MatteBorder enterButtonBorder = new MatteBorder(4, 4, 4, 4, lightBlue);
    final MatteBorder textFieldBorder = new MatteBorder(2, 2, 2, 2, darkGray);

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
        // Set the frame to the maximum width and height of the screen and make it visible
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new LoginScreen();
    }

    /**
     * Remove the previous panel and make the current panel visible
     *
     * @param panel the panel to be displayed
     */
    public void changePanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
    }

    /**
     * Update the panel to include the default title and background color
     *
     * @param panel the panel to be updated to the default settings
     */
    public void updatePanel(JPanel panel) {
        panel.setLayout(null);
        panel.setBorder(panelBorder);

        JLabel matrixTitle = new JLabel("Matrix", SwingConstants.CENTER);
        JLabel matrixTitleShadow = new JLabel("Matrix", SwingConstants.CENTER);

        updateLabel(matrixTitle, 0.35, 0.03, 0.3, 0.1, 0.04, 'd');
        updateLabel(matrixTitleShadow, 0.3525, 0.0325, 0.3, 0.1, 0.04, 'w');

        panel.add(matrixTitle);
        panel.add(matrixTitleShadow);
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
     * @param fontColor the color of the font
     */
    public void updateLabel(JLabel label, double x, double y, double w, double h, double textSize, char fontColor) {
        label.setFont(new Font("Monospaced", Font.BOLD, (int) Math.round((width * 0.5 + height) * textSize)));
        label.setBounds(convert(x, 'w'), convert(y, 'h'), convert(w, 'w'),
                convert(h, 'h'));

        // Update the color of the font
        if (fontColor == 'd') {
            label.setForeground(darkGray);
        } else if (fontColor == 'w') {
            label.setForeground(Color.WHITE);
        } else if (fontColor == 'b') {
            label.setForeground(lightBlue);
        }
    }

    /**
     * Update the settings of each text field (border and color)
     *
     * @param textFields an array list of JTextFields that will be updated to the default settings
     */
    public void updateTextFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            textField.setBorder(textFieldBorder);
            textField.setOpaque(true);
            textField.setBackground(lightGray);
        }
    }

    /**
     * Update the location of each textField.
     * Precondition:
     * - Each parameter is less than 1
     *
     * @param x      the constant multiplied by the screen width to get the starting x-location of the textField
     * @param y      the constant multiplied by the screen height to get the starting y-location of the textField
     * @param width  the constant multiplied by the screen width to get the textField width
     * @param height the constant multiplied by the screen height to get the textField height
     */
    public void updateTextFieldLocation(JTextField textField, double x, double y, double width, double height) {
        int startX = convert(x, 'w');
        int startY = convert(y, 'y');
        int textFieldWidth = convert(width, 'w');
        int textFieldHeight = convert(height, 'h');
        textField.setBounds(startX, startY, textFieldWidth, textFieldHeight);
    }

    /**
     * Update the settings of each button to the default (font, color, and border).
     *
     * @param buttons an array list of JButtons that will be updated to the default settings
     */
    public void defaultButton(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setFont(defaultButtonFont);
            button.setOpaque(true);
            button.setForeground(darkGray);
            button.setBorder(defaultButtonBorder);
            button.setBackground(lightGray);
        }
    }

    /**
     * Update the settings of the button to the default (font, color, and border). Overloaded method with one button.
     *
     * @param button      a JButton that will be updated to the default settings
     * @param borderColor the button border color
     */
    public void defaultButton(JButton button, char borderColor) {
        button.setOpaque(true);
        button.setFont(defaultButtonFont);
        button.setForeground(darkGray);
        button.setBackground(lightGray);

        if (borderColor == 'd') {
            button.setBorder(defaultButtonBorder);
        } else if (borderColor == 'b') {
            button.setBorder(enterButtonBorder);
        }

    }

    /**
     * Update the button to be highlighted with a bigger font.
     *
     * @param button      a JButton that will be highlighted
     * @param borderColor the button border color
     */
    public void highlightButton(JButton button, char borderColor) {
        button.setOpaque(true);
        button.setForeground(lightBlue);
        button.setFont(highlightButtonFont);

        if (borderColor == 'd') {
            button.setBorder(defaultButtonBorder);
        } else if (borderColor == 'b') {
            button.setBorder(enterButtonBorder);
        }

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
    public void updateButtonLocation(JButton button, double x, double y, double width, double height) {
        int startX = convert(x, 'w');
        int startY = convert(y, 'y');
        int buttonWidth = convert(width, 'w');
        int buttonHeight = convert(height, 'h');
        button.setBounds(startX, startY, buttonWidth, buttonHeight);
    }

    /**
     * Convert the constant, num, multiplied by either the width or height ot an integer
     * Precondition:
     * - num must be less than 1
     *
     * @param num       the constant to be multiplied by the width or height
     * @param dimension the width or height to be multiplied by num (w for width, h for height)
     */
    public int convert(double num, char dimension) {
        if (dimension == 'w') {
            return (int) Math.round(num * width);
        } else {
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

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

