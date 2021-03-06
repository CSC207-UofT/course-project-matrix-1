package user_interface;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

/**
 * User Profile class for the User Interface. This screen displays the user's profile (name, age, and role)
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class UserProfileScreen extends Screen implements MouseListener {

    // Create buttons
    final JButton userProfileBackButton = new JButton("Back");
    final JButton deleteUserButton = new JButton("Delete Account");

    // Create a map of the user's details
    final Map<String, Object> userDetails = userPresenter.getUserDetails();

    public UserProfileScreen() {

        // Update the panel to the default settings
        updatePanel(userProfilePanel);

        // Create the title labels
        JLabel userProfileTitle = new JLabel("User Profile", SwingConstants.CENTER);
        JLabel userProfileTitleShadow = new JLabel("User Profile", SwingConstants.CENTER);

        // Create the label's displaying the user's profile information
        JLabel nameProfileLbl = new JLabel("Name:\t" + userDetails.get("name"));
        JLabel ageProfileLbl = new JLabel("Age:\t" + userDetails.get("age"));
        JLabel roleProfileLBL = new JLabel("Role:\t" + userDetails.get("role"));

        // Update the location of the labels
        updateLabel(userProfileTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'b');
        updateLabel(userProfileTitleShadow, 0.2, 0.1625, 0.6, 0.1, 0.03075, 'd');
        updateLabel(nameProfileLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageProfileLbl, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleProfileLBL, 0.325, 0.525, 0.6, 0.1, 0.025, 'd');

        // Update the location of each button
        updateButtonLocation(userProfileBackButton, 0.145, 0.8, 0.125, 0.05);
        updateButtonLocation(deleteUserButton, 0.65, 0.8, 0.225, 0.05);
        defaultButton(userProfileBackButton, 'd');
        defaultButton(deleteUserButton, 'd');

        // Customize the unique settings of the "delete user" button
        deleteUserButton.setOpaque(true);
        deleteUserButton.setBackground(red);

        // Add MouseListener for the hover and clicking features
        userProfileBackButton.addMouseListener(this);
        deleteUserButton.addMouseListener(this);

        // Add each component to the panel
        userProfilePanel.add(userProfileTitle);
        userProfilePanel.add(userProfileTitleShadow);
        userProfilePanel.add(nameProfileLbl);
        userProfilePanel.add(ageProfileLbl);
        userProfilePanel.add(roleProfileLBL);
        userProfilePanel.add(userProfileBackButton);
        userProfilePanel.add(deleteUserButton);

        // Change the panel to the user profile panel
        changePanel(userProfilePanel);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            new OptionScreen();
        } else if (e.getSource() == deleteUserButton) {
            // Create an option pane confirming the user would like to delete their account
            int option = JOptionPane.showConfirmDialog(frame, "Confirm deletion of " + username + "'s account?",
                    "Delete Account", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                // Delete the account and display a pane showing the account was deleted
                userController.deleteAccount(username);
                JOptionPane.showMessageDialog(frame, "Account Successfully Deleted", "", JOptionPane.PLAIN_MESSAGE);
                new LoginScreen();
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            highlightButton(userProfileBackButton, 'd');
        } else if (e.getSource() == deleteUserButton) {
            highlightButton(deleteUserButton, 'd');
            deleteUserButton.setBackground(red);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            defaultButton(userProfileBackButton, 'd');
        } else if (e.getSource() == deleteUserButton) {
            defaultButton(deleteUserButton, 'd');
            deleteUserButton.setBackground(red);
        }
    }
}
