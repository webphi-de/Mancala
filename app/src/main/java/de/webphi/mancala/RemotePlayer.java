/**
 * @(#)RemotePlayer.java
 *
 *Der RemotePlayer verhält sich zurzeit wie der manuelle Player. Geplant ist später evtl. eine
 * Verbindung über den Server.
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
package de.webphi.mancala;

public class RemotePlayer extends Spieler {
	
	private int muldenNummer;
	// --Commented out by Inspection (28.08.2019 20:36):private Spielbrett spielbrett;

	public RemotePlayer() {
		super();
		//this.client = new Client();
		muldenNummer = 0;
//		spielbrettGui = spielbrettGui.getGui();
	}
	
//	public void makeMove () {
//		
//		muldenNummer = client.consumeMove(client.getPlayer_1());
//		spielbrett.makeMove(muldenNummer);
////		spielbrettGui.setConsumeMove(muldenNummer);
//	}

// --Commented out by Inspection START (28.08.2019 20:35):
//	public int getMuldenNummer() {
//		return muldenNummer;
//	}
// --Commented out by Inspection STOP (28.08.2019 20:35)

// --Commented out by Inspection START (28.08.2019 20:35):
//	public void setMuldenNummer(int muldenNummer) {
//		this.muldenNummer = muldenNummer;
////		System.out.println("Remote Spieler: " + muldenNummer);
//	}
// --Commented out by Inspection STOP (28.08.2019 20:35)

    @Override
    public void makeMove() {
        // TODO Auto-generated method stub
        
    }
}
