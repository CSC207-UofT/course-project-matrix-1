import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomizeScreen extends StartScreen implements MouseListener {

    // Create buttons
    JButton generateWorksheetButton = new JButton("Generate Worksheet");
    JButton customizeBackButton = new JButton("Back");
    JButton [] generateWSButtons = {generateWorksheetButton, customizeBackButton};

    // Create Equation Details and Formatting JLabels and its shadow
    JLabel title1 = new JLabel("Equation Details", SwingConstants.CENTER);
    JLabel title1Shadow = new JLabel("Equation Details", SwingConstants.CENTER);
    JLabel title2 = new JLabel("Formatting", SwingConstants.CENTER);
    JLabel title2Shadow = new JLabel("Formatting", SwingConstants.CENTER);

    // Create text fields
    JTextField title_tf = new JTextField(1);
    JTextField numQuestions_tf = new JTextField(1);
    JTextField numRows_tf = new JTextField(1);
    JTextField numColumn_tf = new JTextField(1);

    public CustomizeScreen() {

        // Change cardPanel to the custom worksheet screen
        cardLayout.show(cardPanel, "CustomizeScreen");
        customizeWSPanel.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'w'), Color.BLACK));
        customizeWSPanel.setLayout(null);

        // Create Equation Questions JLabel
        JLabel op1Range = new JLabel("Operand 1 Range");
        JLabel dash = new JLabel("-");
        JLabel op2Range = new JLabel("Operand 2 Range");
        JLabel dash2 = new JLabel("-");
        JLabel negAllowed = new JLabel("Negative are Allowed?");

        // Update the labels for the Equation Customization
        updateLabel(title1, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(title1Shadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');
        updateLabel(op1Range, 0.25, 0.1, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash, 0.645, 0.1, 0.05, 0.1, 0.025, 'd');
        updateLabel(op2Range, 0.25, 0.175, 0.6, 0.1, 0.02, 'd');
        updateLabel(dash2, 0.645, 0.175, 0.1, 0.1, 0.025, 'd');
        updateLabel(negAllowed, 0.25, 0.25, 0.6, 0.1, 0.02, 'd');

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
        JCheckBox negAllowedBox = new JCheckBox("");
        negAllowedBox.setBounds(convert(0.565, 'w'), convert(0.275, 'h'), convert(0.5, 'w'),
                convert(0.5, 'h'));
        negAllowedBox.setSize(new Dimension(40, 40));


        // Create Formatting Questions JLabel
        JLabel qFormat = new JLabel("Question Format");
        JLabel titleLabel = new JLabel("Title");
        JLabel numQuestions = new JLabel("Number of Questions");
        JLabel numRows = new JLabel("Number of Rows");
        JLabel numColumns = new JLabel("Number of Columns");


        // Combo Box for the question format
        String[] options2 = {"Vertical", "Horizontal", "Division Bracket"};
        JComboBox<String> questionFormat = new JComboBox<>(options2);
        questionFormat.setBounds(convert(0.535, 'w'), convert(0.405, 'h'), convert(0.15, 'w'),
                convert(0.1, 'h'));
        questionFormat.setSelectedIndex(0);


        // Set the location of each text field
        title_tf.setBounds(convert(0.525, 'w'), convert(0.5, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numQuestions_tf.setBounds(convert(0.525, 'w'), convert(0.575, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numRows_tf.setBounds(convert(0.525, 'w'), convert(0.65, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));
        numColumn_tf.setBounds(convert(0.525, 'w'), convert(0.725, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));


        // Update the labels for formatting
        updateLabel(title2, 0.2, 0.325, 0.6, 0.1, 0.03075, 'n');
        updateLabel(title2Shadow, 0.2025, 0.3275, 0.6, 0.1, 0.03075, 'd');
        updateLabel(qFormat, 0.25, 0.4, 0.6, 0.1, 0.02, 'd');
        updateLabel(titleLabel, 0.25, 0.475, 0.6, 0.1, 0.02, 'd');
        updateLabel(numQuestions, 0.25, 0.55, 0.6, 0.1, 0.02, 'd');
        updateLabel(numRows, 0.25, 0.625, 0.6, 0.1, 0.02, 'd');
        updateLabel(numColumns, 0.25, 0.7, 0.6, 0.1, 0.02, 'd');

        // Update the location of each button
        updateButtonLocation(generateWorksheetButton, 0.37, 0.8, 0.3, 0.1);
        updateButtonLocation(customizeBackButton, 0.145, 0.825, 0.125, 0.05);

        // Update the settings of each button
        defaultButton(generateWSButtons);

        // Add Mouse Listener to each buttons
        generateWorksheetButton.addMouseListener(this);
        customizeBackButton.addMouseListener(this);


        // Add all components to the Panel
        customizeWSPanel.add(generateWorksheetButton);
        customizeWSPanel.add(customizeBackButton);

        // Add tall components to the panel
        customizeWSPanel.add(title1);
        customizeWSPanel.add(title1Shadow);

        customizeWSPanel.add(title2);
        customizeWSPanel.add(title2Shadow);

        customizeWSPanel.add(op1Range);
        customizeWSPanel.add(op1MIN);
        customizeWSPanel.add(dash);
        customizeWSPanel.add(op1MAX);
        customizeWSPanel.add(op2Range);
        customizeWSPanel.add(op2MIN);
        customizeWSPanel.add(dash2);
        customizeWSPanel.add(op2MAX);
        customizeWSPanel.add(negAllowed);
        customizeWSPanel.add(negAllowedBox);

        customizeWSPanel.add(qFormat);
        customizeWSPanel.add(questionFormat);
        customizeWSPanel.add(titleLabel);
        customizeWSPanel.add(title_tf);
        customizeWSPanel.add(numQuestions);
        customizeWSPanel.add(numQuestions_tf);
        customizeWSPanel.add(numRows);
        customizeWSPanel.add(numRows_tf);
        customizeWSPanel.add(numColumns);
        customizeWSPanel.add(numColumn_tf);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            frame.setVisible(false);
            customizeWSPanel.setVisible(false);
            new WSViewerScreen();
        }
        if (e.getSource() == customizeBackButton) {
            frame.setVisible(false);
            customizeWSPanel.setVisible(false);
            new TopicScreen();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            highlightButton(generateWorksheetButton);
        }
        if (e.getSource() == customizeBackButton) {
            highlightButton(customizeBackButton);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            defaultButton(generateWorksheetButton);
        }
        if (e.getSource() == customizeBackButton) {
            defaultButton(customizeBackButton);
        }
    }
}
