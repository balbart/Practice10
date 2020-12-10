import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class CopyPaster {
    private static void copySwitcher(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            copyRecur(src, dest);
        }
        else{
            if(!dest.exists()) Files.copy(src.toPath(), dest.toPath());

        }
    }

    private static void copyRecur(File src, File dest) throws IOException {
        if(!dest.exists()){
            dest.mkdir();
        }
        for(String child: src.list()){
            copySwitcher(new File(src,child), new File(dest, child));
        }
    }

    private static String parseName(String s) {
        String[] arr = s.split("\\\\");
        return arr[arr.length - 1];
    }

    public static void copyDirectory(String srcStr, String destStr) throws IOException {
        if(new File(srcStr).exists() && new File(destStr).exists()){
            File realTarget = new File(destStr + "\\" + parseName(srcStr));
            copySwitcher(new File(srcStr), realTarget);
        }
        else{
            Logger.log("Неправильный ввод.");
        }
    }
}


class CopyPasterLoader{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Logger.log("Начало выполнения CopyPaster.");
        System.out.println("Путь к папке, КОТОРУЮ нужно скопировать:");
        Logger.log("Путь к папке, которую нужно скопировать:");
        String sourceStr = in.next();
        Logger.log(sourceStr);
        System.out.println("Путь к папке, В которую нужно скопировать:");
        Logger.log("Путь к папке, В которую нужно скопировать:");
        String destinationStr = in.next();
        Logger.log(destinationStr);
        try {
            CopyPaster.copyDirectory(sourceStr, destinationStr);
            Logger.log("Копирование успешно.");
        } catch (IOException e) {
            e.printStackTrace();
            Logger.log("Что-то пошло не так");
        }
        Logger.log("Конец выполнения программы CopyPaster.");
    }
}