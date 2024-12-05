import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map.Entry;
public class fileOperation {
    public static String readOriginalFile(String fileName){ //  read from txt file
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
    public static String readcompressedValue(String fileName){ //read from dat file
        double num = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            num = dis.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  Double.toString(num);
    }
    public static Overhead readOverhead(String fileName){ //read overhead from txt file
        Overhead overhead = new Overhead();
        File file = new File(fileName);
        Scanner fileIn;
        try {
            fileIn = new Scanner(file);
            String size = fileIn.nextLine();
            overhead.textSize = Integer.parseInt(size);
        //     String nextLine = "";
        //     while (fileIn.hasNext()) {
        //         nextLine = fileIn.nextLine();
        //         String[] arr = nextLine.spilt(" (?=[a-zA-Z])");
        //         if(arr[0].charAt(0) == '`'){
        //             overhead.probabilities.put('\n', Double.parseDouble(arr[1]));
        //         }
        //         overhead.probabilities.put(arr[0].charAt(0), Double.parseDouble(arr[1]));
          //  }
        } catch (FileNotFoundException e) {
            e.printStackTrace();}
        return overhead;
    }
    public static void WriteOutputFile(String output,String fileName){ //  Write from txt file
        File file = new File(fileName+".txt");
        try {
            FileWriter fileOut = new FileWriter(file);
            fileOut.write(output);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public static void writeCompressedValue(double number,String fileName){ // write value in dat file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName +".dat"))) {
            dos.writeDouble(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeOverhead(Overhead overhead,String fileName){ //write overhead in txt file
        File file = new File(fileName+"_overhead.txt");
        try {
            FileWriter fileOut = new FileWriter(file);
            fileOut.write(Integer.toString(overhead.textSize));
            System.out.println(overhead.textSize);
            fileOut.write("\n");
            if(overhead.probabilities.containsKey('\n')){
                overhead.probabilities.put('`', overhead.probabilities.get('\n'));
                overhead.probabilities.remove('\n');
            }
            for (Entry<Character,Double> entry : overhead.probabilities.entrySet()) {
                fileOut.write(entry.getKey() + " "+ entry.getValue());
                fileOut.write("\n");
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
