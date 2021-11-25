package user_interface;

import javax.swing.*;
import java.awt.*;
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

    // Create back button
    JButton userProfileBackButton = new JButton("Back");
    JButton deleteUserButton = new JButton("Delete Account");

    // Create a map of the user's details
    Map<String, Object> userDetails = userController.getUserDetails();

    public UserProfileScreen() {

        updatePanel(userProfilePanel);

        // Create the user profile and its shadow
        JLabel userProfileTitle = new JLabel("User Profile", SwingConstants.CENTER);
        JLabel userProfileTitleShadow = new JLabel("User Profile", SwingConstants.CENTER);

        // Create the label's displaying the user's profile information
        JLabel nameProfileLbl = new JLabel("Name:\t\t" + userDetails.get("name"));
        JLabel ageProfileLbl = new JLabel("Age:\t\t" + userDetails.get("age"));
        JLabel roleProfileLBL = new JLabel("Role:\t\t" + userDetails.get("role"));

        // Update the location of the labels
        updateLabel(userProfileTitle, 0.2, 0.16, 0.6, 0.1, 0.03075, 'd');
        updateLabel(userProfileTitleShadow, 0.2, 0.1625, 0.6, 0.1, 0.03075, 'w');
        updateLabel(nameProfileLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageProfileLbl, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleProfileLBL, 0.325, 0.525, 0.6, 0.1, 0.025, 'd');

        // Update the location of each button
        updateButtonLocation(userProfileBackButton, 0.145, 0.8, 0.125, 0.05);
        updateButtonLocation(deleteUserButton, 0.65, 0.8, 0.225, 0.05);
        defaultButton(userProfileBackButton);
        defaultButton(deleteUserButton);
        deleteUserButton.setOpaque(true);
        deleteUserButton.setBackground(new Color(199, 63, 63));

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

        changePanel(userProfilePanel);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            new OptionScreen();
        }
        else if (e.getSource() == deleteUserButton) {
            userController.deleteAccount(userName);
            new LoginScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            highlightButton(userProfileBackButton);
        }
        else if (e.getSource() == deleteUserButton) {
            highlightButton(deleteUserButton);
            deleteUserButton.setBackground(new Color(199, 63, 63));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            defaultButton(userProfileBackButton);
        }
        else if (e.getSource() == deleteUserButton) {
            defaultButton(deleteUserButton);
            deleteUserButton.setBackground(new Color(199, 63, 63));
        }
    }
}
