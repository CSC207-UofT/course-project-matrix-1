package user_interface;

import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Topic Screen class for the User Interface. The topic screen prompts the user for their desired
 * worksheet topic (e.g. addition)
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class TopicScreen extends Screen implements MouseListener {


    JButton topicNextButton = new JButton("Next");
    JButton topicScreenBackButton = new JButton("Back");
    JButton[] topicButtons = {topicNextButton, topicScreenBackButton};

    // Create the equation and format details maps
    Map<String, Object> equationDetailsTopic = new HashMap<>();

    String[] numTypeOptions = {"Integers"};
    JComboBox<String> numOptions = new JComboBox<>(numTypeOptions);

    String[] topicOptions = {"Addition", "Subtraction", "Multiplication", "Division"};
    JComboBox<String> topicChose = new JComboBox<>(topicOptions);
    //TODO: add input for other types (ex. fraction, decimal) and change the equation detail here accordingly.
    EquationDetails equationDetails = new WholeNumEquationDetails();

    public TopicScreen() {

        updatePanel(topicPanel);

        // Create JLabels
        JLabel topicTitle = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel topicTitleShadow = new JLabel("Choose Topic", SwingConstants.CENTER);

        JLabel formatTitle = new JLabel("Formatting", SwingConstants.CENTER);
        JLabel formatTitleShadow = new JLabel("Formatting", SwingConstants.CENTER);

        JLabel topic = new JLabel("Operator");
        JLabel numTypes = new JLabel("Number Types");

        // Update the settings of each JLabel
        updateLabel(topicTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'd');
        updateLabel(topicTitleShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'w');
        updateLabel(formatTitle, 0.2, 0.375, 0.6, 0.1, 0.03075, 'd');
        updateLabel(formatTitleShadow, 0.2025, 0.3775, 0.6, 0.1, 0.03075, 'w');

        updateLabel(numTypes, 0.325, 0.325, 0.25, 0.1, 0.02, 'd');
        updateLabel(topic, 0.325, 0.225, 0.25, 0.1, 0.02, 'd');

        updateButtonLocation(topicNextButton, 0.4, 0.775, 0.2, 0.09);
        updateButtonLocation(topicScreenBackButton, 0.145, 0.8, 0.15, 0.05);

        // Update the settings of each button
        defaultButton(topicButtons);

        topicNextButton.addMouseListener(this);
        topicScreenBackButton.addMouseListener(this);

        // Create comboBox for number types (for now, just integers is available)
        numOptions.setBounds(convert(0.525, 'w'), convert(0.35, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
//        numOptions.setSelectedIndex(0);

        topicChose.setBounds(convert(0.525, 'w'), convert(0.25, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
//        topicChose.setSelectedIndex(0);


        // Add each component to the panel
        topicPanel.add(numOptions);
        topicPanel.add(topicChose);
        topicPanel.add(topicNextButton);
        topicPanel.add(topicScreenBackButton);
        topicPanel.add(topicTitle);
        topicPanel.add(topicTitleShadow);
        topicPanel.add(numTypes);
        topicPanel.add(topic);
        topicPanel.add(formatTitle);
        topicPanel.add(formatTitleShadow);

        changePanel(topicPanel);

    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == addButton) {
            defaultButton(subButton);
            defaultButton(multiButton);
            defaultButton(divButton);
            highlightButton(addButton);
            equationDetails.setOperator('+');
        }
        if (e.getSource() == subButton) {
            defaultButton(addButton);
            defaultButton(divButton);
            defaultButton(multiButton);
            highlightButton(subButton);
            equationDetails.setOperator('-');
        }
        if (e.getSource() == multiButton) {
            defaultButton(divButton);
            defaultButton(addButton);
            defaultButton(subButton);
            highlightButton(multiButton);
            equationDetails.setOperator('*');
        }
        if (e.getSource() == divButton) {
            defaultButton(addButton);
            defaultButton(subButton);
            defaultButton(multiButton);
            highlightButton(divButton);
            equationDetails.setOperator('/');
        }
        if (e.getSource() == topicNextButton) {
            String topic = (String) topicChose.getSelectedItem();
            if (Objects.equals(topic, "Addition")) {
                equationDetailsTopic.put("operator", '+');
            }
            else if (Objects.equals(topic, "Subtraction")) {
                equationDetailsTopic.put("operator", '-');
            }
            else if (Objects.equals(topic, "Multiplication")) {
                equationDetailsTopic.put("operator", '*');
            }
            else if (Objects.equals(topic, "Division")) {
                equationDetailsTopic.put("operator", '/');
            }

            new CustomizeScreen(equationDetailsTopic);
            topicPanel.setVisible(false);
            frame.setVisible(false);
            new CustomizeScreen(equationDetails);
        }
        else if (e.getSource() == topicScreenBackButton) {
            new OptionScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            highlightButton(topicNextButton);
        }
        else if (e.getSource() == topicScreenBackButton) {
            highlightButton(topicScreenBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            defaultButton(topicNextButton);
        }
        else if (e.getSource() == topicScreenBackButton) {
            defaultButton(topicScreenBackButton);
        }
    }
}
