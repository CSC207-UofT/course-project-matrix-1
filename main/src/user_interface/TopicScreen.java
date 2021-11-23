package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public TopicScreen() {

        topicPanel.setLayout(null);
        topicPanel.setBackground(new Color(177, 203, 187));

        // Create JLabels
        JLabel topicTitle = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel topicTitleShadow = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel numTypes = new JLabel("Number Types");
        JLabel topic = new JLabel("Topic");

        // Update the settings of each JLabel
        updateLabel(topicTitle, 0.2, 0.02, 0.6, 0.1, 0.03075, 'r');
        updateLabel(topicTitleShadow, 0.2025, 0.0225, 0.6, 0.1, 0.03075, 'd');
        updateLabel(numTypes, 0.325, 0.595, 0.25, 0.1, 0.02, 'd');
        updateLabel(topic, 0.325, 0.268, 0.25, 0.1, 0.02, 'd');

        updateButtonLocation(topicNextButton, 0.4, 0.775, 0.2, 0.09);
        updateButtonLocation(topicScreenBackButton, 0.145, 0.8, 0.15, 0.05);

        // Update the settings of each button
        defaultButton(topicButtons);

        topicNextButton.addMouseListener(this);
        topicScreenBackButton.addMouseListener(this);

        // Create comboBox for number types (for now, just integers is available)
        numOptions.setBounds(convert(0.525, 'w'), convert(0.625, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
//        numOptions.setSelectedIndex(0);

        topicChose.setBounds(convert(0.525, 'w'), convert(0.3, 'h'), convert(0.15, 'w'),
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

        changePanel(topicPanel);

    }

    public void mouseClicked(MouseEvent e) {
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
