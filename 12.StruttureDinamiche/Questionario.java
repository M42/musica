import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.InputStreamReaer;
import java.io.IOException;

public class Questionario {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> risposte = new LinkedHashMap<>();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        System.out.println("Inizio del questionario:");

        try {
            while ((line = buffer.readLine()) != null) {
                line = line.toUpperCase();
                int val = 1;
                if (risposte.containsKey(line)) {
                    val = risposte.get(line);
                    val++;
                }
                risposte.put(line,val);
            }
        }
        catch (IOException e) {
            System.out.println("Questionario finito con errori (!)");
        }

        System.out.println("\nRisposte per il questionario:");
        for (Entry<String,Integer> pair : risposte.entrySet()) {
            System.out.println(pair.getKey() + " > " + pair.getValue());
        }
    }
}
