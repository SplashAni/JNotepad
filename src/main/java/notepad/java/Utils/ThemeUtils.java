package notepad.java.Utils;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;

public class ThemeUtils extends JFrame {
    public static void Manager(JFrame window, JTextArea textArea) {
        textArea.setText("testing");
    }

    public void themeCreator() {
        setTitle("Theme Creator");
        FlatMacLightLaf.install();
        setLayout(new GridBagLayout()); // thanks bro code

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Theme creator");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(title, gbc);

        gbc.gridwidth = 1;

        JLabel l1 = new JLabel("Background");

        l1.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(l1, gbc);

        JButton colorButton = new JButton("C");
        colorButton.setPreferredSize(new Dimension(50, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(colorButton, gbc);

        JLabel l2 = new JLabel("Font");
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(l2, gbc);

        JButton fontButton = new JButton("F");
        fontButton.setPreferredSize(new Dimension(50, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(fontButton, gbc);

        JLabel l3 = new JLabel("Size");
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(l3, gbc);

        JButton sizeButton = new JButton("S");
        sizeButton.setPreferredSize(new Dimension(50, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(sizeButton, gbc);

        JLabel l4 = new JLabel("Icon");
        l4.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(l4, gbc);

        JButton iconButton = new JButton("I");
        iconButton.setPreferredSize(new Dimension(50, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(iconButton, gbc);

        setSize(300,320);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}