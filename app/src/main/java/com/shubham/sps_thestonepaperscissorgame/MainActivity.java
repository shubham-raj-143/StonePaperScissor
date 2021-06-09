package com.shubham.sps_thestonepaperscissorgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageButton p_stone, p_paper, p_scissor;
    private ImageView icon, result_img, like;
    private TextView result_text, p_win, p_lost, played_games;
    private Button save, reset;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p_stone = findViewById(R.id.p_stone);
        p_paper = findViewById(R.id.p_paper);
        p_scissor = findViewById(R.id.p_scissor);
        like = findViewById(R.id.like);

        icon = findViewById(R.id.icon);
        save = findViewById(R.id.save);
        reset = findViewById(R.id.reset);
        settings=getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        result_img = findViewById(R.id.result_img);

        result_text = findViewById(R.id.result_text);
        p_win = findViewById(R.id.p_win);
        p_lost = findViewById(R.id.p_lost);
        played_games = findViewById(R.id.played_games);

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

        Toolbar toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Stone Paper Scissor");
        setSupportActionBar(toolbar);

        final int[] w = {0, 0, 0};
        final int[] l = {0, 0, 0};
        final int[] p = {0, 0, 0};

        p_stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_stone.setVisibility(View.GONE);
                p_paper.setVisibility(View.GONE);
                p_scissor.setVisibility(View.GONE);
                float[] degree = new float[]{120f, 240f, 360f, 480f, 600f, 720f, 840f, 960f, 1080f};
                int[] duration = new int[] {500, 800, 1000, 1200, 1500, 1800, 2000, 2300, 2500, 2800, 3000};
                float deg = degree[new Random().nextInt(degree.length)];
                int time = duration[new Random().nextInt(duration.length)];
                icon.animate().rotation(deg).setDuration(time).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        save.setVisibility(View.GONE);
                        if(deg == 360f || deg == 720f || deg == 1080f){
//                            Toast.makeText(MainActivity.this, "Match Tied!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Tied!");
                            result_text.setVisibility(View.VISIBLE);
                            Random ran = new Random();
                            int low = -2;
                            int high = 3;
                            int result = ran.nextInt(high-low) + low;
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[0]);
                            w[0] = w[0]+result;
                            w[1] = w[1]+result;
                            w[2] = w[2]+result;
                            p_win.setText(""+w[0]);
                        }
                        else if(deg == 240f || deg == 600f || deg == 960f){
//                            Toast.makeText(MainActivity.this, "Match Won!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Won!");
                            w[0]++;
                            w[1]++;
                            w[2]++;
                            p_win.setText(""+w[0]);
                            result_img.setImageResource(R.drawable.stonewin);
                            like.setImageResource(R.drawable.like);
                            like.setVisibility(View.VISIBLE);
                            result_img.setVisibility(View.VISIBLE);
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[0]);
                        }
                        else{
//                            Toast.makeText(MainActivity.this, "Match Lost!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Lost!");
                            like.setImageResource(R.drawable.dislike);
                            like.setVisibility(View.VISIBLE);
                            l[0]++;
                            l[1]++;
                            l[2]++;
                            p_lost.setText(""+l[0]);
                            result_img.setImageResource(R.drawable.paperwin);
                            result_img.setVisibility(View.VISIBLE);
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[0]);
                        }
                        icon.animate().rotationBy(360f - deg).setDuration(500).setStartDelay(1000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                p_stone.setVisibility(View.VISIBLE);
                                p_paper.setVisibility(View.VISIBLE);
                                save.setVisibility(View.VISIBLE);
                                p_scissor.setVisibility(View.VISIBLE);
                                result_img.setVisibility(View.GONE);
                                result_text.setVisibility(View.GONE);
                                like.setVisibility(View.GONE);
                            }
                        }).start();


                    }
                }).start();

            }
        });

        p_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_stone.setVisibility(View.GONE);
                p_paper.setVisibility(View.GONE);
                p_scissor.setVisibility(View.GONE);
                float[] degree = new float[]{120f, 240f, 360f, 480f, 600f, 720f, 840f, 960f, 1080f};
                int[] duration = new int[] {500, 800, 1000, 1200, 1500, 1800, 2000, 2300, 2500, 2800, 3000};
                float deg = degree[new Random().nextInt(degree.length)];
                int time = duration[new Random().nextInt(duration.length)];
                icon.animate().rotation(deg).setDuration(time).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        save.setVisibility(View.GONE);
                        if(deg == 360f || deg == 720f || deg == 1080f){
//                            Toast.makeText(MainActivity.this, "Match Won!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Won!");
                            like.setImageResource(R.drawable.like);
                            like.setVisibility(View.VISIBLE);
                            w[0]++;
                            w[1]++;
                            w[2]++;
                            p_win.setText(""+w[1]);
                            result_img.setImageResource(R.drawable.paperwin);
                            result_img.setVisibility(View.VISIBLE);
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[1]);
                        }
                        else if(deg == 240f || deg == 600f || deg == 960f){
//                            Toast.makeText(MainActivity.this, "Match Lost!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Lost!");
                            like.setImageResource(R.drawable.dislike);
                            like.setVisibility(View.VISIBLE);
                            l[0]++;
                            l[1]++;
                            l[2]++;
                            p_lost.setText(""+l[1]);
                            result_img.setImageResource(R.drawable.scissorwin);
                            result_img.setVisibility(View.VISIBLE);
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[1]);
                        }
                        else{
//                            Toast.makeText(MainActivity.this, "Match Tied!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Tied!");
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[1]);

                            Random ran = new Random();
                            int low = -2;
                            int high = 3;
                            int result = ran.nextInt(high-low) + low;
                            w[0] = w[0]+result;
                            w[1] = w[1]+result;
                            w[2] = w[2]+result;
                            p_win.setText(""+w[1]);
                        }
                        icon.animate().rotationBy(360f - deg).setDuration(500).setStartDelay(500).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                p_stone.setVisibility(View.VISIBLE);
                                p_paper.setVisibility(View.VISIBLE);
                                save.setVisibility(View.VISIBLE);
                                p_scissor.setVisibility(View.VISIBLE);
                                result_img.setVisibility(View.GONE);
                                result_text.setVisibility(View.GONE);
                                like.setVisibility(View.GONE);
                            }
                        }).start();

                    }
                }).start();

            }
        });

        p_scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_stone.setVisibility(View.GONE);
                p_paper.setVisibility(View.GONE);
                p_scissor.setVisibility(View.GONE);
                float[] degree = new float[]{120f, 240f, 360f, 480f, 600f, 720f, 840f, 960f, 1080f};
                int[] duration = new int[] {500, 800, 1000, 1200, 1500, 1800, 2000, 2300, 2500, 2800, 3000};
                float deg = degree[new Random().nextInt(degree.length)];
                int time = duration[new Random().nextInt(duration.length)];
                icon.animate().rotation(deg).setDuration(time).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        save.setVisibility(View.GONE);
                        if(deg == 360f || deg == 720f || deg == 1080f){
//                            Toast.makeText(MainActivity.this, "Match Lost!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Lost!");
                            like.setImageResource(R.drawable.dislike);
                            like.setVisibility(View.VISIBLE);
                            l[0]++;
                            l[1]++;
                            l[2]++;
                            p_lost.setText(""+l[2]);
                            result_img.setImageResource(R.drawable.stonewin);
                            result_img.setVisibility(View.VISIBLE);
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[2]);
                        }
                        else if(deg == 240f || deg == 600f || deg == 960f){
//                            Toast.makeText(MainActivity.this, "Match Tied!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Tied!");
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[2]);
                            Random ran = new Random();
                            int low = -2;
                            int high = 3;
                            int result = ran.nextInt(high-low) + low;
                            w[0] = w[0]+result;
                            w[1] = w[1]+result;
                            w[2] = w[2]+result;
                            p_win.setText(""+w[2]);
                        }
                        else{
//                            Toast.makeText(MainActivity.this, "Match Won!", Toast.LENGTH_SHORT).show();
                            result_text.setText("Match Won!");
                            like.setImageResource(R.drawable.like);
                            like.setVisibility(View.VISIBLE);
                            w[0]++;
                            w[1]++;
                            w[2]++;
                            p_win.setText(""+w[2]);
                            result_img.setImageResource(R.drawable.scissorwin);
                            result_img.setVisibility(View.VISIBLE);
                            result_text.setVisibility(View.VISIBLE);
                            p[0]++;
                            p[1]++;
                            p[2]++;
                            played_games.setText(""+p[2]);
                        }
                        icon.animate().rotationBy(360f - deg).setDuration(500).setStartDelay(500).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                p_stone.setVisibility(View.VISIBLE);
                                p_paper.setVisibility(View.VISIBLE);
                                save.setVisibility(View.VISIBLE);
                                p_scissor.setVisibility(View.VISIBLE);
                                result_img.setVisibility(View.GONE);
                                result_text.setVisibility(View.GONE);
                                like.setVisibility(View.GONE);
                            }
                        }).start();

                    }
                }).start();

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w[0] = 0;
                w[1] = 0;
                w[2] = 0;

                l[0] = 0;
                l[1] = 0;
                l[2] = 0;

                p[0] = 0;
                p[1] = 0;
                p[2] = 0;

                played_games.setText(""+0);
                p_win.setText(""+0);
                p_lost.setText(""+0);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String win, lost, played, percent;
                win = p_win.getText().toString();
                lost = p_lost.getText().toString();
                played = played_games.getText().toString();

                Intent intent = new Intent(v.getContext(), Settings.class);
                intent.putExtra("Played", played);
                intent.putExtra("Win", win);
                intent.putExtra("Lost", lost);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_dark:
                startActivity(new Intent(MainActivity.this, DarkMode.class));
                break;
            case R.id.menu_rules:
                startActivity(new Intent(MainActivity.this, Rules.class));
                break;
            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this, Settings.class));
                break;
            case R.id.menu_point:
                startActivity(new Intent(MainActivity.this, PointDistribution.class));
        }
        return true;
    }
}