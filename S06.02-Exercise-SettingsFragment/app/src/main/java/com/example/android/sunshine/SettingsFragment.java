package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private PreferenceScreen preferenceScreen;
    private SharedPreferences sharedPreferences;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);

        preferenceScreen=getPreferenceScreen();
        sharedPreferences = preferenceScreen.getSharedPreferences();
        int preferenceCount=preferenceScreen.getPreferenceCount();
        for(int i=0;i<preferenceCount;i++){
            Preference p=preferenceScreen.getPreference(i);
            if(!(p instanceof CheckBoxPreference)){
                setPreferenceSummary(p,sharedPreferences.getString(p.getKey(),""));
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference=findPreference(key);
        if(null!=preference && !(preference instanceof CheckBoxPreference)){
            setPreferenceSummary(preference,sharedPreferences.getString(key,""));
        }
    }

    private void setPreferenceSummary(Preference preferenceSummary, Object value){
        String stringValue=((String) value).trim();

        if(preferenceSummary instanceof ListPreference){
            ListPreference listPreference=(ListPreference) preferenceSummary;

            int index=listPreference.findIndexOfValue(stringValue);
            if(index>=0){
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        }else{
            preferenceSummary.setSummary(stringValue);
        }
    }


}
