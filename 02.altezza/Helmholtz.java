package helmholtz;

/**
 * Esercizio 1.
 * @author Luca
 * @author m42
 */
public class Helmholtz {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Numero di argomenti errato");
            System.exit(1);
        }

        String p = args[0].toLowerCase();

        if (!"do".equals(p) && !"c".equals(p) 
            && !"re".equals(p) && !"d".equals(p)
            && !"mi".equals(p) && !"e".equals(p)
            && !"fa".equals(p) && !"f".equals(p)
            && !"sol".equals(p) && !"g".equals(p)
            && !"la".equals(p) && !"a".equals(p)
            && !"si".equals(p) && !"b".equals(p)) {
            System.out.println("Nome della nota errato: " + p);
            System.exit(1);
        }

        int octave = 0; // va dichiarata fuori dal blocco try e inizializzata!!!

        try {
            octave = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e) {
            System.out.println("Numero dell'ottava errato: " + p);
            System.exit(1);
        }

        if (octave >= 3) {
            System.out.print("La nota " + args[0] + args[1] + " convertita in codice di Helmholtz risulta " + p);
            for (int i = octave; i > 3 ; i--)
                System.out.print("'");
            System.out.println();
        }
        else {
            System.out.print("La nota " + args[0] + args[1] + " convertita in codice di Helmholtz risulta " + p.toUpperCase());
            for (int i = 1; i >= octave; i--)
                System.out.print(",");
            System.out.println();
        }

        System.exit(0);
    }
}
