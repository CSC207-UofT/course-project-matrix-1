import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomizeScreen extends StartScreen implements MouseListener {

    // Create buttons
    JButton generateWorksheetButton = new JButton("Generate Worksheet");
    JButton customizeNextButton = new JButton("Next");
    JButton backButton = new JButton("Back");
    JButton [] generateWSButtons = {generateWorksheetButton, customizeNextButton, backButton};

    // Create Equation Details and Formatting JLabels and its shadow
    JLabel title1 = new JLabel("Equation Details", SwingConstants.CENTER);
    JLabel title1Shadow = new JLabel("Equation Details", SwingConstants.CENTER);

    JLabel title2 = new JLabel("Formatting", SwingConstants.CENTER);
    JLabel title2Shadow = new JLabel("Formatting", SwingConstants.CENTER);

    public CustomizeScreen() {

        // Change cardPanel to the customize worksheet screen
        cardLayout.show(cardPanel, "CustomizeScreen");
        customizeWSPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        customizeWSPanel.setLayout(null);

        // Create Equation Questions JLabel
        JLabel q1 = new JLabel("Operand 1 Range");
        JLabel dash = new JLabel("-");
        JLabel q2 = new JLabel("Operand 2 Range");
        JLabel dash2 = new JLabel("-");
        JLabel q3 = new JLabel("Negative are Allowed?");

        // Update the labels for the Equation Customization
        updateLabel(title1, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(title1Shadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(q1, 0.25, 0.1, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash, 0.645, 0.1, 0.05, 0.1, 0.025, 'd');
        updateLabel(q2, 0.25, 0.175, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash2, 0.645, 0.175, 0.1, 0.1, 0.025, 'd');
        updateLabel(q3, 0.25, 0.25, 0.6, 0.1, 0.02, 'd');

        // Minimum and Maximum text fields
        JTextField op1MIN = new JTextField(1);
        op1MIN.setBounds(convert(0.525, 'w'), convert(0.125, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        JTextField op1MAX = new JTextField(1);
        op1MAX.setBounds(convert(0.675, 'w'), convert(0.125, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

        JTextField op2MIN = new JTextField(1);
        op2MIN.setBounds(convert(0.525, 'w'), convert(0.2, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));
        JTextField op2MAX = new JTextField(1);
        op2MAX.setBounds(convert(0.675, 'w'), convert(0.2, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

        // CheckBox for negatives
        JCheckBox negativeAllowed = new JCheckBox("");
        negativeAllowed.setBounds(convert(0.565, 'w'), convert(0.275, 'h'), convert(0.5, 'w'),
                convert(0.5, 'h'));
        negativeAllowed.setSize(new Dimension(40, 40));


        // Create Formatting Questions JLabel
        JLabel q4 = new JLabel("Question Format");
        JLabel q5 = new JLabel("Title");
        JLabel q6 = new JLabel("Number of Questions");
        JLabel q7 = new JLabel("Number of Rows");
        JLabel q8 = new JLabel("Number of Columns");


        // Combo Box for the question format
        String[] options2 = {"Vertical", "Horizontal", "Division Bracket"};
        JComboBox<String> questionFormat = new JComboBox<>(options2);
        questionFormat.setBounds(convert(0.535, 'w'), convert(0.405, 'h'), convert(0.15, 'w'),
                convert(0.1, 'h'));
        questionFormat.setSelectedIndex(0);


        // Text fields for formatting
        JTextField text3 = new JTextField(1);
        text3.setBounds(convert(0.525, 'w'), convert(0.5, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        JTextField text4 = new JTextField(1);
        text4.setBounds(convert(0.525, 'w'), convert(0.575, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        JTextField text5 = new JTextField(1);
        text5.setBounds(convert(0.525, 'w'), convert(0.65, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        JTextField text6 = new JTextField(1);
        text6.setBounds(convert(0.525, 'w'), convert(0.725, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));


        // Update the labels for formatting
        updateLabel(title2, 0.2, 0.325, 0.6, 0.1, 0.03075, 'n');
        updateLabel(title2Shadow, 0.2025, 0.3275, 0.6, 0.1, 0.03075, 'd');
        updateLabel(q4, 0.25, 0.4, 0.6, 0.1, 0.02, 'd');
        updateLabel(q5, 0.25, 0.475, 0.6, 0.1, 0.02, 'd');
        updateLabel(q6, 0.25, 0.55, 0.6, 0.1, 0.02, 'd');
        updateLabel(q7, 0.25, 0.625, 0.6, 0.1, 0.02, 'd');
        updateLabel(q8, 0.25, 0.7, 0.6, 0.1, 0.02, 'd');

        // Update the location of each button
        updateButtonLocation(generateWorksheetButton, 0.35, 0.825, 0.3, 0.1);
        updateButtonLocation(customizeNextButton, 0.775, 0.85, 0.125, 0.05);
        updateButtonLocation(backButton, 0.225, 0.85, 0.125, 0.05);

        // Update the settings of each button
        defaultButton(generateWSButtons);

        // Add Mouse Listener to each buttons
        generateWorksheetButton.addMouseListener(this);
        customizeNextButton.addMouseListener(this);
        backButton.addMouseListener(this);


        // Add all components to the Panel
        customizeWSPanel.add(generateWorksheetButton);
        customizeWSPanel.add(customizeNextButton);
        customizeWSPanel.add(backButton);

        // Add tall components to the panel
        customizeWSPanel.add(title1);
        customizeWSPanel.add(title1Shadow);

        customizeWSPanel.add(title2);
        customizeWSPanel.add(title2Shadow);

        customizeWSPanel.add(q1);
        customizeWSPanel.add(op1MIN);
        customizeWSPanel.add(dash);
        customizeWSPanel.add(op1MAX);
        customizeWSPanel.add(q2);
        customizeWSPanel.add(op2MIN);
        customizeWSPanel.add(dash2);
        customizeWSPanel.add(op2MAX);
        customizeWSPanel.add(q3);
        customizeWSPanel.add(negativeAllowed);

        customizeWSPanel.add(q4);
        customizeWSPanel.add(questionFormat);
        customizeWSPanel.add(q5);
        customizeWSPanel.add(text3);
        customizeWSPanel.add(q6);
        customizeWSPanel.add(text4);
        customizeWSPanel.add(q7);
        customizeWSPanel.add(text5);
        customizeWSPanel.add(q8);
        customizeWSPanel.add(text6);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            System.out.println("Generate Worksheet");
        }
        if (e.getSource() == customizeNextButton) {
            frame.setVisible(false);
            customizeWSPanel.setVisible(false);
            new WorksheetViewerScreen();
        }
        if (e.getSource() == backButton) {
            frame.setVisible(false);
            customizeWSPanel.setVisible(false);
            new TopicScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            highlightButton(generateWorksheetButton);
        }
        if (e.getSource() == customizeNextButton) {
            highlightButton(customizeNextButton);
        }
        if (e.getSource() == backButton) {
            highlightButton(backButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton)
            defaultButton(generateWorksheetButton);
        if (e.getSource() == customizeNextButton)
            defaultButton(customizeNextButton);
        if (e.getSource() == backButton)
            defaultButton(backButton);
    }
}
