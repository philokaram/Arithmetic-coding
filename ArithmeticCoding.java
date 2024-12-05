import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
public class ArithmeticCoding implements LosslessCompression{
    public Overhead overhead = new Overhead();
    public Overhead getOverHead(){
        return overhead;
    }

    @Override
    public double compress(String originalText) {
        //1- calculate the probabilities
        //Map<Character,Double> probabilities  = Calculation.CalculateProbabilities(originalText);
        Map<Character,Double> probabilities  = new HashMap();
        probabilities.put('A',0.8);
        probabilities.put('B',0.02);
        probabilities.put('C',0.18);
        //2- make array of ranges
        RangeNode[] ranges = new RangeNode[probabilities.size()];
        int index = 0;
        double highRange = 0.0;
        for (Entry<Character,Double> entry : probabilities.entrySet()) {
            ranges[index] = new RangeNode(entry.getKey(),highRange,highRange+entry.getValue());
            highRange += entry.getValue(); 
            index++;
        }
        // //3- compress
        double low = 0;
        double high = 0;
        double range = 0;
        boolean isFirst = true;
        for (Character ch : originalText.toCharArray()) {
            for(int i = 0; i < ranges.length ; i++){
                if(ch == ranges[i].ch){
                    if(isFirst){
                        low = ranges[i].lowRange;
                        high = ranges[i].highRange;
                        isFirst=false;
                    }
                    else{
                        range = high - low;
                        high = low + (range * ranges[i].highRange);
                        low = low + (range * ranges[i].lowRange);
                    }
                    break;
                }
            }
        }
        //4- store overhead
        overhead.probabilities = probabilities;
        overhead.textSize = originalText.length();
        //5- select number from last range
        return (high+low)/2;
    }

    @Override
    public String deCompress(String decodedText) {
        return "";
    }

    
}