package CSQuizGrader;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class OCRstatsLib {

    File unFormattedOCRInput;
    String filePath;

    public OCRstatsLib(String filepath) {
        this.filePath = filepath;
        unFormattedOCRInput = new File(filepath);
    }

    public ArrayList<String> formattedCode(String string) {
        ArrayList<String> lines = new ArrayList<>();
        String counter = "";
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ';' && string.charAt(i) != '{' && string.charAt(i) != '}') {
                counter += string.charAt(i);
            } else {
                lines.add(counter);
                counter = "";
            }
        }
        return lines;
    }

    private String getUnformattedData() {
        String output = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = br.readLine()) != null) {
                output += str;
            }
        } catch (Exception e) {
            System.err.println("File not found... returning null");
        }
        return output;
    }

    public static void main(String[] args)// main method executes the code inside
    {
        JFrame window = new JFrame("CS Grader");
        window.setBounds(300, 300, 200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Collections panel = new Collections();
        panel.setBackground(Color.WHITE);
        Container c = window.getContentPane();
        c.add(panel);
        window.setVisible(true);
    }
}
