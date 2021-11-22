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

    // Create back button
    JButton userProfileBackButton = new JButton("Back");

    // Create a map of the user's details
    Map<String, Object> userDetails = userController.getUserDetails();

    public UserProfileScreen() {

        changePanel(userProfilePanel);

        // Create the user profile and its shadow
        JLabel userProfileTitle = new JLabel("User Profile", SwingConstants.CENTER);
        JLabel userProfileTitleShadow = new JLabel("User Profile", SwingConstants.CENTER);

        // Create the label's displaying the user's profile information
        JLabel nameProfileLbl = new JLabel("Name:\t\t" + userDetails.get("name"));
        JLabel ageProfileLbl = new JLabel("Age:\t\t" + userDetails.get("age"));
        JLabel roleProfileLBL = new JLabel("Role:\t\t" + userDetails.get("role"));

        // Update the location of the labels
        updateLabel(userProfileTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'r');
        updateLabel(userProfileTitleShadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(nameProfileLbl, 0.325, 0.15, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageProfileLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleProfileLBL, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');

        // Update the location of each button
        updateButtonLocation(userProfileBackButton, 0.145, 0.8, 0.125, 0.05);
        defaultButton(userProfileBackButton);

        // Add MouseListener for the hover and clicking features
        userProfileBackButton.addMouseListener(this);

        // Add each component to the panel
        userProfilePanel.add(userProfileTitle);
        userProfilePanel.add(userProfileTitleShadow);
        userProfilePanel.add(nameProfileLbl);
        userProfilePanel.add(ageProfileLbl);
        userProfilePanel.add(roleProfileLBL);
        userProfilePanel.add(userProfileBackButton);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            new OptionScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            highlightButton(userProfileBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            defaultButton(userProfileBackButton);
        }
    }
}
