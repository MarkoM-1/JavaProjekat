package Account;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Kreiraj {

    public static int redKorisnikaKojiSeUlogovao(String korisnik) {
        File file = new File("BazaKorisnika.xlsx");
        int korisnikovRed = 0;

        try {
            InputStream is = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(is);

            Sheet sheet = wb.getSheetAt(0);

            int brRedova = sheet.getLastRowNum();

            for (int i = 1; i <= brRedova; i++) {
                Row row = sheet.getRow(i);

                Cell usernameCelija = row.getCell(3);
                String username = usernameCelija.getStringCellValue();
                if(username.equals(korisnik)){
                    korisnikovRed = i;
                    break;
                }
            }
            wb.close();
        } catch (IOException e) {
            System.out.println("Odgovarajuci fajl nije pronadjen.");
        }
        return korisnikovRed;
    }

    public static Korisnik kreirajKorisnika(String korisnik) {
        File file = new File("BazaPodataka.xlsx");
        Korisnik kor = new Korisnik();

        try {
            InputStream is = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(is);

            Sheet sheet = wb.getSheetAt(0);

            Row row = sheet.getRow(redKorisnikaKojiSeUlogovao(korisnik));

            Cell celijaId = row.getCell(0);
            Cell celijaIme = row.getCell(1);
            Cell celijaPrezime = row.getCell(2);

            int id = (int)(celijaId.getNumericCellValue());
            String ime = celijaIme.getStringCellValue();
            String prezime = celijaPrezime.getStringCellValue();

             kor = new Korisnik(id, ime, prezime);

        } catch (IOException e) {
            System.out.println("Odgovarajuci fajl nije pronadjen.");
        }
        return kor;
    }

    public static Nalog kreirajNalog(String korisnik) {
        File file = new File("BazaPodataka.xlsx");
        ArrayList<String> nizRacuna = new ArrayList<>();
        Nalog nalog = new Nalog();

        try {
            InputStream is = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(is);

            Sheet sheet = wb.getSheetAt(0);

            Row row = sheet.getRow(redKorisnikaKojiSeUlogovao(korisnik));

            Cell celijaUsername = row.getCell(3);
            Cell celijaPassword = row.getCell(4);
            Cell celijaPin = row.getCell(5);

            String username = celijaUsername.getStringCellValue();
            String password = celijaPassword.getStringCellValue();
            String pin = celijaPin.getStringCellValue();

            nalog = new Nalog(username, password, pin);
            wb.close();
        } catch (IOException e) {
            System.out.println("Odgovarajuci fajl nije pronadjen.");
        }
        return nalog;
    }

    public static Racun kreirajRacun(String korisnik) {
        File file = new File("BazaKorisnika.xlsx");
        ArrayList<String> nizRacuna = new ArrayList<>();
        Racun racun = new Racun();


        try {
            InputStream is = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(is);

            Sheet sheet = wb.getSheetAt(0);

            Row row = sheet.getRow(redKorisnikaKojiSeUlogovao(korisnik));
            Cell celijaId = row.getCell(0);
            Cell celijaIme = row.getCell(1);
            Cell celijaPrezime = row.getCell(2);
            Cell celijaStanje = row.getCell(6);
            Cell celijaBrRacuna = row.getCell(7);
            Cell celijaBrRacunaDva = row.getCell(8);
            Cell celijaBrRacunaTri = row.getCell(9);


            int id = (int)(celijaId.getNumericCellValue());
            String ime = celijaIme.getStringCellValue();
            String prezime = celijaPrezime.getStringCellValue();
            double stanje = celijaStanje.getNumericCellValue();
            String brRacunaDva = null;
            String brRacunaTri = null;
            for (int i = 0; i < 3; i++) {
                String brRacuna = celijaBrRacuna.getStringCellValue();
                if (celijaBrRacunaDva != null) {
                    brRacunaDva = celijaBrRacunaDva.getStringCellValue();
                }
                if (celijaBrRacunaTri != null) {
                    brRacunaTri = celijaBrRacunaTri.getStringCellValue();
                }
                nizRacuna.add(brRacuna);
                nizRacuna.add(brRacunaDva);
                nizRacuna.add(brRacunaTri);
            }

            Korisnik kor = new Korisnik(id, ime, prezime);
            racun = new Racun(kor, stanje, nizRacuna );
            wb.close();
        } catch (IOException e) {
            System.out.println("Odgovarajuci fajl nije pronadjen.");
        }
        return racun;
    }
}
