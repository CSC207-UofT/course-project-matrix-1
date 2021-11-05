import javax.swing.*;
import java.awt.*;
public class WorksheetViewerScreen extends StartScreen{
    public WorksheetViewerScreen(){
        // Set Frame
        JFrame frame3 = new JFrame();
        frame3.setSize(width, height);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Panel and Border
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        panel3.setLayout(null);
        frame3.add(panel3);
        frame3.setVisible(true);
        frame.setVisible(false);
    }
}
