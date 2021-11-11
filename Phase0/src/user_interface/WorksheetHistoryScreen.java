package user_interface;

import user_package.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class WorksheetHistoryScreen extends StartScreen implements MouseListener {

    // Create JLabel and JButton for the Worksheet History Screen
    JLabel title = new JLabel("Worksheet History", SwingConstants.CENTER);
    JLabel titleShadow = new JLabel("Worksheet History", SwingConstants.CENTER);
    JButton customizeBackButton = new JButton("Back");
    JButton removeButton = new JButton("Remove");
    JButton updateScoreButton = new JButton("Update Score");
    JButton regenerateButton = new JButton("Regenerate");

    ArrayList<String> tempData = new ArrayList<>();

    UserController uc = new UserController();
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList <String> history;
    List<Map<String, Object>> userHistoryMap;
    
    JScrollPane scrollPane = new JScrollPane();

    public WorksheetHistoryScreen() {

        // Temp for Testing
        tempData.add("Worksheet One");
        tempData.add("Worksheet Two");
        tempData.add("Worksheet Three");
        tempData.add("Worksheet Four");

//        userHistoryMap = uc.getUserHistory(usernameInput);
        for (Map<String, Object> map : userHistoryMap) {
            for (String worksheetName : map.keySet()) {
                listModel.addElement(worksheetName);
            }
        }
        history = new JList<>(listModel);

        // Set the Panel to the Option Screen
        cardLayout.show(cardPanel, "WorksheetHistoryScreen");

        historyPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        historyPanel.setLayout(null);

        // Update the Settings of the JLabels
        updateLabel(title, 0.2, 0.02, 0.6, 0.1, 0.03075, 'n');
        updateLabel(titleShadow, 0.2025, 0.025, 0.6, 0.1, 0.03075, 'd');

        // Update the Location and Settings of each Button
        updateButtonLocation(customizeBackButton, 0.145, 0.825, 0.175, 0.05);
        updateButtonLocation(removeButton, 0.2, 0.725, 0.15, 0.05);
        updateButtonLocation(updateScoreButton, 0.375, 0.725, 0.2, 0.05);
        updateButtonLocation(regenerateButton, 0.6, 0.725, 0.2, 0.05);
        defaultButton(customizeBackButton);
        defaultButton(removeButton);
        defaultButton(updateScoreButton);
        defaultButton(regenerateButton);

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
        historyPanel.add(title);
        historyPanel.add(titleShadow);
        historyPanel.add(customizeBackButton);
        historyPanel.add(removeButton);
        historyPanel.add(updateScoreButton);
        historyPanel.add(regenerateButton);
        historyPanel.add(scrollPane);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            frame.setVisible(false);
            historyPanel.setVisible(false);
            try {
                new WSViewerScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == removeButton) {
            int index = history.getSelectedIndex();
            if (index != -1){listModel.removeElementAt(index);}
        }
        if (e.getSource() == updateScoreButton) {System.out.println("update score");}
        if (e.getSource() == regenerateButton) {System.out.println("regenerate");}
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            highlightButton(customizeBackButton);
        }
        if (e.getSource() == removeButton) {
            highlightButton(removeButton);
        }
        if (e.getSource() == updateScoreButton) {
            highlightButton(updateScoreButton);
        }
        if (e.getSource() == regenerateButton) {
            highlightButton(regenerateButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == customizeBackButton) {
            defaultButton(customizeBackButton);
        }
        if (e.getSource() == removeButton) {
            defaultButton(removeButton);
        }
        if (e.getSource() == updateScoreButton) {
            defaultButton(updateScoreButton);
        }
        if (e.getSource() == regenerateButton) {
            defaultButton(regenerateButton);
        }
    }
}
