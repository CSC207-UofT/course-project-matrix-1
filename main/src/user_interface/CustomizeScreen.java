package user_interface;

import equation_parameters.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class CustomizeScreen extends Screen implements MouseListener, KeyListener {

    // Create buttons
    JButton generateWorksheetButton = new JButton("Generate Worksheet");
    JButton customizeBackButton = new JButton("Back");

    // Invalid input JLabel
    JLabel operatorWarning = new JLabel("Operand's minimum must be lower than the maximum", SwingConstants.CENTER);

    // Create text fields for the Integer worksheet
    JTextField op1MIN;
    JTextField op1MAX;
    JTextField op2MIN;
    JTextField op2MAX;

    // Create text fields for the fraction worksheets
    JTextField denMIN;
    JTextField denMAX;
    JTextField secondFieldInput;
    JTextField maxVal;

    String[] formatOptions = {"Mixed", "Improper"};
    JComboBox<String> formatComboBox = new JComboBox<>(formatOptions);

    JTextField[] textFields;

    // Create checkbox
    JCheckBox negAllowedBox;

    // Create the equation details variables with initial invalid values for fraction worksheet
    int [] operandRange1 = {-1, -1};
    int [] operandRange2 = {-1, -1};
    boolean negAllowed = false;

    // Create the equation details with initial invalid values for fraction worksheet
    int [] denRange = {-1, -1};
    int secondField = -1;
    int maxNumVal = -1;
    boolean fractionNegAllowed = false;
    String fractionFormat = "";

    // Create the formatting details variables with initial invalid values
    String dateAndTime;

    // Create the equation and format details
    EquationDetails equationDetails;
    FormatDetails formatDetails;

    // Create the temporary map's to be passed into worksheet viewer screen
    Map <String, Object> worksheetHistoryDetails;

    public CustomizeScreen(EquationDetails equationDetails, FormatDetails formatDetails) {
        updatePanel(customizePanel);

        // Add common panel features
        addPanelFeatures();

        // Gets the chosen operator/equationDetail type from the previous screen
        this.equationDetails = equationDetails;
        this.formatDetails = formatDetails;
        this.worksheetHistoryDetails = new HashMap<>();
        worksheetHistoryDetails.put("equationDetails", this.equationDetails);
        worksheetHistoryDetails.put("formatDetails", this.formatDetails);

        if (equationDetails instanceof WholeNumEquationDetails) {
            // Initialize text fields
            op1MIN = new JTextField(1);
            op1MAX = new JTextField(1);
            op2MIN = new JTextField(1);
            op2MAX = new JTextField(1);
            textFields = new JTextField[]{op1MIN, op1MAX, op2MIN, op2MAX};

            // Initial check boxes
            negAllowedBox = new JCheckBox("");
            fillScreen();
        }
        else if (equationDetails instanceof FractionAddSubEquationDetails ||
                equationDetails instanceof FractionMultiDivEquationDetails) {
            // Initialize text fields
            denMIN = new JTextField(1);
            denMAX = new JTextField(1);
            secondFieldInput = new JTextField(1);
            maxVal = new JTextField(1);
            textFields = new JTextField[]{denMIN, denMAX, secondFieldInput, maxVal};

            // Initial check boxes
            negAllowedBox = new JCheckBox("");
            formatComboBox.setSelectedIndex(0);
            formatComboBox.setVisible(true);
            fillFractionScreen();
        }
        updateTextFields(textFields);
    }
    /**
     * Overloaded constructor method. Used to persist previous user input.
     *
     * @param worksheetDetails contains worksheet details that user inputted in this screen previously
     */
    public CustomizeScreen(Map <String, Object> worksheetDetails) {
        updatePanel(customizePanel);

        // Add common panel features
        addPanelFeatures();

        // Gets the chosen operator/equationDetail type from the previous screen
        this.equationDetails = (EquationDetails) worksheetDetails.get("equationDetails");
        this.formatDetails = (FormatDetails) worksheetDetails.get("formatDetails");
        this.worksheetHistoryDetails = worksheetDetails;

        // Initialize equation details with previous input
        if (equationDetails instanceof WholeNumEquationDetails) {
            operandRange1 = ((WholeNumEquationDetails) equationDetails).getOperandRange1();
            operandRange2 = ((WholeNumEquationDetails) equationDetails).getOperandRange2();

            op1MIN = new JTextField(Integer.toString(operandRange1[0]), 1);
            op1MAX = new JTextField(Integer.toString(operandRange1[1]),1);
            op2MIN = new JTextField(Integer.toString(operandRange2[0]),1);
            op2MAX = new JTextField(Integer.toString(operandRange2[1]),1);
            textFields = new JTextField[]{op1MIN, op1MAX, op2MIN, op2MAX};
            negAllowedBox = new JCheckBox("", equationDetails.isNegAllowed());
            fillScreen();
        }
        else {
            if (equationDetails instanceof FractionAddSubEquationDetails) {
                denRange = ((FractionAddSubEquationDetails) equationDetails).getOperand1DenomRange();
                secondField = ((FractionAddSubEquationDetails) equationDetails).getMaxOperand2AndAnswerDenom();
                maxNumVal = ((FractionAddSubEquationDetails) equationDetails).getMaxOperandValue();
            } else if (equationDetails instanceof FractionMultiDivEquationDetails) {
                denRange = ((FractionMultiDivEquationDetails) equationDetails).getAnsDenominatorRange();
                secondField = ((FractionMultiDivEquationDetails) equationDetails).getComplexity();
                maxNumVal = ((FractionMultiDivEquationDetails) equationDetails).getMaxAnsValue();
            }

            textFields = new JTextField[]{denMIN, denMAX, secondFieldInput, maxVal};
            negAllowedBox = new JCheckBox("", equationDetails.isNegAllowed());

            fillFractionScreen();
            formatComboBox.setVisible(true);
        }
        updateTextFields(textFields);
    }

    /**
     * Adds all necessary parts of CustomizeScreen.
     */
    private void fillScreen() {
        // Create equation questions labels
        JLabel op1Range = new JLabel("Operand 1 Range");
        JLabel dash = new JLabel("-");
        JLabel op2Range = new JLabel("Operand 2 Range");
        JLabel dash2 = new JLabel("-");
        JLabel negAllowed = new JLabel("Negative are Allowed?");

        // Update the labels for the equation customization
        updateLabel(op1Range, 0.25, 0.24, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash, 0.645, 0.24, 0.05, 0.1, 0.025, 'd');
        updateLabel(op2Range, 0.25, 0.335, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash2, 0.645, 0.335, 0.1, 0.1, 0.025, 'd');
        updateLabel(negAllowed, 0.25, 0.43, 0.6, 0.1, 0.02, 'd');

        // Minimum and maximum text fields
        updateTextFieldLocation(op1MIN, 0.525, 0.265, 0.1, 0.05);
        updateTextFieldLocation(op1MAX, 0.675, 0.265, 0.1, 0.05);
        updateTextFieldLocation(op2MIN, 0.525, 0.36, 0.1, 0.05);
        updateTextFieldLocation(op2MAX, 0.675, 0.36, 0.1, 0.05);

        // Update checkbox location
        negAllowedBox.setBounds(convert(0.565, 'w'), convert(0.455, 'h'), convert(0.05, 'w'),
                convert(0.05, 'h'));

        // Add Key Listener for the JTextFields
        op1MIN.addKeyListener(this);
        op1MAX.addKeyListener(this);
        op2MIN.addKeyListener(this);
        op2MAX.addKeyListener(this);

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
    }

    private void fillFractionScreen(){
        // Create labels
        JLabel denRange = new JLabel();
        JLabel maxAns = new JLabel();
        JLabel maxValLbl = new JLabel();
        JLabel dash = new JLabel("-");
        JLabel negAllowed = new JLabel("Negative are Allowed?");
        JLabel format = new JLabel("Format");

        if (equationDetails instanceof FractionAddSubEquationDetails) {
            denRange.setText("Operand 1's Denominator Range");
            maxAns.setText("Max Answer Denominator");
            maxValLbl.setText("Max Operand Value");
        }
        else if (equationDetails instanceof FractionMultiDivEquationDetails) {
            denRange.setText("Answer's Denominator Range");
            maxAns.setText("Complexity");
            maxValLbl.setText("Max Answer Value");
            JLabel complexityRecommendation = new JLabel("(Recommended values: 1-3)");
            updateLabel(complexityRecommendation, 0.645, 0.335, 0.3, 0.1, 0.0125, 'd');
            customizePanel.add(complexityRecommendation);
        }
        // Update the labels
        updateLabel(denRange, 0.225, 0.24, 0.6, 0.1, 0.015, 'd');
        updateLabel(dash, 0.645, 0.24, 0.05, 0.1, 0.025, 'd');
        updateLabel(maxAns, 0.225, 0.335, 0.6, 0.1, 0.015, 'd');
        updateLabel(maxValLbl, 0.225, 0.43, 0.6, 0.1, 0.015, 'd');
        updateLabel(negAllowed, 0.225, 0.525, 0.6, 0.1, 0.015, 'd');
        updateLabel(format, 0.225, 0.62, 0.6, 0.1,0.015, 'd');

        // Minimum and maximum text fields
        updateTextFieldLocation(denMIN, 0.53, 0.265, 0.1, 0.05);
        updateTextFieldLocation(denMAX, 0.68, 0.265, 0.1, 0.05);
        updateTextFieldLocation(secondFieldInput, 0.53, 0.36, 0.1, 0.05);
        updateTextFieldLocation(maxVal, 0.53, 0.455, 0.1, 0.05);

        // Update checkbox location
        negAllowedBox.setBounds(convert(0.565, 'w'), convert(0.56, 'h'), convert(0.05, 'w'),
                convert(0.05, 'h'));

        // Add Key Listener for the JTextFields
        denMIN.addKeyListener(this);
        denMAX.addKeyListener(this);
        secondFieldInput.addKeyListener(this);
        maxVal.addKeyListener(this);

        customizePanel.add(denRange);
        customizePanel.add(denMIN);
        customizePanel.add(dash);
        customizePanel.add(denMAX);
        customizePanel.add(maxAns);
        customizePanel.add(secondFieldInput);
        customizePanel.add(maxValLbl);
        customizePanel.add(maxVal);
        customizePanel.add(negAllowed);
        customizePanel.add(negAllowedBox);
        customizePanel.add(format);
    }

    private void generateIntegerWorksheet() {

        boolean passed = true;

            // Create temporary equation details and format details variables
            int op1MinTemp, op1MaxTemp, op2MinTemp, op2MaxTemp;
            op1MinTemp = op1MaxTemp = op2MinTemp = op2MaxTemp = -1;

            // Check if any operand range cannot be parsed (invalid input)
            if (tryToParse(op1MIN.getText().trim()) == null || tryToParse(op1MAX.getText().trim()) == null ||
                    tryToParse(op2MIN.getText().trim()) == null || tryToParse(op2MAX.getText().trim()) == null) {
                operatorWarning.setText("Operand's must be positive numbers");
                operatorWarning.setVisible(true);
                passed = false;
            } else {
                // Each operand value can be parsed (is an integer)
                op1MinTemp = Integer.parseInt(op1MIN.getText().trim());
                op1MaxTemp = Integer.parseInt(op1MAX.getText().trim());
                op2MinTemp = Integer.parseInt(op2MIN.getText().trim());
                op2MaxTemp = Integer.parseInt(op2MAX.getText().trim());
            }
            if (Objects.equals(equationDetails.getOperator(), "/")) {
                boolean checkNotDiv = true;
                boolean noZeroOrNeg = true;
                for (int i = op1MinTemp; i <= op1MaxTemp; i++) {
                    for (int j = op2MinTemp; j <= op2MaxTemp; j++) {
                        if (j <= 0) {
                            noZeroOrNeg = false;
                            break;
                        }
                        if (i % j == 0) {
                            checkNotDiv = false;
                            break;
                        }
                    }
                }
                if (!noZeroOrNeg) {
                    operatorWarning.setText("Operand 2 cannot include 0 or negatives");
                    operatorWarning.setVisible(true);
                    passed = false;
                } else if (checkNotDiv) {
                    operatorWarning.setText("Operand 2 range must have a number divisible by a number in Operand 1 range");
                    operatorWarning.setVisible(true);
                    passed = false;
                }
            }

            if (Objects.equals(equationDetails.getOperator(), "-")) {
                boolean allNeg = false;
                if (!negAllowed) {
                    allNeg = true;
                    for (int i = op1MinTemp; i <= op1MaxTemp; i++) {
                        for (int j = op2MinTemp; j <= op2MaxTemp; j++) {
                            if ((i - j) >= 0) {
                                allNeg = false;
                                break;
                            }
                        }
                    }
                }
                if (allNeg) {
                    operatorWarning.setText("Operand 2 must have a number less than a number in Operand 1");
                    operatorWarning.setVisible(true);
                    passed = false;
                }
            }

            // Get selection for checkbox, question format, and title
            negAllowed = negAllowedBox.isSelected();

            // Check to see if all operand range are greater than zero and max > min
            if (op1MinTemp >= 0 && op1MaxTemp >= 0 && op2MinTemp >= 0 && op2MaxTemp >= 0
                    && op1MaxTemp >= op1MinTemp && op2MaxTemp >= op2MinTemp) {
                operandRange1 = new int[]{op1MinTemp, op1MaxTemp};      // Set the operand 1 range to inputted values
                operandRange2 = new int[]{op2MinTemp, op2MaxTemp};      // Set the operand 2 range to inputted values
            } else if (op1MaxTemp < op1MinTemp || op2MaxTemp < op2MinTemp) {
                operatorWarning.setText("Operand's' minimum must be lower than the maximum");
                operatorWarning.setVisible(true);
                passed = false;
            } else {
                operatorWarning.setText("Operand's must be positive numbers");
                operatorWarning.setVisible(true);
                passed = false;
            }

        // If all inputs check out, add to the equation details and formatting details maps
        if (passed) {
            this.equationDetails.setNegAllowed(negAllowed);
            ((WholeNumEquationDetails) this.equationDetails).setOperandRange1(operandRange1);
            ((WholeNumEquationDetails) this.equationDetails).setOperandRange2(operandRange2);
            createWorksheetDetails();
        }

    }

    private void generateFractionWorksheet() {

        boolean passed = true;

        // Create temporary equation details and format details variables
        int denMINTemp, denMAXTemp, secondFieldInputTemp, maxValTemp;
       // denMINTemp = denMAXTemp = secondFieldInputTemp = maxValTemp = -1;

        // Check if any operand range cannot be parsed (invalid input)
        if (tryToParse(denMIN.getText().trim()) == null || tryToParse(denMAX.getText().trim()) == null ||
                tryToParse(secondFieldInput.getText().trim()) == null || tryToParse(maxVal.getText().trim()) == null) {
            operatorWarning.setText("Please enter positive numbers (greater than 0)");
            operatorWarning.setVisible(true);
            passed = false;
        }

        else {
            // Each operand value can be parsed (is an integer)
            denMINTemp = Integer.parseInt(denMIN.getText().trim());
            denMAXTemp = Integer.parseInt(denMAX.getText().trim());
            secondFieldInputTemp = Integer.parseInt(secondFieldInput.getText().trim());
            maxValTemp = Integer.parseInt(maxVal.getText().trim());

            // Check to see if all operand range are greater than zero and max > min
            if (denMINTemp > 0 && denMAXTemp > 0 && maxValTemp > 0 && denMAXTemp >= denMINTemp &&
                    (secondFieldInputTemp > 0)) {
                denRange = new int[]{denMINTemp, denMAXTemp};      // Set the denominator range to inputted values
                secondField = secondFieldInputTemp;
                maxNumVal = maxValTemp;
            } else if (denMAXTemp < denMINTemp) {
                operatorWarning.setText("Denominator's minimum must be lower than the maximum");
                operatorWarning.setVisible(true);
                passed = false;
            } else if (equationDetails instanceof FractionAddSubEquationDetails && maxValTemp <= denMAXTemp) {
                operatorWarning.setText("Max answer must be greater than operand 1 denominator range");
                operatorWarning.setVisible(true);
                passed = false;
            } else {
                operatorWarning.setText("Please enter positive numbers (greater than 0)");
                operatorWarning.setVisible(true);
                passed = false;
            }
        }

        // Get selection for checkbox, question format, and title
        fractionNegAllowed = negAllowedBox.isSelected();
        fractionFormat = (String) formatComboBox.getSelectedItem();

        // If all inputs check out, add to the equation details and formatting details maps
        if (passed) {

            this.equationDetails.setNegAllowed(fractionNegAllowed);

            if (equationDetails instanceof FractionAddSubEquationDetails) {
                ((FractionAddSubEquationDetails)this.equationDetails).setOperand1DenomRange(denRange);
                ((FractionAddSubEquationDetails)this.equationDetails).setMaxOperand2AndAnswerDenom(secondField);
                ((FractionAddSubEquationDetails)this.equationDetails).setMaxOperandValue(maxNumVal);
            }
            else if (equationDetails instanceof FractionMultiDivEquationDetails) {
                ((FractionMultiDivEquationDetails) this.equationDetails).setAnsDenominatorRange(denRange);
                ((FractionMultiDivEquationDetails) this.equationDetails).setComplexity(secondField);
                ((FractionMultiDivEquationDetails) this.equationDetails).setMaxAnsValue(maxNumVal);
            }
            createWorksheetDetails();
        }
    }

    private void addPanelFeatures() {

        // Create Equation Details and Formatting JLabels and its shadow
        JLabel equationDetailsTitle = new JLabel("Equation Details", SwingConstants.CENTER);
        JLabel equationDetailsShadow = new JLabel("Equation Details", SwingConstants.CENTER);

        // Update the labels for the equation customization
        updateLabel(equationDetailsTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(equationDetailsShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(operatorWarning, 0.25, 0.72, 0.5, 0.07, 0.01, 'w');

        operatorWarning.setOpaque(true);
        operatorWarning.setBackground(lightYellow);
        operatorWarning.setVisible(false);

        // Update the location and settings of each button
        updateButtonLocation(generateWorksheetButton, 0.35, 0.8, 0.3, 0.1);
        updateButtonLocation(customizeBackButton, 0.145, 0.8, 0.15, 0.05);
        defaultButton(generateWorksheetButton, 'b');
        defaultButton(customizeBackButton, 'd');

        // Add Mouse Listener for hover and clicking features
        generateWorksheetButton.addMouseListener(this);
        customizeBackButton.addMouseListener(this);

        formatComboBox.setBounds(convert(0.53, 'w'), convert(0.64, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        formatComboBox.setVisible(false);

        // Add all components to the panel
        customizePanel.add(equationDetailsTitle);
        customizePanel.add(equationDetailsShadow);
        customizePanel.add(generateWorksheetButton);
        customizePanel.add(customizeBackButton);
        customizePanel.add(formatComboBox);
        customizePanel.add(operatorWarning);

        changePanel(customizePanel);
    }

    private void createWorksheetDetails() {
        // Find the exact date/time the user created the worksheet
        dateAndTime = LocalDateTime.now().toString();

        // Create the unique worksheet history details
        worksheetHistoryDetails.put("worksheetKey", dateAndTime);
        worksheetHistoryDetails.put("equationDetails", this.equationDetails);
        worksheetHistoryDetails.put("formatDetails", this.formatDetails);

        try {
            new WorksheetViewerScreen(worksheetHistoryDetails);
        } catch (IOException ex) {
            operatorWarning.setVisible(true);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {

            if (equationDetails instanceof WholeNumEquationDetails) {
                generateIntegerWorksheet();
            }
            else {
                generateFractionWorksheet();
            }
        }
        else if (e.getSource() == customizeBackButton) {
            new TopicScreen(worksheetHistoryDetails);
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            highlightButton(generateWorksheetButton, 'b');
        }
        else if (e.getSource() == customizeBackButton) {
            highlightButton(customizeBackButton, 'd');
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            defaultButton(generateWorksheetButton, 'b');
        }
        else if (e.getSource() == customizeBackButton) {
            defaultButton(customizeBackButton, 'd');
        }
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            if (equationDetails instanceof WholeNumEquationDetails) {
                generateIntegerWorksheet();
            }
            else {
                generateFractionWorksheet();
            }
        }
    }
}