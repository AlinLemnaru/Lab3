/*2. Să se scrie un program care citește din fișierul produse.csv informații referitoare la
produsele dintr-un magazin. Pe fiecare linie se află: denumirea produsului, preţul (număr real)
cantitatea (număr întreg), data expirării (LocalDate). Cele patru elemente sunt separate prin
caracterul "," (zahar, 1.5, 50, 2024-02-25). Pentru fiecare rând citit se va crea un obiect de tip
Produs care se va adăuga unei colecții de obiecte de tip List. Se va defini o clasă Produs care
va conține: variabile membre private corespunzătoare celor trei elemente care descriu un produs,
cel puţin un constructor, gettere si settere în funcție de necesități şi redefinirea metodei toString()
din clasa Object, metodă care va fi utilizată pentru afișarea produselor.
Să se realizeze un program care va dispune de un meniu și va implementa următoarele
cerințe:
    a) afișarea tuturor produselor din colecția List
    b) afișarea produselor expirate
    c) vânzarea unui produs, care se poate realiza doar dacă există suficientă cantitate pe stoc.
    Daca produsul ajunge la cantitate zero, se elimina din listă. În clasa Produs se va declara
    o variabilă statica încasări care se va actualiza la fiecare vânzare a unui produs, luând în
    considerare prețul produsului vândut şi cantitatea vândută .
    d) afișarea produselor cu prețul minim (pot fi mai multe cu același preț)
    e) salvarea produselor care au o cantitate mai mică decât o valoare citită de la tastatură
    într-un fișier de ieșire.*/

package Pb2;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pb2_Main {
    //Scanner pentru citirea de la tastatura
    static Scanner sc = new Scanner(System.in);

    public static void updateListaProduse(List<Produs> listaProduse) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        BufferedReader br = new BufferedReader(new FileReader("produse.csv"));
        String linie;

        while ((linie = br.readLine()) != null) {
            String[] parametrii = linie.split(",");

            listaProduse.add(new Produs(parametrii[0], Float.parseFloat(parametrii[1]),
                    Integer.parseInt(parametrii[2]), LocalDate.parse(parametrii[3], dtf)));
        }

        br.close();
    }

    public static void displayMeniu() {
        System.out.println("""
                
                a) Afisarea tuturor produselor
                b) Afisarea produselor expirate
                c) Vanzarea unui produs
                d) Afisarea produselor cu pretul minim
                e) Salvare fisier cantitate mica
                """);
    }

    public static void afisareProduse(List<Produs> listaProduse) {
        for (Produs produs : listaProduse)
            System.out.println(produs.toString());
    }

    public static void afisareProduseExpirate(List<Produs> listaProduse) {
        LocalDate dataCurenta = LocalDate.now();
        for (Produs produs : listaProduse)
            if (dataCurenta.isAfter(produs.getDataExpirarii()))
                System.out.println(produs);
    }

    public static void vanzareProduse(List<Produs> listaProduse) {
        String numeProdus;
        int cantitateVanduta;

        System.out.print("Produs de vandut: ");
        numeProdus = sc.next();
        System.out.print("Cantitate vanduta: ");
        cantitateVanduta = sc.nextInt();

        for (Produs produs : listaProduse)
            if (produs.getDenumire().equals(numeProdus)) {
                System.out.println(produs + " " + Produs.getIncasari());
                produs.vinde(cantitateVanduta);
                System.out.println(produs + " " + Produs.getIncasari());
                if (produs.getCantitate() == 0)
                    listaProduse.remove(produs);
                break;
            }
    }

    public static float getPretMin(List<Produs> listaProduse) {
        float pretMin = Float.MAX_VALUE;

        for (Produs produs : listaProduse){
            if (produs.getPret() < pretMin)
                pretMin = produs.getPret();
        }

        return pretMin;
    }

    public static void afisareProdusePretMin(List<Produs> listaProduse) {
        for(Produs produs : listaProduse)
            if(produs.getPret() == getPretMin(listaProduse))
                System.out.println(produs);
    }

    public static void salvareFisierCantMic(List<Produs> listaProduse) throws FileNotFoundException {
        PrintStream ps = new PrintStream("produse_out.csv");

        int cant;

        System.out.print("Cantitate prag max: ");
        cant = sc.nextInt();

        for(Produs produs : listaProduse)
            if (produs.getCantitate() < cant)
                ps.println(produs.toStringCsv());

        ps.close();
    }

    public static void main(String[] args) throws IOException {
        char optiune;
        List<Produs> listaProduse = new ArrayList<>();

        /*Adaugare lista*/
        updateListaProduse(listaProduse);

        /*Meniu*/
        while (true) {
            displayMeniu();
            System.out.print("\nOptiunea dvs: ");
            optiune = sc.next().charAt(0);
            switch (optiune) {
                case 'a':
                    System.out.println("\nLista produse:");
                    afisareProduse(listaProduse);
                    break;
                case 'b':
                    System.out.println("\nLista produse filtru -> expirate:");
                    afisareProduseExpirate(listaProduse);
                    break;
                case 'c':
                    System.out.println("\n<VANZARE>");
                    vanzareProduse(listaProduse);
                    break;
                case 'd':
                    System.out.println("\nLista produse filtru -> pret minim:");
                    afisareProdusePretMin(listaProduse);
                    break;
                case 'e':
                    System.out.println("\n<SALVARE FISIER>");
                    salvareFisierCantMic(listaProduse);
                    break;
                case 'q':
                    System.out.println("\n\nIesire...\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\nOptiune invalida!\n");
                    break;
            }
        }
    }
}
