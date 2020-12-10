import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class FolderSize {
    class PathSize{
        String sizeFormat;
        double size;
        public PathSize(String sizeFormat, double size) {
            this.sizeFormat = sizeFormat;
            this.size = size;
        }

        public String getSizeFormat() {
            return sizeFormat;
        }

        public void setSizeFormat(String sizeFormat) {
            this.sizeFormat = sizeFormat;
        }

        public double getSize() {
            DecimalFormat decimalFormat = new DecimalFormat("####.#");
            return Double.parseDouble(decimalFormat.format(size));
        }

        public void setSize(double size) {
            this.size = size;
        }
    }


    private long getSize(File folder) {
        long size = 0;
        File[] folderFiles = folder.listFiles();
        for (File s : folderFiles) {
            if(s.isFile()){
                size+=s.length();
            }
            else if(s.isDirectory()){
                size+= getSize(s);
            }
        }
        return size;
    }

    public PathSize formatToReadable(File folder){
        String[] sizeFormats = {"B", "MB", "KB","GB", "TB"};
        double size = getSize(folder);
        int i = 0;
        while(size > 1024 && i < 4){
            i++;
            size /= 1024;
        }
        return new PathSize(sizeFormats[i], size);
    }


}

class FolderSizeLoader{
    public static void main(String[] args) {
        Logger.log("Начало выполнения FolderSize.");
        System.out.println("Введите путь до папки");
        Logger.log("Введите путь до папки");
        Scanner in = new Scanner(System.in);
        String way = in.next();
        Logger.log(way);
        File file = new File(way);
        if(file.exists()){
            FolderSize folderSize = new FolderSize();
            FolderSize.PathSize pathSize = folderSize.formatToReadable(file);
            System.out.println("Размер папки " + file.getAbsolutePath() + " составляет " + pathSize.getSize()+pathSize.getSizeFormat());
            Logger.log("Размер папки " + file.getAbsolutePath() + " составляет " + pathSize.getSize()+pathSize.getSizeFormat());
        }
        else{
            System.out.println("неправильный ввод");
            Logger.log("Неправильный ввод.");
        }
            Logger.log("Конец выполнения программы FolderSize.");
    }
}