package Datum;

public class Datum {
        private int dan;
        private int mesec;
        private int godina;

        public Datum(int dan, int mesec, int godina) throws ArithmeticException {
            boolean dobarDatum = proveriDatum(dan, mesec, godina);

            if(dobarDatum == false){
                //System.out.println("Datum.Datum nije dobar");
                ArithmeticException greska = new ArithmeticException("Datum.Datum nije dobar");
                throw greska;
            }

            this.dan = dan;
            this.mesec = mesec;
            this.godina = godina;
        }

        public int getDan() {
            return dan;
        }

        public void setDan(int dan) throws ArithmeticException {
            boolean dobarDatum = proveriDatum(dan, mesec, godina);

            if(dobarDatum == false){
                //System.out.println("Datum.Datum nije dobar");
                ArithmeticException greska = new ArithmeticException("Datum.Datum nije dobar");
                throw greska;
            }
            this.dan = dan;
        }

        public int getMesec() {
            return mesec;
        }

        public void setMesec(int mesec) throws ArithmeticException {
            boolean dobarDatum = proveriDatum(dan, mesec, godina);

            if(dobarDatum == false){
                //System.out.println("Datum.Datum nije dobar");
                ArithmeticException greska = new ArithmeticException("Datum.Datum nije dobar");
                throw greska;
            }
            this.mesec = mesec;
        }

        public int getGodina() {
            return godina;
        }

        public void setGodina(int godina) throws ArithmeticException {
            boolean dobarDatum = proveriDatum(dan, mesec, godina);

            if(dobarDatum == false){
                //System.out.println("Datum.Datum nije dobar");
                ArithmeticException greska = new ArithmeticException("Datum.Datum nije dobar");
                throw greska;
            }
            this.godina = godina;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append(dan).append("-").append(mesec).append("-").append(godina);

            return sb.toString();
        }

        private static boolean proveriDatum(int dan, int mesec, int godina){
            return true;
        }
    }

