package user_interface;

import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Topic Screen class for the User Interface. The topic screen prompts the user for their desired
 * worksheet topic (e.g. addition)
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class TopicScreen extends Screen implements MouseListener {

    // Create buttons
    JButton addButton = new JButton("Addition");
    JButton subButton = new JButton("Subtraction");
    JButton multiButton = new JButton("Multiplication");
    JButton divButton = new JButton("Division");
    JButton topicNextButton = new JButton("Next");
    JButton topicScreenBackButton = new JButton("Back");
    JButton[] topicButtons = {addButton, subButton, multiButton, divButton, topicNextButton, topicScreenBackButton};

    // Create the equation and format details maps
    //TODO: add input for other types (ex. fraction, decimal) and change the equation detail here accordingly.
    EquationDetails equationDetails = new WholeNumEquationDetails();

    public TopicScreen() {

        // Set the Panel to the Topic Screen
        cardLayout.show(cardPanel, "TopicScreen");

        // Create JLabels
        JLabel topicTitle = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel topicTitleShadow = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel numTypes = new JLabel("Number Types", SwingConstants.CENTER);

        // Update the settings of each JLabel
        updateLabel(topicTitle, 0.2, 0.02, 0.6, 0.1, 0.03075, 'r');
        updateLabel(topicTitleShadow, 0.2, 0.0225, 0.6, 0.1, 0.03075, 'd');
        updateLabel(numTypes, 0.3, 0.59, 0.25, 0.1, 0.02, 'd');

        // "Update the location of each Button
        updateButtonLocation(addButton, 0.35, 0.125, 0.3, 0.1);
        updateButtonLocation(subButton, 0.35, 0.25, 0.3, 0.1);
        updateButtonLocation(multiButton, 0.35, 0.375, 0.3, 0.1);
        updateButtonLocation(divButton, 0.35, 0.5, 0.3, 0.1);
        updateButtonLocation(topicNextButton, 0.4, 0.775, 0.2, 0.09);
        updateButtonLocation(topicScreenBackButton, 0.145, 0.8, 0.15, 0.05);

        // Update the settings of each button and start with the addition button as the default choice
        defaultButton(topicButtons);
        highlightButton(addButton);
        equationDetails.setOperator("+");

        // Create comboBox for number types (for now, just integers is available)
        String[] numTypeOptions = {"Integers"};
        JComboBox<String> numOptions = new JComboBox<>(numTypeOptions);
        numOptions.setBounds(convert(0.525, 'w'), convert(0.625, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
        numOptions.setSelectedIndex(0);

        // Add Mouse Listener for hover and clicking features
        addButton.addMouseListener(this);
        subButton.addMouseListener(this);
        multiButton.addMouseListener(this);
        divButton.addMouseListener(this);
        topicNextButton.addMouseListener(this);
        topicScreenBackButton.addMouseListener(this);

        // Add each component to the panel
        topicPanel.add(addButton);
        topicPanel.add(subButton);
        topicPanel.add(multiButton);
        topicPanel.add(divButton);
        topicPanel.add(topicNextButton);
        topicPanel.add(topicScreenBackButton);
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
            highlightButton(addButton);
            equationDetails.setOperator("+");
        }
        if (e.getSource() == subButton) {
            defaultButton(addButton);
            defaultButton(divButton);
            defaultButton(multiButton);
            highlightButton(subButton);
            equationDetails.setOperator("-");
        }
        if (e.getSource() == multiButton) {
            defaultButton(divButton);
            defaultButton(addButton);
            defaultButton(subButton);
            highlightButton(multiButton);
            equationDetails.setOperator("*");
        }
        if (e.getSource() == divButton) {
            defaultButton(addButton);
            defaultButton(subButton);
            defaultButton(multiButton);
            highlightButton(divButton);
            equationDetails.setOperator("/");
        }
        if (e.getSource() == topicNextButton) {
            topicPanel.setVisible(false);
            frame.setVisible(false);
            new CustomizeScreen(equationDetails);
        }
        if (e.getSource() == topicScreenBackButton) {
            topicPanel.setVisible(false);
            frame.setVisible(false);
            new OptionScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            highlightButton(topicNextButton);
        }
        if (e.getSource() == topicScreenBackButton) {
            highlightButton(topicScreenBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            defaultButton(topicNextButton);
        }
        if (e.getSource() == topicScreenBackButton) {
            defaultButton(topicScreenBackButton);
        }
    }
}
