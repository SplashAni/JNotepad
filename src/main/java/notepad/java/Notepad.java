package notepad.java;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import notepad.java.Utils.ThemeUtils;
import notepad.java.Utils.Utils;

import javax.swing.*;
import java.awt.*;

import static notepad.java.Utils.Utils.*;

public class Notepad extends JFrame {
    private JTextArea textArea;

    public Notepad() {
        super("Notepad");
        switch (theme()) {
            case "Dark":
                FlatDarkLaf.install();
                break;
            case "Light":
                FlatLightLaf.install();
            case "Custom":
                ThemeUtils.Manager();
        }

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu configMenu = new JMenu("Config");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem newFileItem = new JMenuItem("New");
        JMenuItem openFileItem = new JMenuItem("Open");
        JMenuItem saveFileItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        JMenuItem themeConfigItem = new JMenuItem("Theme");
        JMenuItem usernameConfigItem = new JMenuItem("Username");
        JMenuItem resizeConfigItem = new JMenuItem("Resizeable");
        JMenuItem windowSizeConfigItem = new JMenuItem("WindowSize");
        JMenuItem configItem = new JMenuItem("Config");
        configMenu.add(themeConfigItem);
        configMenu.add(resizeConfigItem);
        configMenu.add(usernameConfigItem);
        configMenu.add(windowSizeConfigItem);
        configMenu.addSeparator();
        configMenu.add(configItem);


        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(configMenu);
        menuBar.add(windowMenu);

        setJMenuBar(menuBar);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        newFileItem.addActionListener(e -> textArea.setText(""));
        openFileItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                //ok
            }
        });
        saveFileItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                //son
            }
        });
        exitItem.addActionListener(e -> System.exit(0));
        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        usernameConfigItem.addActionListener(e -> openConfig("username.txt"));
        themeConfigItem.addActionListener(e -> openConfig("theme.txt"));
        resizeConfigItem.addActionListener(e -> openConfig("resizeable.txt"));
        windowSizeConfigItem.addActionListener(e -> openConfig("windowSize.txt"));
        configItem.addActionListener(e -> openConfig(""));

        Utils.setWindowSize(this);
        setVisible(true);
        Utils.resizeable(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
