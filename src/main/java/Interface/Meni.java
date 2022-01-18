package Interface;

import Account.AktivnostiSaNalogom;
import Account.Kreiraj;
import Account.RegLog;

import java.util.Scanner;

public class Meni {

    public static void PocetniMeni() {
        Scanner sc = new Scanner(System.in);
        String izbor = "0";

        System.out.println("Molim Vas izaberite opciju (1-4)");
        System.out.println("1. Uloguj se");
        System.out.println("2. Zaboravio sam sifru?");
        System.out.println("3. Napravi novi nalog");
        System.out.println("4. Izadji iz programa");
        System.out.println("***************************");

        while(!izbor.equals("4")) {
            izbor = sc.nextLine();
            System.out.println("***************************");
            switch (izbor) {
                case "1":
                    ulogujSeMeni();
                    break;
                case "2":
                    zaboravioSifruMeni();
                    break;
                case "3":
                    napraviNoviNalogMeni();
                    break;
                case "4":
                    System.out.println("Uspesno ste izasli iz programa.");
                    System.exit(0);
                default:
                    System.out.println("Pogresan unos, pokusajte ponovo.");
                    System.out.println("***************************");
            }
        }
    }

    public static void ulogujSeMeni(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Unesite Vase korisnicko ime: ");
        String username = sc.nextLine();

        System.out.println("Unesite Vasu sifru: ");
        String password = sc.nextLine();

        System.out.println("***************************");
        System.out.println("Izaberite opciju (1-3): ");
        System.out.println("1. Uloguj se");
        System.out.println("2. Vrati se u prethodni meni");
        System.out.println("3. Izadji iz programa");
        System.out.println("***************************");
        String izbor = sc.nextLine();
        System.out.println("***************************");

        switch(izbor){
            case "1":
                RegLog.login(username, password);
                break;
            case "2":
                Meni.PocetniMeni();
                break;
            case "3":
                System.out.println("Uspesno ste izasli iz programa.");
                System.exit(0);
            default:
                System.out.println("Pogresan unos, pokusajte ponovo.");
                System.out.println("***************************");
                ulogujSeMeni();

        }
    }

    public static void napraviNoviNalogMeni(){
        Scanner sc = new Scanner(System.in);
        boolean nastavi = false;
        String username = "username";

        System.out.println("Unesite Vase ime: ");
        String ime = sc.next();

        System.out.println("Unesite Vase prezime: ");
        String prezime = sc.next();

        while(!nastavi) {
            System.out.println("Unesite Vase korisnicko ime: ");
            username = sc.next();
            if(!RegLog.postojiUsername(username)){
                nastavi = true;
            }
            else{
                System.out.println("Korisnik postoji, pokusajte ponovo.");
            }
        }

        System.out.println("Unesite sifru: ");
        String password = sc.next();

        System.out.println("Unesite PIN za resetovanje sifre: ");
        String pin = sc.next();


        System.out.println("Unesite stanje Vase racuna: ");
        double stanje = sc.nextDouble();

        System.out.println("Unesite broj racuna u sledecem formatu:\n[Broj banke]-[Broj racuna]-[Kontrolni broj]: ");
        sc.nextLine();
        String brRacuna = sc.nextLine();

        RegLog.registracija(ime, prezime, username, password, pin, stanje, brRacuna);

        Meni.PocetniMeni();
    }

    public static void zaboravioSifruMeni(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Unesite Vas username: ");
        String username = sc.nextLine();

        System.out.println("Unesite Vas PIN: ");
        String pin = sc.nextLine();

        if(RegLog.proveriPin(username, pin)){
            System.out.println("Unesite novu sifru: ");
            String password = sc.nextLine();
            RegLog.promeniSifru(username, password, pin);
            System.out.println("Uspesno promenjena sifra.");
            System.out.println("***************************");
            PocetniMeni();
        }
        else{
            System.out.println("Pogresan username ili PIN, pokusajte ponovo.");
            zaboravioSifruMeni();
        }
    }

    public static void ulogovanMeni(String username){
        Scanner sc = new Scanner(System.in);


        System.out.println("***************************");
        System.out.println("Izaberite opciju (1-3): ");
        System.out.println("1. Provera stanja");
        System.out.println("2. Izvrsi kupovinu");
        System.out.println("3. Istorija kupovine");
        System.out.println("4. Izloguj se");
        System.out.println("***************************");
        int izbor = sc.nextInt();
        System.out.println("***************************");

        switch(izbor) {
           case 1:
                double stanje = Kreiraj.kreirajRacun(username).getStanje();
                System.out.println("Stanje na Vasem racunu je " + stanje);
                ulogovanMeni(username);
               break;
            case 2:
                Meni.PocetniMeni();
                break;
            case 3:
                Meni.PocetniMeni();
                break;
            case 4:
                System.out.println("Uspesno ste izasli iz programa.");
                System.exit(0);
            default:
                System.out.println("Pogresan unos, pokusajte ponovo.");
                System.out.println("***************************");
                PocetniMeni();
        }
    }

}
