package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button customerBtn;
    private Button receptionbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        customerBtn=findViewById(R.id.customerbtn);
        receptionbtn =findViewById(R.id.receptionbtn);

        }

    public void reception(View view) {
        Intent intent = new Intent(this,ReceptionLogin.class) ;
        startActivity(intent);
    }

    public void customer(View view) {
        Intent intent= new Intent(this,CustomerRegisterActivity.class);
        startActivity(intent);
    }
}