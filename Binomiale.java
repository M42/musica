/**
 * Binomiale.
 * Rappresentazione biniomiale dell'altezza.
 * @author M42
 */

public class Binomiale {
    int pc;
    int nc;

    public Binomiale (int pc, int nc) {
        this.pc = pc;
        this.nc = nc;
    }

    // Rappresentazione binomiale su singolo intero
    // Usiamo base dieci per facilitare la lettura
    public int br() {
        return (pc * 10) + nc;
    }
    
    // Continuous Binomial Representation
    // Usiamo base dieci per facilitare la lettura
    public int cbr(int oct) {
        return (oct*1000) + (10*pc) + nc;
    } 

    // ALGEBRA
    // Inversione di un intervallo
    public Binomiale inversa() {
        return new Binomiale((12-pc)%12, (7-nc)%7);
    }

    // Suma due note in rapresentazione binomiale
    public Binomiale suma(Binomiale altro) {
        int newpc = mod(altro.pc + this.pc, 12);
        int newnc = mod(altro.nc + this.nc, 12);
        return new Binomiale(newpc, newnc);
    }

    // Prodotto
    public Binomiale prodotto(Binomalie altro) {
        int newpc = mod(altro.pc * this.pc, 12);
        int newnc = mod(altro.nc * this.nc, 12);
        return new Binomiale(newpc, newnc);
    }

    // Modulus
    private int mod(int x, int n) {
        return ((x%n)+n)%n;
    }

    // Continous Name Code
    public int cnc(int oct) {
        return oct*7+12; 
    }

    public String nome() {
        String nomenc = "?";
        String nomepc = "?";
        int natpc = 0;

        switch (nc) {
        case 0: nomenc = "Do";  natpc = 0;  break;
        case 1: nomenc = "Re";  natpc = 2;  break;
        case 2: nomenc = "Mi";  natpc = 4;  break;
        case 3: nomenc = "Fa";  natpc = 5;  break;
        case 4: nomenc = "Sol"; natpc = 7;  break;
        case 5: nomenc = "La";  natpc = 9;  break;
        case 6: nomenc = "Si";  natpc = 11; break;
        }

        switch (pc - natpc) {
        case -2: nomepc = "bb"; break;
        case -1: nomepc = "b";  break;
        case  0: nomepc = "";   break;
        case  1: nomepc = "#";  break;
        case  2: nomepc = "x";  break;
        }

        return nomenc + nomepc;
    }

    public static void main(String[] args) {
        int pc = Integer.parseInt(args[0]);
        int nc = Integer.parseInt(args[1]);
        System.out.println(new Binomiale(pc,nc).nome());
    }

};
