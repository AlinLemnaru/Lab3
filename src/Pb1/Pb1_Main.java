package Pb1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pb1_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        String line;

        List<Parabola> parabolaList = new ArrayList<Parabola>();

        while ((line = br.readLine()) != null) {
            String[] parametrii = new String[3];
            parametrii = line.split(" ");

            parabolaList.add(new Parabola(Integer.parseInt(parametrii[0]), Integer.parseInt(parametrii[1]), Integer.parseInt(parametrii[2])));
        }

        System.out.println("\nParabole:");
        for (Parabola parabola : parabolaList) {
            System.out.println(parabola);
        }

        System.out.println("\nParabole + varf:");
        for (Parabola parabola : parabolaList) {
            System.out.println(parabola + " varf: " + parabola.getVarfParabola());
        }

        System.out.println("\nMijl p1 + p2:\n" + parabolaList.get(0).getCoordMijl(parabolaList.get(1)));
        System.out.println("\nMijl p3 + p4:\n" + parabolaList.get(2).getLungime(parabolaList.get(3)));
    }
}
