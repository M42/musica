/**
 * Midi2It
 * Trasforma il valore di una nota midi al suo nome in italiano.
 * @author M42
 */
public class midi2it {
    public static void main(String[] args) {
        int midi = Integer.parseInt(args[0]);
        System.out.println(midi2it(midi));
    }

    public static String midi2it(int midi) {
        int nomenum = midi%12;
        int ottava = midi/12 - 1;

        return (nome(nomenum) + ottava);
    }

    public static String nome(int nomenum) {
        switch (nomenum) {
        case 0:  return "Do";
        case 1:  return "Do#";
        case 2:  return "Re";
        case 3:  return "Mib";
        case 4:  return "Mi";
        case 5:  return "Fa";
        case 6:  return "Fa#";
        case 7:  return "Sol";
        case 8:  return "Sol#";
        case 9:  return "La";
        case 10: return "Sib";
        case 11: return "Si";
        }

        return "??";
    }
}
