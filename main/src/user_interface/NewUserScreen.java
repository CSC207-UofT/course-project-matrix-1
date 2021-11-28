package user_interface;

import exceptions.UsernameTakenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * New User class for the User Interface. User can create a new unique user with username, name, age, and role
 * (Invalid inputs and taken username exceptions are handled)
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class NewUserScreen extends Screen implements MouseListener, KeyListener {

    // Create buttons
    JButton createUserButton = new JButton("Create User");
    JButton newUserBackButton = new JButton("Back");

    // Create Invalid Input JLabel
    JLabel newUserInvalidInput = new JLabel("Invalid Input(s)", SwingConstants.CENTER);

    // Create text fields
    JTextField newUsernameInput = new JTextField(1);
    JTextField nameInput = new JTextField(1);
    JTextField ageInput = new JTextField(1);

    // Create combo box for user role
    String[] roleOptions = {"Student", "Teacher"};
    JComboBox<String> role = new JComboBox<>(roleOptions);

    public NewUserScreen() {

        updatePanel(newUserPanel);

        // Create the title
        JLabel newUserTitle = new JLabel("Create New User", SwingConstants.CENTER);
        JLabel newUserTitleShadow = new JLabel("Create New user", SwingConstants.CENTER);

        // Create labels
        JLabel usernameLbl = new JLabel("Username");
        JLabel nameLbl = new JLabel("Name");
        JLabel ageLbl = new JLabel("Age");
        JLabel roleLBL = new JLabel("Role");

        // Update the labels
        updateLabel(newUserTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(newUserTitleShadow, 0.2, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(usernameLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(nameLbl, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageLbl, 0.325, 0.525, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleLBL, 0.325, 0.65, 0.6, 0.1, 0.025, 'd');
        updateLabel(newUserInvalidInput, 0.425, 0.74, 0.15, 0.05, 0.014, 'w');

        newUserInvalidInput.setOpaque(true);
        newUserInvalidInput.setBackground(new Color(217, 207, 131, 252));

        // Initially set the invalid input to not visible
        newUserInvalidInput.setVisible(false);

        // Update the location of the combobox
        role.setBounds(convert(0.5, 'w'), convert(0.655, 'h'), convert(0.175, 'w'),
                convert(0.1, 'h'));
        role.setSelectedIndex(0);

        // Update the location of the text fields
        newUsernameInput.setBounds(convert(0.5, 'w'), convert(0.3, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        nameInput.setBounds(convert(0.5, 'w'), convert(0.425, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        ageInput.setBounds(convert(0.5, 'w'), convert(0.55, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Update the location of each button
        updateButtonLocation(createUserButton, 0.4, 0.8, 0.2, 0.1);
        updateButtonLocation(newUserBackButton, 0.145, 0.8, 0.125, 0.05);
        defaultButton(createUserButton, 'b');
        defaultButton(newUserBackButton, 'd');

        // Add MouseListener for the hover and clicking features
        createUserButton.addMouseListener(this);
        newUserBackButton.addMouseListener(this);

        // Add Key Listener for the JTextFields
        newUsernameInput.addKeyListener(this);
        nameInput.addKeyListener(this);
        ageInput.addKeyListener(this);

        // Add all components to the panel
        newUserPanel.add(newUserTitle);
        newUserPanel.add(newUserTitleShadow);
        newUserPanel.add(newUsernameInput);
        newUserPanel.add(nameInput);
        newUserPanel.add(ageInput);
        newUserPanel.add(role);
        newUserPanel.add(usernameLbl);
        newUserPanel.add(nameLbl);
        newUserPanel.add(ageLbl);
        newUserPanel.add(roleLBL);
        newUserPanel.add(createUserButton);
        newUserPanel.add(newUserBackButton);
        newUserPanel.add(newUserInvalidInput);

        changePanel(newUserPanel);
    }

    private void createUser(){
        String currUsername = newUsernameInput.getText();
        String currName = nameInput.getText();

        // Check if any input is empty or cannot be parsed
        if (tryToParse(ageInput.getText()) == null || currName.length() == 0 || currUsername.length() == 0) {
            newUserInvalidInput.setText("Invalid Input(s)");
            newUserInvalidInput.setVisible(true);              // Set invalid input to visible
        }
        else {
            int currAge = Integer.parseInt(ageInput.getText());   // Parse the age, which is a valid integer
            String currRole = (String) role.getSelectedItem();  // Get the selected role of the user

            // Attempt to register the user
            try {
                userController.registerUser(currUsername, currName, currAge, currRole);
                username = currUsername;
                new OptionScreen();                         // Successful registration
            } catch (UsernameTakenException u) {
                newUserInvalidInput.setText("Username taken");
                newUserInvalidInput.setVisible(true);              // Show invalid username label if the username is taken
            }
        }
    }
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == createUserButton) {
            createUser();
        }
        else if (e.getSource() == newUserBackButton){
            new LoginScreen();
        }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createUserButton) {
            highlightButton(createUserButton, 'b');
        }
        else if (e.getSource() == newUserBackButton) {
            highlightButton(newUserBackButton, 'd');
        }
    }
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createUserButton) {
            defaultButton(createUserButton, 'b');
        }
        else if (e.getSource() == newUserBackButton) {
            defaultButton(newUserBackButton, 'd');
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            createUser();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
