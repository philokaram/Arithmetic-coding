public class Main {
    public static void main(String[] args) {
        LosslessCompression compressor = new ArithmeticCoding();
        System.out.println(compressor.compress("ACBA"));
    }
}
