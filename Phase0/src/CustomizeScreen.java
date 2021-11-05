import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomizeScreen extends StartScreen implements MouseListener {

    JButton generateWorksheetButton = new JButton("Generate Worksheet");
    JButton nextButton = new JButton("Next");

    JPanel panel3 = new JPanel();

    public CustomizeScreen() {

        // Set Panel and Border
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel3);
        frame.revalidate();

        panel3.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        panel3.setLayout(null);

        // Create Equation Details JLabel and it's shadow
        JLabel title1 = new JLabel("Equation Details", SwingConstants.CENTER);
        JLabel title1Shadow = new JLabel("Equation Details", SwingConstants.CENTER);
        updateLabel(title1, 0.2, 0.01, 0.6, 0.1, 0.03075, 'n');
        updateLabel(title1Shadow, 0.2, 0.0125, 0.6, 0.1, 0.03075, 'd');

        // Add to Panel
        panel3.add(title1);
        panel3.add(title1Shadow);

        // Create Equation Questions JLabel
        JLabel q1 = new JLabel("Operand 1 Range");
        updateLabel(q1, 0.25, 0.1, 0.6, 0.1, 0.02, 'd');

        JTextField op1MIN = new JTextField(1);
        op1MIN.setBounds(convert(0.525, 'w'), convert(0.125, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

        JLabel dash = new JLabel("-");
        updateLabel(dash, 0.645, 0.1, 0.05, 0.1, 0.025, 'd');

        JTextField op1MAX = new JTextField(1);
        op1MAX.setBounds(convert(0.675, 'w'), convert(0.125, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

        JLabel q2 = new JLabel("Operand 2 Range");
        updateLabel(q2, 0.25, 0.175, 0.6, 0.1, 0.02, 'd');

        JTextField op2MIN = new JTextField(1);
        op2MIN.setBounds(convert(0.525, 'w'), convert(0.2, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

        JLabel dash2 = new JLabel("-");
        updateLabel(dash2, 0.645, 0.175, 0.1, 0.1, 0.025, 'd');

        JTextField op2MAX = new JTextField(1);
        op2MAX.setBounds(convert(0.675, 'w'), convert(0.2, 'h'), convert(0.1, 'w'),
                convert(0.05, 'h'));

        JLabel q3 = new JLabel("Negative are Allowed?");
        updateLabel(q3, 0.25, 0.25, 0.6, 0.1, 0.02, 'd');

        JCheckBox negativeAllowed = new JCheckBox("");
        negativeAllowed.setBounds(convert(0.565, 'w'), convert(0.275, 'h'), convert(0.5, 'w'),
                convert(0.5, 'h'));
        negativeAllowed.setSize(new Dimension(40, 40));


        // Add to Panel
        panel3.add(q1);
        panel3.add(op1MIN);
        panel3.add(dash);
        panel3.add(op1MAX);
        panel3.add(q2);
        panel3.add(op2MIN);
        panel3.add(dash2);
        panel3.add(op2MAX);
        panel3.add(q3);
        panel3.add(negativeAllowed);

        // Create Formatting JLabel and it's shadow
        JLabel title2 = new JLabel("Formatting", SwingConstants.CENTER);
        JLabel title2Shadow = new JLabel("Formatting", SwingConstants.CENTER);
        updateLabel(title2, 0.2, 0.325, 0.6, 0.1, 0.03075, 'n');
        updateLabel(title2Shadow, 0.2025, 0.3275, 0.6, 0.1, 0.03075, 'd');

        // Add to Panel
        panel3.add(title2);
        panel3.add(title2Shadow);

        // Create Formatting Questions JLabel
        JLabel q4 = new JLabel("Question Format");
        updateLabel(q4, 0.25, 0.4, 0.6, 0.1, 0.02, 'd');

        String[] options2 = {"Vertical", "Horizontal", "Division Bracket"};
        JComboBox<String> questionFormat = new JComboBox<>(options2);
        questionFormat.setBounds(convert(0.535, 'w'), convert(0.405, 'h'), convert(0.15, 'w'),
                convert(0.1, 'h'));
        questionFormat.setSelectedIndex(0);

        JLabel q5 = new JLabel("Title");
        updateLabel(q5, 0.25, 0.475, 0.6, 0.1, 0.02, 'd');

        JTextField text3 = new JTextField(1);
        text3.setBounds(convert(0.525, 'w'), convert(0.5, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        JLabel q6 = new JLabel("Number of Questions");
        updateLabel(q6, 0.25, 0.55, 0.6, 0.1, 0.02, 'd');

        JTextField text4 = new JTextField(1);
        text4.setBounds(convert(0.525, 'w'), convert(0.575, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        JLabel q7 = new JLabel("Number of Rows");
        updateLabel(q7, 0.25, 0.625, 0.6, 0.1, 0.02, 'd');

        JTextField text5 = new JTextField(1);
        text5.setBounds(convert(0.525, 'w'), convert(0.65, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        JLabel q8 = new JLabel("Number of Columns");
        updateLabel(q8, 0.25, 0.7, 0.6, 0.1, 0.02, 'd');

        JTextField text6 = new JTextField(1);
        text6.setBounds(convert(0.525, 'w'), convert(0.725, 'h'), convert(0.175, 'w'),
                convert(0.05, 'h'));

        // Add to Panel
        panel3.add(q4);
        panel3.add(questionFormat);
        panel3.add(q5);
        panel3.add(text3);
        panel3.add(q6);
        panel3.add(text4);
        panel3.add(q7);
        panel3.add(text5);
        panel3.add(q8);
        panel3.add(text6);

        // Create Generate Worksheet JButton
        updateButtonLocation(generateWorksheetButton, 0.35, 0.825, 0.3, 0.1);
        defaultButton(generateWorksheetButton);
        generateWorksheetButton.addMouseListener(this);

        updateButtonLocation(nextButton, 0.775, 0.875, 0.125, 0.05);
        defaultButton(nextButton);
        nextButton.addMouseListener(this);

        // Add to Panel
        panel3.add(generateWorksheetButton);
        panel3.add(nextButton);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == generateWorksheetButton) {
            System.out.println("Generate Worksheet");
        }
        if (e.getSource() == nextButton) {
            System.out.println("Next Page");
        }
    }
        public void mouseEntered (MouseEvent e){
            if (e.getSource() == generateWorksheetButton)
                highlightButton(generateWorksheetButton);
            if (e.getSource() == nextButton)
                highlightButton(nextButton);
        }

        public void mouseExited (MouseEvent e){
            if (e.getSource() == generateWorksheetButton)
                defaultButton(generateWorksheetButton);
            if (e.getSource() == nextButton)
                defaultButton(nextButton);
        }

    public static void main(String[] args) {
        new CustomizeScreen();
    }

}
