package de.webphi.mancala;

import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import de.webphi.mancala.R;

public class GameRulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);

//        tv_about = (TextView) findViewById(R.id.tv_about);
//        Spanned sp = HtmlCompat.fromHtml( getString(R.string.text_about), HtmlCompat.FROM_HTML_MODE_LEGACY);
//        tv_about.setText(sp);

        TextView tv_rules = findViewById(R.id.tv_rules);
        Spanned sp_rules = HtmlCompat.fromHtml( getString(R.string.tv_game_rules), HtmlCompat.FROM_HTML_MODE_LEGACY);
        tv_rules.setText(sp_rules);

//        TextView tv_game = findViewById(R.id.tv_game);
//        Spanned sp_desc = HtmlCompat.fromHtml( getString(R.string.tv_game_discription), HtmlCompat.FROM_HTML_MODE_LEGACY);
//        tv_game.setText(sp_desc);

//        tv_game_rules = (TextView) findViewById(R.id.tv_rules);
//        tv_game_rules.setText(HtmlCompat.fromHtml(getString(R.string.tv_game_rules), HtmlCompat.FROM_HTML_MODE_LEGACY));

//        tv_game_rules = (TextView) findViewById(R.id.tv_game);
//        tv_game_rules.setText(HtmlCompat.fromHtml(getString(R.string.tv_game_discription), HtmlCompat.FROM_HTML_MODE_COMPACT));
    }
}
