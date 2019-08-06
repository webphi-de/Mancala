/**
 * @(#)ManuellePlayer.java
 *
 *Von Spieler abgeleitet. Sitzt mit am Spielbrett und setzt den Zug um, den der Benutzer vorgibt.
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

public class ManuellerPlayer extends Spieler {

	private int muldenNummer;

	public ManuellerPlayer() {
		super();
		muldenNummer = 0;
	}
	
//	public void makeMove () {
//		
//		client.makeMove(client.getPlayer_1(), client.getSpielbrett().getMulde().getMuldenNummer());
//	}

	public int getMuldenNummer() {
		return muldenNummer;
	}

	public void setMuldenNummer(int muldenNummer) {
		this.muldenNummer = muldenNummer;
	}

    @Override
    public void makeMove() {
        // TODO Auto-generated method stub
        
    }
}
