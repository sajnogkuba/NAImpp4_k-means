import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class UserInterface extends JFrame {
    KMeans kMeans;

    public UserInterface(){
        JPanel north = new JPanel(new FlowLayout());
        JFileChooser jFileChooser = new JFileChooser("./");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        JButton compute = new JButton("Compute");
        JButton selectFile = new JButton("Select file with data");
        selectFile.addActionListener((event) -> {
            jFileChooser.showOpenDialog(this);
            compute.setEnabled(true);
        });
        JComboBox<Integer> kValues = new JComboBox<>(new Integer[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        compute.setEnabled(false);
        JTextArea center = new JTextArea();
        compute.addActionListener(
                (event) -> {
                    kMeans = new KMeans(jFileChooser.getSelectedFile(), (Integer) kValues.getSelectedItem());
                    center.setText("Final Groups:\n" + kMeans.getGroups());
                    this.repaint();
                }

        );
        JLabel kLabel = new JLabel("How many groups do you want?");
        center.setEnabled(true);
        JScrollPane jScrollPane = new JScrollPane(center);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new Dimension(300, 500));


        north.add(selectFile);
        north.add(kLabel);
        north.add(kValues);
        north.add(compute);
        this.getContentPane().add(north, BorderLayout.NORTH);
        this.getContentPane().add(jScrollPane, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
