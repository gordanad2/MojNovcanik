package Prijava;


import Osoba.Osoba;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class RegistrovaniKorisnici extends Osoba {
    protected String korisnickoIme;
    protected String lozinka;
    protected int id;

    // konstruktori
    public RegistrovaniKorisnici(String ime, String prezime, String maticniBroj, String adresa, String kucniBroj, int brojStana, String eMail, String korisnickoIme, String lozinka, int id) {
        super(ime, prezime, maticniBroj, adresa, kucniBroj, brojStana, eMail);
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.id = id;
    }

    public RegistrovaniKorisnici(String ime, String prezime, String maticniBroj, String adresa, String kucniBroj, String eMail, String korisnickoIme, String lozinka, int id) {
        super(ime, prezime, maticniBroj, adresa, kucniBroj, eMail);
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.id = id;
    }

    public RegistrovaniKorisnici(String ime, String prezime, String maticniBroj, String adresa, String eMail, String korisnickoIme, String lozinka, int id) {
        super(ime, prezime, maticniBroj, adresa, eMail);
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.id = id;
    }

    public RegistrovaniKorisnici(String ime, String prezime, String maticniBroj, String adresa, String eMail, String korisnickoIme, String lozinka) {
            super(ime, prezime, maticniBroj, adresa, eMail);
            this.korisnickoIme = korisnickoIme;
            this.lozinka = lozinka;
    }
    public RegistrovaniKorisnici(int id){
        super();
        this.id = id;
    }

    public RegistrovaniKorisnici(String ime, String prezime, int id) {
        super(ime, prezime);
        this.id = id;
    }
    // getteri i setteri
 // id moze da se dohvati ali ne i da se setuje
    public int getId() {
        return id;
    }


    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    // toString metoda
    @Override
    public String toString() {
        return "Registracija{" +
                "korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", id=" + id +
                '}';
    }
    // metoda za registraciju korisnika
    public static void registracijaKorisnika() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesi ime: ");
        String ime = sc.next();

        System.out.println("Unesi prezime: ");
        String prezime = sc.next();


        System.out.println("Unesi maticni broj: ");
        String maticniBroj = sc.next();


        System.out.println("Unesi adresu: ");
        String adresa = sc.next();

        System.out.println("Unesite email adresu: ");
        String eMail = sc.next();

        System.out.println("Unesite korisnicko ime: ");
        String korisnickoIme = sc.next();

        System.out.println("Unesite lozinku: ");
        String lozinka = sc.next();

        System.out.println("Potvrdite lozinku: ");
        String lozinka2 = sc.next();
        int brojac = 3;
        while(brojac > 0){
        if (lozinka.equals(lozinka2)) {
            RegistrovaniKorisnici korisnik = new RegistrovaniKorisnici(ime, prezime, maticniBroj, adresa, eMail, korisnickoIme, lozinka);

            korisnik.ispisUExcel(korisnik);
            break;

        } else {
            System.out.println("Lozinke se ne poklapaju, pokusajte ponovo. Imate ukupno" + brojac + "pokusaja.");
            System.out.println("Unesite lozinku: ");
            lozinka = sc.next();

            System.out.println("Potvrdite lozinku: ");
            lozinka2 = sc.next();
            if (!lozinka.equals(lozinka2)) {
                brojac--;
            }
            System.out.println("Lozinke se ne poklapaju. Korisnik nije registrovan. ");
            System.exit(0);
        }
        }
    }

    //kreira poseban red za korisnika
public void ispisUExcel(RegistrovaniKorisnici korisnik) throws IOException {
    File file = new File("TabelaKorisnika.xlsx");

    FileInputStream fip = new FileInputStream(file);
    XSSFWorkbook wb = new XSSFWorkbook(fip);
    Sheet sh1 = wb.getSheet("RegistrovaniKorisnici");
    int brojac = sh1.getLastRowNum() + 1;
    int id = sh1.getLastRowNum();
    if (file.isFile() && file.exists()) {
        System.out.println("TabelaKorisnika open");
    }
    else {
        System.out.println("TabelaKorisnika either not exist or can't open");
    }

        Row row = sh1.createRow(brojac);
        ispisUExcelKolone(korisnik, row);


    try (FileOutputStream fos = new FileOutputStream("TabelaKorisnika.xlsx")) {
        fip.close();
        wb.write(fos);
        wb.close();
    }
}
// upisuje podatke o konkretnom korisniku u celije reda
public void ispisUExcelKolone(RegistrovaniKorisnici korisnik, Row r){

    Cell cell = r.createCell(0);
    cell.setCellValue(r.getRowNum());

    cell = r.createCell(1);
    cell.setCellValue(korisnik.getIme());

    cell = r.createCell(2);
    cell.setCellValue(korisnik.getPrezime());

    cell = r.createCell(3);
    cell.setCellValue(korisnik.getMaticniBroj());

    Cell c4 = r.createCell(4);
    c4.setCellValue(korisnik.adresa);

    Cell c5 = r.createCell(5);
    c5.setCellValue(korisnik.eMail);

    Cell c6 = r.createCell(6);
    c6.setCellValue(korisnik.korisnickoIme);

    Cell c7 = r.createCell(7);
    c7.setCellValue(korisnik.lozinka);

}
// funkcija za login korisnika u aplikaciju
    public static int logIn() throws IOException {
        int y = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite korisnicko ime: ");
        String kIme = sc.next();
        File file = new File("TabelaKorisnika.xlsx");

        FileInputStream fip = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fip);
        Sheet sh1 = wb.getSheet("RegistrovaniKorisnici");
        for (int i = 1; i <= sh1.getLastRowNum(); i++) {
            Row r = sh1.getRow(i);
            Cell c = r.getCell(6);
            if (kIme.equals(c.getStringCellValue())) {
                System.out.println("Unesi lozinku: ");
                String lozinka = sc.next();
                Cell cl = r.getCell(7);
                if (lozinka.equals(cl.getStringCellValue())) {
                    System.out.println("Lozinka je ispravna. Uspesno ste se ulogovali.");
                    double numericCellValue = r.getCell(0).getNumericCellValue();
                    y = (int)numericCellValue;
                } else {
                    System.out.println("Niste uneli dobru lozinku ili korisnicko ime;");
                }
            }
        }
        return y;
    }
}
