package BankovniRacun;

import Datum.Datum;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Placanja extends BankovniRacun {
    protected BankovniRacun racunKorisnika;
    protected double iznos;
    protected String kategorijaPlacanja;
    protected int racunPrimaoca;
    protected Datum datumIsplate;
    protected String primalacUplate;

    public Placanja(int id, int brojRacuna, double stanjeNaRacunu, String valuta, BankovniRacun racunKorisnika, double iznos, String kategorijaPlacanja, int racunPrimaoca) {
        super(id, brojRacuna, stanjeNaRacunu, valuta);
        this.racunKorisnika = racunKorisnika;
        this.iznos = iznos;
        this.kategorijaPlacanja = kategorijaPlacanja;
        this.racunPrimaoca = racunPrimaoca;
    }

    public Placanja(int id, int brojRacuna, String primalacUplate, double iznos, String kategorijaPlacanja, int racunPrimaoca, Datum datumIsplate) {
        super(id, brojRacuna);
        this.iznos = iznos;
        this.kategorijaPlacanja = kategorijaPlacanja;
        this.racunPrimaoca = racunPrimaoca;
        this.datumIsplate = datumIsplate;
        this.primalacUplate = primalacUplate;
    }


    public BankovniRacun getRacunKorisnika() {
        return racunKorisnika;
    }

    public double getIznos() {
        return iznos;
    }

    public String getKategorijaPlacanja() {
        return kategorijaPlacanja;
    }

    public int getRacunPrimaoca() {
        return racunPrimaoca;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public void setKategorijaPlacanja(String kategorijaPlacanja) {
        this.kategorijaPlacanja = kategorijaPlacanja;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public static void kreirajStranuPlacanja(int id, int brojRacuna) throws IOException {
        String excelName = "TabelaKorisnika.xlsx";

        FileInputStream fip = new FileInputStream(excelName);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh3 = wb.createSheet("Placanja" + brojRacuna);
        BankovniRacun br;
        Sheet sh1 = wb.getSheet("RegistrovaniKorisnici");
        String ime = "";
        String prezime = "";
        for (int i = 1; i <= sh1.getLastRowNum(); i++) {
            Row r = sh1.getRow(i);
            Cell c = r.getCell(0);
            if((int)c.getNumericCellValue() == id){
                ime = r.getCell(1).getStringCellValue();
                prezime = r.getCell(2).getStringCellValue();
            }
        }
        br = new BankovniRacun(ime, prezime, id, brojRacuna);

        Row r = sh3.createRow(0);
        Cell c1 = r.createCell(0);
        c1.setCellValue("Broj racuna: " + br.getBrojRacuna());

        Row r2 = sh3.createRow(1);
        Cell c2 = r2.createCell(0);
        c2.setCellValue("ID korisnika:" + br.getId());

        Row r3 = sh3.createRow(2);
        Cell c3 = r3.createCell(0);
        c3.setCellValue(br.getIme());

        Row r4 = sh3.createRow(3);
        Cell c4 = r4.createCell(0);
        c4.setCellValue(br.getPrezime());


        Row rd = sh3.createRow(4);
        Cell cd1 = rd.createCell(0);
        cd1.setCellValue("Racun primaoca");

        Cell cd2 = rd.createCell(1);
        cd2.setCellValue("Datum placanja");

        Cell cd3 = rd.createCell(2);
        cd3.setCellValue("Svrha uplate");

        Cell cd4 = rd.createCell(3);
        cd4.setCellValue("Iznos");

        FileOutputStream outputStream = new FileOutputStream(excelName);

        wb.write(outputStream);
        outputStream.close();
    }
    public void upisUExcelTabeluPlacanja(Placanja p) throws IOException {
        File fl = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Placanja" + p.brojRacuna);
        int brojac = sh1.getLastRowNum() + 1;
        Row r = sh1.createRow(brojac);

        Cell cell = r.createCell(0);
        cell.setCellValue(p.racunPrimaoca);

        cell = r.createCell(1);
        cell.setCellValue(p.primalacUplate);

        cell = r.createCell(2);
        cell.setCellValue(p.datumIsplate.toString());

        cell = r.createCell(3);
        cell.setCellValue(p.kategorijaPlacanja);

        cell = r.createCell(4);
        cell.setCellValue(p.iznos);




        try (FileOutputStream fos = new FileOutputStream("TabelaKorisnika.xlsx");) {
            fip.close();
            wb.write(fos);
            wb.close();
        }
    }
    }
