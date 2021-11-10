package user_interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class OptionScreen extends StartScreen implements MouseListener {

    JButton createWSButton = new JButton("Generate Worksheet");
    JButton userHistoryButton = new JButton();
    JButton userProfileButton = new JButton();

    JButton[] optionButtons = {createWSButton, userHistoryButton, userProfileButton};

    public OptionScreen() {

        // Set the Panel to the Option Screen
        cardLayout.show(cardPanel, "OptionScreen");

        optionPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        optionPanel.setLayout(null);

        updateButtonLocation(createWSButton, 0.35, 0.375, 0.3, 0.1);

        updateButtonLocation(userProfileButton, 0.775, 0.05, 0.15, 0.15);
        userProfileButton.setBorder(new RoundedBorder(convert(0.055, 'w') + convert(0.055, 'h')));
        updateButtonLocation(userHistoryButton, 0.7825, 0.25, 0.15, 0.15);
        userHistoryButton.setBorder(new RoundedBorder(convert(0.045, 'w') + convert(0.045, 'h')));

        defaultButton(createWSButton);

        // Add mouse listeners to each button
        createWSButton.addMouseListener(this);
        userProfileButton.addMouseListener(this);
        userHistoryButton.addMouseListener(this);

        JLabel userProfileLbl = new JLabel("User Profile");
        JLabel userHistoryLbl = new JLabel("User History");

        updateLabel(userProfileLbl, 0.76, 0.115, 0.2, 0.2, 0.015, 'd');
        updateLabel(userHistoryLbl, 0.76, 0.2875, 0.2, 0.2, 0.015, 'd');

        // Resize and create JLabel's for the images
        ImageIcon profileIconImage = new ImageIcon(getClass().getResource("userProfileIcon.png"));
        Image profileImage = profileIconImage.getImage();
        Image profileScaledImage = profileImage.getScaledInstance(150,150, Image.SCALE_SMOOTH);
        profileIconImage = new ImageIcon(profileScaledImage);
        JLabel profileImageLbl = new JLabel(profileIconImage, JLabel.CENTER);
        updateLabel(profileImageLbl, 0.72, 0.015, 0.2, 0.2, 0, 'n');

        ImageIcon historyIconImage = new ImageIcon(getClass().getResource("userHistoryIcon.png"));
        Image historyImage = historyIconImage.getImage();
        Image historyScaledImage = historyImage.getScaledInstance(67,67, Image.SCALE_SMOOTH);
        historyIconImage = new ImageIcon(historyScaledImage);
        JLabel historyImageLbl = new JLabel(historyIconImage, JLabel.CENTER);
        updateLabel(historyImageLbl, 0.725, 0.21, 0.2, 0.2, 0, 'n');

        // Add components to the Panel
        optionPanel.add(createWSButton);
        optionPanel.add(userProfileButton);
        optionPanel.add(userHistoryButton);
        optionPanel.add(userProfileLbl);
        optionPanel.add(userHistoryLbl);
        optionPanel.add(profileImageLbl);
        optionPanel.add(historyImageLbl);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == createWSButton) {
            frame.setVisible(false);
            optionPanel.setVisible(false);
            new TopicScreen();
        }
        if (e.getSource() == userProfileButton) {
            frame.setVisible(false);
            optionPanel.setVisible(false);
            new UserProfileScreen();
        }
        if (e.getSource() == userHistoryButton) {
            System.out.println("user history");
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createWSButton) {
            highlightButton(createWSButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createWSButton) {
            defaultButton(createWSButton);
        }
    }

        private static class RoundedBorder implements Border {

        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawOval(x, y, radius, radius);
        }
    }
}
