package Razno;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ZaExcel {
    public static void snimiWorkbok(XSSFWorkbook wb){
        try {
            OutputStream fajl = new FileOutputStream("BazaKorisnika.xlsx");
            wb.write(fajl);
            wb.close();
        }
        catch(IOException e){
            System.out.println("Doslo je do greske pri snimanju " + e.getMessage());
            System.exit(1);
        }
    }
}
