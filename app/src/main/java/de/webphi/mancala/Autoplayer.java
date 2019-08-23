/**
 * @(#)Autoplayer.java
 *
 * Er vermittelt das Spielbrett, erzeugt einen neuen Speilbaum und setzt danach die unterschiedlichen
 * Bewertungsfunktionen am Spielbaum. Er vermittelt den Spielzug an den Spielbaum, lässt diesen
 * berechnen.
 *
 * @project Initially a Mancala-Project during the Software Engineering Practical Course in WS/SS 2005/06
 *          at the University of Bayreuth from Melanie and Stefan Kannegießer.
 * //old @version v1.0 17.03.2006
 * //old @since   JDK 1.5.0
 * //old @history v1.00 17.03.2006 - result of practical course to play via rmi against chair ai1 at university of bayreuth
 *
 * @author  Stefan Kannegieße
 * @since   JDK 8, Android API 29
 * @version
 * @history v0.10 01.08.2019 - porting as an Android App. * @history v1.00 17.03.2006 - first executable version
 *          v1.10 05.08.2019 - (new version) first executable version
 *          with 2 manual players or against an automatic player
 *
 */
package de.webphi.mancala;

public class Autoplayer extends Spieler {

    private int muldenNummer;
    private Spielbrett spielbrett;
    private Spielbaum spielbaum;

    public int getAnzSteineInMulde() {
        return spielbrett.getMulde().getAnzSteine();
    }

    private int anzSteineInMulde;

    public Autoplayer(Spielbrett spielbrett) {
        super();
        muldenNummer = 0;
        this.spielbrett = spielbrett;
        spielbaum = new Spielbaum();
    }
    
    public void makeMove () {

        //@todo: 2 argument spieltiefe weg machen. wird über settings gesetzt
        int zug = spielbaum.berechneZug(spielbrett, 7);
        muldenNummer = zug;
    }

    private void setBewertungsFaktoren_johannes () {

        this.getSpielbaum().CAPTURE_ZUG(6);
        this.getSpielbaum().KALAHA_DIFFERENZ(5);
        this.getSpielbaum().STEINE_DIFFERENZ(2);
        this.getSpielbaum().WIEDERHOLTER_ZUG(7);
        this.getSpielbaum().ZUG_13(4);
    }
    private void setBewertungsFaktoren_stefan () {

        this.getSpielbaum().CAPTURE_ZUG(8);
        this.getSpielbaum().KALAHA_DIFFERENZ(4);
        this.getSpielbaum().STEINE_DIFFERENZ(7);
        this.getSpielbaum().WIEDERHOLTER_ZUG(-2);
        this.getSpielbaum().ZUG_13(1);
    }
    private void setBewertungsFaktoren_lukas () {

        this.getSpielbaum().CAPTURE_ZUG(4);
        this.getSpielbaum().KALAHA_DIFFERENZ(5);
        this.getSpielbaum().STEINE_DIFFERENZ(6);
        this.getSpielbaum().WIEDERHOLTER_ZUG(3);
        this.getSpielbaum().ZUG_13(4);
    }


    public int getMove(){
        muldenNummer = spielbaum.berechneZug(spielbrett, 2);
        return  muldenNummer;
    }

    public int getMuldenNummer() {
        return muldenNummer;
    }

    public void setMuldenNummer(int muldenNummer) {
        this.muldenNummer = muldenNummer;
    }

   
    public Spielbaum getSpielbaum() {
        return spielbaum;
    }

    public void setSpielbaum(Spielbaum spielbaum) {
        this.spielbaum = spielbaum;
    }
    

}
