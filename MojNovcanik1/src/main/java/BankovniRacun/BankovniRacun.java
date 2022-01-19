package BankovniRacun;

import Prijava.RegistrovaniKorisnici;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BankovniRacun extends RegistrovaniKorisnici {
    protected RegistrovaniKorisnici korisnik;
    protected int brojRacuna;
    protected double stanjeNaRacunu;
    protected String valuta;

    public BankovniRacun(String ime, String prezime, String maticniBroj, String adresa, String kucniBroj, int brojStana, String eMail, String korisnickoIme, String lozinka, int id, RegistrovaniKorisnici korisnik, int brojRacuna, double stanjeNaRacunu, String valuta) {
        super(ime, prezime, maticniBroj, adresa, kucniBroj, brojStana, eMail, korisnickoIme, lozinka, id);
        this.korisnik = korisnik;
        this.brojRacuna = brojRacuna;
        this.stanjeNaRacunu = stanjeNaRacunu;
        this.valuta = valuta;
    }

    public BankovniRacun(String ime, String prezime, String maticniBroj, String adresa, String kucniBroj, String eMail, String korisnickoIme, String lozinka, int id, RegistrovaniKorisnici korisnik, int brojRacuna, double stanjeNaRacunu, String valuta) {
        super(ime, prezime, maticniBroj, adresa, kucniBroj, eMail, korisnickoIme, lozinka, id);
        this.korisnik = korisnik;
        this.brojRacuna = brojRacuna;
        this.stanjeNaRacunu = stanjeNaRacunu;
        this.valuta = valuta;
    }

    public BankovniRacun(String ime, String prezime, String maticniBroj, String adresa, String eMail, String korisnickoIme, String lozinka, int id, RegistrovaniKorisnici korisnik, int brojRacuna, double stanjeNaRacunu, String valuta) {
        super(ime, prezime, maticniBroj, adresa, eMail, korisnickoIme, lozinka, id);
        this.korisnik = korisnik;
        this.brojRacuna = brojRacuna;
        this.stanjeNaRacunu = stanjeNaRacunu;
        this.valuta = valuta;
    }

    public BankovniRacun(String ime, String prezime, String maticniBroj, String adresa, String eMail, String korisnickoIme, String lozinka, RegistrovaniKorisnici korisnik, int brojRacuna, double stanjeNaRacunu, String valuta) {
        super(ime, prezime, maticniBroj, adresa, eMail, korisnickoIme, lozinka);
        this.korisnik = korisnik;
        this.brojRacuna = brojRacuna;
        this.stanjeNaRacunu = stanjeNaRacunu;
        this.valuta = valuta;
    }

    public BankovniRacun(int id, int brojRacuna, double stanjeNaRacunu, String valuta) {
        super(id);
        this.brojRacuna = brojRacuna;
        this.stanjeNaRacunu = stanjeNaRacunu;
        this.valuta = valuta;
    }

    public BankovniRacun(int id, int brojRacuna) {
        super(id);
        this.brojRacuna = brojRacuna;
    }

    public BankovniRacun(int id) {
        super(id);
    }

    public BankovniRacun(String ime, String prezime, int id, int brojRacuna) {
        super(ime, prezime, id);
        this.brojRacuna = brojRacuna;
    }

    public RegistrovaniKorisnici getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(RegistrovaniKorisnici korisnik) {
        this.korisnik = korisnik;
    }

    public int getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(int brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public double getStanjeNaRacunu() {
        return stanjeNaRacunu;
    }

    public void setStanjeNaRacunu(double stanjeNaRacunu) {
        this.stanjeNaRacunu = stanjeNaRacunu;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    @Override
    public String toString() {
        return "BankovniRacun{" +
                "korisnik=" + korisnik +
                ", brojRacuna=" + brojRacuna +
                ", stanjeNaRacunu=" + stanjeNaRacunu +
                ", valuta='" + valuta + '\'' +
                ", id=" + id +
                '}';
    }

    public void ispisUExcelRacun(ArrayList<BankovniRacun> nizRacuna) throws IOException {
        File file = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Bankovni racuni");
        int brojac = sh1.getLastRowNum() + 1;
        int id = sh1.getLastRowNum();

        for (BankovniRacun racun : nizRacuna) {
            Row row = sh1.createRow(brojac);
            ispisUExcelKolone(racun, row);
        }

        try (FileOutputStream fos = new FileOutputStream("TabelaKorisnika.xlsx");) {
            fip.close();
            wb.write(fos);
            wb.close();
        }
    }

    public void otvaranjeRacuna(int id) throws IOException {
        File fl = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Bankovni racuni");
        int count = sh1.getLastRowNum() + 1;
        String valuta = "RSD";
        int brojRacuna = 150000 + count;
        double stanjeNaRacunu = 0;
        BankovniRacun br = new BankovniRacun(id, brojRacuna, stanjeNaRacunu, valuta);
        ArrayList<BankovniRacun> nizRacuna = new ArrayList<BankovniRacun>();
        nizRacuna.add(br);
        br.ispisUExcelRacun(nizRacuna);
    }
    public double stanjeNaRacunu() throws IOException {
        double stanje = 0;
        File fl = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Bankovni racuni");
        for (int i = 1; i <= sh1.getLastRowNum(); i++) {
            Row r = sh1.getRow(i);
            if (r.getCell(0).getNumericCellValue() == id && r.getCell(1).getNumericCellValue() == brojRacuna) {
                stanje = r.getCell(2).getNumericCellValue();
            }
        }
        return stanje;
    }

    public void gasenjeRacuna(int id, int brojRacuna) throws IOException {
        Scanner sc = new Scanner(System.in);
        File fl = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Bankovni racuni");
<<<<<<< Updated upstream

        for (int i = 1; i < sh1.getLastRowNum(); i++) {
=======
        // iterira se kroz racune u sheet-u
        for (int i = 1; i <= sh1.getLastRowNum(); i++) {
>>>>>>> Stashed changes
            Row r = sh1.getRow(i);
            if (r.getCell(0).getNumericCellValue() == id && r.getCell(1).getNumericCellValue() == brojRacuna) {
                if (r.getCell(2).getNumericCellValue() == 0) {
                    System.out.println("Na vasem racunu nemate sredstava. Racun moze biti ugasen. Da li ste sigurni da zelite da ugasite ovaj racun?DA/NE");
                    String a = "da";
                    if (a.equalsIgnoreCase("DA")) {
                        sh1.removeRow(r);
                        System.out.println("Uspesno ste ugasili racun.");
                        break;
                    } else if (a.equalsIgnoreCase("NE")) {
                        break;
                    } else {
                        System.out.println("Niste uneli rec koja je trazena. Pokusajte ponovo");
                        gasenjeRacuna(id, brojRacuna);
                    }
                } else {
                    System.out.println("Na racunu imate sredstava. Kako biste ugasili racun iznos sredstava na racunu mora biti 0.");
                }

            }
        }

    }


    public void ispisUExcelKolone(BankovniRacun br, Row r) {

        Cell cell = r.createCell(0);
        cell.setCellValue(br.getId());

        cell = r.createCell(1);
        cell.setCellValue(br.getBrojRacuna());

        cell = r.createCell(2);
        cell.setCellValue(br.getStanjeNaRacunu());

        cell = r.createCell(3);
        cell.setCellValue(br.getValuta());

    }

    public double isplata(double x, int id, int brojRacuna) throws IOException {
        File fl = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Bankovni racuni");
        for (int i = 1; i <= sh1.getLastRowNum(); i++) {
            Row r = sh1.getRow(i);
            Cell c = r.getCell(0);
            Cell cr = r.getCell(1);
            Cell celijaIznos = r.getCell(2);


            if (c.getNumericCellValue() == id && cr.getNumericCellValue() == brojRacuna) {
                double stanje = celijaIznos.getNumericCellValue();

                if (x > stanje) {
                    System.out.println("Nemate dovoljno novca na racunu. Unesite drugi iznos koji zelite da podignete.");
                } else {
                    stanjeNaRacunu = stanje - x;
                    celijaIznos.setCellValue(stanjeNaRacunu);
                }
            }
        }
        try (FileOutputStream fos = new FileOutputStream("TabelaKorisnika.xlsx")) {
            fip.close();
            wb.write(fos);
            wb.close();
        }
        return stanjeNaRacunu;
    }

    public double uplata(int x, int id, int brojRacuna) throws IOException {

        File fl = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Bankovni racuni");
        for (int i = 1; i <= sh1.getLastRowNum(); i++) {
            Row r = sh1.getRow(i);
            Cell c = r.getCell(0);
            Cell cr = r.getCell(1);
            Cell celijaIznos = r.getCell(2);
            if (c.getNumericCellValue() == id && cr.getNumericCellValue() == brojRacuna) {
                double stanje = celijaIznos.getNumericCellValue();
                stanjeNaRacunu = stanje + x;
                celijaIznos.setCellValue(stanjeNaRacunu);
            }
        }
        try (FileOutputStream fos = new FileOutputStream("TabelaKorisnika.xlsx")) {
            fip.close();
            wb.write(fos);
            wb.close();
        }
        return stanjeNaRacunu;
    }

    public ArrayList<BankovniRacun> racuniKorisnika(int id) throws IOException {
        int brojRacuna = 0;
        String excelName = "TabelaKorisnika.xlsx";

        FileInputStream fip = new FileInputStream(excelName);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh2 = wb.getSheet("Bankovni racuni");
        ArrayList<BankovniRacun> racuniKorisnika = new ArrayList<BankovniRacun>();
        for (int i = 1; i <= sh2.getLastRowNum(); i++) {
            Row r = sh2.getRow(i);
            Cell c = r.getCell(0);
            if (c.getNumericCellValue() == id) {
                brojRacuna = (int) (r.getCell(1).getNumericCellValue());
                BankovniRacun br = new BankovniRacun(id, brojRacuna);
                racuniKorisnika.add(br);
            }
        }
        return racuniKorisnika;
    }
}
