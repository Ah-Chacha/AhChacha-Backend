package AhChacha.Backend.controller;

import java.io.File;
import java.io.FileFilter;

public class tmp {
    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter(){
            public boolean accept(File file){
                return file.isHidden();
            }
        });
    }
}
