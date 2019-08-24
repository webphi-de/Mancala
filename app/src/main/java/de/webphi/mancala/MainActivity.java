/**
 * @(#)MainActivity.java
 *
 * Main class of the game Kalaha.
 * https://de.wikipedia.org/wiki/Kalaha
 * https://en.wikipedia.org/wiki/Das_Bohnenspiel
 * Variant with 6 pieces per pit
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
 * @history v0.10 01.08.2019 - porting as an Android App.
 *          v0.20 05.08.2019 - (new version) first executable version with 2 manual players or against an automatic player
 *          v0.30 18.08.2019 - save and restore State. add settings with new androidx.preferences
 *          v0.40 20.08.2019 - new game rules activity
 *          v0.50 22.08.2019 - eliminate some errors - properly playable
 *          v0,51 23.08.2019 - write Infos to Display renewed
 *
 *
 */
//@// TODO: 20.08.2019 finish game nicht bei allen spielständen (capture zug ).
// @// TODO: 20.08.2019Spielfelder markieren.
package de.webphi.mancala;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import de.webphi.mancala.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spielbrett spielbrett;

    private TextView tv_move, tv_moveInfo;

    private Button a_1, a_2, a_3, a_4, a_5, a_6, a_end, b_1, b_2, b_3, b_4, b_5, b_6, b_end;

    private int i = 0;
    
    private static final String A1="a_1", A2 = "a_2", A3="a_3", A4="a_4", A5="a_5", A6="a_6",
            A_END="a_end", B1="b_1", B2="b_2", B3="b_3", B4="b_4", B5="b_5", B6="b_6", B_END="b_end";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_move = (TextView) findViewById(R.id.tv_move);
        tv_moveInfo = (TextView) findViewById(R.id.tv_moveInfo);

        a_1 = (Button) findViewById(R.id.a_1); a_2 = (Button) findViewById(R.id.a_2);
        a_3 = (Button) findViewById(R.id.a_3); a_4 = (Button) findViewById(R.id.a_4);
        a_5 = (Button) findViewById(R.id.a_5); a_6 = (Button) findViewById(R.id.a_6);
        a_end = (Button) findViewById(R.id.home_btn_1);
        b_1 = (Button) findViewById(R.id.b_1); b_2 = (Button) findViewById(R.id.b_2);
        b_3 = (Button) findViewById(R.id.b_3); b_4 = (Button) findViewById(R.id.b_4);
        b_5 = (Button) findViewById(R.id.b_5); b_6 = (Button) findViewById(R.id.b_6);
        b_end = (Button) findViewById(R.id.home_btn_2);

        a_1.setOnClickListener(this); a_2.setOnClickListener(this);
        a_3.setOnClickListener(this); a_4.setOnClickListener(this);
        a_5.setOnClickListener(this); a_6.setOnClickListener(this);

        b_1.setOnClickListener(this); b_2.setOnClickListener(this);
        b_3.setOnClickListener(this); b_4.setOnClickListener(this);
        b_5.setOnClickListener(this); b_6.setOnClickListener(this);

        // Restore the state.
        // @tudo testen - evtl. nicht mehr nötig
        if (savedInstanceState != null) {

            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // Zu SettingsActivity wechseln
        if (id == R.id.action_settings) {

//            Toast.makeText(this, "settings selected", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        // Zu GameRulesActivity wechseln
        else if (id == R.id.game_rules){

//            Toast.makeText(this, "game rules selected", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, GameRulesActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.about){

//            Toast.makeText(this, "about selected", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        // Der Benutzer startet das Spiel
        else if (id == R.id.ic_menu_play) {

            resetInfo();
            initSpielfeld();
            setMulden();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Button pressBtn = (Button) findViewById(v.getId());

        // Siehe Spielbrett:
        // Die mulden sind von 1 bis 14 durchnummeriert, wobei 7 und 14 Endmulden sind, also keine Spielmulden
        if (pressBtn == a_1)      {  spielbrett.makeMove(1); }
        else if (pressBtn == a_2) {  spielbrett.makeMove(2); }
        else if (pressBtn == a_3) {  spielbrett.makeMove(3); }
        else if (pressBtn == a_4) {  spielbrett.makeMove(4); }
        else if (pressBtn == a_5) {  spielbrett.makeMove(5); }
        else if (pressBtn == a_6) {  spielbrett.makeMove(6); }

        else if (pressBtn == b_1) {  resetInfo(); spielbrett.makeMove(8);  }
        else if (pressBtn == b_2) {  resetInfo(); spielbrett.makeMove(9);  }
        else if (pressBtn == b_3) {  resetInfo(); spielbrett.makeMove(10); }
        else if (pressBtn == b_4) {  resetInfo(); spielbrett.makeMove(11); }
        else if (pressBtn == b_5) {  resetInfo(); spielbrett.makeMove(12); }
        else if (pressBtn == b_6) {  resetInfo(); spielbrett.makeMove(13); }

        // Nach jedem Spielzug das Spielfeld aktualisieren
        setMulden();

        // Die Spielzüge des Autoplayer
        if (spielbrett.getEigenerSpieler() instanceof Autoplayer &&
                spielbrett.getEigenerSpieler().isAktiv()) {

            playKI();

        }
    }

    private void resetInfo(){

        tv_move.setText(getString(R.string.turn));
        tv_moveInfo.setText(getString(R.string.info));
    }

    private void playKI() {

        if (!spielbrett.isFinished()) {

            // Der Autoplayer schickt eine Berechnungsanfrage an den Spielbaum.
            // Danach wird der berecnete Zug abgefragt
            spielbrett.getEigenerSpieler().makeMove();
            int move_ki = ((Autoplayer) spielbrett.getEigenerSpieler()).getMuldenNummer();

            writeInfosToDisplay(move_ki); // Display Infos


            // Der Autoplayer macht seinen Zug am ViewModel, also der Spielfläche in der MainActivity
            if (move_ki == 1)  a_1.performClick();
            else if (move_ki == 2) a_2.performClick();
            else if (move_ki == 3) a_3.performClick();
            else if (move_ki == 4) a_4.performClick();
            else if (move_ki == 5) a_5.performClick();
            else if (move_ki == 6) a_6.performClick();
        }
        else {
            Toast.makeText(this, getString(R.string.toast_finished), Toast.LENGTH_SHORT).show();
        }
    }

    private void writeInfosToDisplay(int move_ki) {

        // Info: Spielzüge des Autoplayer
        int steineInMulde = spielbrett.getMulde(move_ki).getAnzSteine();
        int spielmulde = 0, zielmulde = 0;
        String zug = "";

        spielmulde = move_ki + 7;

        // a little stupid scheme, anyway look excel doc in projekt
        if      ((spielmulde + steineInMulde) <= 14) zielmulde =  spielmulde + steineInMulde;
        else if ((spielmulde + steineInMulde) <= 20) zielmulde = (spielmulde + steineInMulde) - 14;
        else if ((spielmulde + steineInMulde) <= 27) zielmulde = (spielmulde + steineInMulde) - 13;
        else if ((spielmulde + steineInMulde) <= 33) zielmulde = (spielmulde + steineInMulde) - 27;
        else if ((spielmulde + steineInMulde) <= 40) zielmulde = (spielmulde + steineInMulde) - 26;

        if (zielmulde == 14) zug = String.valueOf(spielmulde) + "(" + String.valueOf(steineInMulde) + ") > end" ;
        else
            zug = String.valueOf(spielmulde) + "(" + String.valueOf(steineInMulde) + ") > " + String.valueOf(zielmulde);

        tv_move.setText(tv_move.getText() + zug + "; ");

        // Info: calculatet moves and used time from last move
        double number = spielbrett.getEigenerSpieler().getSpielbaum().getDurchsuchteSpielbretter();
        String str = NumberFormat.getInstance().format(number);
        long time = spielbrett.getEigenerSpieler().getSpielbaum().getBenoetigteZeit();

        tv_moveInfo.setText("Calculated moves: " + str + "  in " + time/1000 + "." + time%1000 + " s");
    }

    private void setMulden() {
        // Sets score to buttons. En- or disable pit to play.

        a_1.setText(""+spielbrett.getMulde(1).getAnzSteine());
        a_2.setText(""+spielbrett.getMulde(2).getAnzSteine());
        a_3.setText(""+spielbrett.getMulde(3).getAnzSteine());
        a_4.setText(""+spielbrett.getMulde(4).getAnzSteine());
        a_5.setText(""+spielbrett.getMulde(5).getAnzSteine());
        a_6.setText(""+spielbrett.getMulde(6).getAnzSteine());
        a_end.setText(""+spielbrett.getMulde(7).getAnzSteine());
        b_1.setText(""+spielbrett.getMulde(8).getAnzSteine());
        b_2.setText(""+spielbrett.getMulde(9).getAnzSteine());
        b_3.setText(""+spielbrett.getMulde(10).getAnzSteine());
        b_4.setText(""+spielbrett.getMulde(11).getAnzSteine());
        b_5.setText(""+spielbrett.getMulde(12).getAnzSteine());
        b_6.setText(""+spielbrett.getMulde(13).getAnzSteine());
        b_end.setText(""+spielbrett.getMulde(14).getAnzSteine());

        // only playable buttons become active
        a_1.setEnabled(spielbrett.getEigenerSpieler().isAktiv());
        a_2.setEnabled(spielbrett.getEigenerSpieler().isAktiv());
        a_3.setEnabled(spielbrett.getEigenerSpieler().isAktiv());
        a_4.setEnabled(spielbrett.getEigenerSpieler().isAktiv());
        a_5.setEnabled(spielbrett.getEigenerSpieler().isAktiv());
        a_6.setEnabled(spielbrett.getEigenerSpieler().isAktiv());

        b_1.setEnabled(spielbrett.getGegnerSpieler().isAktiv());
        b_2.setEnabled(spielbrett.getGegnerSpieler().isAktiv());
        b_3.setEnabled(spielbrett.getGegnerSpieler().isAktiv());
        b_4.setEnabled(spielbrett.getGegnerSpieler().isAktiv());
        b_5.setEnabled(spielbrett.getGegnerSpieler().isAktiv());
        b_6.setEnabled(spielbrett.getGegnerSpieler().isAktiv());

        a_end.setEnabled(false); b_end.setEnabled(false);
    }

    public void initSpielfeld(){
        // Alle Settings aus den sharedPreferences holen und am Spielbaum setzen.
        // Der Spielbaum ist die Klassse mit MiniMax und Bewertunsfunktion)
        // Neues Spielbrett mit Player erstellen.
        // Evtl. den ersten Spielzug ausführen

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        // values from shared preferences
        boolean player_ki  = sharedPreferences.getBoolean("pref_player_ki", true);
        boolean play_first = sharedPreferences.getBoolean("pref_player_first", false);
        int playing_depth  = Integer.parseInt(sharedPreferences.getString("playing_depth", "5"));
        int capture_turn   = Integer.parseInt(sharedPreferences.getString("capture_turn", "8"));
        int kalaha_diff    = Integer.parseInt(sharedPreferences.getString("kalaha_diff", "4"));
        int stone_diff     = Integer.parseInt(sharedPreferences.getString("stone_diff", "7"));
        int repeated_turn  = Integer.parseInt(sharedPreferences.getString("repeated_turn", "-2"));
        int turn_13        = Integer.parseInt(sharedPreferences.getString("turn_13", "1"));


        // spielbrett mit spieler setzen
        spielbrett = new Spielbrett();
        spielbrett.setPlayer(player_ki, !play_first);
        spielbrett.initMulden();

        if (spielbrett.getEigenerSpieler() instanceof Autoplayer){

            spielbrett.getEigenerSpieler().getSpielbaum().TIEFE(playing_depth);
            spielbrett.getEigenerSpieler().getSpielbaum().CAPTURE_ZUG(capture_turn);
            spielbrett.getEigenerSpieler().getSpielbaum().KALAHA_DIFFERENZ(kalaha_diff);
            spielbrett.getEigenerSpieler().getSpielbaum().STEINE_DIFFERENZ(stone_diff);
            spielbrett.getEigenerSpieler().getSpielbaum().WIEDERHOLTER_ZUG(repeated_turn);
            spielbrett.getEigenerSpieler().getSpielbaum().ZUG_13(turn_13);
        }

        if (!play_first) {

            playKI();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        // Save state after leavibg activity

        super.onSaveInstanceState(savedInstanceState);

        if(spielbrett == null){

            initSpielfeld();
        }

        // Save the state
        savedInstanceState.putInt(A1, spielbrett.getMulde(1).getAnzSteine());
        savedInstanceState.putInt(A2, spielbrett.getMulde(2).getAnzSteine());
        savedInstanceState.putInt(A3, spielbrett.getMulde(3).getAnzSteine());
        savedInstanceState.putInt(A4, spielbrett.getMulde(4).getAnzSteine());
        savedInstanceState.putInt(A5, spielbrett.getMulde(5).getAnzSteine());
        savedInstanceState.putInt(A6, spielbrett.getMulde(6).getAnzSteine());
        savedInstanceState.putInt(A_END, spielbrett.getMulde(7).getAnzSteine());
        savedInstanceState.putInt(B1, spielbrett.getMulde(8).getAnzSteine());
        savedInstanceState.putInt(B2, spielbrett.getMulde(9).getAnzSteine());
        savedInstanceState.putInt(B3, spielbrett.getMulde(10).getAnzSteine());
        savedInstanceState.putInt(B4, spielbrett.getMulde(11).getAnzSteine());
        savedInstanceState.putInt(B5, spielbrett.getMulde(12).getAnzSteine());
        savedInstanceState.putInt(B6, spielbrett.getMulde(13).getAnzSteine());
        savedInstanceState.putInt(B_END, spielbrett.getMulde(14).getAnzSteine());

//        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        // Rstore state

        super.onRestoreInstanceState(savedInstanceState);

        initSpielfeld();

        spielbrett.getMulde(1).setAnzSteine(savedInstanceState.getInt(A1));
        spielbrett.getMulde(2).setAnzSteine(savedInstanceState.getInt(A2));
        spielbrett.getMulde(3).setAnzSteine(savedInstanceState.getInt(A3));
        spielbrett.getMulde(4).setAnzSteine(savedInstanceState.getInt(A4));
        spielbrett.getMulde(5).setAnzSteine(savedInstanceState.getInt(A5));
        spielbrett.getMulde(6).setAnzSteine(savedInstanceState.getInt(A6));
        spielbrett.getMulde(7).setAnzSteine(savedInstanceState.getInt(A_END));
        spielbrett.getMulde(8).setAnzSteine(savedInstanceState.getInt(B1));
        spielbrett.getMulde(9).setAnzSteine(savedInstanceState.getInt(B2));
        spielbrett.getMulde(10).setAnzSteine(savedInstanceState.getInt(B3));
        spielbrett.getMulde(11).setAnzSteine(savedInstanceState.getInt(B4));
        spielbrett.getMulde(12).setAnzSteine(savedInstanceState.getInt(B5));
        spielbrett.getMulde(13).setAnzSteine(savedInstanceState.getInt(B6));
        spielbrett.getMulde(14).setAnzSteine(savedInstanceState.getInt(B_END));


        setMulden();
    }
}
