package com.example.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class menuSonido2 extends AppCompatActivity {
    AudioManager gestorSonido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sonido2);
        gestorSonido = (AudioManager) getSystemService(AUDIO_SERVICE);

        int volumenMaximo = gestorSonido.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumenActual = gestorSonido.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar barraSonido = (SeekBar) findViewById(R.id.barraVolumen);
        barraSonido.setMax(volumenMaximo);
        barraSonido.setProgress(volumenActual);

        barraSonido.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                gestorSonido.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}