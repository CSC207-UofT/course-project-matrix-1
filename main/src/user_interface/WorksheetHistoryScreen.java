package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Worksheet History Screen class for the User Interface. The worksheet history screen displays the details of previous
 * worksheets as well as performing features such as worksheet removal, worksheet scoring, and regeneration of
 * worksheets.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-14
 */
public class WorksheetHistoryScreen extends Screen implements MouseListener {

    // Create JLabels
    JLabel noWorksheets = new JLabel("No Worksheets Available", SwingConstants.CENTER);
    JLabel invalidScore = new JLabel("Invalid Score", SwingConstants.CENTER);

    // Create buttons
    JButton customizeBackButton = new JButton("Back");
    JButton removeButton = new JButton("Remove");
    JButton updateScoreButton = new JButton("Update Score");
    JButton regenerateButton = new JButton("Regenerate");

    // Create JTextField for New Score Option
    JTextField newScore = new JTextField(1);

    // Create JList that holds Strings that will represent the Worksheet History
    JList <String> history;

    // Create DefaultListModel that holds Strings that will be inputted into JList
    DefaultListModel<String> listModel = new DefaultListModel<>();

    // Create String Builder that will hold our Display of the Worksheet in the JList
    StringBuilder totalString = new StringBuilder();

    // Create the Temporary Maps that will be passed into Worksheet Viewer Screen
    Map<String, Object> equation_details_temp = new HashMap<>();
    Map<String, Object> format_details_temp = new HashMap<>();
    Map<String, Object> worksheet_history_details_temp = new HashMap<>();

    // Create List containing a Map that will take output from getUserHistory method in userController
    List<Map<String, Object>> userHistoryList;

    // Create JScrollPane that will hold userHistoryList later
    JScrollPane scrollPane = new JScrollPane();

    // Store the date and time when user regenerates a worksheet
    String dateAndTimeTemp;


    @SuppressWarnings("unchecked")
    public WorksheetHistoryScreen() {

        // Set noWorksheets and invalidScore JLabel messages to not visible
        noWorksheets.setVisible(false);
        invalidScore.setVisible(false);

        // Store necessary info for each element in JList String Builder
        try {
            userHistoryList = userController.getUserHistory();
            // Run through each Worksheet
            for (Map <String, Object> map : userHistoryList) {

                // Crete temporary maps for the format, and equation details
                Map <String, Object> tempMapFormatDetails = (Map<String, Object>) map.get("formatDetails");
                Map <String, Object>  tempMapEquationDetails = (Map <String, Object>) map.get("equationDetails");

                // Clear String Builder
                totalString.setLength(0);

                // Add title to String Builder
                totalString.append("Title: ");
                totalString.append(tempMapFormatDetails.get("title"));
                // Add date to String Builder
                totalString.append(" | Date Created: ");
                String tempName = (String) map.get("worksheetKey");
                totalString.append(tempName, 0, tempName.indexOf("T"));
                // Add topic to String Builder

                totalString.append(" | Topic: ");
                char tempOperator = (char) tempMapEquationDetails.get("operator");
                getOperator(tempOperator);
                // Add number of equations to String Builder
                totalString.append(" | Number of Equations: ");
                totalString.append(tempMapEquationDetails.get("numOfEquations"));
                // Add score to String Builder (currently set to zero - implement next phase)
                totalString.append(" | Score: ");
                if (userHistoryList.get(listModel.size()).get("score") != null){
                    totalString.append(userHistoryList.get(listModel.size()).get("score"));
                }else{totalString.append("0");}
                // Add String Builder to DefaultListModel
                listModel.addElement(totalString.toString());
            }
            // Make noWorksheets JLabel message visible if there are no worksheets
        } catch (NullPointerException u) {noWorksheets.setVisible(true);}

        // Create JList which holds info from the listModel (DefaultListModel<String>)
        history = new JList<>(listModel);

        // Create JLabel and JButton for the Worksheet History Screen
        JLabel previewTitle = new JLabel("Worksheet History", SwingConstants.CENTER);
        JLabel previewTitleShadow = new JLabel("Worksheet History", SwingConstants.CENTER);

        // Set the Panel to the Option Screen
        cardLayout.show(cardPanel, "WorksheetHistoryScreen");

        historyPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        historyPanel.setLayout(null);

        // Update the Settings of the JLabels
        updateLabel(previewTitle, 0.2, 0.02, 0.6, 0.1, 0.03075, 'r');
        updateLabel(previewTitleShadow, 0.2025, 0.0225, 0.6, 0.1, 0.03075, 'd');
        updateLabel(noWorksheets, 0.2, 0.15, 0.3, 0.05, 0.015, 'r');
        updateLabel(invalidScore, 0.5, 0.15, 0.3, 0.05, 0.015, 'r');

        // Update the Location and Settings of each Button
        updateButtonLocation(customizeBackButton, 0.145, 0.825, 0.15, 0.05);
        updateButtonLocation(removeButton, 0.2, 0.725, 0.15, 0.05);
        updateButtonLocation(updateScoreButton, 0.6, 0.79, 0.2, 0.05);
        updateButtonLocation(regenerateButton, 0.375, 0.725, 0.2, 0.05);
        defaultButton(customizeBackButton);
        defaultButton(removeButton);
        defaultButton(updateScoreButton);
        defaultButton(regenerateButton);

        // Update the Location of each Text Field
        newScore.setBounds(convert(0.6, 'w'), convert(0.725, 'h'), convert(0.2, 'w'),
                convert(0.05, 'h'));

        // Add Mouse Listener to each Button
        customizeBackButton.addMouseListener(this);
        removeButton.addMouseListener(this);
        updateScoreButton.addMouseListener(this);
        regenerateButton.addMouseListener(this);

        // Add JList
        history.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        history.setLayoutOrientation(JList.VERTICAL);
        updateList(scrollPane, 0.2, 0.2, 0.6, 0.5);
        scrollPane.setViewportView(history);

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
        historyPanel.add(newScore);
    }

