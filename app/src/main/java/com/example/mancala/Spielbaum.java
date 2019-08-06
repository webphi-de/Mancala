/**
 * @(#)Spielbaum.java
 *
 * Der Spielbaum ist die Klasse, die den Autoplayer Intelligenz einhaucht. der Einstieg ist die
 * Methode berechneZug(), die der Autoplayer aufruft. Sie lässt sich vom MinIMax-Algorithmus mit
 * Alpha/Beta-Schnitt und 2 unterschiedlichen Bewertungsfunktionen den besten Zug berechnen.
 * Für die Bewertungsfunktion lassen sich einige Parameter von außen steuern und so dem Autoplayer
 * unterschiedliche Spielcharakteristik einstellen. Zusätzlich lässt sich die Spiektiefe einstellen.
 * 5 Spielfeldtiefen benötigen auf meinem Laptop (i5 8250U) ca. eine gute Sekunde, in der bis zu
 * 1 Million Spielzüge berechnet werden.
 *
 * @project Initially a Mancala-Project during the Software Engineering Practical Course in WS/SS 2005/06
 *          at the University of Bayreuth from Melanie and Stefan Kannegießer.
 *          Now porting as an Android App from 2019 during a training course at ALP Dillingen.
 * @author  Melanie and Stefan Kannegießer
 * @version v1.0 17.03.2006
 * @since   JDK 1.5.0
 * @history v1.00 17.03.2006 - first executable version
 *          v1.10 05.08.2019 - (new version) first executable version
 *          with 2 manual players or against an automatic player
 *
 */
package com.example.mancala;

public class Spielbaum {

    private static final int POS_INFINITY = (int)Double.POSITIVE_INFINITY;
    private static final int NEG_INFINITY = (int)Double.NEGATIVE_INFINITY;
    
    private static final int END_MULDE = 0;
    private static final int PLAY_MULDE = 1;
    
    private int STEINE_DIFFERENZ = 1;
    private int KALAHA_DIFFERENZ = 1;
    private int ZUG_13 = 1;
    private int CAPTURE_ZUG = 1;
    private int WIEDERHOLTER_ZUG = 1;
    private int TIEFE = 9;//9
    
    private int durchsuchteSpielbretter = 0;
    private long benoetigteZeit = 0;
    private int bewertungsFkt = 0;//0
    
    private int steineDifferenz;
    private int kalahaDifferenz;
    private int zug13;
    private int captureZug ;
    private int wiederholterZug;
    private int retValue;

    
    
    public Spielbaum () {
        
    }
    
