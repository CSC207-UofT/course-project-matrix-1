import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TopicScreen extends StartScreen implements MouseListener {

    // Create Buttons
    JButton addButton = new JButton("Addition");
    JButton subButton = new JButton("Subtraction");
    JButton multiButton = new JButton("Multiplication");
    JButton divButton = new JButton("Division");
    JButton next = new JButton("Next");
    JPanel panel2 = new JPanel();

    String buttonCLicked = new String("None");

    public TopicScreen() {

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel2);
        frame.revalidate();

        // Set Panel and Border
        panel2.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        panel2.setLayout(null);


        // Create Choose Topic JLabel and its shadow
        JLabel title1 = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel title1Shadow = new JLabel("Choose Topic", SwingConstants.CENTER);
        updateLabel(title1, 0.2, 0.02, 0.6, 0.1, 0.03075, 'n');
        updateLabel(title1Shadow, 0.2, 0.0225, 0.6, 0.1, 0.03075, 'd');

        // "Create Worksheet" and "User Profile" Button
        updateButtonLocation(addButton, 0.35, 0.125, 0.3, 0.1);
        defaultButton(addButton);

        updateButtonLocation(subButton, 0.35, 0.25, 0.3, 0.1);
        defaultButton(subButton);

        updateButtonLocation(multiButton, 0.35, 0.375, 0.3, 0.1);
        defaultButton(multiButton);

        updateButtonLocation(divButton, 0.35, 0.5, 0.3, 0.1);
        defaultButton(divButton);

        // "Next" Button
        updateButtonLocation(next, 0.4, 0.75, 0.2, 0.09);
        defaultButton(next);
        next.addMouseListener(this);

        // Create Equation Questions JLabel
        JLabel numTypes = new JLabel("Number Types");
        updateLabel(numTypes, 0.33, 0.6, 0.6, 0.1, 0.02, 'd');

        String[] options = {"Integers", "Fractions", "Decimals"};
        JComboBox<String> numOptions = new JComboBox<>(options);
        numOptions.setBounds(convert(0.525, 'w'), convert(0.6025, 'h'), convert(0.15, 'w'),
                convert(0.1, 'h'));
        numOptions.setSelectedIndex(0);

        addButton.addMouseListener(this);
        subButton.addMouseListener(this);
        multiButton.addMouseListener(this);
        divButton.addMouseListener(this);
        next.addMouseListener(this);

        panel2.add(addButton);
        panel2.add(subButton);
        panel2.add(multiButton);
        panel2.add(divButton);
        panel2.add(next);
        panel2.add(title1);
        panel2.add(title1Shadow);
        panel2.add(numTypes);
        panel2.add(numOptions);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == addButton) {
            defaultButton(subButton);
            defaultButton(multiButton);
            defaultButton(divButton);
            buttonCLicked = "add";
            highlightButton(addButton);
        }
        if (e.getSource() == subButton) {
            defaultButton(addButton);
            defaultButton(multiButton);
            defaultButton(divButton);
            buttonCLicked = "sub";
            highlightButton(subButton);
        }
        if (e.getSource() == multiButton) {
            defaultButton(addButton);
            defaultButton(subButton);
            defaultButton(divButton);
            buttonCLicked = "multi";
            highlightButton(multiButton);
        }
        if (e.getSource() == divButton) {
            defaultButton(addButton);
            defaultButton(subButton);
            defaultButton(multiButton);
            buttonCLicked = "div";
            highlightButton(divButton);
        }
        if (e.getSource() == next) {
            new CustomizeScreen();
            frame.setVisible(false);
        }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == next)
            highlightButton(next);

    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == next)
            defaultButton(next);
    }


}
