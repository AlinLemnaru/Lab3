package Pb1;

public class Parabola {
    private int a, b, c;

    //constructor
    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //Gettere
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    //Settere
    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setC(int c) {
        this.c = c;
    }

    //Metode
    public Punct getVarfParabola() {
        return new Punct((float)-b / (2 * a), (float)(-(b * b) + 4 * a * c) / (4 * a));
    }

    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }

    public Punct getCoordMijl(Parabola p) {
        Punct parabolaAct = getVarfParabola();
        Punct parabolaPar = p.getVarfParabola();

        float x, y;

        x = (parabolaAct.getX() + parabolaPar.getX()) / 2;
        y = (parabolaAct.getY() + parabolaPar.getY()) / 2;

        return new Punct(x, y);
    }

    public static Punct getCoordMijl2(Parabola p1, Parabola p2) {
        Punct parabola1 = p1.getVarfParabola();
        Punct parabola2 = p2.getVarfParabola();

        float x, y;

        x = (parabola1.getX() + parabola2.getX()) / 2;
        y = (parabola1.getY() + parabola2.getY()) / 2;

        return new Punct(x, y);
    }

    public double getLungime(Parabola p) {
        Punct pct1 = getVarfParabola();
        Punct pct2 = p.getVarfParabola();

        return Math.hypot((pct2.getX() - pct1.getX()), (pct2.getY() - pct1.getY()));
    }

    public static double getLungime2(Parabola p1, Parabola p2){
        Punct pct1 = p1.getVarfParabola();
        Punct pct2 = p2.getVarfParabola();

        return Math.hypot((pct2.getX() - pct1.getX()), (pct2.getY() - pct1.getY()));
    }
}
