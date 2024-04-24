import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class UserInterface extends JFrame {
    public UserInterface(){
        JPanel north = new JPanel(new FlowLayout());
        JFileChooser jFileChooser = new JFileChooser("./");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        JButton selectFile = new JButton("Select file with data");
        selectFile.addActionListener((event) -> {
            jFileChooser.showOpenDialog(this);
        });
        JLabel kLabel = new JLabel("How many groups do you want?");
        JComboBox<Integer> kValues = new JComboBox<>(new Integer[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});

        north.add(selectFile);
        north.add(kLabel);
        north.add(kValues);
        this.getContentPane().add(north, BorderLayout.NORTH);

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
