package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class EnterPoint {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        SwingUtilities.invokeLater(() -> {
            setUIFont(new Font("JetBrains Mono Nerd", Font.PLAIN, 16));
            new FrameMain().setVisible(true);
        });
    }

    private static void setUIFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("ComboBox.font", font);
    }
}