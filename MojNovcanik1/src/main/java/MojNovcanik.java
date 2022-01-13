import BankovniRacun.BankovniRacun;
import BankovniRacun.Placanja;
import Datum.Datum;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static BankovniRacun.Placanja.kreirajStranuPlacanja;
import static BazaPodataka.BazaPodataka.kreirajTabeluKorisnika;
import static Prijava.RegistrovaniKorisnici.logIn;
import static Prijava.RegistrovaniKorisnici.registracijaKorisnika;

public class MojNovcanik {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File file = new File("TabelaKorisnika.xlsx");
        if (!file.exists()) {
            kreirajTabeluKorisnika();
        }

        //ulazni meni
        System.out.println("Dobrodosli!");
        System.out.println("1. Registracija");
        System.out.println("2. Log in");
        System.out.println("Ukoliko zelite da se registrujete ukucajte broj 1.");
        System.out.println("U slucaju da ste vec registrovani korisnik/ca i zelite da se ulogujete na vas nalog, ukucajte broj 2");
        int x = sc.nextInt();

        if (x == 1) {
            // poziva se metoda za registraciju korisnika
            registracijaKorisnika();
            System.out.println("Uspesno ste se registrovali");
            System.out.println("Zelite li da se ulogujete?DA/NE");
            String z = sc.next();
            if(z.equals("DA")){
                int id = logIn();
                pocetniMeni(id);
            }
            else if(z.equals("NE")){
                System.out.println("Kraj programa");
                System.exit(0);
            }
        } else if (x == 2) {
            //poziva se metoda za log in, i cuva se id broj korisnika, kako bi se kasnije promene vezivale za konkretnog korisnika
            int id = logIn();
            if(id != 0){
                pocetniMeni(id);
            }
        }
    }


    //pocetni meni - prvi po ulasku u aplikaciju
    public static void pocetniMeni(int id) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dobrodosli u Vas virtuelni novcanik!");
        System.out.println();
        System.out.println("1. Otvaranje novog racuna");
        System.out.println("2. Ukidanje postojeceg racuna.");
        System.out.println("3. Pristup racunima. ");
        System.out.println("4. Log out. ");
        System.out.println();
        System.out.println("Otkucajte broj ispred poruke za zeljenu radnju");
        int y = sc.nextInt();
        if(y == 1){
            BankovniRacun br = new BankovniRacun(id);
            br.otvaranjeRacuna(id);
            System.out.println("Racun je uspesno otvoren.");
            pocetniMeni(id);
        }
        else if(y == 2) {
            BankovniRacun br = new BankovniRacun(id);
            ArrayList<BankovniRacun> racuni = br.racuniKorisnika(id);
            if (racuni.size() == 1) {
                br.gasenjeRacuna(id, br.getBrojRacuna());
                pocetniMeni(id);
            } else if (racuni.size() > 1) {
                int brojac = 1;
                System.out.println("Izaberite kom racunu zelite da pristupite: ");
                for(BankovniRacun br2 : racuni){
                    System.out.println(brojac + ". " + br2.getBrojRacuna());
                    brojac++;
                }
                int b = sc.nextInt();
                br.gasenjeRacuna(id, racuni.get(b - 1).getBrojRacuna());
                pocetniMeni(id);
            } else {
                System.out.println("Nemate otvorenih racuna.");
                pocetniMeni(id);
            }
        }
        else if(y == 3){
            BankovniRacun br1 = new BankovniRacun(id);
            ArrayList<BankovniRacun> racuni = br1.racuniKorisnika(id);
            if(racuni.size() == 1) {
                meniKonkretnogRacuna(id, racuni.get(1).getBrojRacuna());
            }
            else if(racuni.size() == 0){
                System.out.println("Nemate otvorenih racuna.");
                pocetniMeni(id);
            }
            else{
                int brojac = 1;
                System.out.println("Izaberite kom racunu zelite da pristupite: ");
                for(BankovniRacun br : racuni){
                    System.out.println(brojac + ". " + br.getBrojRacuna());
                    brojac++;
                }
                int b = sc.nextInt();
                meniKonkretnogRacuna(id, racuni.get(b - 1).getBrojRacuna());
            }
        }
        else if(y == 4){
            System.out.println("Uspesno ste se izlogovali. Hvala sto ste koristili nasu aplikaciju!");
            System.exit(0);
        }
    }
    public static void meniKonkretnogRacuna(int id,int brojRacuna) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vas racun broj: "+ brojRacuna );
        System.out.println();
        System.out.println("1.Uplata na racun");
        System.out.println("2.Isplata sa racuna");
        System.out.println("3. Konvertovanje novca");
        System.out.println("4. Online placanja");
        System.out.println("5. Povratak na prethodni meni.");
        System.out.println("6. Log out ");
        int z = sc.nextInt();
        if(z == 1) {
            String a = "DA";
            while (a.equals("DA")) {
                System.out.println("Unesite zeljeni iznos");
                int n = sc.nextInt();
                BankovniRacun br = new BankovniRacun(id, brojRacuna);
                br.uplata(n, id, brojRacuna);
                System.out.println("Novo stanje na vasem racunu je: " + br.getStanjeNaRacunu());
                System.out.println("Zelite li novu uplatu?DA/NE");
                a = sc.next();
                if(a.equals("NE")){
                    meniKonkretnogRacuna(id, brojRacuna);
                }
            }
        }
        else if(z == 2){
            String b = "DA";
            while(b.equals("DA")){
            System.out.println("Unesite zeljeni iznos: ");
            double m = sc.nextDouble();
            BankovniRacun br = new BankovniRacun(id);
            br.isplata(m, id, brojRacuna);
            System.out.println("Novo stanje na vasem racunu je: " + br.getStanjeNaRacunu());
            System.out.println("Zelite li novu isplatu?DA/NE");
            b = sc.next();
            if(b.equals("NE")){
                meniKonkretnogRacuna(id, brojRacuna);
            }
            }
        }
        else if(z ==3){
            System.out.println("Deo aplikacije u izradi.");
            meniKonkretnogRacuna(id, brojRacuna);
        }
        else if(z ==4) {

            // pravljenje tabele placanja za konkretan racun, ukoliko tabela ne postoji
            String excelName = "TabelaKorisnika.xlsx";

            FileInputStream fip = new FileInputStream(excelName);
            XSSFWorkbook wb = new XSSFWorkbook(fip);
            boolean postoji = false;
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                if (wb.getSheetName(i).equals("Placanja" + brojRacuna)){
                    postoji = true;
                    break;
            }
            }
            if(!postoji){
                kreirajStranuPlacanja(id, brojRacuna);
            }

            String a = "DA";
            while (a == "DA") {
                Placanja p = uplatnica(id, brojRacuna);
                double isplata = p.getIznos();
                p.isplata(isplata, id, brojRacuna);
                p.upisUExcelTabeluPlacanja(p);
                System.out.println("Zelite li novo placanje?DA/NE");
                a = sc.next();
                if(a.equals("NE")){
                    meniKonkretnogRacuna(id, brojRacuna);
                }
            }
        }
        else if(z == 5){
            pocetniMeni(id);
        }
        else if(z == 6){
            System.out.println("Uspesno ste se izlogovali. Hvala sto ste koristili aplikaciju nasu aplikaciju!");
            System.exit(0);
        }
    }

    public static Placanja uplatnica(int id, int brojRacuna){
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
        while(t) {
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
                    if (a.equals("DA")) {
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
