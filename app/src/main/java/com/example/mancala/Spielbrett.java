/**
 * @(#)Spielbrett.java
 *
 * Das Spielbrett
 *
 * Es werden die Mulden mit den Spielsteinen sowie den Spielern am Spielbrett erzeugt.
 * Das Spielbrett beinhaltet die gesamte Spiellogik und realisiert jeweils den Spielzug des Spielers.
 * Alle Spielrelevanten Dinge werden hier hinterlegt und koennen abgefragt werden.
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

public class Spielbrett {
	
    // die zwei Spieler am Brett
	private Spieler eigener;
	private Spieler gegner;

    // das Mulden Objekt
	private Mulde mulde;


    // zwei final Variabeln, zur Unterscheidung des Muldentyps
	private final int END_MULDE = 0;
	private final int PLAY_MULDE = 1;
	
    // flag
	private boolean finished = false;


	public Spielbrett () {

	}

	/**
     * Initialisiert die beiden Spieler am Brett.
     * Setzt den einen Spieler auf aktiv den anderen passiv.
     * 
	 * @param aktiv der Spieler der bei der Eroeffnung aktiv ist
	 */
	public void initSpieler (boolean aktiv) {

		eigener = new ManuellerPlayer();
		gegner = new RemotePlayer();

        eigener.setAktiv(aktiv);
		gegner.setAktiv(!aktiv);
	}
	
    public void initAutoPlayer(boolean aktiv) {

        eigener = new Autoplayer(this);
        gegner = new RemotePlayer();

        eigener.setAktiv(aktiv);
        gegner.setAktiv(!aktiv);
    }

	/**
	 * Initialisiert die Mulden mit den Spielsteinen am
     * Spielbrett in der Grundaufstellung.
	 */
	public void initMulden () {
		
		mulde = new Mulde();
		
		for (int i = 1; i <= 6; i ++) {
			
			mulde.erzeugeMulde(PLAY_MULDE, 6, i, eigener);
		}
		mulde.erzeugeMulde(END_MULDE, 0, 7, eigener);

		for (int i = 1; i <= 6; i ++) {
			
			mulde.erzeugeMulde(PLAY_MULDE, 6, i+7, gegner);
		}
		mulde.erzeugeMulde(END_MULDE, 0, 14, gegner);

	}
    
	/**
     * Die Methode realisiert jeden Zug am Spielbrett und 
     * beinhaltet damit auch die gesamte Logik.
     * 
	 * @param muldenNummer die Muldennummer, die gespielt werden soll
	 */
	public void makeMove (int muldenNummer) {
		
        if (isRightMove(muldenNummer)) {

            if(eigener.isAktiv()){
			
				eigener.setLetzterZug(muldenNummer);
				//model.notifyObservers("makeMove");
			}
			else {
       
                //model.notifyObservers("moveGegner");
                gegner.setLetzterZug(muldenNummer);
            }

			int steine = mulde.getAnzSteine();
			mulde.setAnzSteine(0);

			for (int i = 0; i < steine; i++) {

				mulde.setNaechsteMulde();

				if (mulde.getMuldenTyp() != END_MULDE || mulde.getSpieler().isAktiv()) {

					mulde.setAnzSteine(mulde.getAnzSteine() + 1);
				}
				else {
					i--;
				}
			}
						
			if (mulde.getMuldenTyp() == PLAY_MULDE) {

                switchPlayer();
				
				if (mulde.getAnzSteine() == 1 && !(mulde.getSpieler().isAktiv())) {
					
					makeGoodMove(mulde.getMuldenNummer());
				}
			}
			
			if (isLastMove(muldenNummer)){

                finish(muldenNummer);
            }
		}
	}

	/**
     * Hilfsmethode, um zu ueberpruefen, ob es sich um einen gueltigen Zug handelt
     * 
	 * @param muldenNummer
	 * @return legaler Zug
	 */
	public boolean isRightMove(int muldenNummer){
		
		mulde.setAktuelleMulde(muldenNummer);
		return mulde.getMuldenTyp() == PLAY_MULDE && mulde.getAnzSteine() > 0;
	}
	
	/**
     * Hilfsmethode zu makeMove()
     * 
     * Macht nach dem Regelwerk einen besonders guten Zug.
     * "Trifft der letzte Stein eine eigene leere Mulde, so
     * geh�ren dem Spieler sein Stein und die der gegen�berliegenden
     * Mulde"
     * 
	 * @param muldenNummer
	 */
	private void makeGoodMove(int muldenNummer) {

		int summe = 0;
		
		summe += mulde.getAnzSteine();
		mulde.setAnzSteine(0);
		mulde.setAktuelleMulde(14-muldenNummer);
		summe += mulde.getAnzSteine();
		mulde.setAnzSteine(0);
		
		if(muldenNummer < 7){

            mulde.setAktuelleMulde(7);
			mulde.setAnzSteine(mulde.getAnzSteine() + summe);
		}
		else {
			
            mulde.setAktuelleMulde(14);
			mulde.setAnzSteine(mulde.getAnzSteine() + summe);
		}
	}

	/**
     * Zieht den letzten Zug zum beenden des Spiels.
     * 
	 * @param muldenNummer
	 */
	private void finish( int muldenNummer) {

		int summe = 0;
		
		if (muldenNummer < 7)

            mulde.setAktuelleMulde(8);
		else
			mulde.setAktuelleMulde(1);
		
		for (int i = 0; i < 6; i++) {

			summe += mulde.getAnzSteine();
			mulde.setAnzSteine(0);
			mulde.setNaechsteMulde();
		}
		mulde.setAnzSteine(mulde.getAnzSteine() + summe);
        setFinished(true);
	}
	
	/**
     * Ueberprueft, ob der letzte Zug gemacht wurde.
     * 
	 * @param muldenNummer
	 * @return 
	 */
	private boolean isLastMove (int muldenNummer) {
		
		boolean status = true;
		
		if (muldenNummer > 7)
			
            mulde.setAktuelleMulde(8);
		else
			
            mulde.setAktuelleMulde(1);
		
		for (int i = 0; i < 6; i++) {
			
			if (mulde.getAnzSteine() > 0)
				
                status = false;
			
			mulde.setNaechsteMulde();
		}
		
		return status;
	}
	
    /**
     * Methode setzt die Spielmulden neu in dem Fall,
     * dass ein Spiel verworfen wird und neu initialisiert
     * werden soll.
     */
    public void setMuldenNeu () {
        
        mulde.setAktuelleMulde(1);
        
        for (int i = 1; i <= 6; i ++) {
            
            mulde.setAnzSteine(6);
            mulde.setNaechsteMulde();
            }
        mulde.setAnzSteine(0);
        mulde.setNaechsteMulde();

        for (int i = 1; i <= 6; i ++) {
            
            mulde.setAnzSteine(6);
            mulde.setNaechsteMulde();
        }
        mulde.setAnzSteine(0);
    }

	/**
	 * @return die erste Mulde "A1"
	 */
	public Mulde getErsteMulde () {
		
		mulde.setAktuelleMulde(1);
		return mulde;
	}
	
	/**
	 * Setzt die beiden Spieler am Spielbrett:
     * einen auf aktiv, den Andere auf passiv
	 */
	public void switchPlayer () {
		
		eigener.setAktiv(!eigener.isAktiv());
		gegner.setAktiv(!gegner.isAktiv());
	}
	
	/**
	 * @param  muldenNummer
	 * @return gibt das gewuenschte Mulden Objekt zurueck
	 */
	public Mulde getMulde (int muldenNummer) {

		mulde.setAktuelleMulde(muldenNummer);
		
		return mulde;
	}

	/**
	 * @return das Mulden Objekt 
	 */
	public Mulde getMulde() {
		return mulde;
	}

	/**
	 * @param mulde
	 */
	public void setMulde(Mulde mulde) {
		this.mulde = mulde;
	}


    /**
     * @return der eigene Spieler
     */
    public Spieler getEigenerSpieler() {
        return eigener;
    }

    /**
     * @return der gegnerische Spieler
     */
    public Spieler getGegnerSpieler() {
        return gegner;
    }

	/**
	 * @return ist das Spiel beendet
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * setzt das Spiel auf beendet
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}


}
