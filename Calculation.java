import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Calculation {
    public static Map<Character,Double>  CalculateProbabilities(String text){
        Map<Character,Double> probabilities = new HashMap<>() ;
        for (Character ch : text.toCharArray()) {
            if(probabilities.containsKey(ch)){
                probabilities.put(ch, probabilities.get(ch)+1);
            }
            else{
                probabilities.put(ch,1.0);
            }
        }
        int size = text.length();
        for (Entry<Character,Double> entry : probabilities.entrySet()) {
            probabilities.put(entry.getKey(), entry.getValue()/size);
        }
        return probabilities;
    }
}
