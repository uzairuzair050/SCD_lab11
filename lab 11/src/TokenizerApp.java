import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class TokenizerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Text Tokenizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(new BorderLayout());

        JTextField textField = new JTextField(20);
        panel.add(textField, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton tokenizeButton = new JButton("Tokenize");
        panel.add(tokenizeButton, BorderLayout.SOUTH);

        tokenizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                StringTokenizer tokenizer = new StringTokenizer(inputText);
                StringBuilder tokenizedText = new StringBuilder();

                while (tokenizer.hasMoreTokens()) {
                    tokenizedText.append(tokenizer.nextToken()).append("\n");
                }

                textArea.setText(tokenizedText.toString());
            }
        });

        frame.setVisible(true);
    }
}
