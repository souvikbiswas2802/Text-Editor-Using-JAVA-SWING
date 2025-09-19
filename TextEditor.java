import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile = null;

    public TextEditor() {
        // Main text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // File chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(helpMenu);

        // File menu items
        addMenuItem(fileMenu, "New");
        addMenuItem(fileMenu, "Open");
        addMenuItem(fileMenu, "Save");
        addMenuItem(fileMenu, "Save As");
        addMenuItem(fileMenu, "Page Setup");
        addMenuItem(fileMenu, "Print");
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Exit");

        // Edit menu items
        addMenuItem(editMenu, "Cut");
        addMenuItem(editMenu, "Copy");
        addMenuItem(editMenu, "Paste");
        addMenuItem(editMenu, "Delete");
        addMenuItem(editMenu, "Find");
        addMenuItem(editMenu, "Replace");
        addMenuItem(editMenu, "Go To");
        editMenu.addSeparator();
        addMenuItem(editMenu, "Select All");
        addMenuItem(editMenu, "Time Stamp");

        // Format menu items
        JCheckBoxMenuItem wordWrap = new JCheckBoxMenuItem("Word Wrap");
        wordWrap.addActionListener(e -> textArea.setLineWrap(wordWrap.isSelected()));
        formatMenu.add(wordWrap);
        addMenuItem(formatMenu, "Font");

        // Help menu items
        addMenuItem(helpMenu, "About TextEditor");

        // Frame settings
        setTitle("Untitled - TextEditor");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addMenuItem(JMenu menu, String text) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(this);
        menu.add(item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "New":
                textArea.setText("");
                currentFile = null;
                setTitle("Untitled - TextEditor");
                break;

            case "Open":
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile();
                    openFile(currentFile);
                }
                break;

            case "Save":
                if (currentFile != null) {
                    saveFile(currentFile);
                } else {
                    saveAs();
                }
                break;

            case "Save As":
                saveAs();
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Cut":
                textArea.cut();
                break;

            case "Copy":
                textArea.copy();
                break;

            case "Paste":
                textArea.paste();
                break;

            case "Delete":
                textArea.replaceSelection("");
                break;

            case "Select All":
                textArea.selectAll();
                break;

            case "Time Stamp":
                textArea.insert(Calendar.getInstance().getTime().toString(),
                        textArea.getCaretPosition());
                break;

            case "Find":
                findText();
                break;

            case "Replace":
                replaceText();
                break;

            case "Go To":
                goToLine();
                break;

            case "Font":
                chooseFont();
                break;

            case "Page Setup":
            case "Print":
                try {
                    textArea.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Print error: " + ex.getMessage());
                }
                break;

            case "About TextEditor":
                JOptionPane.showMessageDialog(this, "Simple TextEditor\nCreated in Java Swing.\nDeveloped by Souvik Biswas✨");
                break;
        }
    }

    private void openFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.read(reader, null);
            setTitle(file.getName() + " - TextEditor");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error opening file");
        }
    }

    private void saveFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            textArea.write(writer);
            setTitle(file.getName() + " - TextEditor");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file");
        }
    }

    private void saveAs() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            if (!currentFile.getName().endsWith(".txt")) {
                currentFile = new File(currentFile.getAbsolutePath() + ".txt");
            }
            saveFile(currentFile);
        }
    }

    private void findText() {
        String find = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (find != null && !find.isEmpty()) {
            String content = textArea.getText();
            int index = content.indexOf(find, textArea.getCaretPosition());
            if (index == -1) {
                index = content.indexOf(find);
            }
            if (index >= 0) {
                textArea.select(index, index + find.length());
            } else {
                JOptionPane.showMessageDialog(this, "Text not found");
            }
        }
    }

    private void replaceText() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField findField = new JTextField();
        JTextField replaceField = new JTextField();
        panel.add(new JLabel("Find:"));
        panel.add(findField);
        panel.add(new JLabel("Replace with:"));
        panel.add(replaceField);

        int option = JOptionPane.showConfirmDialog(this, panel,
                "Find and Replace", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String find = findField.getText();
            String replace = replaceField.getText();
            textArea.setText(textArea.getText().replace(find, replace));
        }
    }

    private void goToLine() {
        try {
            int line = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter line number:"));
            int start = textArea.getLineStartOffset(line - 1);
            textArea.setCaretPosition(start);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid line number");
        }
    }

    private void chooseFont() {
        Font currentFont = textArea.getFont();
        JFontChooser fontChooser = new JFontChooser(currentFont);
        int result = JOptionPane.showConfirmDialog(this, fontChooser, "Choose Font",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            textArea.setFont(fontChooser.getSelectedFont());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new);
    }
}
