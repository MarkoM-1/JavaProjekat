package Account;

import java.util.ArrayList;
import java.util.Scanner;

public class Racun {
    private Korisnik vlasnik;
    private double stanje;
    private ArrayList<String> nizRacuna;


    public Racun(Korisnik vlasnik, double stanje, ArrayList<String> nizBrRacuna) {
        this.vlasnik = vlasnik;
        this.stanje = stanje;
        this.nizRacuna = nizBrRacuna;
    }

    public Racun() {
    }

    public Korisnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }

    public double getStanje() {
        return stanje;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    public ArrayList<String> getNizRacuna() {
        return nizRacuna;
    }

    public void setNizRacuna(ArrayList<String> nizRacuna) {
        this.nizRacuna = nizRacuna;
    }

    public void uplatiNaRacun(double iznos){
        iznos = proveriIznos(iznos);
        stanje += iznos;
    }

    public void skiniSaRacuna (double iznos){
        proveriIznos(iznos);
        double novoStanje = stanje - iznos;
        if(novoStanje > 0){
            stanje = novoStanje;
        }
        else {
            System.out.println("Nemate dovoljno sredstava na racunu.");
        }
    }
    public double proveriIznos(double iznos){
        Scanner sc = new Scanner(System.in);
        while(iznos < 0){
            System.out.println("Iznos ne moze biti negativan ili jednak 0, pokusajte ponovo.");
            iznos = sc.nextDouble();
        }
        return iznos;
    }

}
