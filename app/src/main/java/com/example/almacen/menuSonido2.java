package com.example.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Objects;

public class menuSonido2 extends AppCompatActivity {
    Button btnPlayChill;
    Button btnPlayLofi;
    AudioManager gestorSonido;
    Boolean isPlayingChill;
    Boolean isPlayingLofi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sonido2);
        gestorSonido = (AudioManager) getSystemService(AUDIO_SERVICE);
        btnPlayChill=findViewById(R.id.btnPlayChill);
        btnPlayLofi=findViewById(R.id.btnPlayLofi);
        isPlayingChill = isMyServiceRunning(MyService3.class);
        isPlayingLofi = isMyServiceRunning(AlterService.class);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.pngegg);

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

        btnPlayChill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlayingLofi){
                    stopService(new Intent(menuSonido2.this, AlterService.class));
                    isPlayingLofi =false;
                    startService(new Intent(menuSonido2.this, MyService3.class));
                    isPlayingChill=true;
                }else {
                    startService(new Intent(menuSonido2.this, MyService3.class));
                    isPlayingChill = true;
                }
            }
        });

        btnPlayLofi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlayingChill){
                    stopService(new Intent(menuSonido2.this, MyService3.class));
                    isPlayingChill =false;
                    startService(new Intent(menuSonido2.this, AlterService.class));
                    isPlayingLofi=true;
                }else {
                    startService(new Intent(menuSonido2.this, AlterService.class));
                    isPlayingLofi = true;
                }
            }
        });

    }

    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}