/**
 * @(#)Mulde.java
 *
 * Die Mulde ist eine Objektklasse für das Spielbrett. Somit hat er die Objekte Spieler, Mulden und
 * dern Spielsteine.
 *
 * Die innere Klasse MuldenObj erzeugt Mulden Objekte mit den dazugehoerigen
 * Spielsteinen. Weitere  Klassenfelder dienen der Charakterisierung der Mulden
 * nach Endmulde bzw. Spielmulde und jede Mulde ist einem Spieler zugeordnet.
 *
 * Die Klasse Mulde erzeugt aus den MuldenObjekten eine doppelt verkettete Liste als Ring
 * auf die zusaetzlich mit einem Index zugegriffen werden kann.
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

public class Mulde {

	private MuldenObj mulde;

	/**
	 * default constructor
	 */
	public Mulde () {

		mulde = null;
	}

	/**
     * Erzeugt eine doppelt verkettete Liste als Ring
     * 
	 * @param muldenTyp    Spielmulde '0' oder Endmulde '1'
	 * @param anz          Die Spielsteine die in einer Mulde liegen
	 * @param muldenNummer Die Muldennummer
	 * @param spieler      Einer der beiden Spieler
	 */
	public void erzeugeMulde(int muldenTyp, int anz, int muldenNummer, Spieler spieler) {

		if (mulde == null) {

			mulde = new MuldenObj(muldenTyp, anz, muldenNummer, null, null, spieler);

			mulde.setVorgaengerMulde(mulde);
			mulde.setNachfolgerMulde(mulde);
		} 
		else {

			MuldenObj tmp = mulde;

			mulde = new MuldenObj(muldenTyp, anz, muldenNummer, tmp, tmp.getNachfolgerMulde(), spieler);

			tmp.getNachfolgerMulde().setVorgaengerMulde(mulde);
			tmp.setNachfolgerMulde(mulde);
		}
	}
	/**
     * Getter fuer die aktuelle Mulde
     * 
	 * @return Mulden Objekt
	 */
	public MuldenObj getAktuelleMulde () {
		
		return mulde;
	}

	/**
     * Setzt die aktuelle Mulde anhand der Nummer
     * 
	 * @param muldenNummer
	 */
	public void setAktuelleMulde (int muldenNummer) {
		
		while (mulde.getMuldenNummer() != muldenNummer) {

			setNaechsteMulde();
		}
	}
	
	/**
	 * Setzt die Datenstruktur auf die naechste Mulde
	 */
	public void setNaechsteMulde () {
		
		mulde = mulde.getNachfolgerMulde();
	}
	
	/**
	 * @return das naechste Mulden Objekt
	 */
	public MuldenObj getNaechsteMulde () {
		
		return mulde.getNachfolgerMulde();
	}

	/**
	 * @return die anzahl der Spielsteine in der Mulde
	 */
	public int getAnzSteine () {
		
		return mulde.getAnzSteine();
	}
	
	/**
	 * @return Endmulde '0' oder Spielmulde '1'
	 */
	public int getMuldenTyp () {
		
		return mulde.getMuldenTyp();
	}
	
	/**
     * Setzt die Anzahl der Spielsteine in einer Mulde
     * 
	 * @param anzahlSpielsteine
	 */
	public void setAnzSteine(int anzahlSpielsteine) {
		
		mulde.setAnzSteine(anzahlSpielsteine);
	}
	
	/**
	 * @return der Spieler dem diese Mulde gehoert
	 */
	public Spieler getSpieler () {
		
		return mulde.getSpieler();
	}
	
	/**
	 * @return die Nummer der Spielmulde
	 */
	public int getMuldenNummer () {
		
		return mulde.getMuldenNummer();
	}

	/**
     * Private innere Klasse fuer die Mulden Objekte
	 */
	private class MuldenObj {
		
		private MuldenObj vorgaengerMulde = null;
		private MuldenObj nachfolgerMulde = null;
		private int anzSteine;
		private int muldenTyp;
		private int muldenNummer;
		private Spieler spieler;

        private MuldenObj (int muldenTyp, int anzSteine, int muldenNummer, MuldenObj vorgaengegMulde, MuldenObj nachfolgerMulde, Spieler spieler) {
			
			this.muldenTyp = muldenTyp;
			this.anzSteine = anzSteine;
			this.vorgaengerMulde = vorgaengegMulde;
			this.nachfolgerMulde = nachfolgerMulde;
			this.muldenNummer = muldenNummer;
			this.spieler = spieler;
		}

        private MuldenObj getNachfolgerMulde() {
			return nachfolgerMulde;
		}

        private void setNachfolgerMulde(MuldenObj nachfolgerMulde) {
			this.nachfolgerMulde = nachfolgerMulde;
		}

        private MuldenObj getVorgaengerMulde() {
			return vorgaengerMulde;
		}

        private void setVorgaengerMulde(MuldenObj vorgaengerMulde) {
			this.vorgaengerMulde = vorgaengerMulde;
		}

        private int getAnzSteine() {
			return anzSteine;
		}

        private void setAnzSteine(int anzSteine) {
			this.anzSteine = anzSteine;
		}

        private int getMuldenTyp() {
			return muldenTyp;
		}

        private void setMuldenTyp(int muldenTyp) {
			this.muldenTyp = muldenTyp;
		}
        private Spieler getSpieler() {
			return spieler;
		}
        private void setSpieler(Spieler spieler) {
			this.spieler = spieler;
		}

		
        private int getMuldenNummer() {
			return muldenNummer;
		}

        private void setMuldenNummer(int muldenNummer) {
			this.muldenNummer = muldenNummer;
		}

	}

	}
