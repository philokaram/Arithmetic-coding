import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
public class ArithmeticCoding implements LosslessCompression{

    @Override
    public double compress(String originalText) {
        //1- calculate the probabilities
        Map<Character,Double> probabilities  = Calculation.CalculateProbabilities(originalText);
        //2- make array of ranges
        RangeNode[] ranges = new RangeNode[probabilities.size()];
        int index = 0;
        double highRange = 0.0;
        for (Entry<Character,Double> entry : probabilities.entrySet()) {
            ranges[index] = new RangeNode(entry.getKey(),highRange,highRange+entry.getValue());
            highRange += entry.getValue(); 
        }
        //3- compress
        double low = 0;
        double high = 0;
        double range = 0;
        for (Character ch : originalText.toCharArray()) {
            for(int i = 0; i < ranges.size ; i++){
                if(ch == ranges[i].ch){
                    range = high - low;
                    high = low + (range * ranges[i].highRange);
                    low = low + (range * ranges[i].lowRange);
                    break;
                }
            }
        }
        //4- select number from last range
        return (high-low)/2;
    }

    @Override
    public String deCompress(String decodedText) {
        return "";
    }

    
}