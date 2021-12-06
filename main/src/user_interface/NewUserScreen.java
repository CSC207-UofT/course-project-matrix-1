package user_interface;

import exceptions.UsernameTakenException;

import javax.swing.*;
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
    final JButton createUserButton = new JButton("Create User");
    final JButton newUserBackButton = new JButton("Back");

    // Create Invalid Input JLabel
    final JLabel newUserInvalidInput = new JLabel("", SwingConstants.CENTER);

    // Create text fields
    final JTextField newUsernameInput = new JTextField(1);
    final JTextField nameInput = new JTextField(1);
    final JTextField ageInput = new JTextField(1);
    final JTextField[] newUserTextFields = {newUsernameInput, nameInput, ageInput};

    // Create combo box for user role
    final String[] roleOptions = {"Student", "Teacher"};
    final JComboBox<String> role = new JComboBox<>(roleOptions);

    public NewUserScreen() {

        // Update the panel to the default settings
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

        // Initially set the invalid input to not visible
        newUserInvalidInput.setOpaque(true);
        newUserInvalidInput.setBackground(lightYellow);
        newUserInvalidInput.setVisible(false);

        // Update the location of the combobox
        role.setBounds(convert(0.5, 'w'), convert(0.655, 'h'), convert(0.175, 'w'),
                convert(0.1, 'h'));
        role.setSelectedIndex(0);

        // Update the location of the text fields
        updateTextFieldLocation(newUsernameInput, 0.5, 0.3, 0.175, 0.05);
        updateTextFieldLocation(nameInput, 0.5, 0.425, 0.175, 0.05);
        updateTextFieldLocation(ageInput, 0.5, 0.55, 0.175, 0.05);

        updateTextFields(newUserTextFields);

        // Update the location of each button
        updateButtonLocation(createUserButton, 0.4, 0.8, 0.2, 0.1);
        updateButtonLocation(newUserBackButton, 0.145, 0.8, 0.125, 0.05);
        defaultButton(createUserButton, 'b');
        defaultButton(newUserBackButton, 'd');

        // Add MouseListener for the hover and clicking features
        createUserButton.addMouseListener(this);
        newUserBackButton.addMouseListener(this);

        // Add KeyListener for each text field
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

        // Change the panel to the new user screen panel
        changePanel(newUserPanel);
    }

    /**
     * Attempt to create the new user. If the user information is not valid,
     * invalid input(s) or taken username will appear.
     */
    private void createUser() {

        // Initialize the current username and name to be checked for valid inputs
        String currUsername = newUsernameInput.getText();
        String currName = nameInput.getText();

        // Check if any input is empty or cannot be parsed
        if (tryToParse(ageInput.getText()) == null || currName.length() == 0 || currUsername.length() == 0) {
            newUserInvalidInput.setText("Invalid Input(s)");
            newUserInvalidInput.setVisible(true);              // Set invalid input to visible
        } else {
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

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == createUserButton) {
            createUser();
        } else if (e.getSource() == newUserBackButton) {
            new LoginScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createUserButton) {
            highlightButton(createUserButton, 'b');
        } else if (e.getSource() == newUserBackButton) {
            highlightButton(newUserBackButton, 'd');
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createUserButton) {
            defaultButton(createUserButton, 'b');
        } else if (e.getSource() == newUserBackButton) {
            defaultButton(newUserBackButton, 'd');
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            createUser();
        }
    }
}
