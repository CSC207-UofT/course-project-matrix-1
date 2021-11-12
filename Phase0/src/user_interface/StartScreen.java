package user_interface;

import exceptions.UserDoesNotExistException;
import user_package.UserController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Start Screen class for the User Interface. The login screen that prompts the user for their unique username
 * - Superclass that stores every screen's panels, equation details, and format details
 *
 * @author Ethan Ing, Piotr pralat
 * @since 2021-11-01
 */
public class StartScreen extends JFrame implements MouseListener {

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

    // Create Buttons
    JButton loginButton = new JButton("Login");
    JButton createUserButton = new JButton("Create User");

    // Create Title labels and its shadow
    JLabel matrixTitle = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
    JLabel matrixTitleShadow = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
    JLabel username = new JLabel("Username", SwingConstants.CENTER);
    JLabel invalidUsernameError = new JLabel("Invalid Username", SwingConstants.CENTER);

    // Create text fields
    JTextField username_tf = new JTextField(1);

    // Stores the equation details and format details for the worksheet with default values that are invalid
    static HashMap<String, Object> equationDetails = new HashMap<>();
    static HashMap <String, Object> formatDetails = new HashMap<>();
    static HashMap <String, Object> worksheetHistoryDetails = new HashMap<>();

    static char chosen_topic = ' ';
    static int numOfEquations = -1;
    static int [] operandRange1 = {-1, -1};
    static int [] operandRange2 = {-1, -1};
    static boolean negAllowed = false;

    static String equationFormat = " ";
    static String titleInput = " ";
    static int numOfRows = -1;
    static int numOfColumns = -1;
    static LocalDateTime dateAndTime;

    // Create a user controller class to keep track of the user's information
    static UserController uc;

    static {
        try {
            uc = new UserController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StartScreen() {

        // Set Frame size to ful screen
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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

        // Start showing the start screen (login) panel
        cardLayout.show(cardPanel, "StartScreen");

        // Update the location and settings of each Button
        updateButtonLocation(loginButton, 0.4125, 0.66, 0.175, 0.08);
        updateButtonLocation(createUserButton, 0.425, 0.77, 0.15, 0.075);
        defaultButton(loginButton);
        defaultButton(createUserButton);
        loginButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
        createUserButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.015)));

        // Add Mouse Listener for hover and clicking features
        loginButton.addMouseListener(this);
        createUserButton.addMouseListener(this);

        // Update the settings of each Label
        updateLabel(matrixTitle, 0.2, 0.2, 0.6, 0.1, 0.03075, 'r');
        updateLabel(matrixTitleShadow, 0.2025, 0.2025, 0.6, 0.1, 0.03075, 'd');
        updateLabel(username, 0.25, 0.525, 0.3, 0.1,0.025, 'd');
        updateLabel(invalidUsernameError, 0.4,0.525,0.2,0.2,0.015, 'r');

        // Initially set the invalid username error to not visible
        invalidUsernameError.setVisible(false);

        // Set the location of the text field
        username_tf.setBounds(convert(0.5, 'w'), convert(0.55, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Add each component to the panel
        startPanel.add(matrixTitle);
        startPanel.add(matrixTitleShadow);
        startPanel.add(loginButton);
        startPanel.add(createUserButton);
        startPanel.add(username);
        startPanel.add(username_tf);
        startPanel.add(invalidUsernameError);
    }

    /**
     * Update the border and layout settings of each panel
     *
     * @param panels an array list of JPanels that will be updated
     */
    public void updatePanels(JPanel[] panels) {
        for (JPanel p: panels) {
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
        else {
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
        for (JButton button: b) {
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
        b.setForeground(new Color(255,55,51));
        b.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
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
     * Mouse clicked features when each button is clicked
     *
     * @param e keeps track of which button was clicked
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == loginButton) {
            // Attempt to login the user with the username entered
            try {
                uc.login(username_tf.getText());
                startPanel.setVisible(false);
                frame.setVisible(false);
                new OptionScreen();
            } catch (UserDoesNotExistException u) {
                invalidUsernameError.setVisible(true);  // Display an error message of the username doesn't exist
            }
        }
        // Move to create new user screen
        if (e.getSource() == createUserButton) {
            startPanel.setVisible(false);
            frame.setVisible(false);
            new NewUserScreen();
        }
    }

    /**
     * Mouse entered feature when each button is being hovered over
     *
     * @param e keeps track of which button was being hovered over
     */
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == loginButton) {
            highlightButton(loginButton);
            loginButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.025)));
        }
        if (e.getSource() == createUserButton) {
            highlightButton(createUserButton);
            createUserButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0175)));
        }
    }

    /**
     * Mouse exited feature when each button is not being hovered over
     *
     * @param e keeps track of which button stopped being overed over
     */
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginButton) {
            defaultButton(loginButton);
            loginButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
        }
        if (e.getSource() == createUserButton) {
            defaultButton(createUserButton);
            createUserButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.015)));
        }
    }
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {

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
        new StartScreen();
    }

}
