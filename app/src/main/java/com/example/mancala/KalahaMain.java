package com.example.mancala;

//import java.io.*;
//import java.rmi.*;
//import java.rmi.registry.*;
import java.util.*;
import com.example.mancala.*;

import com.example.mancala.Spielbaum;
//import com.example.mancala.KalahaGUI;
//import ai1.mancala.server.*;


/**
 * @(#)KalahaMain.java
 *
 * Main-, Hauptklasse des Spiels Kalaha.
 * 
 * Wird die Applikation mit Parameter gestartet steht eine Konsole zur Verf�hgung, die mehrere
 * M�glichkeiten zum testen bietet.
 * 
 * Ohne Parameter startet die Oberfl�che von wo aus Kalaha alleine gespielt werden kann, als auch
 * �ber einen Server und verschiedene Autoplayer gesetzt werden k�nnen.
 *
 * @project Mancala-Project of Group II. 
 *          The project is built during the Softare Engineering Practical Course in WS/SS2005/06 
 *          at the University of Bayreuth.
 * @author  Melanie Kannegiesser, Stefan Kannegiesser
 * @version v1.0 17.03.2006
 * @since   JDK 1.5.0
 * @history v1.00 17.03.2006 - erste lauff�hige Version mit dem Server
 *          v1.01 20.03.2006 - Login Button mit Dialog Fenster zum Server Connect erstellt
 *                           - Application kann zunaechst ohne Server gestartet werden und das Connect spaeter erfolgen
 *          v1.02 21.03.2006 - Autoplayer mit Spielbaum erstellt und eingebunden 
 *          v1.03 24.03.2006 - Spielstrategien erstellt 
 *          v1.1  25.03.2006 - Finale Version zum Praktikumsende                
 *          v1.11 01.04.2006 - Einige �nderungen an der Oberfl�chen                
 * 
 */
public class KalahaMain {

    // globale Varaiblen dienten nur zum Testen
	//private static Player player_1;
	//private static Player player_2;
//	private static MancalaServerRemote stub;
	private static Runtime runtime;
	private static Process pr;

	//	globale Varaiblen
	private Spielbrett spielbrett = null;
	//private Client client;
	//private KalahaGUI gui;
	
	/**
	 * default constructor
     * startet die Applikation mit Gui
	 */
	public KalahaMain () {
		
        startApplication_default();
	}

	/**
     * second constructor
     * zum Aufrufen der Konsole
     * nur zum Testen gedacht
	 * @param b
	 */
	public KalahaMain (boolean b) {
		
		setConsole();
	}

	/**
     * Mit Parameter wird die Konsole gestartet (zum Testen)
     * Ohne Parameter wird die Applikation mit Gui gestartet
     * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 0){
			
			KalahaMain startAppl = new KalahaMain();
		}
		
		else{
			
			KalahaMain setConsole = new KalahaMain(true);
		}
			
	}

    /**
     * Startet die Applikation ohne Server Connection
     */
    private void startApplication_default () {
        
        //client = new Client();
        
        spielbrett = new Spielbrett();
        spielbrett.initSpieler(true);
        spielbrett.initMulden();
        //gui = new KalahaGUI(spielbrett, spielbrett.getModel(), client);
    }

    /**
     * Startet die Applikation mit Server Connection
     */
    private void startApplication_AutoPlayer () {
        
        //client = new Client();
        //client.connect();
        spielbrett = new Spielbrett();
        //client.setSpielbrett(spielbrett);
        //spielbrett.initAutoPlayer(client.getPlayer_1().isFirstPlayer());
        spielbrett.initMulden();
        //gui = new KalahaGUI(spielbrett, spielbrett.getModel(), client);
        //Thread spielThread = new  SpielThread (spielbrett, client, spielbrett.getModel());
        //spielThread.start();
    }
    
    /**
     * Startet die Applikation mit einer Server Connection.
     * Setzt voraus, dass der Server laeuft.
     * Erzeugt als Esrter das Objekt Spielbrett.
     * Startet die Spielzuege in einem neuen Thread.
     */
    private void startApplication_with_Server () {
        
        //client = new Client();
        //client.connect();
        spielbrett = new Spielbrett();
        //client.setSpielbrett(spielbrett);
        //spielbrett.initSpieler(client.getPlayer_1().isFirstPlayer());
        spielbrett.initMulden();
       // gui = new KalahaGUI(spielbrett, spielbrett.getModel(), client);
       // Thread spielThread = new  SpielThread (spielbrett, client, spielbrett.getModel());
       // spielThread.start();
    }
 
