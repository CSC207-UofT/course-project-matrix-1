import user_package.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewUserScreen extends StartScreen implements MouseListener {

    // Create Equation Details and Formatting JLabels and its shadow
    JLabel newUserTitle = new JLabel("Create New User", SwingConstants.CENTER);
    JLabel newUserTitleShadow = new JLabel("Create New user", SwingConstants.CENTER);

    // Create text fields
    JTextField username_tf = new JTextField(1);
    JTextField name_tf = new JTextField(1);
    JTextField age_tf = new JTextField(1);

    // Create combo box for user role
    String[] roleOptions = {"Student", "Teacher"};
    JComboBox<String> role = new JComboBox<>(roleOptions);

    // Buttons
    JButton createUserButton = new JButton("Create User");
    JButton newUserBackButton = new JButton("Back");

    public NewUserScreen() {
        // Set the Panel to the new user screen
        cardLayout.show(cardPanel, "NewUserScreen");

        newUserPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        newUserPanel.setLayout(null);

        // Create Equation Questions JLabel
        JLabel usernameLbl = new JLabel("Username");
        JLabel nameLbl = new JLabel("Name");
        JLabel ageLbl = new JLabel("Age");
        JLabel roleLBL = new JLabel("Role");

        updateLabel(newUserTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(newUserTitleShadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');

        updateLabel(usernameLbl, 0.325, 0.15, 0.6, 0.1, 0.025, 'd');
        updateLabel(nameLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageLbl, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleLBL, 0.325, 0.525, 0.6, 0.1, 0.025, 'd');

        // Update the location of each button
        updateButtonLocation(createUserButton, 0.4, 0.8, 0.2, 0.1);
        updateButtonLocation(newUserBackButton, 0.145, 0.825, 0.125, 0.05);
        defaultButton(createUserButton);
        defaultButton(newUserBackButton);

        // Location of combobox
        role.setBounds(convert(0.5, 'w'), convert(0.53, 'h'), convert(0.175, 'w'),
                convert(0.1, 'h'));
        role.setSelectedIndex(0);

        // Set the location of the text fields
        username_tf.setBounds(convert(0.5, 'w'), convert(0.175, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        name_tf.setBounds(convert(0.5, 'w'), convert(0.3, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        age_tf.setBounds(convert(0.5, 'w'), convert(0.425, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        createUserButton.addMouseListener(this);
        newUserBackButton.addMouseListener(this);

        newUserPanel.add(newUserTitle);
        newUserPanel.add(newUserTitleShadow);
        newUserPanel.add(username_tf);
        newUserPanel.add(name_tf);
        newUserPanel.add(age_tf);
        newUserPanel.add(role);
        newUserPanel.add(usernameLbl);
        newUserPanel.add(nameLbl);
        newUserPanel.add(ageLbl);
        newUserPanel.add(roleLBL);
        newUserPanel.add(createUserButton);
        newUserPanel.add(newUserBackButton);
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == createUserButton) {

            String currUsername = username_tf.getText();
            String currName = name_tf.getText();

            if (tryToParse(age_tf.getText()) == null || currName.length() == 0 || currUsername.length() == 0) {
                invalidInput.setVisible(true);
            }
            else {
                int currAge = Integer.parseInt(age_tf.getText());
                String currRole = (String) role.getSelectedItem();

            new StartScreen();
            uc.registerUser(currUsername, currName, currAge, currRole);
        }
        if (e.getSource() == newUserBackButton){
            frame.setVisible(false);
            newUserPanel.setVisible(false);
            new StartScreen();
        }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createUserButton)
            highlightButton(createUserButton);
        if (e.getSource() == newUserBackButton)
            highlightButton(newUserBackButton);
    }
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createUserButton)
            defaultButton(createUserButton);
        if (e.getSource() == newUserBackButton)
            defaultButton(newUserBackButton);
    }
}
