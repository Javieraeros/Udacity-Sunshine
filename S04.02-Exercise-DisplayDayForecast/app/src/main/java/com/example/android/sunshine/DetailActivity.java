package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    public static final String SHARE_STRING="share_string";

    private TextView mForecastWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mForecastWeather=(TextView) findViewById(R.id.tv_display_weather);
        // TODO (2) Display the weather forecast that was passed from MainActivity
        if(getIntent()!=null && getIntent().hasExtra(SHARE_STRING)){
            String intentExtra=getIntent().getStringExtra(SHARE_STRING);
            mForecastWeather.setText(intentExtra);
        }
    }
}