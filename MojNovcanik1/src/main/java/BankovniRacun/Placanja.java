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
import java.util.ArrayList;
import java.util.Scanner;

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

    public Placanja(int racunPrimaoca, String primalacUplate, double iznos, String kategorijaPlacanja) {
        super(racunPrimaoca);
        this.primalacUplate = primalacUplate;
        this.iznos = iznos;
        this.kategorijaPlacanja = kategorijaPlacanja;
        this.racunPrimaoca = racunPrimaoca;

    }

    public Placanja(int id, int brojRacuna, double iznos, int racunPrimaoca, Datum datumIsplate, String primalacUplate) {
        super(id, brojRacuna);
        this.iznos = iznos;
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
        sb.append("Racun primaoca : ").append(racunPrimaoca).append("\n");
        sb.append("Ime primaoca: ").append(primalacUplate).append("\n");
        sb.append("Iznos: ").append(iznos).append("\n");
        sb.append("Svrha uplate: ").append(kategorijaPlacanja).append("\n");
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
            if ((int) c.getNumericCellValue() == id) {
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

        Cell cd5 = rd.createCell(1);
        cd5.setCellValue("Naziv primaoca");

        Cell cd2 = rd.createCell(2);
        cd2.setCellValue("Datum placanja");

        Cell cd3 = rd.createCell(3);
        cd3.setCellValue("Svrha uplate");

        Cell cd4 = rd.createCell(4);
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

    public static void placanjaPoDatumu(int brojRacuna, Datum d) throws IOException {
        ArrayList<Placanja> pl = new ArrayList<Placanja>();
        File fl = new File("TabelaKorisnika.xlsx");
        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Placanja" + brojRacuna);
        for (int i = 5; i <= sh1.getLastRowNum(); i++) {
            Row r = sh1.getRow(i);
            if (r.getCell(2).getStringCellValue().equals(d.toString())) {
                int racunPrimaoca = (int) r.getCell(0).getNumericCellValue();
                String nazivPrimaoca = r.getCell(1).getStringCellValue();
                double iznos = r.getCell(4).getNumericCellValue();
                String kategorijaPlacanja = r.getCell(3).getStringCellValue();
                Placanja p = new Placanja(racunPrimaoca, nazivPrimaoca, iznos, kategorijaPlacanja);
                pl.add(p);
            }
        }
        if(pl.size() == 0){
            System.out.println("Nemate placanja po ovom datumu.");
        }
        else {
            for (Placanja p : pl) {
                System.out.println(p.toString());
            }
        }
    }

    public static void placanjaPoKategoriji(int brojRacuna, String kategorijaPlacanja) throws IOException {
        ArrayList<Placanja> pl = new ArrayList<Placanja>();
        File fl = new File("TabelaKorisnika.xlsx");
        FileInputStream fip = new FileInputStream(fl);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("Placanja" + brojRacuna);
        for (int i = 5; i <= sh1.getLastRowNum(); i++) {
            Row r = sh1.getRow(i);
            if (r.getCell(3).getStringCellValue().equalsIgnoreCase(kategorijaPlacanja)) {
                int racunPrimaoca = (int) r.getCell(0).getNumericCellValue();
                String nazivPrimaoca = r.getCell(1).getStringCellValue();
                double iznos = r.getCell(4).getNumericCellValue();
                //potrebno uneti datum
              //  String d = r.getCell(2).getStringCellValue();

                Placanja p = new Placanja(racunPrimaoca, nazivPrimaoca, iznos, kategorijaPlacanja);
                pl.add(p);
            }
        }
        if(pl.size() == 0){
            System.out.println("Nemate isplata po ovoj kategoriji.");
        }
        else {
            for (Placanja p : pl) {
                System.out.println(p.toString());
            }
        }

    }
    public static Placanja uplatnica(int id, int brojRacuna) {
        Scanner sc = new Scanner(System.in);
        Placanja p;
        System.out.println("Popunite naredna polja.");
        System.out.println();
        System.out.println("Primalac uplate je: ");
        String primalacUplate = sc.next();
        System.out.println("Racun uplatioca je: ");
        int racunUplatioca = sc.nextInt();
        System.out.println("Iznos uplate je: ");
        double iznosUplate = sc.nextDouble();
        System.out.println("Unesite datum uplate. Godina uplate: ");
        int godina = sc.nextInt();
        System.out.println("Mesec uplate: ");
        int mesec = sc.nextInt();
        System.out.println("Dan u mesecu uplate: ");
        int dan = sc.nextInt();
        Datum datum = new Datum(dan, mesec, godina);
        System.out.println("Kada je rec o svrsi uplate, odaberite jednu od ponudjenih opcija: ");
        System.out.println("1. Hrana");
        System.out.println("2. Racuni");
        System.out.println("3. Zabava");
        System.out.println("4. Putovanja");
        System.out.println("5. Online kupovina ");
        System.out.println("6. Ostalo ");
        String svrha = "";
        boolean t = true;
        while (t) {
            int s = sc.nextInt();
            switch (s) {
                case 1:
                    svrha = "Hrana";
                    t = false;
                    break;

                case 2:
                    svrha = "Racuni";
                    t = false;
                    break;
                case 3:
                    svrha = "Zabava";
                    t = false;
                    break;
                case 4:
                    svrha = "Putovanja";
                    t = false;
                    break;
                case 5:
                    svrha = "Online kupovina";
                    t = false;
                    break;
                case 6:
                    svrha = "Ostalo";
                    t = false;
                    break;
                default:
                    System.out.println("Niste uneli dobru vrednost. Zelite li da se unese 'Ostalo' ili biste ponovo pokusali da unesete vrednost?DA/NE");
                    String a = sc.next();
                    if (a.equalsIgnoreCase("DA")) {
                        svrha = "Ostalo";
                        t = false;
                        break;
                    }
            }
        }
        p = new Placanja(id, brojRacuna, primalacUplate, iznosUplate, svrha, racunUplatioca, datum);
        return p;
    }
}

