package de.webphi.mancala;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import de.webphi.mancala.R;

public class GameRulesActivity extends AppCompatActivity {

    private TextView tv_game_rules;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);

        tv_game_rules = (TextView) findViewById(R.id.tv_rules);
        tv_game_rules.setText(HtmlCompat.fromHtml(getString(R.string.tv_game_rules), HtmlCompat.FROM_HTML_MODE_LEGACY));

        tv_game_rules = (TextView) findViewById(R.id.tv_game);
        tv_game_rules.setText(HtmlCompat.fromHtml(getString(R.string.tv_game_discription), HtmlCompat.FROM_HTML_MODE_COMPACT));
    }
}
