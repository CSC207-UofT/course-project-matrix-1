package user_interface;

import equation_parameters.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Objects;

import static constants.EquationFormats.*;
import static constants.EquationType.FRACTION;
import static constants.EquationType.WHOLE_NUMBER;

/**
 * Topic Screen class for the User Interface. The topic screen prompts the user for their desired
 * worksheet topic (e.g. addition) and formatting details.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
@SuppressWarnings("ALL")
public class TopicScreen extends Screen implements MouseListener, KeyListener {

    // Craete buttons
    JButton topicNextButton = new JButton("Next");
    JButton topicScreenBackButton = new JButton("Back");

    // Create combo box options
    String[] numTypeOptions = {WHOLE_NUMBER, FRACTION};
    JComboBox<String> numOptions = new JComboBox<>(numTypeOptions);
    String[] topicOptions = {"Addition", "Subtraction", "Multiplication", "Division", "Exponentiation", "LCM", "GCD"};
    JComboBox<String> topicChose = new JComboBox<>(topicOptions);
    String[] questionFormatOptions = {HORIZONTAL, VERTICAL, DIVISION_BRACKET};
    JComboBox<String> questionFormat = new JComboBox<>(questionFormatOptions);

    // Create text fields
    JTextField titleInput;
    JTextField numQuestionsInput;
    JTextField numRowsInput;
    JTextField numColumnInput;

    JTextField[] textFields;

    // Make Equation and format details
    EquationDetails equationDetails;
    FormatDetails formatDetails;

    // Invalid input JLabel
    JLabel invalidFormat = new JLabel("Invalid Input(s)", SwingConstants.CENTER);
    JLabel invalidQuestionType = new JLabel("Invalid Combination", SwingConstants.CENTER);

    // Create variables for the details and formatting
    String equationFormat = " ";
    String worksheetTitle = " ";
    int numOfRows = -1;
    int numOfColumns = -1;
    int numOfEquations = -1;

    public TopicScreen() {

        // Update the panel to the default settings
        updatePanel(topicPanel);

        // Instantiate the equation and format details
        equationDetails = new WholeNumEquationDetails();
        formatDetails = new FormatDetails();

        // Create the text fields
        titleInput = new JTextField(1);
        numQuestionsInput = new JTextField(1);
        numRowsInput = new JTextField(1);
        numColumnInput = new JTextField(1);

        textFields = new JTextField[]{titleInput, numQuestionsInput, numRowsInput, numColumnInput};

        // Set JComboBox
        topicChose.setSelectedIndex(0);
        numOptions.setSelectedIndex(0);
        questionFormat.setSelectedIndex(0);
        fillScreen();
    }

    /**
     * Overloaded constructor method. Used to persist previous user input.
     *
     * @param worksheetDetails contains worksheet details that user inputted in this screen previously
     */
    public TopicScreen(Map<String, Object> worksheetDetails) {

        // Update the panel's default settings
        updatePanel(topicPanel);

        // Get the previous equation and format details
        this.equationDetails = (EquationDetails) worksheetDetails.get("equationDetails");
        this.formatDetails = (FormatDetails) worksheetDetails.get("formatDetails");

        // Initialize equation details with previous input
        if (equationDetails instanceof WholeNumEquationDetails) {
            numOptions.setSelectedItem(WHOLE_NUMBER);
        } else if (equationDetails instanceof FractionAddSubEquationDetails || equationDetails instanceof FractionMultiDivEquationDetails) {
            numOptions.setSelectedItem(FRACTION);
        }

        // Set the selected items to be seen on the screen
        switch (equationDetails.getOperator()) {
            case "+":
                topicChose.setSelectedItem("Addition");
                break;
            case "-":
                topicChose.setSelectedItem("Subtraction");
                break;
            case "/":
                topicChose.setSelectedItem("Division");
                break;
            case "*":
                topicChose.setSelectedItem("Multiplication");
                break;
            case "^":
                topicChose.setSelectedItem("Exponentiation");
                break;
            case "LCM":
                topicChose.setSelectedItem("LCM");
                break;
            case "GCD":
                topicChose.setSelectedItem("GCD");
                break;
        }

        // Initialize format details with previous input
        titleInput = new JTextField(formatDetails.getTitle(), 1);
        numQuestionsInput = new JTextField(Integer.toString(equationDetails.getNumOfEquations()), 1);
        numRowsInput = new JTextField(Integer.toString(formatDetails.getNumRows()), 1);
        numColumnInput = new JTextField(Integer.toString(formatDetails.getNumColumns()), 1);
        questionFormat.setSelectedItem(formatDetails.getEquationFormat());

        textFields = new JTextField[]{titleInput, numQuestionsInput, numRowsInput, numColumnInput};

        fillScreen();
    }

    /**
     * Adds all necessary parts of TopicScreen (labels, textfields, buttons).
     */
    private void fillScreen() {

        // Create topic JLabels
        JLabel topicTitle = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel topicTitleShadow = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel topic = new JLabel("Operator");
        JLabel numTypes = new JLabel("Number Types");

        // Create formatting questions labels
        JLabel formatTitle = new JLabel("Formatting", SwingConstants.CENTER);
        JLabel formatTitleShadow = new JLabel("Formatting", SwingConstants.CENTER);
        JLabel qFormat = new JLabel("Question Format");
        JLabel titleLabel = new JLabel("Title");
        JLabel numQuestions = new JLabel("Number of Questions");
        JLabel numRows = new JLabel("Number of Rows");
        JLabel numColumns = new JLabel("Number of Columns");

        // Update the settings of each JLabel
        updateLabel(topicTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(topicTitleShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(formatTitle, 0.2, 0.39, 0.6, 0.1, 0.03075, 'b');
        updateLabel(formatTitleShadow, 0.2025, 0.3925, 0.6, 0.1, 0.03075, 'd');
        updateLabel(numTypes, 0.325, 0.3, 0.25, 0.1, 0.02, 'd');
        updateLabel(topic, 0.325, 0.225, 0.25, 0.1, 0.02, 'd');
        updateLabel(qFormat, 0.25, 0.45, 0.6, 0.1, 0.02, 'd');
        updateLabel(titleLabel, 0.25, 0.525, 0.6, 0.1, 0.02, 'd');
        updateLabel(numQuestions, 0.25, 0.6, 0.6, 0.1, 0.02, 'd');
        updateLabel(numRows, 0.25, 0.675, 0.6, 0.1, 0.02, 'd');
        updateLabel(numColumns, 0.25, 0.75, 0.6, 0.1, 0.02, 'd');
        updateLabel(invalidFormat, 0.35, 0.831, 0.3, 0.03, 0.0125, 'w');
        updateLabel(invalidQuestionType, 0.3, 0.3775, 0.4, 0.03, 0.012, 'w');

        // Create the settings of the invalid input warnings
        invalidFormat.setOpaque(true);
        invalidFormat.setBackground(lightYellow);
        invalidFormat.setVisible(false);
        invalidQuestionType.setOpaque(true);
        invalidQuestionType.setBackground(lightYellow);
        invalidQuestionType.setVisible(false);

        // Update the location and settings of each text field
        updateTextFieldLocation(titleInput, 0.525, 0.55, 0.175, 0.05);
        updateTextFieldLocation(numQuestionsInput, 0.525, 0.625, 0.175, 0.05);
        updateTextFieldLocation(numRowsInput, 0.525, 0.7, 0.175, 0.05);
        updateTextFieldLocation(numColumnInput, 0.525, 0.775, 0.175, 0.05);
        updateTextFields(textFields);

        // Update the settings and location of the buttons
        updateButtonLocation(topicNextButton, 0.4, 0.87, 0.17, 0.09);
        updateButtonLocation(topicScreenBackButton, 0.145, 0.85, 0.15, 0.05);
        defaultButton(topicNextButton, 'b');
        defaultButton(topicScreenBackButton, 'd');

        // Add Mouse Listener for hover and clicking features
        topicNextButton.addMouseListener(this);
        topicScreenBackButton.addMouseListener(this);

        // Add Key Listener for the JTextFields
        titleInput.addKeyListener(this);
        numQuestionsInput.addKeyListener(this);
        numRowsInput.addKeyListener(this);
        numColumnInput.addKeyListener(this);

        // Create comboBox for number types
        numOptions.setBounds(convert(0.525, 'w'), convert(0.325, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
        topicChose.setBounds(convert(0.525, 'w'), convert(0.25, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
        questionFormat.setBounds(convert(0.535, 'w'), convert(0.48, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));

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

        topicPanel.add(qFormat);
        topicPanel.add(questionFormat);
        topicPanel.add(titleLabel);
        topicPanel.add(titleInput);
        topicPanel.add(numQuestions);
        topicPanel.add(numQuestionsInput);
        topicPanel.add(numRows);
        topicPanel.add(numRowsInput);
        topicPanel.add(numColumns);
        topicPanel.add(numColumnInput);
        topicPanel.add(invalidFormat);
        topicPanel.add(invalidQuestionType);

        // Change the panel to the topic panel
        changePanel(topicPanel);
    }


    /**
     * Check if the user's inputted values are valid and add them to equation and format details.
     * If they are invalid, let the user know via warnings.
     */
    private void checkValidDetails() {
        String type = (String) numOptions.getSelectedItem();

        // Add the topic to the equation details
        if (Objects.equals(type, FRACTION)) {
            String topic = (String) topicChose.getSelectedItem();
            if (Objects.equals(topic, "Addition")) {
                equationDetails = new FractionAddSubEquationDetails();
                this.equationDetails.setOperator("+");
            } else if (Objects.equals(topic, "Subtraction")) {
                equationDetails = new FractionAddSubEquationDetails();
                this.equationDetails.setOperator("-");
            } else if (Objects.equals(topic, "Multiplication")) {
                equationDetails = new FractionMultiDivEquationDetails();
                this.equationDetails.setOperator("*");
            } else if (Objects.equals(topic, "Division")) {
                equationDetails = new FractionMultiDivEquationDetails();
                this.equationDetails.setOperator("/");
            }
        } else if (Objects.equals(type, WHOLE_NUMBER)) {
            String topic = (String) topicChose.getSelectedItem();
            equationDetails = new WholeNumEquationDetails();
            if (Objects.equals(topic, "Addition")) {
                this.equationDetails.setOperator("+");
            } else if (Objects.equals(topic, "Subtraction")) {
                this.equationDetails.setOperator("-");
            } else if (Objects.equals(topic, "Multiplication")) {
                this.equationDetails.setOperator("*");
            } else if (Objects.equals(topic, "Division")) {
                this.equationDetails.setOperator("/");
            } else if (Objects.equals(topic, "Exponentiation")) {
                this.equationDetails.setOperator("^");
            } else if (Objects.equals(topic, "LCM")) {
                this.equationDetails.setOperator("LCM");
            } else if (Objects.equals(topic, "GCD")) {
                this.equationDetails.setOperator("GCD");
            }
        }

        boolean passed = true;

        // Create temporary equation details and format details variables
        int numOfEquationsTemp, numOfRowsTemp, numOfColumnsTemp;
        numOfEquationsTemp = numOfRowsTemp = numOfColumnsTemp = -1;

        // Set the title and question format
        equationFormat = (String) questionFormat.getSelectedItem();
        worksheetTitle = titleInput.getText();

        // Check if any formatting text fields are empty
        if (tryToParse(numQuestionsInput.getText().trim()) == null || tryToParse(numRowsInput.getText().trim()) == null ||
                tryToParse(numColumnInput.getText().trim()) == null || worksheetTitle.trim().length() == 0) {
            invalidFormat.setVisible(true);
            passed = false;
        } else {
            // Each value can be parsed
            numOfEquationsTemp = Integer.parseInt(numQuestionsInput.getText().trim());
            numOfRowsTemp = Integer.parseInt(numRowsInput.getText().trim());
            numOfColumnsTemp = Integer.parseInt(numColumnInput.getText().trim());
        }

        // Check that number of equations, rows, and columns are greater than zero
        if (numOfEquationsTemp > 0 && numOfRowsTemp > 0 && numOfColumnsTemp > 0) {
            numOfEquations = numOfEquationsTemp;
            numOfRows = numOfRowsTemp;
            numOfColumns = numOfColumnsTemp;
        } else {
            invalidFormat.setVisible(true);
            passed = false;
        }

        // Check invalid combinations (question types, format, and topics)
        if (((String) topicChose.getSelectedItem() == "Exponentiation" && (String) numOptions.getSelectedItem() == FRACTION) ||
                ((String) topicChose.getSelectedItem() == "LCM" && (String) numOptions.getSelectedItem() == FRACTION) ||
                ((String) topicChose.getSelectedItem() == "GCF" && (String) numOptions.getSelectedItem() == FRACTION)) {
            invalidQuestionType.setText("Invalid Operator & Question Type Combination");
            invalidQuestionType.setVisible(true);
            passed = false;
        } else if ((String) topicChose.getSelectedItem() != "Division" && equationFormat == DIVISION_BRACKET ||
                (String) topicChose.getSelectedItem() == "Exponentiation" && equationFormat != HORIZONTAL) {
            invalidQuestionType.setText("Invalid Operator & Question Format Combination");
            invalidQuestionType.setVisible(true);
            passed = false;
        } else if ((String) numOptions.getSelectedItem() == FRACTION && equationFormat != HORIZONTAL) {
            invalidQuestionType.setText("Invalid Question Type & Format Combination");
            invalidQuestionType.setVisible(true);
            passed = false;
        }

        // All inputs are valid and can be added to equation and format details
        if (passed) {
            this.equationDetails.setNumOfEquations(numOfEquations);
            this.formatDetails.setTitle(titleInput.getText().trim());
            this.formatDetails.setEquationFormat(equationFormat);
            this.formatDetails.setNumColumns(numOfColumns);
            this.formatDetails.setNumRows(numOfRows);

            new CustomizeScreen(equationDetails, formatDetails);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            checkValidDetails();
        } else if (e.getSource() == topicScreenBackButton) {
            new OptionScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            highlightButton(topicNextButton, 'b');
        } else if (e.getSource() == topicScreenBackButton) {
            highlightButton(topicScreenBackButton, 'd');
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            defaultButton(topicNextButton, 'b');
        } else if (e.getSource() == topicScreenBackButton) {
            defaultButton(topicScreenBackButton, 'd');
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            checkValidDetails();
        }
    }
}