    /**
     * Getter fuers Spielbrett
     * @return spielbrett
     */
    public Spielbrett getSpielbrett() {
        return spielbrett;
    }
    /**
     * Setter fuers spielbrett
     * @param spielbrett
     */
    public void setSpielbrett(Spielbrett spielbrett) {
        this.spielbrett = spielbrett;
    }
    /**
     * Getter fuer den Client
     * @return client
     */
//    //public Client getClient() {
//        return client;
//    }

    public void setBewertungsFaktoren_1 () {
        
        spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(0);
        spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(1);
        spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(0);
        spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(0);
        spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(0);
    }
    // schlecht
    public void setBewertungsFaktoren_2 () {
        
        spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(7);
        spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(5);
        spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(10);
        spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(8);
        spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(5);
    }
    // Er�ffnungsplayer
    public void setBewertungsFaktoren_3 () {
        
        spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(8);
        spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(0);
        spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(5);
        spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(5);
        spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(8);
    }
    // gut als zweiter
    public void setBewertungsFaktoren_4 () {
        
        spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(8);
        spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(5);
        spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(2);
        spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(7);
        spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(8);
    }
    // f�nf deklassiert vier in beiden varianten
    public void setBewertungsFaktoren_5 () {
        
        spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(4);
        spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(5);
        spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(6);
        spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(3);
        spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(4);
    }
    public void setBewertungsFaktoren_6 () {
        
        spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(6);
        spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(4);
        spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(7);
        spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(0);
        spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(6);
    }
    public void setBewertungsFaktoren_7 () {
        
        spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(6);
        spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(4);
        spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(7);
        spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(-2);
        spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(1);
    }

// -------------------- nachfolgende Methoden dienten nur zum Testen -------------------------------------

	/**
	 * Die Konsole
     * wird �ber die main gestartet
     * diente nur zum Testen 
	 */
	private void setConsole() {
		
		boolean menue = true;
		
		
		Scanner sc = new Scanner(System.in);

		System.out.println("\n\t   ");
		System.out.println("\n\t                         Kalaha");
		System.out.println("\n\t   ");
		System.out.println("\n..");

		while (menue) {

			System.out.println("\t ____________________________________________________");
			System.out.println("\t|                     Menue:                         |");
			System.out.println("\t|                    --------                        |");
            System.out.println("\t|                                                    |");
            System.out.println("\t| 1:  startApplication: AutoPlayer 1                 |");
            System.out.println("\t| 2:  startApplication: AutoPlayer 2                 |");
            System.out.println("\t| 3:  startApplication: AutoPlayer 3                 |");
            System.out.println("\t| 4:  startApplication: AutoPlayer 4                 |");
            System.out.println("\t| 5:  startApplication: AutoPlayer 5                 |");
            System.out.println("\t| 6:  startApplication: AutoPlayer 6                 |");
            System.out.println("\t| 7:  startApplication: AutoPlayer 7                 |");
			System.out.println("\t|                                                    |");
            System.out.println("\t| 8:  startApplication: default                      |");
            System.out.println("\t| 9:  startApplication: with Server                  |");
			System.out.println("\t|  :                                                 |");
			System.out.println("\t|  :                                                 |");
			System.out.println("\t|                                                    |");
			System.out.println("\t| 11:  Beenden!                                      |");
			System.out.println("\t|____________________________________________________|");
			System.out.println("");
			
			System.out.print("\n  ->   ");

			// Hilfsvariablen
			int t = sc.nextInt();

			switch (t) {

            case 8: {

                startApplication_default();
            }
                break;
            case 9: {

                startApplication_with_Server();
            }
                break;
            case 1: {

                startApplication_AutoPlayer();
                setBewertungsFaktoren_1();
            }
                break;
            case 2: {

                startApplication_AutoPlayer();
                setBewertungsFaktoren_2();
            }
                break;
            case 3: {

                startApplication_AutoPlayer();
                setBewertungsFaktoren_3();
            }
                break;
            case 4: {

                startApplication_AutoPlayer();
                setBewertungsFaktoren_4();
            }
                break;
            case 5: {

                startApplication_AutoPlayer();
                setBewertungsFaktoren_5();
            }
                break;
            case 6: {

                startApplication_AutoPlayer();
                setBewertungsFaktoren_6();
            }
                break;
            case 7: {

                startApplication_AutoPlayer();
                setBewertungsFaktoren_7();
            }
                break;

			default: {

				System.out.println("EXIT");
				menue = false;
				break;
			}
		}
	}
	}

 } // end Class 
