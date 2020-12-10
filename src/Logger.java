import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {
    static void log(String data){
        String pathFile = "log/log.txt";
        Path path = Paths.get("log");
        if(!Files.exists(path)){
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            File file = new File(pathFile);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            File file = new File(pathFile);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data + "\n");
            bufferedWriter.close();
            fileWriter.close();
//            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
