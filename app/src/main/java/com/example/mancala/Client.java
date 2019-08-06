/**
 * @(#)Client.java
 *
 * Der Client ist das Bindeglied zwischen der MainActivity und dem ganzen Rest des Spiels
 * Er initialisiert das Spielbrett und setzt Spieler und Mulden daran.
 * Der Client handelt die Spielzüge aus.
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

class Client {

    private MainActivity mainActivity;

    private Spielbrett spielbrett;

    private int muldenNummer;

    public Client(MainActivity mainActivity){

        this.mainActivity = mainActivity;
        spielbrett = new Spielbrett();
    }

    /**
     * Startet die Applikation ohne Server Connection
     */
    public void startApplication_default () {

        //spielbrett = new Spielbrett();
        spielbrett.initSpieler(true);
        spielbrett.initMulden();

    }

    /**
     * Startet die Applikation mit Server Connection
     */
    public void startApplication_AutoPlayer() {

        //spielbrett = new Spielbrett();
        //spielbrett.initSpieler(true);
        spielbrett.initAutoPlayer(true);
        spielbrett.initMulden();
        //setBewertungsFaktoren_1();

        //setBewertungsFaktoren_1();
    }


    public void spiele() {

        int move = 0;

        if (!spielbrett.isFinished()) {

            if (spielbrett.getEigenerSpieler() instanceof Autoplayer &&
                    spielbrett.getEigenerSpieler().isAktiv()) {

                spielbrett.getEigenerSpieler().makeMove();
                muldenNummer = ((Autoplayer) spielbrett.getEigenerSpieler()).getMuldenNummer();

                switch (muldenNummer){
                    case 1 :
                        mainActivity.findViewById(R.id.a_1).performClick();
                        break;
                    case 2:
                        mainActivity.findViewById(R.id.a_2).performClick();
                        break;
                    case 3:
                        mainActivity.findViewById(R.id.a_3).performClick();
                        break;
                    case 4:
                        mainActivity.findViewById(R.id.a_4).performClick();
                        break;
                    case 5:
                        mainActivity.findViewById(R.id.a_5).performClick();
                        break;
                    case 6:
                        mainActivity.findViewById(R.id.a_6).performClick();
                        break;
                }

                //spielbrett.makeMove(((Autoplayer) spielbrett.getEigenerSpieler()).getMove());
            }
            else if (spielbrett.getGegnerSpieler() instanceof  Autoplayer &&
                    spielbrett.getGegnerSpieler().isAktiv()){

                spielbrett.getGegnerSpieler().makeMove();
                muldenNummer = ((Autoplayer) spielbrett.getGegnerSpieler()).getMuldenNummer();

                switch (muldenNummer){
                    case 8 :
                        mainActivity.findViewById(R.id.b_1).performClick();
                        break;
                    case 9:
                        mainActivity.findViewById(R.id.b_2).performClick();
                        break;
                    case 10:
                        mainActivity.findViewById(R.id.b_3).performClick();
                        break;
                    case 11:
                        mainActivity.findViewById(R.id.b_4).performClick();
                        break;
                    case 12:
                        mainActivity.findViewById(R.id.b_5).performClick();
                        break;
                    case 13:
                        mainActivity.findViewById(R.id.b_5).performClick();
                        break;
                }

            }
            else
                return;
        }
        else
            return;
    }




    public Spielbrett getSpielbrett() {
        return spielbrett;
    }

    public void setSpielbrett(Spielbrett spielbrett) {

        this.spielbrett = spielbrett;
    }
}
