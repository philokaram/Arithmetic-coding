import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class fileOperation {
    public static String read(String fileName){
        File file = new File(fileName);
        String readedFile = "";
        Scanner fileIn;
        try {
            fileIn = new Scanner(file);
            while (fileIn.hasNext()) {
                readedFile  += fileIn.nextLine();
                if(fileIn.hasNext()){
                    readedFile += "\n";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return readedFile;
    }
    public static void writeResult(double number,String fileName){
        
    }
    public static void writeOverhead(Overhead overhead,String fileName){
        
    }
}
