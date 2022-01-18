package Account;

import Interface.Meni;
import Razno.ZaExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class RegLog {

    public static void registracija(String ime, String prezime, String username, String password, String pin, double stanje, String brRacuna) {

            File f = new File("BazaKorisnika.xlsx");
            boolean popuniCeliju = false;
            int i = 1;
            int id = 1;

            while(!popuniCeliju) {

                try {
                    InputStream is = new FileInputStream(f);
                    XSSFWorkbook wb = new XSSFWorkbook(is);

                    Sheet sheet = wb.getSheetAt(0);

                    Row row = sheet.getRow(i);

                    if(row == null){
                        row = sheet.createRow(i);
                        Cell celijaId = row.createCell(0);
                        Cell celijaIme = row.createCell(1);
                        Cell celijaPrezime = row.createCell(2);
                        Cell celijaUsername = row.createCell(3);
                        Cell celijaPassword = row.createCell(4);
                        Cell celijaPin = row.createCell(5);
                        Cell celijaStanje = row.createCell(6);
                        Cell celijaBrRacuna = row.createCell(7);
                        for (int j = 7; j < 10; j++) {
                            Cell celijaBrRacunaNovog = row.getCell(j);
                            if(celijaBrRacunaNovog == null){
                                celijaBrRacunaNovog = row.createCell(j);
                                celijaBrRacunaNovog.setCellValue(brRacuna);
                            }
                            else{
                                break;
                            }
                        }

                        celijaId.setCellValue(id);
                        celijaIme.setCellValue(ime);
                        celijaPrezime.setCellValue(prezime);
                        celijaUsername.setCellValue(username);
                        celijaPassword.setCellValue(password);
                        celijaPin.setCellValue(pin);
                        celijaStanje.setCellValue(stanje);
                        celijaBrRacuna.setCellValue(brRacuna);
                        id++;

                        popuniCeliju = true;
                    }
                    ZaExcel.snimiWorkbok(wb);
                    i++;
                } catch (IOException e) {
                    System.out.println("Greska sa bazom podataka.");
                }
            }
            System.out.println("***************************");
            System.out.println("Uspesno ste napravili nalog.");
            System.out.println("***************************");
    }



    public static void login(String username, String password) {
        Scanner sc = new Scanner(System.in);
        int brPokusaja = 0;
        for (int i = 1; i <= 3 ; i++) {
            if (proveriUsername(username) && proveriPassword(password)) {
                System.out.println("Uspesno ste se ulgovali");
                Meni.ulogovanMeni(username);
            }
            else {
                System.out.println("Pogresan username ili password, broj pokusaja: " + (3-i));
                System.out.println("***************************");
                brPokusaja++;
            }

            if(brPokusaja == 3){
                System.out.println("Prekoracili ste broj pokusaja. Pokusajte ponovo za 3 minuta.");
                System.exit(0);
            }

            System.out.println("Unesite Vase korisnicko ime: ");
            username = sc.next();

            System.out.println("Unesite Vasu sifru: ");
            password = sc.next();
        }
    }

    public static boolean proveriUsername(String username) {
        File f = new File("BazaKorisnika.xlsx");
        boolean dobarUsername = false;
        boolean vrti = true;
        int i = 1;

        while(vrti) {
            try {
                InputStream is = new FileInputStream(f);
                XSSFWorkbook wb = new XSSFWorkbook(is);
                DataFormatter formatter = new DataFormatter();

                Sheet sheet = wb.getSheetAt(0);
                Row row = sheet.getRow(i);

                if(row == null) {
                    break;
                }
                Cell celijaUsername = row.getCell(3);
                String celijaUsernameProcitana = formatter.formatCellValue(celijaUsername);


                if(celijaUsernameProcitana.equals(username)){
                    dobarUsername = true;
                    vrti = false;
                }
                i++;
            } catch (IOException e) {
                System.out.println("Greska sa bazom podataka.");
            }
        }
        return dobarUsername;
    }

    public static boolean proveriPassword(String password) {
        File f = new File("BazaKorisnika.xlsx");
        boolean dobarPassword = false;
        boolean vrti = true;
        int i = 1;

        while(vrti) {
            try {
                InputStream is = new FileInputStream(f);
                XSSFWorkbook wb = new XSSFWorkbook(is);
                DataFormatter formatter = new DataFormatter();

                Sheet sheet = wb.getSheetAt(0);
                Row row = sheet.getRow(i);

                if(row == null) {
                    break;
                }
                Cell celijaPassword = row.getCell(4);
                String celijaPasswordProcitana = formatter.formatCellValue(celijaPassword);


                if(celijaPasswordProcitana.equals(password)){
                    dobarPassword = true;
                    vrti = false;
                }
                i++;
            } catch (IOException e) {
                System.out.println("Greska sa bazom podataka.");
            }
        }
        return dobarPassword;
    }

    public static boolean postojiUsername(String username){
        File f = new File("BazaKorisnika.xlsx");
        boolean postojiUsername = false;
        boolean vrti = true;
        int i = 1;

        while(vrti) {
            try {
                InputStream is = new FileInputStream(f);
                XSSFWorkbook wb = new XSSFWorkbook(is);
                DataFormatter formatter = new DataFormatter();

                Sheet sheet = wb.getSheetAt(0);
                Row row = sheet.getRow(i);

                if(row == null) {
                    break;
                }
                Cell celijaUsername = row.getCell(3);
                String celijaUsernameProcitana = formatter.formatCellValue(celijaUsername);

                if(celijaUsernameProcitana.equals(username)){
                    postojiUsername = true;
                    vrti = false;
                }
                i++;
            } catch (IOException e) {
                System.out.println("Greska sa bazom podataka.");
            }
        }
        return postojiUsername;
    }

    public static void promeniSifru(String username, String password, String pin) {
        File f = new File("BazaKorisnika.xlsx");
        boolean promeniPassword = false;
        int i = 1;

        while(!promeniPassword) {

            try {
                InputStream is = new FileInputStream(f);
                XSSFWorkbook wb = new XSSFWorkbook(is);

                Sheet sheet = wb.getSheetAt(0);

                Row row = sheet.getRow(i);

                if (proveriPin(username, pin)){
                    Cell celijaPassword = row.getCell(4);
                    celijaPassword.setCellValue(password);

                    ZaExcel.snimiWorkbok(wb);

                    promeniPassword = true;
                }
                else{

                }
                i++;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean proveriPin(String username, String pin) {
        boolean dobarPin = false;
        File f = new File("BazaKorisnika.xlsx");

        try{

            InputStream is = new FileInputStream(f);
            XSSFWorkbook wb = new XSSFWorkbook(is);
            DataFormatter formatter = new DataFormatter();

            Sheet sheet = wb.getSheetAt(0);
            boolean praznaCelija = false;
            int i = 1;

            while(!praznaCelija) {

                Row row = sheet.getRow(i);

                if(row == null){
                    break;
                }
                Cell celija = row.getCell(5);
                String vrednostCelije = formatter.formatCellValue(celija);

                if (postojiUsername(username) && vrednostCelije.equals(pin)){
                    dobarPin = true;
                    break;
                }

                i++;
            }
            wb.close();
        } catch (IOException e) {
            System.out.println("Greska.");
        }
        return dobarPin;
    }

}
