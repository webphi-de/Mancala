package de.webphi.mancala;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import de.webphi.mancala.R;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction().replace(R.id.settings_holder, new SettingsFragment()).commit();

//        Intent intent = getIntent();
//        int a1 = intent.getIntExtra("a1", 0);
//
//        //intent = new Intent(this, MainActivity.class);
//        intent.putExtra("a1", a1);
        if (savedInstanceState != null){

        }
    }



    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            setPreferencesFromResource(R.xml.preferences, rootKey);//addPreferencesFromResource

            bindSummaryValue(findPreference("user_value_i"));
        }
    }

    private static void bindSummaryValue(Preference preference){

        preference.setOnPreferenceChangeListener(listener);
        listener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            String strValue = newValue.toString();

            if (preference instanceof ListPreference){

                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(strValue);
                // set summary to selected value
                preference.setSummary( index > 0
                        ? listPreference.getEntries()[index]
                        : null);
            } else if (preference instanceof EditTextPreference){
                preference.setSummary( strValue);
            }
            return true;
        }
    };
}

