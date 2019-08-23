package de.webphi.mancala;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import de.webphi.mancala.R;

public class AboutActivity extends AppCompatActivity {


    private TextView tv_about;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_about.setText(HtmlCompat.fromHtml(getString(R.string.text_about), HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}
