package user_interface;

import equation_parameters.EquationDetails;
import equation_parameters.FormatDetails;
import equation_parameters.WholeNumEquationDetails;

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

    JTextField op1MIN = new JTextField(1);
    JTextField op1MAX = new JTextField(1);
    JTextField op2MIN = new JTextField(1);
    JTextField op2MAX = new JTextField(1);

    JTextField[] textFields = {op1MIN, op1MAX, op2MIN, op2MAX};

    // Create checkbox
    JCheckBox negAllowedBox = new JCheckBox("");

    // Create the equation details variables with initial invalid values
    int [] operandRange1 = {-1, -1};
    int [] operandRange2 = {-1, -1};
    boolean negAllowed = false;

    // Create the formatting details variables with initial invalid values
    String dateAndTime;

    // Create the temporary map's to be passed into worksheet viewer screen
    Map <String, Object> worksheetHistoryDetails = new HashMap<>();

    EquationDetails equationDetails;
    FormatDetails formatDetails;

    public CustomizeScreen(EquationDetails equationDetails, FormatDetails formatDetails) {

        updatePanel(customizePanel);

        // Gets the chosen operator/equationDetail type from the previous screen
        this.equationDetails = equationDetails;
        this.formatDetails = formatDetails;

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
        operatorWarning.setBackground(new Color(217, 207, 131, 252));
        operatorWarning.setVisible(false);

        // Minimum and maximum text fields
        op1MIN.setBounds(convert(0.525, 'w'), convert(0.265, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        op1MAX.setBounds(convert(0.675, 'w'), convert(0.265, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        op2MIN.setBounds(convert(0.525, 'w'), convert(0.36, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        op2MAX.setBounds(convert(0.675, 'w'), convert(0.36, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

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
            new TopicScreen();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            generateWorksheet();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
