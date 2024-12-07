import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class gui {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Floating Point Arithmetic Coding");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create a label
        JLabel label = new JLabel("Choose an option:");
        label.setBounds(150, 20, 200, 30);
        frame.add(label);

        // Create a button for compressing
        JButton compressButton = new JButton("Compress");
        compressButton.setBounds(50, 80, 120, 30);
        frame.add(compressButton);

        // Create a button for decompressing
        JButton decompressButton = new JButton("Decompress");
        decompressButton.setBounds(220, 80, 120, 30);
        frame.add(decompressButton);

        // Add action listeners to buttons
        compressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("ZIP files", "zip"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String inputFileName = selectedFile.getAbsolutePath();
                    String originalText = fileOperation.readOriginalFile(inputFileName);
                    ArithmeticCoding compressor = new ArithmeticCoding();
                    double compressedValue = compressor.compress(originalText);
                    JOptionPane.showMessageDialog(frame, "Compression is done.");
                    
                    // Save compressed value and overhead
                    String outputFileName = JOptionPane.showInputDialog(frame, "Enter name for compressed file:");
                    fileOperation.writeCompressedValue(compressedValue, outputFileName);
                    fileOperation.writeOverhead(compressor.getOverHead(), outputFileName);
                }
            }
        });

        decompressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("ZIP files", "zip"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String inputFileName = selectedFile.getAbsolutePath();
                    String compressedNum = fileOperation.readcompressedValue(inputFileName);
                    
                    String overheadFileName = JOptionPane.showInputDialog(frame, "Enter name of overhead file:");
                    Overhead overhead = fileOperation.readOverhead(overheadFileName);
                    
                    ArithmeticCoding compressor = new ArithmeticCoding();
                    compressor.setOverHead(overhead);
                    String outputText = compressor.deCompress(compressedNum);
                    JOptionPane.showMessageDialog(frame, "Decompression is done.");
                    
                    // Save decompressed output
                    String outputFileName = JOptionPane.showInputDialog(frame, "Enter name for output file:");
                    fileOperation.WriteOutputFile(outputText, outputFileName);
                }
            }
        });

        // Set the frame visibility
        frame.setVisible(true);
    }
}
