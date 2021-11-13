package user_interface;

import exceptions.UsernameTakenException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * New User class for the User Interface. User can create a new unique user with username, name, age, and role
 * (Invalid inputs and taken username exceptions are handled)
 *
 * @author Ethan Ing, Piotr pralat
 * @since 2021-11-01
 */
public class NewUserScreen extends StartScreen implements MouseListener {

    // Create buttons
    JButton createUserButton = new JButton("Create User");
    JButton newUserBackButton = new JButton("Back");

    // Create the title
    JLabel newUserTitle = new JLabel("Create New User", SwingConstants.CENTER);
    JLabel newUserTitleShadow = new JLabel("Create New user", SwingConstants.CENTER);

    // Create Invalid Input JLabel
    JLabel invalidInput = new JLabel("Invalid Input(s)", SwingConstants.CENTER);

    // Create text fields
    JTextField username_tf = new JTextField(1);
    JTextField name_tf = new JTextField(1);
    JTextField age_tf = new JTextField(1);

    // Create combo box for user role
    String[] roleOptions = {"Student", "Teacher"};
    JComboBox<String> role = new JComboBox<>(roleOptions);

    public NewUserScreen() {

        // Set the Panel to the new user screen
        cardLayout.show(cardPanel, "NewUserScreen");

        // Create labels
        JLabel usernameLbl = new JLabel("Username");
        JLabel nameLbl = new JLabel("Name");
        JLabel ageLbl = new JLabel("Age");
        JLabel roleLBL = new JLabel("Role");

        // Update the labels
        updateLabel(newUserTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'r');
        updateLabel(newUserTitleShadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(usernameLbl, 0.325, 0.15, 0.6, 0.1, 0.025, 'd');
        updateLabel(nameLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageLbl, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleLBL, 0.325, 0.525, 0.6, 0.1, 0.025, 'd');
        updateLabel(invalidInput, 0.4, 0.74, 0.2, 0.05, 0.015, 'r');

        // Initially set the invalid input to not visible
        invalidInput.setVisible(false);

        // Update the location of the combobox
        role.setBounds(convert(0.5, 'w'), convert(0.53, 'h'), convert(0.175, 'w'),
                convert(0.1, 'h'));
        role.setSelectedIndex(0);

        // Update the location of the text fields
        username_tf.setBounds(convert(0.5, 'w'), convert(0.175, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        name_tf.setBounds(convert(0.5, 'w'), convert(0.3, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        age_tf.setBounds(convert(0.5, 'w'), convert(0.425, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Update the location of each button
        updateButtonLocation(createUserButton, 0.4, 0.8, 0.2, 0.1);
        updateButtonLocation(newUserBackButton, 0.145, 0.8, 0.125, 0.05);
        defaultButton(createUserButton);
        defaultButton(newUserBackButton);

        // Add MouseListener for the hover and clicking features
        createUserButton.addMouseListener(this);
        newUserBackButton.addMouseListener(this);

        // Add all components to the panel
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
        newUserPanel.add(invalidInput);
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == createUserButton) {

            String currUsername = username_tf.getText();
            String currName = name_tf.getText();

            // Check if any input is empty or cannot be parsed
            if (tryToParse(age_tf.getText()) == null || currName.length() == 0 || currUsername.length() == 0) {
                invalidInput.setText("Invalid Input(s)");
                invalidInput.setVisible(true);
            }
            else {
                int currAge = Integer.parseInt(age_tf.getText());
                String currRole = (String) role.getSelectedItem();

                // Attempt to register the user
                try {
                    uc.registerUser(currUsername, currName, currAge, currRole);
                    frame.setVisible(false);
                    newUserPanel.setVisible(false);
                    new OptionScreen();
                } catch (UsernameTakenException u) {
                    invalidInput.setText("Invalid username");   // Show invalid username label if the username is taken
                    invalidInput.setVisible(true);
                }
            }
        }
        if (e.getSource() == newUserBackButton){
            frame.setVisible(false);
            newUserPanel.setVisible(false);
            new StartScreen();
        }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createUserButton) {
            highlightButton(createUserButton);
        }
        if (e.getSource() == newUserBackButton) {
            highlightButton(newUserBackButton);
        }
    }
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createUserButton) {
            defaultButton(createUserButton);
        }
        if (e.getSource() == newUserBackButton) {
            defaultButton(newUserBackButton);
        }
    }
}
