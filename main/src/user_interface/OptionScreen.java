package user_interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * Option Screen class for the User Interface. The screen that contains options to generate worksheet,
 * access user profile, and user history.
 *
 * @author Ethan Ing, Piotr Pralat
 * @since 2021-11-01
 */
public class OptionScreen extends Screen implements MouseListener {

    // Create buttons
    final JButton createWSButton = new JButton("Generate Worksheet");
    final JButton userHistoryButton = new JButton();
    final JButton userProfileButton = new JButton();
    final JButton logoutButton = new JButton("Log out");

    public OptionScreen() {

        // Update the panel to the default settings
        updatePanel(optionPanel);

        // Create the labels
        JLabel userProfileLbl = new JLabel("Profile", SwingConstants.CENTER);
        JLabel userHistoryLbl = new JLabel("History", SwingConstants.CENTER);

        // Update the location of each label
        updateLabel(userProfileLbl, 0.82, 0.215, 0.2, 0.2, 0.015, 'd');
        updateLabel(userHistoryLbl, 0.82, 0.405, 0.2, 0.2, 0.015, 'd');

        // Create the profile and history image label
        JLabel profileImageLbl = new JLabel("", SwingConstants.CENTER);
        updateLabel(profileImageLbl, 0.82, 0.12, 0.2, 0.2, 0, 'd');
        JLabel historyImageLbl = new JLabel("", SwingConstants.CENTER);
        updateLabel(historyImageLbl, 0.825, 0.32, 0.2, 0.2, 0, 'd');

        // Get the images from within the class and display them inside the JLabel (that is inside the button)
        ImageIcon profileIconImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("userProfileIcon.png")));
        Image profileImage = profileIconImage.getImage();
        Image profileScaledImage = profileImage.getScaledInstance(155, 150, Image.SCALE_SMOOTH);

        ImageIcon historyIconImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("userHistoryIcon.png")));
        Image historyImage = historyIconImage.getImage();
        Image historyScaledImage = historyImage.getScaledInstance(67, 67, Image.SCALE_SMOOTH);

        // Resize and create JLabels for the images
        profileIconImage = new ImageIcon(profileScaledImage);
        profileImageLbl.setIcon(profileIconImage);

        historyIconImage = new ImageIcon(historyScaledImage);
        historyImageLbl.setIcon(historyIconImage);

        // Update the location of each button and make the user profile and user history button's circles
        updateButtonLocation(createWSButton, 0.35, 0.375, 0.3, 0.1);
        defaultButton(createWSButton, 'd');

        updateButtonLocation(userProfileButton, 0.8795, 0.16, 0.14, 0.15);
        userProfileButton.setBorder(new RoundedBorder(convert(0.051, 'w') + convert(0.051, 'h')));
        userProfileButton.setOpaque(false);
        userProfileButton.setContentAreaFilled(false);

        updateButtonLocation(userHistoryButton, 0.8795, 0.3525, 0.14, 0.15);
        userHistoryButton.setBorder(new RoundedBorder(convert(0.05, 'w') + convert(0.05, 'h')));
        userHistoryButton.setOpaque(false);
        userHistoryButton.setContentAreaFilled(false);

        // Update the settings of the logout button
        updateButtonLocation(logoutButton, 0.8575, 0.5525, 0.125, 0.06);
        defaultButton(logoutButton, 'd');
        logoutButton.setBackground(red);

        // Add Mouse Listener for hover and clicking features
        createWSButton.addMouseListener(this);
        userProfileButton.addMouseListener(this);
        userHistoryButton.addMouseListener(this);
        logoutButton.addMouseListener(this);

        // Add all components to the panel
        optionPanel.add(createWSButton);
        optionPanel.add(userProfileButton);
        optionPanel.add(userHistoryButton);
        optionPanel.add(logoutButton);
        optionPanel.add(userProfileLbl);
        optionPanel.add(userHistoryLbl);
        optionPanel.add(profileImageLbl);
        optionPanel.add(historyImageLbl);

        // Change the panel to the option screen panel
        changePanel(optionPanel);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == createWSButton) {
            new TopicScreen();
        } else if (e.getSource() == userProfileButton) {
            new UserProfileScreen();
        } else if (e.getSource() == userHistoryButton) {
            new WorksheetHistoryScreen();
        } else if (e.getSource() == logoutButton) {

            // Display an option pane confirming the user would like to log out
            int option = JOptionPane.showConfirmDialog(frame, "Would you like to logout?",
                    "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                new LoginScreen();      // Log the user out if they clicked yes
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createWSButton) {
            highlightButton(createWSButton, 'd');
        } else if (e.getSource() == logoutButton) {
            highlightButton(logoutButton, 'd');
            logoutButton.setBackground(red);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createWSButton) {
            defaultButton(createWSButton, 'd');
        } else if (e.getSource() == logoutButton) {
            defaultButton(logoutButton, 'd');
            logoutButton.setBackground(red);
        }
    }

    /**
     * RoundedBorder private class that creates a circle border for the JButtons.
     * <p>
     * Note that for this particular class, it is similar to
     * https://github.com/kikonen/glazed-share/blob/master/extensions/issuesbrowser/source/com/publicobject/misc/
     * swing/RoundedBorder.java which was seen before implementing this private class.
     */
    @SuppressWarnings("ALL")
    private static class RoundedBorder implements Border {

        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawOval(x, y, radius, radius);
        }
    }
}
