package de.webphi.mancala;

import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import de.webphi.mancala.R;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        TextView tv_about = findViewById(R.id.tv_about);
        Spanned sp = HtmlCompat.fromHtml( getString(R.string.text_about), HtmlCompat.FROM_HTML_MODE_LEGACY);
        tv_about.setText(sp);
    }
}
