import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("    ________________________________________    ");
        System.out.println("   |                                        |   ");
        System.out.println("   |    Floating Point Arithmetic Coding    |   ");
        System.out.println("   |________________________________________|   \n");
        LosslessCompression compressor = new ArithmeticCoding();
        Scanner input = new Scanner(System.in);
        int choice;
        String inputFileName;
        String overheadFileName;
        String outputFileName;
        String orignalText;
        String outputText;
        double compressedValue;
        String compressedNum;
        Overhead overhead;
        boolean isRunning = true;
        while(isRunning){
            menu();
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Name of a file to compress it:  ");
                    inputFileName =input.next();
                    orignalText = fileOperation.readOriginalFile(inputFileName);
                    compressedValue = compressor.compress(orignalText);
                    System.out.println("compression is done.");
                    System.out.print("Enter Name of a compressed file:  ");
                    outputFileName =input.next();
                    fileOperation.writeCompressedValue(compressedValue, outputFileName);                    
                    fileOperation.writeOverhead(((ArithmeticCoding)compressor).getOverHead(), outputFileName);                    
                    break;
                case 2:
                System.out.print("Enter Name of a file to decompress it:  ");
                inputFileName =input.next();
                compressedNum = fileOperation.readcompressedValue(inputFileName);
                System.out.print("Enter Name of an overhead file:  ");
                overheadFileName =input.next();
                overhead = fileOperation.readOverhead(overheadFileName); //--> not complete
                ((ArithmeticCoding) compressor).setOverHead(overhead);
                outputText = compressor.deCompress(compressedNum);
                System.out.println("decompression is done.");
                System.out.print("Enter Name of a output file:  ");
                outputFileName =input.next();
                fileOperation.WriteOutputFile(outputText,outputFileName);
                    break;
                case 0:
                isRunning = false;
                    break;
                default:
                System.out.println("invalid option, Please try again.");
                    break;
            }
        }
        input.close();
    }
    static void menu(){
        System.out.println("Do you want:");
        System.out.println("   1- Compress");
        System.out.println("   2- Decompress");
        System.out.println("   0- Exit");
        System.out.print("Enter your choice: ");
    }
}
