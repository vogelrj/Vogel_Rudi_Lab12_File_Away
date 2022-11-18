import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class File_Away {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        int lineCount = 0;
        int charCount = 0;
        int wordCount = 0;
        String[] words = new String[0];
        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while (reader.ready()) {
                    rec = reader.readLine();
                    lines.add(rec);
                    lineCount++;
                    words = rec.split(" ");
                    wordCount = wordCount + words.length;
                    charCount = charCount + rec.length();
                }
                System.out.println("\nData file " + selectedFile.getName() + " Read!");
                System.out.println("\nThis file has " + lineCount + " lines, " + charCount + " characters, and " + wordCount + " words.");
                reader.close();
            }
            else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
