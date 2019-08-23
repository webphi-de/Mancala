/**
 * @(#)Spieler.java
 *
 *Abstrakte Oberklasse der Spieler.
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

public abstract class Spieler {


	private boolean aktiv;
	private int letzterZug;
    private Spielbaum spielbaum;
	
	public Spieler () {
		
	}
	
	public abstract void makeMove();

	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}

	public int getLetzterZug() {
		return letzterZug;
	}

	public void setLetzterZug(int letzterZug) {
		this.letzterZug = letzterZug;
	}

    public Spielbaum getSpielbaum() {
        return spielbaum;
    }

    public void setSpielbaum(Spielbaum spielbaum) {
        this.spielbaum = spielbaum;
    }
}
