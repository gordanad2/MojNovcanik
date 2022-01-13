package Prijava;

import Osoba.Osoba;

public class LogIn {
    protected Osoba korisnik;
    protected RegistrovaniKorisnici podaci;

    public LogIn(Osoba korisnik, RegistrovaniKorisnici podaci) {
        this.korisnik = korisnik;
        this.podaci = podaci;
    }

    public Osoba getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Osoba korisnik) {
        this.korisnik = korisnik;
    }

    public RegistrovaniKorisnici getPodaci() {
        return podaci;
    }

    public void setPodaci(RegistrovaniKorisnici podaci) {
        this.podaci = podaci;
    }

    @Override
    public String toString() {
        return "LogIn{" +
                "korisnik=" + korisnik +
                ", podaci=" + podaci +
                '}';
    }
}
