public interface LosslessCompression{
double compress(String originalText);
String deCompress(String decodedText);
}