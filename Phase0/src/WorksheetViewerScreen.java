import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class WorksheetViewerScreen extends StartScreen implements MouseListener {

    JPanel panel4 = new JPanel();

    public WorksheetViewerScreen(){
        // Set Frame


        // Set Panel and Border
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createMatteBorder(1, convert(0.1, 'w'), 1,
                convert(0.1, 'h'), Color.BLACK));
        panel3.setLayout(null);
        frame.add(panel3);
        frame.setVisible(true);
        frame.setVisible(false);
    }
}
