package com.example.farmease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
    public void logOut(View view)
    {
        Intent intent = new Intent(MainScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void goMessages(View view)
    {
        Intent intent = new Intent(MainScreen.this, ChatPageForEngineers.class);
        startActivity(intent);
        finish();
    }
    public void goAddFields(View view)
    {
        Intent intent = new Intent(MainScreen.this, AddField.class);
        startActivity(intent);
        finish();
    }
    public void goFields(View view)
    {
        Intent intent = new Intent(MainScreen.this, Fields.class);
        startActivity(intent);
        finish();
    }
    public void showWeather(View view)
    {
        Intent intent = new Intent(MainScreen.this, WeatherScreen.class);
        startActivity(intent);
        finish();
    }

    public void showCalendar(View view)
    {
        Intent intent = new Intent(MainScreen.this,Calendar.class);
        startActivity(intent);
        finish();
    }

}