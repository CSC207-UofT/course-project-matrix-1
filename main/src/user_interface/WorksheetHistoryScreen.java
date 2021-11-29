package user_interface;

import equation_parameters.EquationDetails;
import equation_parameters.FormatDetails;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

/**
 * Worksheet History Screen class for the User Interface. The worksheet history screen displays the details of previous
 * worksheets as well as performing features such as worksheet removal, worksheet scoring, and regeneration of
 * worksheets.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-14
 */
public class WorksheetHistoryScreen extends Screen implements MouseListener, KeyListener {

    // Create JLabels
    JLabel noWorksheets = new JLabel("No Worksheets Available", SwingConstants.CENTER);
    JLabel invalidScore = new JLabel("Invalid Score", SwingConstants.CENTER);

    // Create JButtons
    JButton customizeBackButton = new JButton("Back");
    JButton removeButton = new JButton("Remove");
    JButton updateScoreButton = new JButton("Update Score");
    JButton regenerateButton = new JButton("Regenerate");

    // Create JTextField for New Score Option
    JTextField scoreInput = new JTextField(1);

    // Create the Temporary Maps that will be passed into Worksheet Viewer Screen
    Map<String, Object> worksheetHistoryDetailsTemp = new HashMap<>();

    // Create List containing a Map that will take output from getUserHistory method in userController
    List<Map<String, Object>> userHistoryList;

    // Create JTable and Arrays affiliated with it (rows and columns)
    String[] columnNames = {"Title","Date Created","Topic","Number of Equations","Score"};
    String[][] data = {};
    JTable table;

    // Create JScrollPane that holds the JTable
    JScrollPane scrollPane = new JScrollPane(table);

    // Store the date and time when user regenerates a worksheet
    String dateAndTimeTemp;


