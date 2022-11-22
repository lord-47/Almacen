package com.example.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MyService3.class));
    }

    public void tutorial(View view) {
        mp = MediaPlayer.create(this, R.raw.rocas);
        mp.start();
        Intent intent = new Intent(this, tutorial2.class);
        startActivity(intent);
    }
    public void almacen(View view) {
        mp = MediaPlayer.create(this, R.raw.rocas);
        mp.start();
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }
    public void ajustes(View view) {
        mp = MediaPlayer.create(this, R.raw.rocas);
        mp.start();
        Intent intent = new Intent(this, menuSonido2.class);
        startActivity(intent);
    }
}