    public int bewertungsFkt (Spielbrett spielbrett) {
        
        if (bewertungsFkt == 0)
            
            return bewertung_1(spielbrett);
        else
            
            return bewertung_2(spielbrett);
    }
    /**
     * Bewertungsfunktion:
     * 
     * @param   spielbrett Spielbrett
     * @return  die Bewrtung
     */
    public int bewertung_1 (Spielbrett spielbrett) {

        steineDifferenz = 0; kalahaDifferenz = 0; zug13 = 0; captureZug = 0; wiederholterZug = 0; retValue = 0;
        
        kalahaDifferenz = spielbrett.getMulde(7).getAnzSteine() - spielbrett.getMulde(14).getAnzSteine();
        
        for (int i = 1; i <= 6; i++) {
            
            if (spielbrett.getMulde(i).getAnzSteine() == 13) {
                
                zug13 += spielbrett.getMulde(14-i).getAnzSteine() / 2;
            }
            steineDifferenz += spielbrett.getMulde(i).getAnzSteine();
            spielbrett.setMulde(spielbrett.getMulde(i));
            
            for (int j = 0; j < spielbrett.getMulde(i).getAnzSteine(); j++){
                
                spielbrett.getMulde().setNaechsteMulde();

                
                if (spielbrett.getMulde().getMuldenTyp() != END_MULDE || spielbrett.getMulde().getSpieler() instanceof Autoplayer) {
                }
                else {
                    j--;
                }
            }
            
            if(spielbrett.getMulde().getAnzSteine() == 0 && spielbrett.getMulde().getMuldenTyp() == PLAY_MULDE && spielbrett.getMulde().getSpieler() instanceof Autoplayer ){
                
                captureZug += spielbrett.getMulde(14-i).getAnzSteine() / 2;
            }
            
            if(spielbrett.getMulde().getMuldenTyp() == END_MULDE){
                
                wiederholterZug++;
                
                if(spielbrett.getMulde().getAnzSteine() > 16) {
                    
                    STEINE_DIFFERENZ += 2;
                    KALAHA_DIFFERENZ --;
                    WIEDERHOLTER_ZUG --;
                }
            }
        }
        for (int i = 8; i <= 13; i++) {
            
            if (spielbrett.getMulde(i).getAnzSteine() == 13){
             
                zug13 -= spielbrett.getMulde(14-i).getAnzSteine() / 2;
            }
            steineDifferenz -= spielbrett.getMulde(i).getAnzSteine();
            spielbrett.setMulde(spielbrett.getMulde(i));
            
            for (int j = 0; j < spielbrett.getMulde(i).getAnzSteine(); j++){
                
                spielbrett.getMulde().setNaechsteMulde();

                if (spielbrett.getMulde().getMuldenTyp() != END_MULDE || spielbrett.getMulde().getSpieler() instanceof RemotePlayer) {
                }
                else {
                    j--;
                }
            }
            
            if(spielbrett.getMulde().getAnzSteine() == 0 && spielbrett.getMulde().getMuldenTyp() == PLAY_MULDE && spielbrett.getMulde().getSpieler() instanceof RemotePlayer ){
                
                captureZug -= spielbrett.getMulde(14-i).getAnzSteine() / 2;
            }

            if(spielbrett.getMulde().getMuldenTyp() == END_MULDE){
                
                wiederholterZug--;

                if(spielbrett.getMulde().getAnzSteine() > 16)
                    STEINE_DIFFERENZ += 1;
            }
        }
        retValue =   STEINE_DIFFERENZ * kalahaDifferenz + 
                     KALAHA_DIFFERENZ * steineDifferenz + 
                     ZUG_13 * zug13 + 
                     CAPTURE_ZUG * captureZug + 
                     WIEDERHOLTER_ZUG * wiederholterZug;
        
        return retValue;
    }

