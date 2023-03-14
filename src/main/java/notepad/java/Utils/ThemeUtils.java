package notepad.java.Utils;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;


import static java.io.File.separator;
import static notepad.java.Utils.Utils.*;
import static notepad.java.Utils.Utils.mainPath;

public class ThemeUtils extends JFrame {

    private String mainPath =  Utils.mainPath + separator + "custom-themes";

    private String tempTheme = mainPath + separator;


    private File themeFile = new File(mainPath);

    public static void Manager(JFrame window, JTextArea textArea) {
        textArea.setText("testing");
    }

    public void themeCreator() {
        setTitle("Theme Creator");
        FlatMacLightLaf.install();
        createThemeConfig();
        refresh();
        setLayout(new GridBagLayout());

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

        JLabel l3 = new JLabel("Font Size");
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(saveButton);
        JButton close = new JButton("close");
        close.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(close);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(buttonPanel, gbc);

        colorButton.addActionListener(e -> chooseColour());
        fontButton.addActionListener(e -> chooseFont());
        sizeButton.addActionListener(e -> chooseFontSize());
        iconButton.addActionListener(e -> chooseIcon());
        saveButton.addActionListener(e -> saving());
        close.addActionListener(e -> this.setVisible(false));

        setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createThemeConfig() {
        if (!themeFile.exists()) {
            themeFile.mkdir();
        }
    }
    private void refresh(){
        File tempThemeFile = new File(mainPath,"temp.theme");
        if(tempThemeFile.exists()){
            tempThemeFile.delete();
            tempThemeFile.mkdir();
        }
        else {
            tempThemeFile.mkdir();
        }
    }

    private void chooseColour() {
        Color backgroundColor = JColorChooser.showDialog(null, "Choose a color", Color.LIGHT_GRAY);
        if (backgroundColor != null) {
            write(tempTheme + "", String.valueOf(backgroundColor), 1);
        }
    }

    private void chooseFont() {
        GraphicsEnvironment beepboop = GraphicsEnvironment.getLocalGraphicsEnvironment(); // all credits due to some random on stackoveflow for this
        Font[] allFonts = beepboop.getAllFonts();
        String[] fontNames = new String[allFonts.length];

        for (int i = 0; i < allFonts.length; i++) {
            fontNames[i] = allFonts[i].getFontName();
        }

        String selectedFont = (String) JOptionPane.showInputDialog(null, "Choose a font:", "Font", JOptionPane.QUESTION_MESSAGE, null, fontNames, fontNames[0]);

        if (selectedFont != null) {
            write(tempTheme, selectedFont, 2);
        }
    }

    public void chooseFontSize() {
        Integer[] ints = new Integer[16];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        int defaultOption = 6;
        JComboBox<Integer> comboBox = new JComboBox<>(ints);
        comboBox.setSelectedItem(defaultOption);
        JOptionPane.showMessageDialog(null, comboBox, "Font size", JOptionPane.QUESTION_MESSAGE);
        int selectedSize = (int) comboBox.getSelectedItem();
        write(tempTheme, String.valueOf(selectedSize), 3);
    }

    private void chooseIcon() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Types", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File imgPath = fileChooser.getSelectedFile();
            write(tempTheme, String.valueOf(imgPath), 4);
        }
    }

    private void saving() {
        Component[] components = getContentPane().getComponents();
        for (Component c : components) {
            c.setVisible(false);
        }

        GridBagConstraints c = new GridBagConstraints();

        JLabel[] labels = new JLabel[4];
        labels[0] = new JLabel("Color: " + read(tempTheme ,"d", 1));
        labels[1] = new JLabel("Font: " + read(tempTheme,2));
        labels[2] = new JLabel("Font Size: " + read(tempTheme,3));
        labels[3] = new JLabel("Icon: " + read(tempTheme,4));

        Font font = new Font("Arial", Font.BOLD, 18);
        for (JLabel label : labels) {
            label.setFont(font);
        }

        c.insets = new Insets(0, 0, 10, 0);

        c.gridx = 0;
        c.gridy = 1;

        add(labels[0], c);

        c.gridy = 2;

        add(labels[1], c);

        c.gridy = 3;

        add(labels[2], c);

        c.gridy = 4;

        add(labels[3], c);

        JTextField iconTextField = new JTextField();

        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;

        add(iconTextField, c);

        JButton confirm = new JButton("Confirm");

        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(20, 0, 0, 0);

        add(confirm, c);
        setSize(300, 300);
        setVisible(true);
    }
}