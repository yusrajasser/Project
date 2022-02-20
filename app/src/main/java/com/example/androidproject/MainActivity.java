package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Animation up,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.logo);
        up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up);
        imageView.setAnimation(up);
        down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down);
        imageView.setAnimation(down);
        textView = findViewById(R.id.nameapp);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        },3500);
    }
}