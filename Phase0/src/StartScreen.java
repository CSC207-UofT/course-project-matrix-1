import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartScreen extends JFrame implements MouseListener {

    // Screen size Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    JFrame frame = new JFrame();
    JPanel Panel = new JPanel();

    // Create Buttons
    JButton createWorksheetButton = new JButton("Create Worksheet");
    JButton userButton = new JButton("User Profile");

    public StartScreen() {

        // Set Frame
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Panel and Border
        Panel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        Panel.setLayout(null);
        frame.add(Panel);
        frame.setVisible(true);

        // "Create Worksheet" and "User Profile" Button
        updateButtonLocation(createWorksheetButton, 0.35, 0.5, 0.3, 0.1);
        defaultButton(createWorksheetButton);

        updateButtonLocation(userButton, 0.35, 0.625, 0.3, 0.1);
        defaultButton(userButton);

        // Add Mouse Listener for hover features
        createWorksheetButton.addMouseListener(this);
        userButton.addMouseListener(this);

        // Create Title JLabel and it's shadow
        JLabel title = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
        JLabel titleShadow = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
        updateLabel(title, 0.2, 0.2, 0.6, 0.1, 0.03075, 'n');
        updateLabel(titleShadow, 0.2025, 0.2025, 0.6, 0.1, 0.03075, 'd');

        // Add Buttons and Label to the panel
        Panel.add(createWorksheetButton);
        Panel.add(userButton);
        Panel.add(title);
        Panel.add(titleShadow);
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

    // Default Button Font, Font size
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
        if (e.getSource() == createWorksheetButton) {
            new TopicScreen();

        }
        if (e.getSource() == userButton)
            System.out.println("User Profile Page");
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createWorksheetButton)
            highlightButton(createWorksheetButton);
        if (e.getSource() == userButton)
            highlightButton(userButton);
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createWorksheetButton)
            defaultButton(createWorksheetButton);
        if (e.getSource() == userButton)
            defaultButton(userButton);
    }

    public static void main(String[] args) {
        new StartScreen();
    }

}