    public int bewertung_2 (Spielbrett spielbrett) {

        steineDifferenz = 0; kalahaDifferenz = 0; zug13 = 0; captureZug = 0; wiederholterZug = 0; retValue = 0;
        
        kalahaDifferenz = spielbrett.getMulde(7).getAnzSteine() - spielbrett.getMulde(14).getAnzSteine();
        
        for (int i = 1; i <= 6; i++) {
            
            if (spielbrett.getMulde(i).getAnzSteine() == 13) {
                
                if (spielbrett.getMulde(14-i).getAnzSteine() < 3)
                    
                    zug13++;
                else
                    
                    zug13 += 2;
            }
            steineDifferenz += spielbrett.getMulde(i).getAnzSteine();
            spielbrett.setMulde(spielbrett.getMulde(i));
            
            for (int j = 0; j < spielbrett.getMulde(i).getAnzSteine(); j++){
                
                spielbrett.getMulde().setNaechsteMulde();

                if (spielbrett.getMulde().getMuldenTyp() != END_MULDE || spielbrett.getMulde().getSpieler() instanceof Autoplayer) {
                }
                else {
                    j--;
                }
            }
            if(spielbrett.getMulde().getAnzSteine() == 0 && spielbrett.getMulde().getMuldenTyp() == PLAY_MULDE && spielbrett.getMulde().getSpieler() instanceof Autoplayer ){
                
                if (spielbrett.getMulde(14-i).getAnzSteine() < 3)
                    
                    captureZug++;
                else
                    
                    captureZug += 2;
            }
            if(spielbrett.getMulde().getMuldenTyp() == END_MULDE){
                
                wiederholterZug++;
            }
        }
        for (int i = 8; i <= 13; i++) {
            
            if (spielbrett.getMulde(i).getAnzSteine() == 13){
             
                if (spielbrett.getMulde(14-i).getAnzSteine() < 3)
                
                    zug13--;
                else
                    
                    zug13 -= 2;
            }
            steineDifferenz -= spielbrett.getMulde(i).getAnzSteine();
            spielbrett.setMulde(spielbrett.getMulde(i));
            
            for (int j = 0; j < spielbrett.getMulde(i).getAnzSteine(); j++){
                
                spielbrett.getMulde().setNaechsteMulde();

                if (spielbrett.getMulde().getMuldenTyp() != END_MULDE || spielbrett.getMulde().getSpieler() instanceof RemotePlayer) {
                }
                else {
                    j--;
                }
            }
            if(spielbrett.getMulde().getAnzSteine() == 0 && spielbrett.getMulde().getMuldenTyp() == PLAY_MULDE && spielbrett.getMulde().getSpieler() instanceof RemotePlayer ){
                
                if (spielbrett.getMulde(14-i).getAnzSteine() < 3)
                    
                    captureZug--;
                else
                    
                    captureZug -= 2;
            }
            if(spielbrett.getMulde().getMuldenTyp() == END_MULDE){
                
                wiederholterZug--;
            }
        }
        retValue =   STEINE_DIFFERENZ * kalahaDifferenz + 
                            KALAHA_DIFFERENZ * steineDifferenz + 
                            ZUG_13 * zug13 + 
                            CAPTURE_ZUG * captureZug + 
                            WIEDERHOLTER_ZUG * wiederholterZug;
        return retValue;
    }


    /**
     * Methode kopiert ein Spielbrett mit den aktuellen Einstellungen
     * 
     * @param  spielbrett
     * @return eine Kopie des aktuellen Spielbretts
     */
    private Spielbrett copy (Spielbrett spielbrett) {
        
        Spielbrett spielbrettCopy = new Spielbrett();
        
        spielbrettCopy.initAutoPlayer(spielbrett.getEigenerSpieler().isAktiv());
        spielbrettCopy.initMulden();
        
        for (int i = 1; i <= 14; i++) {
            
            spielbrettCopy.getMulde(i).setAnzSteine(spielbrett.getMulde(i).getAnzSteine());
        }
        ;
        return spielbrettCopy;
    }

    /**
     * MinIMax-Algorithmus mit Alpha/Beta-Schnitt
     * 
     * @param  spielbrett die jeweiligen Spielbretter
     * @param  tiefe      die Suchbaumtiefe
     * @param  alpha      die sich veraendernden Werte aus der Fkt selbst ("Knotenwerte")
     * @param  beta       die sich veraendernden Werte aus der Fkt selbst ("Knotenwerte")
     * @return wert       dessen Maximum unseren Spielzug ergibt
     */
    private int minimaxWert (Spielbrett spielbrett, int tiefe, int alpha, int beta) { 

        durchsuchteSpielbretter ++;
        
        Spielbrett tmp;
        int minimax_tmp;
        int minimax_lokal;

        if (spielbrett.getEigenerSpieler().isAktiv())//getEigenerSpieler

            minimax_lokal = alpha;
        else 
        
            minimax_lokal = beta;

        if (tiefe == 0 || spielbrett.isFinished()) {

            return bewertungsFkt (spielbrett);
        }
    
        else {
            
        
            if (spielbrett.getEigenerSpieler().isAktiv()) {
       
                for (int mulde = 1; mulde <= 6; mulde++) {
          
                    tmp = copy (spielbrett);
          
                    if (tmp.isRightMove (mulde)) {

                        tmp.makeMove (mulde);
                        minimax_tmp = minimaxWert (tmp, tiefe - 1, alpha, beta);
                          
                        minimax_lokal = java.lang.Math.max (minimax_tmp, minimax_lokal);
                        alpha = minimax_lokal;
              
                        if (alpha >= beta) 
                            
                            return beta;
                    }
                }
            }
                        
            
            else {
                    
                for (int mulde = 8; mulde <= 13; mulde++) {
                        
                    tmp = copy(spielbrett);

                    if (tmp.isRightMove(mulde)) {

                        tmp.makeMove(mulde);
                        minimax_tmp = minimaxWert(tmp, tiefe - 1, alpha, beta);

                        minimax_lokal = java.lang.Math.min(minimax_tmp, minimax_lokal);
                        beta = minimax_lokal;

                        if (beta <= alpha)

                            return alpha;
                    }
                }
            }
        return minimax_lokal;
        
        }
    }

