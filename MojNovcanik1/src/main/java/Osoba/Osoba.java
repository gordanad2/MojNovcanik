package Osoba;

public class Osoba {
        protected String ime;
        protected String prezime;
        protected String maticniBroj;
        protected String adresa;
        protected String kucniBroj;
        protected int brojStana;
        protected String eMail;

        public Osoba(String ime, String prezime, String maticniBroj, String adresa, String kucniBroj, int brojStana, String eMail) {
            this.ime = ime;
            this.prezime = prezime;
            this.maticniBroj = maticniBroj;
            this.adresa = adresa;
            this.kucniBroj = kucniBroj;
            this.brojStana = brojStana;
            this.eMail = eMail;
        }

        public Osoba(String ime, String prezime, String maticniBroj, String adresa, String kucniBroj, String eMail) {
            this.ime = ime;
            this.prezime = prezime;
            this.maticniBroj = maticniBroj;
            this.adresa = adresa;
            this.kucniBroj = kucniBroj;
            this.eMail = eMail;
        }

        public Osoba(String ime, String prezime, String maticniBroj, String adresa, String eMail) {
            this.ime = ime;
            this.prezime = prezime;
            this.maticniBroj = maticniBroj;
            this.adresa = adresa;
            this.eMail = eMail;
        }

    public Osoba() {

    }

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
            return ime;
        }

        public void setIme(String ime) {
            this.ime = ime;
        }

        public String getPrezime() {
            return prezime;
        }

        public void setPrezime(String prezime) {
            this.prezime = prezime;
        }

        public String getMaticniBroj() {
            return maticniBroj;
        }

        public void setMaticniBroj(String maticniBroj) {
            this.maticniBroj = maticniBroj;
        }

        public String getAdresa() {
            return adresa;
        }

        public void setAdresa(String adresa) {
            this.adresa = adresa;
        }

        public String getKucniBroj() {
            return kucniBroj;
        }

        public void setKucniBroj(String kucniBroj) {
            this.kucniBroj = kucniBroj;
        }

        public int getBrojStana() {
            return brojStana;
        }

        public void setBrojStana(int brojStana) {
            this.brojStana = brojStana;
        }

        public String geteMail() {
            return eMail;
        }

        public void seteMail(String eMail) {
            this.eMail = eMail;
        }

        @Override
        public String toString() {
            return "Osoba.Osoba{" +
                    "ime='" + ime + '\'' +
                    ", prezime='" + prezime + '\'' +
                    ", maticniBroj=" + maticniBroj +
                    ", adresa='" + adresa + '\'' +
                    ", kucniBroj='" + kucniBroj + '\'' +
                    ", brojStana=" + brojStana +
                    ", eMail='" + eMail + '\'' +
                    '}';
        }
    }

