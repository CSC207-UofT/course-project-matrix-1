package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;

/**
 * Customize Screen class for the User Interface. The customize worksheet screen prompts the user for their desired
 * equation details and format details for the worksheet and handles invalid inputs.
 *
 * @author Ethan Ing, Piotr pralat
 * @since 2021-11-01
 */
public class CustomizeScreen extends StartScreen implements MouseListener {

    // Create buttons
    JButton generateWorksheetButton = new JButton("Generate Worksheet");
    JButton customizeBackButton = new JButton("Back");
    JButton [] generateWSButtons = {generateWorksheetButton, customizeBackButton};

    // Create Equation Details and Formatting JLabels and its shadow
    JLabel equationDetailsTitle = new JLabel("Equation Details", SwingConstants.CENTER);
    JLabel equationDetailsShadow = new JLabel("Equation Details", SwingConstants.CENTER);
    JLabel formatTitle = new JLabel("Formatting", SwingConstants.CENTER);
    JLabel formatShadow = new JLabel("Formatting", SwingConstants.CENTER);
    JLabel invalidInput = new JLabel("Invalid Input(s)", SwingConstants.CENTER);

    // Create text fields
    JTextField title_tf = new JTextField(1);
    JTextField numQuestions_tf = new JTextField(1);
    JTextField numRows_tf = new JTextField(1);
    JTextField numColumn_tf = new JTextField(1);

    JTextField op1MIN = new JTextField(1);
    JTextField op1MAX = new JTextField(1);
    JTextField op2MIN = new JTextField(1);
    JTextField op2MAX = new JTextField(1);

    // Create checkbox
    JCheckBox negAllowedBox = new JCheckBox("");

    // Create combo box for question format
    String[] options2 = {"Horizontal"};
    JComboBox<String> questionFormat = new JComboBox<>(options2);

