package user_interface;

import equation_parameters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
    JLabel operatorWarning = new JLabel("Operand's' minimum must be lower than the maximum", SwingConstants.CENTER);

    // Create text fields
    JTextField op1MIN;
    JTextField op1MAX;
    JTextField op2MIN;
    JTextField op2MAX;
    JTextField denMIN;
    JTextField denMAX;
    JTextField ansMIN;
    JTextField ansMAX;
    JTextField valMIN;
    JTextField valMAX;
    JTextField complex;


    JTextField[] textFields;

    // Create checkbox
    JCheckBox negAllowedBox;

    // Create the equation details variables with initial invalid values
    int [] operandRange1 = {-1, -1};
    int [] operandRange2 = {-1, -1};
    boolean negAllowed = false;

    // Create the formatting details variables with initial invalid values
    String dateAndTime;

    // Create the temporary map's to be passed into worksheet viewer screen
    EquationDetails equationDetails;
    FormatDetails formatDetails;

    Map <String, Object> worksheetHistoryDetails;

    public CustomizeScreen(EquationDetails equationDetails, FormatDetails formatDetails) {
        updatePanel(customizePanel);

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
        else if (equationDetails instanceof FractionAddSubEquationDetails) {
            // Initialize text fields
            denMIN = new JTextField(1);
            denMAX = new JTextField(1);
            ansMIN = new JTextField(1);
            ansMAX = new JTextField(1);
            valMIN = new JTextField(1);
            valMAX = new JTextField(1);
            textFields = new JTextField[]{denMIN, denMAX, ansMIN, ansMAX, valMIN, valMAX};

            // Initial check boxes
            negAllowedBox = new JCheckBox("");
            fillFractionAddSubScreen();
        }
        else if (equationDetails instanceof FractionMultiDivEquationDetails){
            // Initialize text fields
            denMIN = new JTextField(1);
            denMAX = new JTextField(1);
            complex = new JTextField(1);
            ansMIN = new JTextField(1);
            ansMAX = new JTextField(1);
            textFields = new JTextField[]{denMIN, denMAX, complex, ansMIN, ansMAX};

            // Initial check boxes
            negAllowedBox = new JCheckBox("");
            fillFractionMultiDivScreenScreen();
        }

        }
    /**
     * Overloaded constructor method. Used to persist previous user input.
     *
     * @param worksheetDetails contains worksheet details that user inputted in this screen previously
     */
    public CustomizeScreen(Map <String, Object> worksheetDetails) {
        updatePanel(customizePanel);

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
        else if (equationDetails instanceof FractionAddSubEquationDetails) {
            // TODO not finished
            textFields = new JTextField[]{denMIN, denMAX, ansMIN, ansMAX, valMIN, valMAX};
            negAllowedBox = new JCheckBox("", equationDetails.isNegAllowed());
            fillFractionAddSubScreen();
        }
        else if (equationDetails instanceof FractionMultiDivEquationDetails){
            // TODO not finished
            textFields = new JTextField[]{denMIN, denMAX, complex, ansMIN, ansMAX};
            negAllowedBox = new JCheckBox("", equationDetails.isNegAllowed());
            fillFractionMultiDivScreenScreen();
        }
    }

    /**
     * Adds all necessary parts of CustomizeScreen.
     */
    private void fillScreen() {
        // Create Equation Details and Formatting JLabels and its shadow
        JLabel equationDetailsTitle = new JLabel("Equation Details", SwingConstants.CENTER);
        JLabel equationDetailsShadow = new JLabel("Equation Details", SwingConstants.CENTER);

        // Create equation questions labels
        JLabel op1Range = new JLabel("Operand 1 Range");
        JLabel dash = new JLabel("-");
        JLabel op2Range = new JLabel("Operand 2 Range");
        JLabel dash2 = new JLabel("-");
        JLabel negAllowed = new JLabel("Negative are Allowed?");

        // Update the labels for the equation customization
        updateLabel(equationDetailsTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(equationDetailsShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(operatorWarning, 0.3, 0.525, 0.4, 0.07, 0.0125, 'w');
        updateLabel(op1Range, 0.25, 0.24, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash, 0.645, 0.24, 0.05, 0.1, 0.025, 'd');
        updateLabel(op2Range, 0.25, 0.335, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash2, 0.645, 0.335, 0.1, 0.1, 0.025, 'd');
        updateLabel(negAllowed, 0.25, 0.43, 0.6, 0.1, 0.02, 'd');

        operatorWarning.setOpaque(true);
        operatorWarning.setBackground(lightYellow);
        operatorWarning.setVisible(false);

        // Minimum and maximum text fields
        updateTextFieldLocation(op1MIN, 0.525, 0.265, 0.1, 0.05);
        updateTextFieldLocation(op1MAX, 0.675, 0.265, 0.1, 0.05);
        updateTextFieldLocation(op2MIN, 0.525, 0.36, 0.1, 0.05);
        updateTextFieldLocation(op2MAX, 0.675, 0.36, 0.1, 0.05);

        // Update checkbox location
        negAllowedBox.setBounds(convert(0.565, 'w'), convert(0.455, 'h'), convert(0.5, 'w'),
                convert(0.5, 'h'));
        negAllowedBox.setSize(new Dimension(40, 40));

        // Update the location of each button
        updateButtonLocation(generateWorksheetButton, 0.35, 0.8, 0.3, 0.1);
        updateButtonLocation(customizeBackButton, 0.145, 0.8, 0.15, 0.05);

        // Update the settings of each button
        defaultButton(generateWorksheetButton, 'b');
        defaultButton(customizeBackButton, 'd');

        // Add Mouse Listener for hover and clicking features
        generateWorksheetButton.addMouseListener(this);
        customizeBackButton.addMouseListener(this);

        updateTextFields(textFields);

        // Add Key Listener for the JTextFields
        op1MIN.addKeyListener(this);
        op1MAX.addKeyListener(this);
        op2MIN.addKeyListener(this);
        op2MAX.addKeyListener(this);

        // Add all components to the panel
        customizePanel.add(equationDetailsTitle);
        customizePanel.add(equationDetailsShadow);

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
        customizePanel.add(operatorWarning);

        changePanel(customizePanel);
    }

    public void fillFractionAddSubScreen(){
        // Create Equation Details and Formatting JLabels and its shadow
        JLabel equationDetailsTitle = new JLabel("Equation Details", SwingConstants.CENTER);
        JLabel equationDetailsShadow = new JLabel("Equation Details", SwingConstants.CENTER);

        // Create equation questions labels
        JLabel denRange = new JLabel("Operand 1's Denominator Range");
        JLabel dash = new JLabel("-");
        JLabel maxAns = new JLabel("Max Answer Denominator");
        JLabel dash2 = new JLabel("-");
        JLabel maxVal = new JLabel("Max Operand Value");
        JLabel dash3 = new JLabel("-");
        JLabel negAllowed = new JLabel("Negative are Allowed?");

        // Update the labels for the equation customization
        updateLabel(equationDetailsTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(equationDetailsShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(operatorWarning, 0.3, 0.725, 0.4, 0.07, 0.0125, 'w');
        updateLabel(denRange, 0.25, 0.24, 0.6, 0.1, 0.013, 'd');
        updateLabel(dash, 0.645, 0.24, 0.05, 0.1, 0.025, 'd');
        updateLabel(maxAns, 0.25, 0.335, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash2, 0.645, 0.335, 0.1, 0.1, 0.025, 'd');
        updateLabel(maxVal, 0.25, 0.43, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash3, 0.645, 0.43, 0.1, 0.1, 0.025, 'd');
        updateLabel(negAllowed, 0.25, 0.63, 0.6, 0.1, 0.02, 'd');

        // TODO add proper warnings
        operatorWarning.setOpaque(true);
        operatorWarning.setBackground(lightYellow);
        operatorWarning.setVisible(false);

        // Minimum and maximum text fields
        updateTextFieldLocation(denMIN, 0.525, 0.265, 0.1, 0.05);
        updateTextFieldLocation(denMAX, 0.675, 0.265, 0.1, 0.05);
        updateTextFieldLocation(ansMIN, 0.525, 0.36, 0.1, 0.05);
        updateTextFieldLocation(ansMAX, 0.675, 0.36, 0.1, 0.05);
        updateTextFieldLocation(valMIN, 0.525, 0.455, 0.1, 0.05);
        updateTextFieldLocation(valMAX, 0.675, 0.455, 0.1, 0.05);

        // Update checkbox location
        negAllowedBox.setBounds(convert(0.565, 'w'), convert(0.655, 'h'), convert(0.5, 'w'),
                convert(0.5, 'h'));
        negAllowedBox.setSize(new Dimension(40, 40));

        // Update the location of each button
        updateButtonLocation(generateWorksheetButton, 0.35, 0.8, 0.3, 0.1);
        updateButtonLocation(customizeBackButton, 0.145, 0.8, 0.15, 0.05);

        // Update the settings of each button
        defaultButton(generateWorksheetButton, 'b');
        defaultButton(customizeBackButton, 'd');

        // Add Mouse Listener for hover and clicking features
        generateWorksheetButton.addMouseListener(this);
        customizeBackButton.addMouseListener(this);

        updateTextFields(textFields);

        // Add Key Listener for the JTextFields
        denMIN.addKeyListener(this);
        denMAX.addKeyListener(this);
        ansMIN.addKeyListener(this);
        ansMAX.addKeyListener(this);
        valMIN.addKeyListener(this);
        valMAX.addKeyListener(this);

        // Add all components to the panel
        customizePanel.add(equationDetailsTitle);
        customizePanel.add(equationDetailsShadow);

        customizePanel.add(generateWorksheetButton);
        customizePanel.add(customizeBackButton);

        customizePanel.add(denRange);
        customizePanel.add(denMIN);
        customizePanel.add(dash);
        customizePanel.add(denMAX);
        customizePanel.add(maxAns);
        customizePanel.add(ansMIN);
        customizePanel.add(dash2);
        customizePanel.add(ansMAX);
        customizePanel.add(maxVal);
        customizePanel.add(valMIN);
        customizePanel.add(dash3);
        customizePanel.add(valMAX);
        customizePanel.add(negAllowed);
        customizePanel.add(negAllowedBox);
        customizePanel.add(operatorWarning);

        changePanel(customizePanel);
    }

    public void fillFractionMultiDivScreenScreen(){
        // Create Equation Details and Formatting JLabels and its shadow
        JLabel equationDetailsTitle = new JLabel("Equation Details", SwingConstants.CENTER);
        JLabel equationDetailsShadow = new JLabel("Equation Details", SwingConstants.CENTER);

        // Create equation questions labels
        JLabel denRange = new JLabel("Answer Denominator Range");
        JLabel dash = new JLabel("-");
        JLabel maxRange = new JLabel("Max Answer Value");
        JLabel dash2 = new JLabel("-");
        JLabel complexity = new JLabel("Complexity");
        JLabel negAllowed = new JLabel("Negative are Allowed?");

        // Update the labels for the equation customization
        updateLabel(equationDetailsTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(equationDetailsShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(operatorWarning, 0.3, 0.725, 0.4, 0.07, 0.0125, 'w');
        updateLabel(denRange, 0.25, 0.24, 0.6, 0.1, 0.015, 'd');
        updateLabel(dash, 0.645, 0.24, 0.05, 0.1, 0.025, 'd');
        updateLabel(maxRange, 0.25, 0.335, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash2, 0.645, 0.335, 0.1, 0.1, 0.025, 'd');
        updateLabel(complexity, 0.25, 0.43, 0.6, 0.1, 0.02, 'd');
        updateLabel(negAllowed, 0.25, 0.63, 0.6, 0.1, 0.02, 'd');

        // TODO add proper warnings
        operatorWarning.setOpaque(true);
        operatorWarning.setBackground(lightYellow);
        operatorWarning.setVisible(false);

        // Minimum and maximum text fields
        updateTextFieldLocation(denMIN, 0.525, 0.265, 0.1, 0.05);
        updateTextFieldLocation(denMAX, 0.675, 0.265, 0.1, 0.05);
        updateTextFieldLocation(ansMIN, 0.525, 0.36, 0.1, 0.05);
        updateTextFieldLocation(ansMAX, 0.675, 0.36, 0.1, 0.05);
        updateTextFieldLocation(complex, 0.525, 0.455, 0.2, 0.05);

        // Update checkbox location
        negAllowedBox.setBounds(convert(0.565, 'w'), convert(0.655, 'h'), convert(0.5, 'w'),
                convert(0.5, 'h'));
        negAllowedBox.setSize(new Dimension(40, 40));

        // Update the location of each button
        updateButtonLocation(generateWorksheetButton, 0.35, 0.8, 0.3, 0.1);
        updateButtonLocation(customizeBackButton, 0.145, 0.8, 0.15, 0.05);

        // Update the settings of each button
        defaultButton(generateWorksheetButton, 'b');
        defaultButton(customizeBackButton, 'd');

        // Add Mouse Listener for hover and clicking features
        generateWorksheetButton.addMouseListener(this);
        customizeBackButton.addMouseListener(this);

        updateTextFields(textFields);

        // Add Key Listener for the JTextFields
        denMIN.addKeyListener(this);
        denMAX.addKeyListener(this);
        ansMIN.addKeyListener(this);
        ansMAX.addKeyListener(this);
        complex.addKeyListener(this);

        // Add all components to the panel
        customizePanel.add(equationDetailsTitle);
        customizePanel.add(equationDetailsShadow);

        customizePanel.add(generateWorksheetButton);
        customizePanel.add(customizeBackButton);

        customizePanel.add(denRange);
        customizePanel.add(denMIN);
        customizePanel.add(dash);
        customizePanel.add(denMAX);
        customizePanel.add(maxRange);
        customizePanel.add(ansMIN);
        customizePanel.add(dash2);
        customizePanel.add(ansMAX);
        customizePanel.add(complexity);
        customizePanel.add(complex);
        customizePanel.add(negAllowed);
        customizePanel.add(negAllowedBox);
        customizePanel.add(operatorWarning);

        changePanel(customizePanel);
    }
    private void generateWorksheet(){
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
        }
        else {
            // Each operand value can be parsed (is an integer)
            op1MinTemp = Integer.parseInt(op1MIN.getText().trim());
            op1MaxTemp = Integer.parseInt(op1MAX.getText().trim());
            op2MinTemp = Integer.parseInt(op2MIN.getText().trim());
            op2MaxTemp = Integer.parseInt(op2MAX.getText().trim());
        }

        // Check to see if all operand range are greater than zero and max > min
        if (op1MinTemp >= 0 && op1MaxTemp >= 0 && op2MinTemp >= 0 && op2MaxTemp >= 0
                && op1MaxTemp >= op1MinTemp && op2MaxTemp >= op2MinTemp) {
            operandRange1 = new int[]{op1MinTemp, op1MaxTemp};      // Set the operand 1 range to inputted values
            operandRange2 = new int[]{op2MinTemp, op2MaxTemp};      // Set the operand 2 range to inputted values
        }
        else if (op1MaxTemp < op1MinTemp || op2MaxTemp < op2MinTemp){
            operatorWarning.setText("Operand's' minimum must be lower than the maximum");
            operatorWarning.setVisible(true);
            passed = false;
        }
        else {
            operatorWarning.setText("Operand's must be positive numbers");
            operatorWarning.setVisible(true);
        }

        // Get selection for checkbox, question format, and title
        negAllowed = negAllowedBox.isSelected();

        // If all inputs check out, add to the equation details and formatting details maps
        if (passed) {

            this.equationDetails.setNegAllowed(negAllowed);

            //TODO: need different casting for different equation details, make if statements
            ((WholeNumEquationDetails) this.equationDetails).setOperandRange1(operandRange1);
            ((WholeNumEquationDetails) this.equationDetails).setOperandRange2(operandRange2);

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
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            generateWorksheet();
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
            generateWorksheet();
        }
    }
}
