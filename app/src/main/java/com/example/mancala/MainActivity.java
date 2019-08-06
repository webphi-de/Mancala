/**
 * @(#)MainActivity.java
 *
 * Main class of the game Kalaha.
 * https://de.wikipedia.org/wiki/Kalaha
 * https://en.wikipedia.org/wiki/Das_Bohnenspiel
 * Variant with 6 pieces per trough
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

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mancala.Spielbrett;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Client client;

    private Button start_btn;

    //private Spielbrett spielbrett;

    private TextView player_1;
    private TextView player_2;

    private String sp_txt_1;
    private String sp_txt_2;

    private Button a_1;
    private Button a_2;
    private Button a_3;
    private Button a_4;
    private Button a_5;
    private Button a_6;
    private Button a_end;
    private Button b_1;
    private Button b_2;
    private Button b_3;
    private Button b_4;
    private Button b_5;
    private Button b_6;
    private Button b_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        start_btn = findViewById(R.id.button_reset);
        start_btn.setOnClickListener(this);

        a_1 = (Button) findViewById(R.id.a_1);
        a_2 = (Button) findViewById(R.id.a_2);
        a_3 = (Button) findViewById(R.id.a_3);
        a_4 = (Button) findViewById(R.id.a_4);
        a_5 = (Button) findViewById(R.id.a_5);
        a_6 = (Button) findViewById(R.id.a_6);
        a_end = (Button) findViewById(R.id.home_btn_1);
        b_1 = (Button) findViewById(R.id.b_1);
        b_2 = (Button) findViewById(R.id.b_2);
        b_3 = (Button) findViewById(R.id.b_3);
        b_4 = (Button) findViewById(R.id.b_4);
        b_5 = (Button) findViewById(R.id.b_5);
        b_6 = (Button) findViewById(R.id.b_6);
        b_end = (Button) findViewById(R.id.home_btn_2);

        a_1.setOnClickListener(this);
        a_2.setOnClickListener(this);
        a_3.setOnClickListener(this);
        a_4.setOnClickListener(this);
        a_5.setOnClickListener(this);
        a_6.setOnClickListener(this);

        b_1.setOnClickListener(this);
        b_2.setOnClickListener(this);
        b_3.setOnClickListener(this);
        b_4.setOnClickListener(this);
        b_5.setOnClickListener(this);
        b_6.setOnClickListener(this);

        player_1 = (TextView) this.findViewById(R.id.text_view_p1);
        player_2 = (TextView) this.findViewById(R.id.text_view_p2);
        sp_txt_1 = "";
        sp_txt_2 = "";

        //spielbrett = new Spielbrett();
        client = new Client(this);
    }



    @Override
    public void onClick(View v) {

        Button pressBtn = (Button) findViewById(v.getId());


        if (pressBtn == start_btn){

            client.startApplication_AutoPlayer();
            //startApplication_with_Server();
        }
        else if (pressBtn == a_1){
            client.getSpielbrett().makeMove(1);
        }
        else if (pressBtn == a_2){
            client.getSpielbrett().makeMove(2);
        }
        else if (pressBtn == a_3){
            client.getSpielbrett().makeMove(3);
        }
        else if (pressBtn == a_4){
            client.getSpielbrett().makeMove(4);
        }
        else if (pressBtn == a_5){
            client.getSpielbrett().makeMove(5);
        }
        else if (pressBtn == a_6){
            client.getSpielbrett().makeMove(6);
        }
        else if (pressBtn == b_1){
            client.getSpielbrett().makeMove(8);
        }
        else if (pressBtn == b_2){
            client.getSpielbrett().makeMove(9);
        }
        else if (pressBtn == b_3){
            client.getSpielbrett().makeMove(10);
        }
        else if (pressBtn == b_4){
            client.getSpielbrett().makeMove(11);
        }
        else if (pressBtn == b_5){
            client.getSpielbrett().makeMove(12);
        }
        else if (pressBtn == b_6){
            client.getSpielbrett().makeMove(13);
        }

        a_1.setText(""+client.getSpielbrett().getMulde(1).getAnzSteine());
        a_2.setText(""+client.getSpielbrett().getMulde(2).getAnzSteine());
        a_3.setText(""+client.getSpielbrett().getMulde(3).getAnzSteine());
        a_4.setText(""+client.getSpielbrett().getMulde(4).getAnzSteine());
        a_5.setText(""+client.getSpielbrett().getMulde(5).getAnzSteine());
        a_6.setText(""+client.getSpielbrett().getMulde(6).getAnzSteine());
        a_end.setText(""+client.getSpielbrett().getMulde(7).getAnzSteine());
        b_1.setText(""+client.getSpielbrett().getMulde(8).getAnzSteine());
        b_2.setText(""+client.getSpielbrett().getMulde(9).getAnzSteine());
        b_3.setText(""+client.getSpielbrett().getMulde(10).getAnzSteine());
        b_4.setText(""+client.getSpielbrett().getMulde(11).getAnzSteine());
        b_5.setText(""+client.getSpielbrett().getMulde(12).getAnzSteine());
        b_6.setText(""+client.getSpielbrett().getMulde(13).getAnzSteine());
        b_end.setText(""+client.getSpielbrett().getMulde(14).getAnzSteine());

        a_1.setEnabled(client.getSpielbrett().getEigenerSpieler().isAktiv());
        a_2.setEnabled(client.getSpielbrett().getEigenerSpieler().isAktiv());
        a_3.setEnabled(client.getSpielbrett().getEigenerSpieler().isAktiv());
        a_4.setEnabled(client.getSpielbrett().getEigenerSpieler().isAktiv());
        a_5.setEnabled(client.getSpielbrett().getEigenerSpieler().isAktiv());
        a_6.setEnabled(client.getSpielbrett().getEigenerSpieler().isAktiv());

        b_1.setEnabled(client.getSpielbrett().getGegnerSpieler().isAktiv());
        b_2.setEnabled(client.getSpielbrett().getGegnerSpieler().isAktiv());
        b_3.setEnabled(client.getSpielbrett().getGegnerSpieler().isAktiv());
        b_4.setEnabled(client.getSpielbrett().getGegnerSpieler().isAktiv());
        b_5.setEnabled(client.getSpielbrett().getGegnerSpieler().isAktiv());
        b_6.setEnabled(client.getSpielbrett().getGegnerSpieler().isAktiv());

//        player_1.setText(sp_txt_1 + spielbrett.getEigenerSpieler().isAktiv());
//        player_2.setText(sp_txt_2 + spielbrett.getGegnerSpieler().isAktiv());

        client.spiele();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
