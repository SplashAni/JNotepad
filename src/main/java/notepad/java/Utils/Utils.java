package notepad.java.Utils;

import com.formdev.flatlaf.FlatDarkLaf;
import notepad.java.Notepad;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static java.io.File.separator;

public class Utils {
    public static String mainPath = System.getProperty("user.home") + separator + "JNotepad";
    private static File mainFile = new File(mainPath);

    public static String configPath = mainPath + separator + "config";
    public static File configFile = new File(configPath);


    public static void config(boolean delete) {
        if (!mainFile.exists() && !delete) {
            mainFile.mkdir();
        }
        else {
            mainFile.delete();
        }
    }
    private static void createConfig(){
        if(!configFile.exists()){
            configFile.mkdir();
        }
    }
    public static boolean isNew(){
        if(mainFile.exists()){
            return false;
        }
        return true;
    }
    public static void write(String path,String content){
        FileWriter writer = null;
        try {
            writer = new FileWriter(mainPath + separator + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(String path) {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;

        try {
            File file = new File(path);
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                if (reader.ready()) {
                    content.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }

    public static void init(){ // basically setup
        FlatDarkLaf.install();
        config(false);
        createConfig();

        // name
        String username = JOptionPane.showInputDialog("Whats your name?", "SplashAni");
        write(configFile +"username.txt", username);

        // window size
        String w = JOptionPane.showInputDialog(null, "Select the editors width", "800");
        int width = Integer.parseInt(w);
        String h = JOptionPane.showInputDialog(null, "Select the editors height", "600");
        int height = Integer.parseInt(h);
        write(configPath + separator + "windowSize.txt",width + "," + height);


        //resizable
        int response = JOptionPane.showConfirmDialog(null, "Should the editors window be resizable", "Boolean", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            write(configPath + separator + "resizeable.txt","true");
        } else {
            write(configPath + separator + "resizeable.txt", "false");
        }

        //theme
        String[] themes = {"Dark", "Light", "Custom"};
        String theme = (String) JOptionPane.showInputDialog(null, "Select an theme:", "Theme", JOptionPane.QUESTION_MESSAGE, null, themes, themes[0]);
        if(theme != null) { // i love java symbols :yum:
            write( "theme.txt", theme);
            write(configPath,"800,600");
            new Notepad();
        } else {
            config(true);
        }
    }
    public static String theme(){
        String choosenTheme = read(configFile+ separator + "theme.txt");
        return choosenTheme;
    }
    public static void setWindowSize(JFrame window){
        File dir = new File(configPath + separator + "windowSize.txt");
        String open = read(String.valueOf(dir));
        String[] splitValues = open.split(",");
        int width = Integer.parseInt(splitValues[0]);
        int height = Integer.parseInt(splitValues[1]);
        window.setSize(width, height);
    }
    public static void resizeable(JFrame window){
        switch (read(configPath + separator + "resizeable.txt")){
            case "true":
                window.setResizable(true);
            case "false":
                window.setResizable(false);
        }
    }
    public static void openConfig(String which) {
        File open = new File(configFile + separator + which);
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(open);
            } else {
                JOptionPane.showMessageDialog(null, "Not supported open, " + open + "instead", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
