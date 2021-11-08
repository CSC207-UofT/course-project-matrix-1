import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OptionScreen extends StartScreen implements MouseListener {

    JButton createWSButton = new JButton("Generate Worksheet");
    JButton userHistoryButton = new JButton("User History");
    JButton userProfileButton = new JButton("User Profile");
    JButton[] optionButtons = {createWSButton, userHistoryButton, userProfileButton};

    public OptionScreen() {

        // Set the Panel to the Option Screen
        cardLayout.show(cardPanel, "OptionScreen");

        optionPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        optionPanel.setLayout(null);


        updateButtonLocation(createWSButton, 0.35, 0.375, 0.3, 0.1);

        updateButtonLocation(userProfileButton, 0.8, 0.05, 0.1, 0.1);
        userProfileButton.setContentAreaFilled(false);
        userProfileButton.setFocusPainted(false);
        userProfileButton.setOpaque(false);

        defaultButton(userProfileButton);
        defaultButton(createWSButton);

        // Add mouse listeners to each button
        createWSButton.addMouseListener(this);
        userProfileButton.addMouseListener(this);
        userHistoryButton.addMouseListener(this);

        // Add components to the Panel
        optionPanel.add(createWSButton);
        optionPanel.add(userProfileButton);
        optionPanel.add(userHistoryButton);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == createWSButton) {
            frame.setVisible(false);
            optionPanel.setVisible(false);
            new TopicScreen();
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
}
