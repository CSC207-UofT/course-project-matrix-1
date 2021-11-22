package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Customize Screen class for the User Interface. The "customize worksheet screen" prompts the user for their desired
 * equation details and format details for the worksheet and handles invalid inputs.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class CustomizeScreen extends Screen implements MouseListener {

    // Create buttons
    JButton generateWorksheetButton = new JButton("Generate Worksheet");
    JButton customizeBackButton = new JButton("Back");
    JButton [] generateWSButtons = {generateWorksheetButton, customizeBackButton};

    // Invalid input JLabel
    JLabel invalidInput = new JLabel("Invalid Input(s)", SwingConstants.CENTER);

    // Create text fields
    JTextField titleInput = new JTextField(1);
    JTextField numQuestionsInput = new JTextField(1);
    JTextField numRowsInput = new JTextField(1);
    JTextField numColumnInput = new JTextField(1);

    JTextField op1MIN = new JTextField(1);
    JTextField op1MAX = new JTextField(1);
    JTextField op2MIN = new JTextField(1);
    JTextField op2MAX = new JTextField(1);

    // Create checkbox
    JCheckBox negAllowedBox = new JCheckBox("");

    // Create combo box for question format
    String[] questionFormatOptions = {"Horizontal"};
    JComboBox<String> questionFormat = new JComboBox<>(questionFormatOptions);

    // Create the equation details variables with initial invalid values
    int numOfEquations = -1;
    int [] operandRange1 = {-1, -1};
    int [] operandRange2 = {-1, -1};
    boolean negAllowed = false;

    // Create the formatting details variables with initial invalid values
    String equationFormat = " ";
    String worksheetTitle = " ";
    int numOfRows = -1;
    int numOfColumns = -1;
    String dateAndTime;

    // Create the temporary map's to be passed into worksheet viewer screen
    Map <String, Object> equationsDetailsCustomizeScreen = new HashMap<>();
    Map <String, Object> formatDetailsCustomizeScreen = new HashMap<>();
    Map <String, Object> worksheetHistoryDetails = new HashMap<>();

    public CustomizeScreen(Map<String, Object> equation_details) {

        customizePanel.setLayout(null);

        // Gets the chosen topic from the previous screen
        equationsDetailsCustomizeScreen.put("operator", equation_details.get("operator"));

        // Create Equation Details and Formatting JLabels and its shadow
        JLabel equationDetailsTitle = new JLabel("Equation Details", SwingConstants.CENTER);
        JLabel equationDetailsShadow = new JLabel("Equation Details", SwingConstants.CENTER);
        JLabel formatTitle = new JLabel("Formatting", SwingConstants.CENTER);
        JLabel formatShadow = new JLabel("Formatting", SwingConstants.CENTER);

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

        // Initially set the invalid input to not visible
        invalidInput.setVisible(false);

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
        titleInput.setBounds(convert(0.525, 'w'), convert(0.475, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numQuestionsInput.setBounds(convert(0.525, 'w'), convert(0.55, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numRowsInput.setBounds(convert(0.525, 'w'), convert(0.625, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numColumnInput.setBounds(convert(0.525, 'w'), convert(0.7, 'h'), convert(0.175, 'w'),
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
        customizePanel.add(equationDetailsTitle);
        customizePanel.add(equationDetailsShadow);
        customizePanel.add(formatTitle);
        customizePanel.add(formatShadow);

        customizePanel.add(generateWorksheetButton);
        customizePanel.add(customizeBackButton);

        customizePanel.add(op1Range);
        customizePanel.add(op1MIN);
        customizePanel.add(dash);
        customizePanel.add(op1MAX);
        customizePanel.add(op2Range);
        customizePanel.add(op2MIN);
        customizePanel.add(dash2);
        customizePanel.add(op2MAX);
        customizePanel.add(negAllowed);
        customizePanel.add(negAllowedBox);

        customizePanel.add(qFormat);
        customizePanel.add(questionFormat);
        customizePanel.add(titleLabel);
        customizePanel.add(titleInput);
        customizePanel.add(numQuestions);
        customizePanel.add(numQuestionsInput);
        customizePanel.add(numRows);
        customizePanel.add(numRowsInput);
        customizePanel.add(numColumns);
        customizePanel.add(numColumnInput);
        customizePanel.add(invalidInput);

        changePanel(customizePanel);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {

            boolean passed = true;

            // Create temporary equation details and format details variables
            int op1MinTemp, op1MaxTemp, op2MinTemp, op2MaxTemp;
            op1MinTemp = op1MaxTemp = op2MinTemp = op2MaxTemp = -1;

            int numOfEquationsTemp, numOfRowsTemp, numOfColumnsTemp;
            numOfEquationsTemp = numOfRowsTemp = numOfColumnsTemp = -1;

            // Check if any operand range cannot be parsed (invalid input)
            if (tryToParse(op1MIN.getText()) == null || tryToParse(op1MAX.getText()) == null ||
                    tryToParse(op2MIN.getText()) == null || tryToParse(op2MAX.getText()) == null) {
                invalidInput.setVisible(true);
                passed = false;
            }
            else {
                // Each operand value can be parsed (is an integer)
                op1MinTemp = Integer.parseInt(op1MIN.getText());
                op1MaxTemp = Integer.parseInt(op1MAX.getText());
                op2MinTemp = Integer.parseInt(op2MIN.getText());
                op2MaxTemp = Integer.parseInt(op2MAX.getText());
            }

            // Check to see if all operand range are greater than zero and max > min
            if (op1MinTemp >= 0 && op1MaxTemp >= 0 && op2MinTemp >= 0 && op2MaxTemp >= 0
                    && op1MaxTemp >= op1MinTemp && op2MaxTemp >= op2MinTemp) {
                operandRange1 = new int[]{op1MinTemp, op1MaxTemp};      // Set the operand 1 range to inputted values
                operandRange2 = new int[]{op2MinTemp, op2MaxTemp};      // Set the operand 2 range to inputted values
            }
            else {
                invalidInput.setVisible(true);
                passed = false;
            }

            // Get selection for checkbox, question format, and title
            negAllowed = negAllowedBox.isSelected();
            equationFormat = Objects.requireNonNull(questionFormat.getSelectedItem()).toString();
            worksheetTitle = titleInput.getText();

            // Check if any formatting text fields are empty
            if (tryToParse(numQuestionsInput.getText()) == null || tryToParse(numRowsInput.getText()) == null ||
                    tryToParse(numColumnInput.getText()) == null || worksheetTitle.length() == 0) {
                invalidInput.setVisible(true);
                passed = false;
            }
            else {
                // Each value can be parsed
                numOfEquationsTemp = Integer.parseInt(numQuestionsInput.getText());
                numOfRowsTemp = Integer.parseInt(numRowsInput.getText());
                numOfColumnsTemp = Integer.parseInt(numColumnInput.getText());
            }

            // Check that number of equations, rows, and columns are greater than zero
            if (numOfEquationsTemp > 0 && numOfRowsTemp > 0 && numOfColumnsTemp > 0) {
                numOfEquations = numOfEquationsTemp;
                numOfRows = numOfRowsTemp;
                numOfColumns = numOfColumnsTemp;
            }
            else {
                invalidInput.setVisible(true);
                passed = false;
            }

            // If all inputs check out, add to the equation details and formatting details maps
            if (passed) {

                // Add all equation details to the map
                equationsDetailsCustomizeScreen.put("numOfEquations", numOfEquations);
                equationsDetailsCustomizeScreen.put("operandRange1", operandRange1);
                equationsDetailsCustomizeScreen.put("operandRange2", operandRange2);
                equationsDetailsCustomizeScreen.put("negAllowed", negAllowed);

                // Add all formatting details to the map
                formatDetailsCustomizeScreen.put("equationFormat", equationFormat);
                formatDetailsCustomizeScreen.put("title", worksheetTitle);
                formatDetailsCustomizeScreen.put("numRows", numOfRows);
                formatDetailsCustomizeScreen.put("numColumns", numOfColumns);

                // Find the exact date/time the user created the worksheet
                dateAndTime = LocalDateTime.now().toString();

                // Create the unique worksheet history details
                worksheetHistoryDetails.put("worksheetKey", dateAndTime);
                worksheetHistoryDetails.put("equationDetails", equationsDetailsCustomizeScreen);
                worksheetHistoryDetails.put("formatDetails", formatDetailsCustomizeScreen);

                try {
                    new WorksheetViewerScreen(equationsDetailsCustomizeScreen, formatDetailsCustomizeScreen,
                            worksheetHistoryDetails);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if (e.getSource() == customizeBackButton) {
            new TopicScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            highlightButton(generateWorksheetButton);
        }
        else if (e.getSource() == customizeBackButton) {
            highlightButton(customizeBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            defaultButton(generateWorksheetButton);
        }
        else if (e.getSource() == customizeBackButton) {
            defaultButton(customizeBackButton);
        }
    }
}
