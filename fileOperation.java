import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
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
    public static Overhead readOverhead(String fileName) { // read overhead from txt file
        Overhead overhead = new Overhead();
        overhead.probabilities = new HashMap<>(); // Initialize the map
        File file = new File(fileName);
        Scanner fileIn;
        try {
            fileIn = new Scanner(file);
            if (fileIn.hasNextLine()) {
                String size = fileIn.nextLine();
                overhead.textSize = Integer.parseInt(size);
            }
            while (fileIn.hasNextLine()) {
                String nextLine = fileIn.nextLine();
                String[] arr = nextLine.split(" ", 2); // Split into two parts only
                if (arr.length == 2) {
                    char key;
                    if(arr[0].equals("")){
                        key = ' ';
                    }
                    else{
                        key = arr[0].charAt(0);
                    }
                    if (key == '`') {
                        key = '\n'; // Handle newline character
                    }
                    double probability = Double.parseDouble(arr[1]);
                    overhead.probabilities.put(key, probability);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (Exception e) {
            System.err.println("Error reading overhead: " + e.getMessage());
        }
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
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName +".bin"))) {
            dos.writeDouble(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeOverhead(Overhead overhead, String fileName) { // write overhead in txt file
        File file = new File(fileName + "_overhead.txt");
        try (FileWriter fileOut = new FileWriter(file)) {
            fileOut.write(Integer.toString(overhead.textSize));
            fileOut.write("\n");
            if (overhead.probabilities.containsKey('\n')) {
                overhead.probabilities.put('`', overhead.probabilities.get('\n'));
                overhead.probabilities.remove('\n');
            }
            for (Entry<Character, Double> entry : overhead.probabilities.entrySet()) {
                fileOut.write(entry.getKey() + " " + entry.getValue());
                fileOut.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing overhead: " + e.getMessage());
        }
    }
}
