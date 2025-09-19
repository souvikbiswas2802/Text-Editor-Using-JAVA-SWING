import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class JFontChooser extends JPanel {
    private JComboBox<String> fontBox;
    private JComboBox<Integer> sizeBox;
    private JCheckBox boldBox, italicBox;
    private Font selectedFont;

    public JFontChooser(Font initialFont) {
        setLayout(new GridLayout(2, 2, 5, 5));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();
        fontBox = new JComboBox<>(fonts);
        fontBox.setSelectedItem(initialFont.getFamily());

        Integer[] sizes = {8, 10, 12, 14, 16, 18, 20, 24, 28, 32, 36, 40};
        sizeBox = new JComboBox<>(sizes);
        sizeBox.setSelectedItem(initialFont.getSize());

        boldBox = new JCheckBox("Bold", initialFont.isBold());
        italicBox = new JCheckBox("Italic", initialFont.isItalic());

        add(new JLabel("Font:"));
        add(fontBox);
        add(new JLabel("Size:"));
        add(sizeBox);
        add(boldBox);
        add(italicBox);
    }

    public Font getSelectedFont() {
        int style = Font.PLAIN;
        if (boldBox.isSelected()) style |= Font.BOLD;
        if (italicBox.isSelected()) style |= Font.ITALIC;
        selectedFont = new Font((String) fontBox.getSelectedItem(),
                style,
                (Integer) sizeBox.getSelectedItem());
        return selectedFont;
    }
}