    public CustomizeScreen() {

        // Change cardPanel to the custom worksheet screen
        cardLayout.show(cardPanel, "CustomizeScreen");

        // Create equation questions labels
        JLabel op1Range = new JLabel("Operand 1 Range");
        JLabel dash = new JLabel("-");
        JLabel op2Range = new JLabel("Operand 2 Range");
        JLabel dash2 = new JLabel("-");
        JLabel negAllowed = new JLabel("Negative are Allowed?");

        // Update the labels for the equation customization
        updateLabel(equationDetailsTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'r');
        updateLabel(equationDetailsShadow, 0.2025, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(op1Range, 0.25, 0.1, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash, 0.645, 0.1, 0.05, 0.1, 0.025, 'd');
        updateLabel(op2Range, 0.25, 0.175, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash2, 0.645, 0.175, 0.1, 0.1, 0.025, 'd');
        updateLabel(negAllowed, 0.25, 0.25, 0.6, 0.1, 0.02, 'd');
        updateLabel(invalidInput, 0.4, 0.75, 0.2, 0.05, 0.015, 'r');

        // Minimum and maximum text fields
        op1MIN.setBounds(convert(0.525, 'w'), convert(0.125, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        op1MAX.setBounds(convert(0.675, 'w'), convert(0.125, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        op2MIN.setBounds(convert(0.525, 'w'), convert(0.2, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        op2MAX.setBounds(convert(0.675, 'w'), convert(0.2, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

        // Update checkbox location
        negAllowedBox.setBounds(convert(0.565, 'w'), convert(0.275, 'h'), convert(0.5, 'w'),
                convert(0.5, 'h'));
        negAllowedBox.setSize(new Dimension(40, 40));

        // Create formatting questions labels
        JLabel qFormat = new JLabel("Question Format");
        JLabel titleLabel = new JLabel("Title");
        JLabel numQuestions = new JLabel("Number of Questions");
        JLabel numRows = new JLabel("Number of Rows");
        JLabel numColumns = new JLabel("Number of Columns");

        // Update location of the combobox for the question format
        questionFormat.setBounds(convert(0.535, 'w'), convert(0.405, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
        questionFormat.setSelectedIndex(0);

        // Update the location of each text field
        title_tf.setBounds(convert(0.525, 'w'), convert(0.475, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numQuestions_tf.setBounds(convert(0.525, 'w'), convert(0.55, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numRows_tf.setBounds(convert(0.525, 'w'), convert(0.625, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numColumn_tf.setBounds(convert(0.525, 'w'), convert(0.7, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Update the labels for formatting
        updateLabel(formatTitle, 0.2, 0.3025, 0.6, 0.1, 0.03075, 'r');
        updateLabel(formatShadow, 0.2025, 0.305, 0.6, 0.1, 0.03075, 'd');
        updateLabel(qFormat, 0.25, 0.375, 0.6, 0.1, 0.02, 'd');
        updateLabel(titleLabel, 0.25, 0.45, 0.6, 0.1, 0.02, 'd');
        updateLabel(numQuestions, 0.25, 0.525, 0.6, 0.1, 0.02, 'd');
        updateLabel(numRows, 0.25, 0.6, 0.6, 0.1, 0.02, 'd');
        updateLabel(numColumns, 0.25, 0.675, 0.6, 0.1, 0.02, 'd');

        // Update the location of each button
        updateButtonLocation(generateWorksheetButton, 0.35, 0.8, 0.3, 0.1);
        updateButtonLocation(customizeBackButton, 0.145, 0.8, 0.15, 0.05);

        // Update the settings of each button
        defaultButton(generateWSButtons);

        // Add Mouse Listener for hover and clicking features
        generateWorksheetButton.addMouseListener(this);
        customizeBackButton.addMouseListener(this);

        // Add all components to the panel
        customizeWSPanel.add(equationDetailsTitle);
        customizeWSPanel.add(equationDetailsShadow);
        customizeWSPanel.add(formatTitle);
        customizeWSPanel.add(formatShadow);

        customizeWSPanel.add(generateWorksheetButton);
        customizeWSPanel.add(customizeBackButton);

        customizeWSPanel.add(op1Range);
        customizeWSPanel.add(op1MIN);
        customizeWSPanel.add(dash);
        customizeWSPanel.add(op1MAX);
        customizeWSPanel.add(op2Range);
        customizeWSPanel.add(op2MIN);
        customizeWSPanel.add(dash2);
        customizeWSPanel.add(op2MAX);
        customizeWSPanel.add(negAllowed);
        customizeWSPanel.add(negAllowedBox);

        customizeWSPanel.add(qFormat);
        customizeWSPanel.add(questionFormat);
        customizeWSPanel.add(titleLabel);
        customizeWSPanel.add(title_tf);
        customizeWSPanel.add(numQuestions);
        customizeWSPanel.add(numQuestions_tf);
        customizeWSPanel.add(numRows);
        customizeWSPanel.add(numRows_tf);
        customizeWSPanel.add(numColumns);
        customizeWSPanel.add(numColumn_tf);
        customizeWSPanel.add(invalidInput);
        invalidInput.setVisible(false);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            boolean passed = true;

            // Create temporary equation details and format details variables
            int op1Min_temp, op1Max_temp, op2Min_temp, op2Max_temp;
            op1Min_temp = op1Max_temp = op2Min_temp = op2Max_temp = -1;

            int numOfEquations_temp, numOfRows_temp, numOfColumns_temp;
            numOfEquations_temp = numOfRows_temp = numOfColumns_temp = -1;

            // Check if any operand range cannot be parsed (invalid input)
            if (tryToParse(op1MIN.getText()) == null || tryToParse(op1MAX.getText()) == null ||
                    tryToParse(op2MIN.getText()) == null || tryToParse(op2MAX.getText()) == null) {
                invalidInput.setVisible(true);
                passed = false;
            }
            else {
                op1Min_temp = Integer.parseInt(op1MIN.getText());
                op1Max_temp = Integer.parseInt(op1MAX.getText());
                op2Min_temp = Integer.parseInt(op2MIN.getText());
                op2Max_temp = Integer.parseInt(op2MAX.getText());
            }

            // Check to see if all operand range are greater than zero and max > min
            if (op1Min_temp >= 0 && op1Max_temp >= 0 && op2Min_temp >= 0 && op2Max_temp >= 0
                    && op1Max_temp >= op1Min_temp && op2Max_temp >= op2Min_temp) {
                operandRange1 = new int[]{op1Min_temp, op1Max_temp};
                operandRange2 = new int[]{op2Min_temp, op2Max_temp};
                invalidInput.setVisible(false);
            }
            else {
                invalidInput.setVisible(true);
                passed = false;
            }

            // Get selection for checkbox, question format, and title
            negAllowed = negAllowedBox.isSelected();
            equationFormat = Objects.requireNonNull(questionFormat.getSelectedItem()).toString();
            titleInput = title_tf.getText();

            // Check if any formatting text fields are empty
            if (tryToParse(numQuestions_tf.getText()) == null || tryToParse(numRows_tf.getText()) == null ||
                    tryToParse(numColumn_tf.getText()) == null || titleInput.length() == 0) {
                invalidInput.setVisible(true);
                passed = false;
            }
            else {
                numOfEquations_temp = Integer.parseInt(numQuestions_tf.getText());
                numOfRows_temp = Integer.parseInt(numRows_tf.getText());
                numOfColumns_temp = Integer.parseInt(numColumn_tf.getText());
            }

            // Check that number of equations, rows, and columns are greater than zero
            if (numOfEquations_temp > 0 && numOfRows_temp > 0 && numOfColumns_temp > 0) {
                numOfEquations = numOfEquations_temp;
                numOfRows = numOfRows_temp;
                numOfColumns = numOfColumns_temp;
            }
            else {
                invalidInput.setVisible(true);
                passed = false;
            }

            // If all inputs check out, add to the equation details and formatting details hashmaps
            if (passed) {
                frame.setVisible(false);
                customizeWSPanel.setVisible(false);

                equationDetails.put("numOfEquations", numOfEquations);
                equationDetails.put("operator", chosen_topic);
                equationDetails.put("operandRange1", operandRange1);
                equationDetails.put("operandRange2", operandRange2);
                equationDetails.put("negAllowed", negAllowed);

                formatDetails.put("equationFormat", equationFormat);
                formatDetails.put("title", titleInput);
                formatDetails.put("numRows", numOfRows);
                formatDetails.put("numColumns", numOfColumns);

                try {
                    new WorksheetViewerScreen();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == customizeBackButton) {
            frame.setVisible(false);
            customizeWSPanel.setVisible(false);
            new TopicScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            highlightButton(generateWorksheetButton);
        }
        if (e.getSource() == customizeBackButton) {
            highlightButton(customizeBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            defaultButton(generateWorksheetButton);
        }
        if (e.getSource() == customizeBackButton) {
            defaultButton(customizeBackButton);
        }
    }
}
