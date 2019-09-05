/**
 * @(#)ManuellePlayer.java
 *
 *Von Spieler abgeleitet. Sitzt mit am Spielbrett und setzt den Zug um, den der Benutzer vorgibt.
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

public class ManuellerPlayer extends Spieler {

	private int muldenNummer;

	public ManuellerPlayer() {
		super();
		muldenNummer = 0;
	}

// --Commented out by Inspection START (28.08.2019 20:34):
//	public int getMuldenNummer() {
//		return muldenNummer;
//	}
// --Commented out by Inspection STOP (28.08.2019 20:34)

// --Commented out by Inspection START (28.08.2019 20:34):
//	public void setMuldenNummer(int muldenNummer) {
//		this.muldenNummer = muldenNummer;
//	}
// --Commented out by Inspection STOP (28.08.2019 20:34)

    @Override
    public void makeMove() {
        // TODO Auto-generated method stub
        
    }
}
