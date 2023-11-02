package lab11;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TokenizerApp {
    private static ArrayList<String> rootWords = new ArrayList<>();
    private static ArrayList<String> poemWords = new ArrayList<>();
    private static DefaultTableModel tableModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Text Tokenizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        JTextArea poemTextArea = new JTextArea(10, 30);
        poemTextArea.setLineWrap(true);
        frame.add(new JScrollPane(poemTextArea), BorderLayout.WEST);

        frame.setVisible(true);

        rootWords.add("ورد");
        rootWords.add("حمر");
        rootWords.add("حديقة");
        rootWords.add("فتح");
        rootWords.add("ألوان");
        rootWords.add("شرق");
        rootWords.add("عطي");
        rootWords.add("رائحة");
        rootWords.add("جميل");
        rootWords.add("سعد");
        rootWords.add("قلب");
        rootWords.add("حزن");

        JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.SOUTH);

        JButton tokenizeButton = new JButton("Tokenize Poem");
        controlPanel.add(tokenizeButton);

        tokenizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String poemText = poemTextArea.getText();
                tokenizePoem(poemText, frame);
            }
        });

        // Create a table model with two columns for combined words
        tableModel = new DefaultTableModel(new Object[]{"Root", "Word"}, 0);
        JTable combinedTable = new JTable(tableModel);
        frame.add(new JScrollPane(combinedTable), BorderLayout.CENTER);
    }

    private static void tokenizePoem(String poemText, JFrame parentFrame) {
        JPanel poemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        parentFrame.add(new JScrollPane(poemPanel), BorderLayout.NORTH);

        poemWords.clear();

        String[] words = poemText.split("\\s+");
        for (String word : words) {
            JButton wordButton = new JButton(word);
            wordButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedWord = ((JButton) e.getSource()).getText();
                    showRoots(selectedWord);
                }
            });
            poemWords.add(word);
            poemPanel.add(wordButton);
        }

        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private static void showRoots(String selectedWord) {
        JFrame rootsFrame = new JFrame("Select a Root for " + selectedWord);
        rootsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rootsFrame.setSize(400, 200);
        rootsFrame.setLayout(new GridLayout(0, 1));

        for (String root : rootWords) {
            JButton rootButton = new JButton(root);
            rootButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedRoot = ((JButton) e.getSource()).getText();
                    combineRootWithWord(selectedWord, selectedRoot);
                    rootsFrame.dispose();
                }
            });
            rootsFrame.add(rootButton);
        }

        rootsFrame.setVisible(true);
    }

    private static void combineRootWithWord(String selectedWord, String selectedRoot) {
        tableModel.addRow(new Object[]{selectedRoot, selectedWord});
    }
}
