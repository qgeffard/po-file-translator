package org.qgeff;

import org.qgeff.utils.POParser;

import java.io.File;


public class App {
    public static void main(String[] args) {
        String srcFilename = args[0];
        File srcFile = new File(srcFilename);
        POParser.translate(srcFile, new File(srcFilename.substring(0,srcFilename.length()-3)+"-fr.po"));
    }
}
