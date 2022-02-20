package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

class RoomActivity extends AppCompatActivity {

    CircleImageView img;
    TextView name, desc, price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        img=findViewById(R.id.image);
        name= findViewById(R.id.RoomName);
        desc= findViewById(R.id.RoomDesc);

        price= findViewById(R.id.RoomPrice);

        Intent intent = this.getIntent();

        String roomname= intent.getStringExtra("name");
        String description= intent.getStringExtra("description");
        String roomprice= intent.getStringExtra("price");
        int imageId= intent.getIntExtra("imageId",R.drawable.singleroom);

        img.setImageResource(imageId);
        name.setText(roomname);
        desc.setText(description);
        price.setText(roomprice);

    }
}