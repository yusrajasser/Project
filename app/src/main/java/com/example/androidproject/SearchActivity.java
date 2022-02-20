package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    NumberPicker numberPicker ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        numberPicker = findViewById(R.id.numberpicker) ;
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(4);
    }
    public void search(View view) {
        Intent intent = new Intent(this,SearchResult.class) ;
        intent.putExtra("data",""+numberPicker.getValue()) ;
        startActivity(intent);
    }

}