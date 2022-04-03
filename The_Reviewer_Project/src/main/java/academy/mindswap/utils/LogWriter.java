package academy.mindswap.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LogWriter {

    public static void writeToFile(String output){

        FileOutputStream fileToWrite;
        try {
            fileToWrite = new FileOutputStream("./src/main/resources/log.txt",true);
            fileToWrite.write(output.getBytes(StandardCharsets.UTF_8));
            fileToWrite.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
