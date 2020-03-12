package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTxt {

    private BufferedWriter writeOut;

    public void writeFile(String file, String pathOutput, String name) {
        try {
            writeOut = new BufferedWriter(new FileWriter(pathOutput + name));
            writeOut.write(file);
            writeOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
