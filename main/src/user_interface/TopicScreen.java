package user_interface;

import equation_parameters.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Objects;

/**
 * Topic Screen class for the User Interface. The topic screen prompts the user for their desired
 * worksheet topic (e.g. addition)
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
@SuppressWarnings("ALL")
public class TopicScreen extends Screen implements MouseListener, KeyListener {


    JButton topicNextButton = new JButton("Next");
    JButton topicScreenBackButton = new JButton("Back");

    String[] numTypeOptions = {"Integers", "Fractions"};
    JComboBox<String> numOptions = new JComboBox<>(numTypeOptions);

    String[] topicOptions = {"Addition", "Subtraction", "Multiplication", "Division"};
    JComboBox<String> topicChose = new JComboBox<>(topicOptions);

    // Create text fields
    JTextField titleInput;
    JTextField numQuestionsInput;
    JTextField numRowsInput;
    JTextField numColumnInput;

    JTextField[] textFields;

    // Create combo box for question format
    String[] questionFormatOptions = {"Horizontal"};
    JComboBox<String> questionFormat = new JComboBox<>(questionFormatOptions);

    //TODO: add input for other types (ex. fraction, decimal) and change the equation detail here accordingly.
    EquationDetails equationDetails;
    FormatDetails formatDetails;

    // Invalid input JLabel
    JLabel invalidFormat = new JLabel("Please enter valid input(s)", SwingConstants.CENTER);
    JLabel invalidQuestionType = new JLabel("LCM not supported for fractions", SwingConstants.CENTER);

    String equationFormat = " ";
    String worksheetTitle = " ";
    int numOfRows = -1;
    int numOfColumns = -1;
    int numOfEquations = -1;

    public TopicScreen() {
        updatePanel(topicPanel);

        equationDetails = new WholeNumEquationDetails();
        formatDetails = new FormatDetails();

        titleInput = new JTextField(1);
        numQuestionsInput = new JTextField(1);
        numRowsInput = new JTextField(1);
        numColumnInput = new JTextField(1);

        textFields = new JTextField[]{titleInput, numQuestionsInput, numRowsInput, numColumnInput};

        // Set JComboBox
        topicChose.setSelectedIndex(0);
        numOptions.setSelectedIndex(0);
        fillScreen();
    }

    /**
     * Overloaded constructor method. Used to persist previous user input.
     *
     * @param worksheetDetails contains worksheet details that user inputted in this screen previously
     */
    public TopicScreen(Map<String, Object> worksheetDetails) {
        updatePanel(topicPanel);

        this.equationDetails = (EquationDetails) worksheetDetails.get("equationDetails");
        this.formatDetails = (FormatDetails) worksheetDetails.get("formatDetails");

        // Initialize equation details with previous input
        if (equationDetails instanceof WholeNumEquationDetails){
            numOptions.setSelectedItem("Integers");
        }
        else if (equationDetails instanceof FractionAddSubEquationDetails || equationDetails instanceof FractionMultiDivEquationDetails) {
            numOptions.setSelectedItem("Fractions");
        }

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
        }

        // Initialize format details with previous input
        titleInput = new JTextField(formatDetails.getTitle(), 1);
        numQuestionsInput = new JTextField(Integer.toString(equationDetails.getNumOfEquations()),1);
        numRowsInput = new JTextField(Integer.toString(formatDetails.getNumRows()), 1);
        numColumnInput = new JTextField(Integer.toString(formatDetails.getNumColumns()), 1);
        questionFormat.setSelectedItem(formatDetails.getEquationFormat());

        textFields = new JTextField[]{titleInput, numQuestionsInput, numRowsInput, numColumnInput};

        fillScreen();
    }


    /**
     * Adds all necessary parts of TopicScreen.
     */
    private void fillScreen() {
        // Create JLabels
        JLabel topicTitle = new JLabel("Choose Topic", SwingConstants.CENTER);
        JLabel topicTitleShadow = new JLabel("Choose Topic", SwingConstants.CENTER);

        JLabel formatTitle = new JLabel("Formatting", SwingConstants.CENTER);
        JLabel formatTitleShadow = new JLabel("Formatting", SwingConstants.CENTER);

        JLabel topic = new JLabel("Operator");
        JLabel numTypes = new JLabel("Number Types");

        // Update the settings of each JLabel
        updateLabel(topicTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(topicTitleShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(formatTitle, 0.2, 0.39, 0.6, 0.1, 0.03075, 'b');
        updateLabel(formatTitleShadow, 0.2025, 0.3925, 0.6, 0.1, 0.03075, 'd');

        updateLabel(numTypes, 0.325, 0.3, 0.25, 0.1, 0.02, 'd');
        updateLabel(topic, 0.325, 0.225, 0.25, 0.1, 0.02, 'd');

        invalidFormat.setOpaque(true);
        invalidFormat.setBackground(lightYellow);
        invalidFormat.setVisible(false);

        invalidQuestionType.setOpaque(true);
        invalidQuestionType.setBackground(lightYellow);
        invalidQuestionType.setVisible(false);

        // Create formatting questions labels
        JLabel qFormat = new JLabel("Question Format");
        JLabel titleLabel = new JLabel("Title");
        JLabel numQuestions = new JLabel("Number of Questions");
        JLabel numRows = new JLabel("Number of Rows");
        JLabel numColumns = new JLabel("Number of Columns");

        // Update location of the combobox for the question format
        questionFormat.setBounds(convert(0.535, 'w'), convert(0.48, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));
        questionFormat.setSelectedIndex(0);

        // Update the location of each text field
        updateTextFieldLocation(titleInput, 0.525, 0.55, 0.175, 0.05);
        updateTextFieldLocation(numQuestionsInput, 0.525, 0.625, 0.175, 0.05);
        updateTextFieldLocation(numRowsInput, 0.525, 0.7, 0.175, 0.05);
        updateTextFieldLocation(numColumnInput, 0.525, 0.775, 0.175, 0.05);

        // Update the labels for formatting
        updateLabel(qFormat, 0.25, 0.45, 0.6, 0.1, 0.02, 'd');
        updateLabel(titleLabel, 0.25, 0.525, 0.6, 0.1, 0.02, 'd');
        updateLabel(numQuestions, 0.25, 0.6, 0.6, 0.1, 0.02, 'd');
        updateLabel(numRows, 0.25, 0.675, 0.6, 0.1, 0.02, 'd');
        updateLabel(numColumns, 0.25, 0.75, 0.6, 0.1, 0.02, 'd');
        updateLabel(invalidFormat, 0.35, 0.831, 0.3,0.03,0.0125, 'w');
        updateLabel(invalidQuestionType, 0.35, 0.3775, 0.3,0.03,0.0125, 'w');

        updateButtonLocation(topicNextButton, 0.4, 0.87, 0.2, 0.09);
        updateButtonLocation(topicScreenBackButton, 0.145, 0.85, 0.15, 0.05);

        // Update the settings of each button
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

        // Create comboBox for number types (for now, just integers is available)
        numOptions.setBounds(convert(0.525, 'w'), convert(0.325, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));

        topicChose.setBounds(convert(0.525, 'w'), convert(0.25, 'h'), convert(0.15, 'w'),
                convert(0.05, 'h'));

        updateTextFields(textFields);

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

        changePanel(topicPanel);
    }

    private void checkValidDetails(){
        String type = (String) numOptions.getSelectedItem();
        if (Objects.equals(type, "Fractions")) {
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
        }
        else if (Objects.equals(type, "Integers")) {
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
            }
        }

        boolean passed = true;

        // Create temporary equation details and format details variables
        int numOfEquationsTemp, numOfRowsTemp, numOfColumnsTemp;
        numOfEquationsTemp = numOfRowsTemp = numOfColumnsTemp= -1;

        equationFormat = Objects.requireNonNull(questionFormat.getSelectedItem()).toString();
        worksheetTitle = titleInput.getText();

        // Check if any formatting text fields are empty
        if (tryToParse(numQuestionsInput.getText().trim()) == null || tryToParse(numRowsInput.getText().trim()) == null ||
                tryToParse(numColumnInput.getText().trim()) == null || worksheetTitle.trim().length() == 0) {
            invalidFormat.setVisible(true);
            passed = false;
        }
        else {
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
        }
        else {
            invalidFormat.setVisible(true);
            passed = false;
        }

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
        }
        else if (e.getSource() == topicScreenBackButton) {
            new OptionScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            highlightButton(topicNextButton, 'b');
        }
        else if (e.getSource() == topicScreenBackButton) {
            highlightButton(topicScreenBackButton, 'd');
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == topicNextButton) {
            defaultButton(topicNextButton, 'b');
        }
        else if (e.getSource() == topicScreenBackButton) {
            defaultButton(topicScreenBackButton, 'd');
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            checkValidDetails();
        }
    }

}
