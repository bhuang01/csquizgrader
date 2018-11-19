package com.chillyfacts.com;

import java.io.PrintWriter;

public class my_main {
    public static void main(String[] args) {
        //File paths vary on computer
        String input_file = "C:\\Users\\Wang\\testfiles\\input.png";
        String output_file = "C:\\Users\\Wang\\testfiles\\output";
        String tesseract_install_path = "C:\\Tesseract-OCR\\tesseract";
        String[] command =
                {
                        "cmd",
                };
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();

            PrintWriter stdin = new PrintWriter(p.getOutputStream());

            stdin.println("\"" + tesseract_install_path+ "\" \""+input_file+"\" \"" + output_file+ "\"");
            //stdin.println("\"" + tesseract_install_path + "\" \"" + input_file + "\" \"" + output_file + "\" -l eng");
            stdin.close();
            p.waitFor();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(Read_File.read_a_file(output_file + ".txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
