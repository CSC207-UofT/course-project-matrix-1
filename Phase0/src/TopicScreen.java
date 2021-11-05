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
    JButton topicNextButton = new JButton("Next");
    JButton[] topicButtons = {addButton, subButton, multiButton, divButton, topicNextButton};

    // Create Choose Topic JLabel and its shadow
    JLabel topicTitle = new JLabel("Choose Topic", SwingConstants.CENTER);
    JLabel topicTitleShadow = new JLabel("Choose Topic", SwingConstants.CENTER);
    JLabel numTypes = new JLabel("Number Types");

    // Stores the topic clicked
    String buttonCLicked = new String("None");

    public TopicScreen() {

        // Set the Panel to the Topic Screen
        cardLayout.show(cardPanel, "TopicScreen");

        topicPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        topicPanel.setLayout(null);

        // Update the settings of the each JLabel
        updateLabel(topicTitle, 0.2, 0.02, 0.6, 0.1, 0.03075, 'n');
        updateLabel(topicTitleShadow, 0.2, 0.0225, 0.6, 0.1, 0.03075, 'd');
        updateLabel(numTypes, 0.33, 0.6, 0.6, 0.1, 0.02, 'd');

        // "Create Worksheet" and "User Profile" Button
        updateButtonLocation(addButton, 0.35, 0.125, 0.3, 0.1);
        updateButtonLocation(subButton, 0.35, 0.25, 0.3, 0.1);
        updateButtonLocation(multiButton, 0.35, 0.375, 0.3, 0.1);
        updateButtonLocation(divButton, 0.35, 0.5, 0.3, 0.1);

        // "Next" Button
        updateButtonLocation(topicNextButton, 0.4, 0.75, 0.2, 0.09);

        // Update the settings of each button
        defaultButton(topicButtons);

        // Create comboBox of
        String[] numTypeOptions = {"Integers", "Fractions", "Decimals"};
        JComboBox<String> numOptions = new JComboBox<>(numTypeOptions);
        numOptions.setBounds(convert(0.525, 'w'), convert(0.6025, 'h'), convert(0.15, 'w'),
                convert(0.1, 'h'));
        numOptions.setSelectedIndex(0);

        addButton.addMouseListener(this);
        subButton.addMouseListener(this);
        multiButton.addMouseListener(this);
        divButton.addMouseListener(this);
        topicNextButton.addMouseListener(this);

        // Add each component to the panel
        topicPanel.add(addButton);
        topicPanel.add(subButton);
        topicPanel.add(multiButton);
        topicPanel.add(divButton);
        topicPanel.add(topicNextButton);
        topicPanel.add(topicTitle);
        topicPanel.add(topicTitleShadow);
        topicPanel.add(numTypes);
        topicPanel.add(numOptions);
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
            defaultButton(divButton);
            defaultButton(multiButton);
            buttonCLicked = "sub";
            highlightButton(subButton);
        }
        if (e.getSource() == multiButton) {
            defaultButton(divButton);
            defaultButton(addButton);
            defaultButton(subButton);
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
        if (e.getSource() == topicNextButton) {
            topicPanel.setVisible(false);
            frame.setVisible(false);
            new CustomizeScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == topicNextButton)
            highlightButton(topicNextButton);

    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == topicNextButton)
            defaultButton(topicNextButton);
    }
}
