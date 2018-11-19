package CSQuizGrader;

import javax.swing.*;
import javax.tools.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Compiler {

    private final String FILE_NAME;
    private final String separator = File.separator;
    private final JavaCompiler COMPILER = ToolProvider.getSystemJavaCompiler();
    private final File JAVA_FILE;
    private final File TEXT_FILE;

    public Compiler(String FILE_NAME) throws IOException {
       this.FILE_NAME = FILE_NAME;
       File file = new File("src" + separator + "TextSources" + separator + FILE_NAME + ".txt");
       file = changeExtension(file, ".java");
       this.JAVA_FILE = file;
       this.TEXT_FILE = new File("src" + separator + "TextSources" + separator + FILE_NAME + ".txt");
       if (!file.exists()) {
           file.createNewFile();
       }
    }

    public void compile() throws IOException, FileNotFoundException {
        copyContentsFromFileToFile(TEXT_FILE, JAVA_FILE);
        int compilationResult =	COMPILER.run(null, null, null, JAVA_FILE.getAbsolutePath());
        if (compilationResult == 0) {
            System.out.println("error count: 0");
        }
    }

    private void copyContentsFromFileToFile(File file1, File file2) {
        try {
            ArrayList<String> data = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file1));

            String str;
            while ((str = br.readLine()) != null) {
                data.add(str);
            }

            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file2));

            for (String line : data) {
                bw.write(line + "\n");
            }

            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File changeExtension(File f, String newExtension) {
        int i = f.getName().lastIndexOf('.');
        String name = f.getName().substring(0, i);
        return new File(f.getParent() + "/" + name + newExtension);
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