    public WorksheetHistoryScreen() {

        updatePanel(historyPanel);

        // Set noWorksheets and invalidScore JLabel messages to not visible
        noWorksheets.setVisible(false);

        // ArrayList that will add each worksheet's data
        List<String[]> twoDimArrayList = new ArrayList<>();

        // Store necessary info for each element in the JTable
        try {
            userHistoryList = userController.getUserHistory();
            // Run through each Worksheet
            for (Map <String, Object> map : userHistoryList) {

                // Store FormatDetails and EquationDetails for each Worksheet
                FormatDetails tempMapFormatDetails = (FormatDetails) map.get("formatDetails");
                EquationDetails tempMapEquationDetails = (EquationDetails) map.get("equationDetails");

                // ArrayList that stores data for the current worksheet
                ArrayList<String> currentWorksheetArray = new ArrayList<>();

                // Add title to ArrayList
                currentWorksheetArray.add(tempMapFormatDetails.getTitle());

                // Add date of creation to ArrayList
                String tempName = (String) map.get("worksheetKey");
                tempName = tempName.substring(0, tempName.indexOf("T"));
                currentWorksheetArray.add(tempName);

                // Add topic to ArrayList
                String tempOperator = tempMapEquationDetails.getOperator();
                currentWorksheetArray.add(getOperator(tempOperator));

                // Add number of equations to ArrayList
                currentWorksheetArray.add(String.valueOf(tempMapEquationDetails.getNumOfEquations()));

                // Add score to ArrayList or 0 if no score
                if (userHistoryList.get(twoDimArrayList.size()).get("score") != null){
                    currentWorksheetArray.add(String.valueOf(userHistoryList.get(twoDimArrayList.size()).get("score")));
                }else {
                    currentWorksheetArray.add("0");
                }

                // Convert ArrayList to Array
                String[] tempArray = new String[0];
                tempArray = currentWorksheetArray.toArray(tempArray);

                // Add Array to Two Dimensional ArrayList
                twoDimArrayList.add(tempArray);
            }
            // Make noWorksheets JLabel message visible if there are no worksheets
        } catch (NullPointerException u) {noWorksheets.setVisible(true);}

        // Convert Two Dimensional ArrayList to Two Dimensional Array
        data = twoDimArrayList.toArray(data);

        // Create JTable including info
        table = new JTable(data, columnNames);

        // Make JTable not editable
        table.setDefaultEditor(Object.class, null);

        // Create JLabel and JButton for the Worksheet History Screen
        JLabel previewTitle = new JLabel("Worksheet History", SwingConstants.CENTER);
        JLabel previewTitleShadow = new JLabel("Worksheet History", SwingConstants.CENTER);

        // Update the Settings of the JLabels
        updateLabel(previewTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(previewTitleShadow, 0.2025, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(noWorksheets, 0.2, 0.15, 0.3, 0.05, 0.015, 'r');
        updateLabel(invalidScore, 0.625, 0.67, 0.15, 0.05, 0.013, 'w');
        invalidScore.setOpaque(true);
        invalidScore.setBackground(lightYellow);
        invalidScore.setVisible(false);

        // Update the Location and Settings of each Button
        updateButtonLocation(customizeBackButton, 0.145, 0.825, 0.15, 0.05);
        updateButtonLocation(removeButton, 0.2, 0.725, 0.15, 0.05);
        updateButtonLocation(updateScoreButton, 0.6, 0.79, 0.2, 0.05);
        updateButtonLocation(regenerateButton, 0.375, 0.725, 0.2, 0.05);
        defaultButton(customizeBackButton, 'd');
        defaultButton(removeButton, 'd');
        defaultButton(updateScoreButton, 'b');
        defaultButton(regenerateButton, 'd');

        // Update the Location of each Text Field
        updateTextFieldLocation(scoreInput, 0.6, 0.725, 0.2, 0.05);

        // Add Mouse Listener to each Button
        customizeBackButton.addMouseListener(this);
        removeButton.addMouseListener(this);
        updateScoreButton.addMouseListener(this);
        regenerateButton.addMouseListener(this);

        // Add Key Listener for the newScore JTextField
        scoreInput.addKeyListener(this);

        scrollPane.setBounds(convert(0.2, 'w'), convert(0.26,'h'), convert(0.6, 'w'),
                convert(0.4, 'h'));
        scrollPane.setViewportView(table);

        // Add Components to the Panel
        historyPanel.add(previewTitle);
        historyPanel.add(previewTitleShadow);
        historyPanel.add(noWorksheets);
        historyPanel.add(invalidScore);
        historyPanel.add(customizeBackButton);
        historyPanel.add(removeButton);
        historyPanel.add(updateScoreButton);
        historyPanel.add(regenerateButton);
        historyPanel.add(scrollPane);
        historyPanel.add(scoreInput);

        changePanel(historyPanel);
    }

    /**
     * Add the operator (in word form) to the String that displays the worksheet information.
     * Precondition: - operator inputted must be either +,-,*, or /
     *
     * @param operatorInputted the operator inputted as a character
     */
    public String getOperator(String operatorInputted) {
        if (Objects.equals(operatorInputted, "+")) {
            return "Addition";
        }
        else if (Objects.equals(operatorInputted, "-")) {
            return "Subtraction";
        }
        else if (Objects.equals(operatorInputted, "*")) {
            return "Multiplication";
        }
        else if (Objects.equals(operatorInputted, "/")) {
            return "Division";
        }
        return null;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            new OptionScreen();
        }
        if (e.getSource() == removeButton) {
            // Get selected worksheet index
            int index = table.getSelectedRow();
            // If worksheet is selected remove it from the List and History Memory
            if (index != -1) {
                String removeWorksheetStr = String.valueOf(userHistoryList.get(index).get("worksheetKey"));
                userController.removeUserRecord(removeWorksheetStr);
                userHistoryList.remove(index);
                new WorksheetHistoryScreen();
            }
        }

        if (e.getSource() == updateScoreButton) {
            // Check if JText is empty and return message if true
            updateScore();
        }

        if (e.getSource() == regenerateButton) {
            // Check if Worksheet is Selected
            int index = table.getSelectedRow();
            if (index != -1) {

                // Store Necessary info to Regenerate Worksheet
                EquationDetails equationDetailsTemp = (EquationDetails) userHistoryList.get(index).get("equationDetails");
                FormatDetails formatDetailsTemp = (FormatDetails) userHistoryList.get(index).get("formatDetails");

                // Store the date and time the user regenerated the worksheet
                dateAndTimeTemp = LocalDateTime.now().toString();

                worksheetHistoryDetailsTemp.put("worksheetKey", dateAndTimeTemp);
                worksheetHistoryDetailsTemp.put("equationDetails", equationDetailsTemp);
                worksheetHistoryDetailsTemp.put("formatDetails", formatDetailsTemp);

                try {
                    new WorksheetViewerScreen(worksheetHistoryDetailsTemp);
                } catch (IOException ex) {
                    invalidScore.setText("Worksheet cannot be regenerated");
                    invalidScore.setVisible(true);
                }
            }
        }
    }

    private void updateScore() {
        if (tryToParse(scoreInput.getText()) == null) {
            invalidScore.setVisible(true);
        } else {
            int index = table.getSelectedRow();
            if (index != -1) {
                int score = Integer.parseInt(scoreInput.getText());
                EquationDetails tempMapEquationDetails = (EquationDetails) userHistoryList.get(index).get("equationDetails");
                int maxScore = tempMapEquationDetails.getNumOfEquations();
                // Check if score is illegal option and return message if true
                if (score < 0 || score > maxScore) {
                    invalidScore.setVisible(true);

                } else {
                    // Store Score of Worksheet
                    String tempKey = (String) userHistoryList.get(index).get("worksheetKey");
                    userController.storeUserScore(tempKey, score);
                    invalidScore.setVisible(false);
                    userHistoryList = userController.getUserHistory();
                    new WorksheetHistoryScreen();
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            highlightButton(customizeBackButton, 'd');
        }
        else if (e.getSource() == removeButton) {
            highlightButton(removeButton, 'd');
        }
        else if (e.getSource() == updateScoreButton) {
            highlightButton(updateScoreButton, 'b');
        }
        else if (e.getSource() == regenerateButton) {
            highlightButton(regenerateButton, 'd');
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            defaultButton(customizeBackButton, 'd');
        }
        else if (e.getSource() == removeButton) {
            defaultButton(removeButton, 'd');
        }
        else if (e.getSource() == updateScoreButton) {
            defaultButton(updateScoreButton, 'b');
        }
        else if (e.getSource() == regenerateButton) {
            defaultButton(regenerateButton, 'd');
        }
    }

    /**
     * key Pressed feature when each key is being pressed
     *
     * @param e keeps track of which key is being pressed
     */
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            updateScore();
        }
    }
}