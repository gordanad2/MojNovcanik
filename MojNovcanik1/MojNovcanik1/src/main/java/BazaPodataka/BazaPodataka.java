package BazaPodataka;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class BazaPodataka {
    private File tabela = new File("TabelaKorisnika.xlsx");
    private Sheet sheetAplikacije;

    public BazaPodataka(File tabela, Sheet sheetAplikacije) {
        this.tabela = tabela;
        this.sheetAplikacije = sheetAplikacije;
    }

    public File getTabela() {
        return tabela;
    }

    public Sheet getSheetAplikacije() {
        return sheetAplikacije;
    }

    public void setTabela(File tabela) {
        this.tabela = tabela;
    }

    public void setSheetAplikacije(Sheet sheetAplikacije) {
        this.sheetAplikacije = sheetAplikacije;
    }

    public static void kreirajTabeluKorisnika() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sh1 = wb.createSheet("RegistrovaniKorisnici");
        int brojac = 0;
        Row r1 = sh1.createRow(brojac);

        Cell c1 = r1.createCell(1);
        c1.setCellValue("Ime");

        Cell c2 = r1.createCell(2);
        c2.setCellValue("Prezime");

        Cell c3 = r1.createCell(3);
        c3.setCellValue("JMBG");

        Cell c4 = r1.createCell(4);
        c4.setCellValue("Adresa: ");

        Cell c5 = r1.createCell(5);
        c5.setCellValue("Email adresa");

        Cell c6 = r1.createCell(6);
        c6.setCellValue("Korisnicko ime");

        Cell c7 = r1.createCell(7);
        c7.setCellValue("Lozinka");

        Sheet sh2 = wb.createSheet("Bankovni racuni");

        Row r = sh2.createRow(0);
        Cell cr1 = r.createCell(0);
        cr1.setCellValue("ID korisnika");

        Cell cr2 = r.createCell(1);
        cr2.setCellValue("Broj Racuna");

        Cell cr3 = r.createCell(2);
        cr3.setCellValue("Stanje na racunu");

        Cell cr4 = r.createCell(3);
        cr4.setCellValue("Valuta");


        try {
            // pravim taj fajl
            OutputStream fajl = new FileOutputStream("TabelaKorisnika.xlsx");
            // upisujem workbook u fajl
            wb.write(fajl);
            // zatvaram workbook
            wb.close();
        } catch (IOException e) {
            System.out.println("Desila se greska " + e.getMessage());
        }
    }
}
