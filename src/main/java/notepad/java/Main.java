package notepad.java;

import notepad.java.Utils.Utils;

import static notepad.java.Utils.Utils.isNew;

public class Main {
    public static void main(String[] args) {
        if(!isNew()){
            new Notepad();
        }
        else {
            Utils.init();
        }
    }
}