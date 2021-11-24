package user_interface;

import exceptions.UserDoesNotExistException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Login Screen class for the User Interface. The login screen that prompts the user for their unique username.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class LoginScreen extends Screen implements MouseListener {

    // Create Buttons
    JButton loginButton = new JButton("Login");
    JButton createUserButton = new JButton("Create User");

    // Invalid username JLabel
    JLabel invalidUsernameError = new JLabel("Invalid Username", SwingConstants.CENTER);

    // Create text fields
    JTextField username_tf = new JTextField(1);

    public LoginScreen() {

        // Start showing the start screen (login) panel
        cardLayout.show(cardPanel, "StartScreen");

        // Create Title labels and its shadow
        JLabel matrixTitle = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
        JLabel matrixTitleShadow = new JLabel("Matrix - A Worksheet Generator", SwingConstants.CENTER);
        JLabel username = new JLabel("Username", SwingConstants.CENTER);

        // Update the location and settings of each Button
        updateButtonLocation(loginButton, 0.4125, 0.66, 0.175, 0.08);
        updateButtonLocation(createUserButton, 0.425, 0.77, 0.15, 0.075);
        defaultButton(loginButton);
        defaultButton(createUserButton);
        loginButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
        createUserButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.015)));

        // Add Mouse Listener for hover and clicking features
        loginButton.addMouseListener(this);
        createUserButton.addMouseListener(this);

        // Update the settings of each Label
        updateLabel(matrixTitle, 0.2, 0.2, 0.6, 0.1, 0.03075, 'r');
        updateLabel(matrixTitleShadow, 0.2025, 0.2025, 0.6, 0.1, 0.03075, 'd');
        updateLabel(username, 0.25, 0.525, 0.3, 0.1,0.025, 'd');
        updateLabel(invalidUsernameError, 0.4,0.525,0.2,0.2,0.015, 'r');

        // Initially set the invalid username error to not visible
        invalidUsernameError.setVisible(false);

        // Set the location of the text field
        username_tf.setBounds(convert(0.5, 'w'), convert(0.55, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Add each component to the panel
        startPanel.add(matrixTitle);
        startPanel.add(matrixTitleShadow);
        startPanel.add(loginButton);
        startPanel.add(createUserButton);
        startPanel.add(username);
        startPanel.add(username_tf);
        startPanel.add(invalidUsernameError);
    }

    /**
     * Mouse clicked features when each button is clicked
     *
     * @param e keeps track of which button was clicked
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == loginButton) {

            // Attempt to log in the user with the username entered
            try {
                userController.login(username_tf.getText());
                startPanel.setVisible(false);
                frame.setVisible(false);
                new OptionScreen();
            } catch (UserDoesNotExistException u) {
                invalidUsernameError.setVisible(true);  // Display an error message of the username doesn't exist
            }
        }
        // Move to create new user screen
        else if (e.getSource() == createUserButton) {
            startPanel.setVisible(false);
            frame.setVisible(false);
            new NewUserScreen();
        }
    }

    /**
     * Mouse entered feature when each button is being hovered over
     *
     * @param e keeps track of which button was being hovered over
     */
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == loginButton) {
            highlightButton(loginButton);
            loginButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.025)));
        }
        else if (e.getSource() == createUserButton) {
            highlightButton(createUserButton);
            createUserButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0175)));
        }
    }

    /**
     * Mouse exited feature when each button is not being hovered over
     *
     * @param e keeps track of which button stopped being overed over
     */
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginButton) {
            defaultButton(loginButton);
            loginButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.0225)));
        }
        else if (e.getSource() == createUserButton) {
            defaultButton(createUserButton);
            createUserButton.setFont(new Font("Copperplate", Font.BOLD, (int) Math.round((width * 0.5 + height) * 0.015)));
        }
    }
}
