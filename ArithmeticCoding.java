import java.util.Map;
import java.util.Map.Entry;
public class ArithmeticCoding implements LosslessCompression{
    public Overhead overhead = new Overhead();
    public Overhead getOverHead(){
        return overhead;
    }
    public void setOverHead(Overhead overhead){
        this.overhead = overhead;
    }

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
        System.out.println("compressed value: "+((high+low)/2));
        return (high+low)/2;
    }

    @Override
    public String deCompress(String compressedValueStr) {
        double compressedValue = Double.parseDouble(compressedValueStr);
        StringBuilder decodedText = new StringBuilder();
        
        // Reconstruct the range nodes using the stored probabilities
        RangeNode[] ranges = new RangeNode[overhead.probabilities.size()];
        int index = 0;
        double highRange = 0.0;
        for (Entry<Character, Double> entry : overhead.probabilities.entrySet()) {
            ranges[index] = new RangeNode(entry.getKey(), highRange, highRange + entry.getValue());
            highRange += entry.getValue();
            index++;
        }
        // Decode the text
        double low = 0.0;
        double high = 1.0;
        double range;
        
        for (int i = 0; i < overhead.textSize; i++) {
            range = high - low;
            for (RangeNode rangeNode : ranges) {
                double rangeLow = low + range * rangeNode.lowRange;
                double rangeHigh = low + range * rangeNode.highRange;
                if (compressedValue >= rangeLow && compressedValue < rangeHigh) {
                    decodedText.append(rangeNode.ch);
                    low = rangeLow;
                    high = rangeHigh;
                    break;
                }
            }
        }
        
        return decodedText.toString();
    }

    
}