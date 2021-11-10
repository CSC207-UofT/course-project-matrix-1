import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class UserProfileScreen extends StartScreen implements MouseListener {

    // Create Equation Details and Formatting JLabels and its shadow
    JLabel userProfileTitle = new JLabel("User Profile", SwingConstants.CENTER);
    JLabel userProfileTitleShadow = new JLabel("User Profile", SwingConstants.CENTER);

    JButton userProfileBackButton = new JButton("Back");

    public UserProfileScreen() {

        // Set the Panel to the new user screen
        cardLayout.show(cardPanel, "UserProfileScreen");

        userProfilePanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        userProfilePanel.setLayout(null);

        JLabel usernameProfileLbl = new JLabel("Username:");
        JLabel nameProfileLbl = new JLabel("Name:");
        JLabel ageProfileLbl = new JLabel("Age:");
        JLabel roleProfileLBL = new JLabel("Role:");

        updateLabel(userProfileTitle, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(userProfileTitleShadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');

        updateLabel(usernameProfileLbl, 0.325, 0.15, 0.6, 0.1, 0.025, 'd');
        updateLabel(nameProfileLbl, 0.325, 0.275, 0.6, 0.1, 0.025, 'd');
        updateLabel(ageProfileLbl, 0.325, 0.4, 0.6, 0.1, 0.025, 'd');
        updateLabel(roleProfileLBL, 0.325, 0.525, 0.6, 0.1, 0.025, 'd');

        // Update the location of each button
        updateButtonLocation(userProfileBackButton, 0.145, 0.825, 0.125, 0.05);
        defaultButton(userProfileBackButton);

        userProfileBackButton.addMouseListener(this);

        userProfilePanel.add(userProfileTitle);
        userProfilePanel.add(userProfileTitleShadow);
        userProfilePanel.add(usernameProfileLbl);
        userProfilePanel.add(nameProfileLbl);
        userProfilePanel.add(ageProfileLbl);
        userProfilePanel.add(roleProfileLBL);
        userProfilePanel.add(userProfileBackButton);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == userProfileBackButton) {
            frame.setVisible(false);
            userProfilePanel.setVisible(false);
            new OptionScreen();
            }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == userProfileBackButton)
            highlightButton(userProfileBackButton);
    }
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == userProfileBackButton)
            defaultButton(userProfileBackButton);
    }
}