    /**
     * Methode die aus dem Min/Max-Algorithmus den besten Spielzug liefert.
     * 
     * @param  spielbrett das Spielbrett fuer das wir eine Bewertung suchen
     * @param  tiefe      wieviele Zuege wir vorausberechnen wollen
     * @return zug        der errechnete Spielzug
     */
    public int berechneZug (Spielbrett spielbrett, int tiefe) {
        
        // Statistik
        durchsuchteSpielbretter = 0;
        long x = System.currentTimeMillis();
        
        int[] wert = new int[6];

        Spielbrett spiel_tmp;

        for (int mulde = 1; mulde <= 6; mulde++)  {

            if (spielbrett.isRightMove (mulde)) {
            
                spiel_tmp = copy (spielbrett);
                spiel_tmp.makeMove (mulde);
                wert[mulde-1] = minimaxWert (spiel_tmp, tiefe, NEG_INFINITY, POS_INFINITY);//TIEFE
            }
        }

        int zug = -1;  
        int max = NEG_INFINITY;
      
        for (int mulde = 1; mulde <= 6; mulde++) { 
        
            if ((wert[mulde-1] >= max) && spielbrett.isRightMove(mulde)){
                    
                zug = mulde;  
                max = wert[mulde-1]; 
            }
        }

        // Statistik
        setDurchsuchteSpielbretter(durchsuchteSpielbretter);
        long y = System.currentTimeMillis();
        setBenoetigteZeit(y-x);
        
        return zug ;
    }

    public long getBenoetigteZeit() {
        return benoetigteZeit;
    }

    public void setBenoetigteZeit(long benoetigteZeit) {
        this.benoetigteZeit = benoetigteZeit;
    }

    public int getDurchsuchteSpielbretter() {
        return durchsuchteSpielbretter;
    }

    public void setDurchsuchteSpielbretter(int durchsuchteStellungen) {
        this.durchsuchteSpielbretter = durchsuchteStellungen;
    }

    public void CAPTURE_ZUG(int capture_zug) {
        CAPTURE_ZUG = capture_zug;
    }

    public void KALAHA_DIFFERENZ(int kalaha_differenz) {
        KALAHA_DIFFERENZ = kalaha_differenz;
    }

    public void STEINE_DIFFERENZ(int steine_differenz) {
        STEINE_DIFFERENZ = steine_differenz;
    }

    public void TIEFE(int tiefe) {
        TIEFE = tiefe;
    }

    public int TIEFE() {
        return TIEFE;
    }

    public void WIEDERHOLTER_ZUG(int wiederholter_zug) {
        WIEDERHOLTER_ZUG = wiederholter_zug;
    }

    public void ZUG_13(int zug_13) {
        ZUG_13 = zug_13;
    }

    public void setBewertungsFkt(int bewertungsFkt) {
        this.bewertungsFkt = bewertungsFkt;
    }

}
