package user_interface;

import exceptions.UserDoesNotExistException;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Login Screen class for the User Interface. The login screen that prompts the user for their unique username.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class LoginScreen extends Screen implements MouseListener, KeyListener {

    // Create Buttons
    JButton loginButton = new JButton("Login");
    JButton createUserButton = new JButton("Create User");
    JButton exitButton = new JButton("Exit");

    // Invalid username JLabel
    JLabel invalidUsernameError = new JLabel("Invalid Username", SwingConstants.CENTER);

    // Create text fields
    JTextField usernameInput = new JTextField(1);

    public LoginScreen() {

        loginPanel.setLayout(null);
        loginPanel.setBorder(loginPanelBorder);

        // Create Title labels and its shadow
        JLabel matrixTitle = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
        JLabel matrixTitleShadow = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
        JLabel username = new JLabel("Username", SwingConstants.CENTER);

        // Update the location of each button
        updateButtonLocation(loginButton, 0.4125, 0.62, 0.175, 0.08);
        updateButtonLocation(createUserButton, 0.4125, 0.72, 0.175, 0.08);
        updateButtonLocation(exitButton, 0.025, 0.845, 0.125, 0.06);

        // Update the settings of each button
        defaultButton(loginButton, 'b');
        defaultButton(createUserButton, 'd');
        defaultButton(exitButton, 'd');
        exitButton.setBackground(red);

        // Add Mouse Listener for hover and clicking features
        loginButton.addMouseListener(this);
        createUserButton.addMouseListener(this);
        exitButton.addMouseListener(this);

        // Update the settings of each label
        updateLabel(matrixTitle, 0.15, 0.14, 0.7, 0.1, 0.03, 'd');
        updateLabel(matrixTitleShadow, 0.1525, 0.1425, 0.7, 0.1, 0.03, 'w');
        updateLabel(username, 0.25, 0.475, 0.3, 0.1,0.025, 'd');
        updateLabel(invalidUsernameError, 0.4125,0.559,0.175,0.045,0.014, 'w');

        // Create the settings of the invalid label and initially set it to not visible
        invalidUsernameError.setOpaque(true);
        invalidUsernameError.setBackground(lightYellow);
        invalidUsernameError.setVisible(false);

        // Set the location of the text field
        updateTextFieldLocation(usernameInput, 0.5, 0.5, 0.175, 0.05);
        usernameInput.setBorder(textFieldBorder);
        usernameInput.setOpaque(true);
        usernameInput.setBackground(lightGray);

        // Add Key Listener for the username TextField
        usernameInput.addKeyListener(this);

        // Add each component to the panel
        loginPanel.add(matrixTitle);
        loginPanel.add(matrixTitleShadow);
        loginPanel.add(loginButton);
        loginPanel.add(createUserButton);
        loginPanel.add(exitButton);
        loginPanel.add(username);
        loginPanel.add(usernameInput);
        loginPanel.add(invalidUsernameError);

        // Change the panel to the login screen
        changePanel(loginPanel);
    }

    /**
     * Attempt to log in the user with the username inputted. If the username is not
     * registered, the invalid input error will appear.
     *
     */
    private void userLogin() {
        try {
            username = usernameInput.getText();
            userController.login(username);
            new OptionScreen();
        } catch (UserDoesNotExistException u) {
            invalidUsernameError.setVisible(true);  // Display an error message of the username doesn't exist
        }
    }

    /**
     * Mouse clicked features when each button is clicked
     *
     * @param e keeps track of which button was clicked
     */
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == loginButton) {
            userLogin();
        }
        // Move to create new user screen
        else if (e.getSource() == createUserButton) {
            new NewUserScreen();
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    /**
     * Mouse entered feature when each button is being hovered over
     *
     * @param e keeps track of which button was being hovered over
     */
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == loginButton) {
            highlightButton(loginButton, 'b');
        }
        else if (e.getSource() == createUserButton) {
            highlightButton(createUserButton, 'd');
        }
        else if (e.getSource() == exitButton) {
            highlightButton(exitButton, 'd');
            exitButton.setBackground(red);
        }
    }

    /**
     * Mouse exited feature when each button is not being hovered over
     *
     * @param e keeps track of which button stopped being overed over
     */
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginButton) {
            defaultButton(loginButton, 'b');
        }
        else if (e.getSource() == createUserButton) {
            defaultButton(createUserButton, 'd');
        }
        else if (e.getSource() == exitButton) {
            defaultButton(exitButton, 'd');
            exitButton.setBackground(red);
        }
    }

    /**
     * key Pressed feature when each key is being pressed
     *
     * @param e keeps track of which key is being pressed
     */
    public void keyPressed(KeyEvent e){
        // Attempt to log in the user with the username entered
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            userLogin();
        }
    }
}
