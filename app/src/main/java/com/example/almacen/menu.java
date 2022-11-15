package com.example.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void tutorial(View view) {
        mp = MediaPlayer.create(this, R.raw.rocas);
        mp.start();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void almacen(View view) {
        mp = MediaPlayer.create(this, R.raw.rocas);
        mp.start();
        Intent intent = new Intent(this, tutorial.class);
        startActivity(intent);
    }
}