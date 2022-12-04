package com.example.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    Boolean isPlayingChill;
    Boolean isPlayingLofi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.imageView2);
        Animation shakes = AnimationUtils.loadAnimation(this,R.anim.shake);
        image.startAnimation(shakes);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.pngegg);

        isPlayingChill = isMyServiceRunning(MyService3.class);
        isPlayingLofi = isMyServiceRunning(AlterService.class);

        if(!isPlayingChill&!isPlayingLofi){
            startService(new Intent(this, MyService3.class));
        }
    }

    public void tutorial(View view) {
        mp = MediaPlayer.create(this, R.raw.boton);
        mp.start();
        Intent intent = new Intent(this, tutorial2.class);
        startActivity(intent);
    }
    public void almacen(View view) {
        mp = MediaPlayer.create(this, R.raw.boton);
        mp.start();
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }
    public void ajustes(View view) {
        mp = MediaPlayer.create(this, R.raw.boton);
        mp.start();
        Intent intent = new Intent(this, menuSonido2.class);
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