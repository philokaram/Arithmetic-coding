import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class fileOperation {
    public static String readTxtFile(String fileName){
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
    public static double readDatFile(String fileName){
        double num = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            num = dis.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
    public static void writeResult(double number,String fileName){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName +".dat"))) {
            dos.writeDouble(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeOverhead(Overhead overhead,String fileName){
        
    }
}