    /**
     * Add the operator (in word form) to the String that displays the worksheet information.
     * Precondition: - operator inputted must be either +,-,*, or /
     *
     * @param operatorInputted the operator inputted as a character
     */
    public void getOperator(char operatorInputted) {
        if (operatorInputted == '+') {
            totalString.append("Addition");
        }
        else if (operatorInputted == '-') {
            totalString.append("Subtraction");
        }
        else if (operatorInputted == '*') {
            totalString.append("Multiplication");
        }
        else if (operatorInputted == '/') {
            totalString.append("Division");
        }
    }

    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            frame.setVisible(false);
            historyPanel.setVisible(false);
            new OptionScreen();
        }
        if (e.getSource() == removeButton) {
            // Get selected worksheet index
            int index = history.getSelectedIndex();
            // If worksheet is selected remove it from the List and History Memory
            if (index != -1) {
                String removeWorksheetStr = String.valueOf(userHistoryList.get(index).get("worksheetKey"));
                userController.removeUserRecord(removeWorksheetStr);
                userHistoryList.remove(index);
                listModel.removeElementAt(index);
            }
        }

        // Not fully implemented will be complete in next Phase (display change of score in table missing)
        if (e.getSource() == updateScoreButton) {
            // Check if JText is empty and return message if true
            if (tryToParse(newScore.getText()) == null) {
                invalidScore.setVisible(true);
            } else {
                int index = history.getSelectedIndex();
                if (index != -1) {
                    int score = Integer.parseInt(newScore.getText());
                    Map<String, Object> tempMapEquationDetails = (Map<String, Object>) userHistoryList.get(index).get("equationDetails");
                    int maxScore = (int) tempMapEquationDetails.get("numOfEquations");
                    // Check if score is illegal option and return message if true
                    if (score < 0 || score > maxScore) {
                        invalidScore.setVisible(true);

                    } else {
                        // Store Score of Worksheet
                        String tempKey = (String) userHistoryList.get(index).get("worksheetKey");
                        userController.storeUserScore(tempKey, score);
                        invalidScore.setVisible(false);
                        userHistoryList = userController.getUserHistory();
                        frame.setVisible(false);
                        historyPanel.setVisible(false);
                        new WorksheetHistoryScreen();
                    }
                }
            }
        }

        if (e.getSource() == regenerateButton) {
            // Check if Worksheet is Selected
            int index = history.getSelectedIndex();
            if (index != -1) {

                // Store Necessary info to Regenerate Worksheet
                equation_details_temp = (Map<String, Object>) userHistoryList.get(index).get("equationDetails");
                format_details_temp = (Map<String, Object>) userHistoryList.get(index).get("formatDetails");

                // Store the date and time the user regenerated the worksheet
                dateAndTimeTemp = LocalDateTime.now().toString();

                worksheet_history_details_temp.put("worksheetKey", dateAndTimeTemp);
                worksheet_history_details_temp.put("equationDetails", equation_details_temp);
                worksheet_history_details_temp.put("formatDetails", format_details_temp);

                try {
                    frame.setVisible(false);
                    historyPanel.setVisible(false);
                    new WorksheetViewerScreen(equation_details_temp, format_details_temp, worksheet_history_details_temp);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            highlightButton(customizeBackButton);
        }
        else if (e.getSource() == removeButton) {
            highlightButton(removeButton);
        }
        else if (e.getSource() == updateScoreButton) {
            highlightButton(updateScoreButton);
        }
        else if (e.getSource() == regenerateButton) {
            highlightButton(regenerateButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            defaultButton(customizeBackButton);
        }
        else if (e.getSource() == removeButton) {
            defaultButton(removeButton);
        }
        else if (e.getSource() == updateScoreButton) {
            defaultButton(updateScoreButton);
        }
        else if (e.getSource() == regenerateButton) {
            defaultButton(regenerateButton);
        }
    }
}