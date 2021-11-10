import user_package.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class UserProfileScreen extends StartScreen implements MouseListener {

    // Create Equation Details and Formatting JLabels and its shadow
    JLabel newUserTitle = new JLabel("User Profile", SwingConstants.CENTER);
    JLabel newUserTitleShadow = new JLabel("User Profile", SwingConstants.CENTER);

    JButton userProfileButton = new JButton("Back");

    public UserProfileScreen() {

        // Set the Panel to the new user screen
        cardLayout.show(cardPanel, "UserProfileScreen");

        newUserPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        newUserPanel.setLayout(null);

        Map<String, Object> details = uc.getUserDetails(uc.getCurrentUsername());
        System.out.println(details);

        // Create Equation Questions JLabel
        JLabel usernameProfileLbl = new JLabel("Username:" + uc.getCurrentUsername());
        JLabel nameProfileLbl = new JLabel("Name:" + details.get("name"));
        JLabel ageProfileLbl = new JLabel("Age:"+ details.get("age"));
        JLabel roleProfileLBL = new JLabel("Role:"+ details.get("role"));

        updateLabel(newUserTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(newUserTitleShadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');

        updateLabel(usernameProfileLbl, 0.325, 0.15, 0.6, 0.1, 0.025, 'd');
        updateLabel(nameProfileLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageProfileLbl, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleProfileLBL, 0.325, 0.525, 0.6, 0.1, 0.025, 'd');

        // Update the location of each button
        updateButtonLocation(createUserButton, 0.4, 0.8, 0.2, 0.1);
        updateButtonLocation(userProfileButton, 0.145, 0.825, 0.125, 0.05);
        defaultButton(createUserButton);
        defaultButton(userProfileButton);

        userProfileButton.addMouseListener(this);

        newUserPanel.add(newUserTitle);
        newUserPanel.add(newUserTitleShadow);
        newUserPanel.add(usernameProfileLbl);
        newUserPanel.add(nameProfileLbl);
        newUserPanel.add(ageProfileLbl);
        newUserPanel.add(roleProfileLBL);
        newUserPanel.add(userProfileButton);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == userProfileButton) {
            System.out.println("back");
            }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == userProfileButton)
            highlightButton(userProfileButton);
    }
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == userProfileButton)
            defaultButton(userProfileButton);
    }
}

