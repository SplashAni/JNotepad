package notepad.java.Utils;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.io.*;

import static java.io.File.separator;

public class Utils {
    public static String config = System.getProperty("user.home") + separator + "JNotepad";
    private static File dir = new File(config);

    public static void config(boolean delete) {
        if (!dir.exists() && !delete) {
            dir.mkdir();
        }
        else {
            dir.delete();
        }
    }
    private static void createConfig(){
        File configDir = new File(config + separator + "config");
        if(!configDir.exists()){
            configDir.mkdir();
        }
    }
    public static boolean isNew(){
        if(dir.exists()){
            return false;
        }
        return true;
    }
    public static void write(String path,String content){
        FileWriter writer = null;
        try {
            writer = new FileWriter(config + separator + path);
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


    public static void init(){
        FlatDarkLaf.install();
        config(false);
        createConfig();

        String username = JOptionPane.showInputDialog("Whats your name?", "SplashAni");
        write("config" + separator + "username.txt", username);

        String[] themes = {"Dark", "Light", "Custom"};
        String theme = (String) JOptionPane.showInputDialog(null, "Select an theme:", "Theme", JOptionPane.QUESTION_MESSAGE, null, themes, themes[0]);

        if(theme != null) { // i love java symbols :yum:
            write("config" + separator + "theme.txt", theme);
        } else {
            config(true);
        }
    }
    public static String theme(){
        String choosenTheme = read(config + separator + "config" + separator + "theme.txt");
        return choosenTheme;
    }
}
