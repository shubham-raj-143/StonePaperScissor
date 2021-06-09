package com.shubham.sps_thestonepaperscissorgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private TextView s_played, s_win, s_lost;
    private String saved_played, saved_win, saved_lost, saved_percent;
    private SharedPreferences settings;
    private Button saved_button, delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        s_played=findViewById(R.id.s_played);
        s_win=findViewById(R.id.s_win);
        s_lost=findViewById(R.id.s_lost);
        saved_button=findViewById(R.id.save_button);
        delete_button=findViewById(R.id.delete_button);
        settings=getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);


        SharedPreferences sharedPreferences
                = getSharedPreferences(
                "sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor
                = sharedPreferences.edit();
        final boolean isDarkModeOn
                = sharedPreferences
                .getBoolean(
                        "isDarkModeOn", false);

        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_NO);
        }
//        s_played.setText((getIntent().getStringExtra("Played")));
//        s_win.setText((getIntent().getStringExtra("Win")));
//        s_lost.setText((getIntent().getStringExtra("Lost")));
//        s_percent.setText((getIntent().getStringExtra("PercentageWin")));

//        SharedPreferences.Editor saved = settings.edit();
//        saved.putString("played", s_played.getText().toString());
//        saved.putString("won", s_win.getText().toString());
//        saved.putString("lost", s_lost.getText().toString());
//        saved.putString("percent", s_percent.getText().toString());
//        saved.commit();

        saved_played = settings.getString("played", getIntent().getStringExtra("Played"));
        saved_win = settings.getString("won", getIntent().getStringExtra("Win"));
        saved_lost = settings.getString("lost", getIntent().getStringExtra("Lost"));
        saved_percent = settings.getString("percent", getIntent().getStringExtra("PercentageWin"));

        SharedPreferences.Editor saved = settings.edit();
        s_played.setText("Matches Played : "+saved_played);
        s_win.setText("Matches Won : "+saved_win);
        s_lost.setText("Matches Lost : "+saved_lost);


        saved_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved.putString("played", saved_played);
                saved.putString("won", saved_win);
                saved.putString("lost", saved_lost);
                saved.putString("percent", saved_percent);
                saved.apply();
                Toast.makeText(Settings.this, "The current statics saved!", Toast.LENGTH_SHORT).show();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved.clear().apply();
                s_played.setText("Matches Played : ");
                s_win.setText("Matches Won : ");
                s_lost.setText("Matches Lost : ");
                Toast.makeText(Settings.this, "Statistics were reset", Toast.LENGTH_SHORT).show();
            }
        });


    }
}