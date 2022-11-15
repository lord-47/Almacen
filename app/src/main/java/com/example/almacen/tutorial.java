package com.example.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class tutorial extends AppCompatActivity {
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        video = (VideoView) findViewById(R.id.videoView);
        video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.tutorial));
        MediaController controlador = new MediaController(this);
        video.setMediaController(controlador);
        video.start();
    }
    public void menu(View view) {
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }
}