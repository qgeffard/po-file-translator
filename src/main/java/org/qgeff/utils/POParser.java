package org.qgeff.utils;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.qgeff.model.Translation;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class POParser {

    public static void translate(File srcFile, File destFile) {

        try (FileWriter writer = new FileWriter(destFile);
             BufferedWriter bw = new BufferedWriter(writer)) {
            try (FileReader reader = new FileReader(srcFile)) {
                try (BufferedReader br = new BufferedReader(reader)) {
                    br.mark(100);
                    boolean first = true;
                    while (br.readLine() != null) {
                        if (first) {
                            br.reset();
                            first = false;
                        }
                        Translation translation = getTranslation(br);
                        br.readLine();
                        bw.append(translation.toString());
                        bw.newLine();
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    private static Translation getTranslation(BufferedReader br) throws IOException {
        String ref = br.readLine();

        System.out.println("Ref : " + ref);
        Pattern r = Pattern.compile(".*\"(.*)\"");

        String input = br.readLine();
        System.out.println("Input : " + input);
        Matcher matcher = r.matcher(input);
        matcher.matches();
        String id = matcher.group(1);

        Translation translation = new Translation();
        translation.setReference(ref);
        translation.setMsgid(id);
        String msgstr = proposalTranslation(id);
        System.out.println("Target : " + msgstr + "\n");
        translation.setMsgstr(msgstr);

        return translation;
    }

    private static String proposalTranslation(String source) {
        PhantomJsDriverManager.phantomjs().setup();
        Configuration.headless = true;
        open("https://translate.google.com/?hl=fr#view=home&op=translate&sl=en&tl=fr");
        $x("//textarea[@id='source']").clear();
        $x("//textarea[@id='source']").sendKeys(source);
        return $x("//span[@class='tlid-translation translation']").getText();
    }

}
