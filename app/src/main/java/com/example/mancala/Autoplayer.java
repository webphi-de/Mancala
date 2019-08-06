/**
 * @(#)Autoplayer.java
 *
 * Er vermittelt das Spielbrett, erzeugt einen neuen Speilbaum und setzt danach die unterschiedlichen
 * Bewertungsfunktionen am Spielbaum. Er vermittelt den Spielzug an den Spielbaum, lässt diesen
 * berechnen und schickt ihn zurück an den Client, der den Zug ausführt.
 *
 * @project Initially a Mancala-Project during the Software Engineering Practical Course in WS/SS 2005/06
 *          at the University of Bayreuth from Melanie and Stefan Kannegießer.
 *          Now porting as an Android App from 2019 during a training course at ALP Dillingen.
 * @author  Stefan Kannegießer
 * @version v1.0 17.03.2006
 * @since   JDK 1.5.0
 * @history v1.00 17.03.2006 - first executable version
 *          v1.10 05.08.2019 - (new version) first executable version
 *          with 2 manual players or against an automatic player
 *
 */
package com.example.mancala;

public class Autoplayer extends Spieler {

    private int muldenNummer;
    private Spielbrett spielbrett;
    private Spielbaum spielbaum;

    public Autoplayer(Spielbrett spielbrett) {
        super();
        muldenNummer = 0;
        this.spielbrett = spielbrett;
        spielbaum = new Spielbaum();
        setBewertungsFaktoren_lukas();
    }
    
    public void makeMove () {

        //muldenNummer = berechneZug();
        int zug = spielbaum.berechneZug(spielbrett, 7);
        //spielbrett.makeMove();
        muldenNummer = zug;
    }

    private void setBewertungsFaktoren_johannes () {

        this.getSpielbaum().CAPTURE_ZUG(6);
        this.getSpielbaum().KALAHA_DIFFERENZ(5);
        this.getSpielbaum().STEINE_DIFFERENZ(2);
        this.getSpielbaum().WIEDERHOLTER_ZUG(7);
        this.getSpielbaum().ZUG_13(4);
    }
    private void setBewertungsFaktoren_lukas () {

        this.getSpielbaum().CAPTURE_ZUG(7);
        this.getSpielbaum().KALAHA_DIFFERENZ(6);
        this.getSpielbaum().STEINE_DIFFERENZ(4);
        this.getSpielbaum().WIEDERHOLTER_ZUG(5);
        this.getSpielbaum().ZUG_13(2);
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
