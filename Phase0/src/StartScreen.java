import user_package.UserController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class StartScreen extends JFrame implements MouseListener {

    // Screen size Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    // JPanels and JFrames
    JFrame frame = new JFrame();
    JPanel cardPanel = new JPanel();
    JPanel startPanel = new JPanel();
    JPanel optionPanel = new JPanel();
    JPanel topicPanel = new JPanel();
    JPanel customizeWSPanel = new JPanel();
    JPanel viewerPanel = new JPanel();
    JPanel historyPanel = new JPanel();

    // Card Layout for the Panels
    CardLayout cardLayout = new CardLayout();

    // Create Buttons
    JButton loginButton = new JButton("Login");
    JButton createUserButton = new JButton("Create User");

    // Create Title JLabel and its shadow
    JLabel title = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
    JLabel titleShadow = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
    JLabel username = new JLabel("Username", SwingConstants.CENTER);

    // Create text fields
    JTextField username_tf = new JTextField(1);

    // Stores the equation details for the worksheet
    static char chosen_topic = ' ';
    static int numOfEquations = -1;
    static int [] operandRange1 = {-1, -1};
    static int [] operandRange2 = {-1, -1};
    static boolean negAllowed = false;

    // Stores the format details for the worksheet
    static String equationFormat = " ";
    static String titleInput = " ";
    static int numOfRows = -1;
    static int numOfColumns = -1;

    static HashMap<String, Object> equationDetails = new HashMap<>();
    static HashMap <String, Object> formatDetails = new HashMap<>();

    static UserController uc = new UserController();

    public StartScreen() {

        // Set Frame size
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Set Panel and Border
        startPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        startPanel.setLayout(null);

        // Adding the card layout to the frame
        frame.add(cardPanel);
        cardPanel.setLayout(cardLayout);
        cardPanel.add(startPanel, "StartScreen");
        cardPanel.add(optionPanel, "OptionScreen");
        cardPanel.add(topicPanel, "TopicScreen");
        cardPanel.add(customizeWSPanel, "CustomizeScreen");
        cardPanel.add(viewerPanel, "ViewerScreen");
        cardPanel.add(historyPanel, "WorksheetHistoryScreen");
        cardPanel.add(newUserPanel, "NewUserScreen");

        cardLayout.show(cardPanel, "StartScreen");

        // "Create Worksheet" and "User Profile" Button
        updateButtonLocation(loginButton, 0.4125, 0.645, 0.175, 0.08);
        updateButtonLocation(createUserButton, 0.425, 0.77, 0.15, 0.075);
        defaultButton(loginButton);
        defaultButton(createUserButton);
        loginButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
        createUserButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.015)));

        // Add Mouse Listener for hover features
        loginButton.addMouseListener(this);
        createUserButton.addMouseListener(this);

        // Update the settings of each Label
        updateLabel(title, 0.2, 0.2, 0.6, 0.1, 0.03075, 'n');
        updateLabel(titleShadow, 0.2025, 0.2025, 0.6, 0.1, 0.03075, 'd');
        updateLabel(username, 0.25, 0.525, 0.3, 0.1,0.025, 'd');

        username_tf.setBounds(convert(0.5, 'w'), convert(0.55, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Add Buttons and Label to the panel
        startPanel.add(title);
        startPanel.add(titleShadow);
        startPanel.add(loginButton);
        startPanel.add(createUserButton);
        startPanel.add(username);
        startPanel.add(username_tf);
    }

    // Location and dimensions helper
    public void updateButtonLocation(JButton b, double x, double y, double w, double h) {
        int start_x = convert(x, 'w');
        int start_y = convert(y, 'y');
        int b_width = convert(w, 'w');
        int b_height = convert(h, 'h');
        b.setBounds(start_x, start_y, b_width, b_height);
    }

    // JLabel Updater
    public void updateLabel(JLabel l, double x, double y, double w, double h, double t, char n) {
        l.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * t)));
        l.setBounds(convert(x, 'w'), convert(y, 'h'), convert(w, 'w'),
                convert(h, 'h'));
        if (n == 'n') {
            l.setForeground(new Color(255, 55, 51));
        }
        else {
            l.setForeground(Color.DARK_GRAY);
        }
    }

    // JList Updater
    public void updateList(JScrollPane l, double x, double y, double w, double h) {
        l.setBounds(convert(x, 'w'), convert(y, 'h'), convert(w, 'w'),
                convert(h, 'h'));
    }

    // Default Button Font, Font size
    public void defaultButton(JButton[] b) {
        for (JButton button: b) {
            button.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
            button.setForeground(Color.DARK_GRAY);
            button.setBorder(new LineBorder(Color.DARK_GRAY));
        }
    }

    public void defaultButton(JButton b) {
        b.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.02)));
        b.setForeground(Color.DARK_GRAY);
        b.setBorder(new LineBorder(Color.DARK_GRAY));
    }

    // Highlighted button features
    public void highlightButton(JButton b) {
        b.setForeground(new Color(255,55,51));
        b.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
    }

    // Quick convert from double to integer
    public int convert(double num, char c) {
        if (c == 'w')
            return (int) Math.round(num * width);
        else {
            return (int) Math.round(num * height);
        }
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == loginButton) {
            startPanel.setVisible(false);
            frame.setVisible(false);
            new OptionScreen();
        }
        if (e.getSource() == createUserButton) {
            System.out.println("Create new user Page");
        }

    }

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
