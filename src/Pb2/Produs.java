package Pb2;

import java.time.LocalDate;

public class Produs {
    private String denumire;
    private float pret;
    private int cantitate;
    private LocalDate dataExpirarii;
    private static float incasari = 0;

    //conctructor
    public Produs(String denumire, float pret, int cantitate, LocalDate dataExpirarii) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirarii = dataExpirarii;
    }

    //Getter
    public String getDenumire() {
        return denumire;
    }

    public float getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public LocalDate getDataExpirarii() {
        return dataExpirarii;
    }

    public static float getIncasari() {
        return incasari;
    }

    //Setter
    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setDataExpirarii(LocalDate dataExpirarii) {
        this.dataExpirarii = dataExpirarii;
    }

    @Override
    public String toString() {
        return denumire + "\t" + pret + "\t" + cantitate + "\t" + dataExpirarii;
    }

    //Metoda vanzare
    public void vinde(int cantitateVantuta) {
        if(cantitateVantuta <= cantitate) {
            incasari += pret * cantitateVantuta;
            cantitate -= cantitateVantuta;
        }
    }

    public String toStringCsv() {
        return denumire + "," + pret + "," + cantitate + "," + dataExpirarii;
    }
}
