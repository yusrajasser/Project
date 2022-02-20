package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class CustomerLoginActivity extends AppCompatActivity {

    private String name, password;
    private String BASE_URL = "http://10.0.2.2:80/hotel/login.php";

    private EditText editTextUserName;
    private EditText editTextPassword;
    private TextView txtViewHandleClick;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        name ="";
        password = "";

        txtViewHandleClick =findViewById(R.id.txtView);
        loginButton =findViewById(R.id.loginButton);
        editTextUserName = findViewById(R.id.usernameEdt);
        editTextPassword = findViewById(R.id.passEdt);

    }

    public void login(View view) {

        Intent intent2 = new Intent(this,SearchActivity.class);
        startActivity(intent2);

        name = editTextUserName.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        if(!name.equals("name") && !password.equals("password")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res", response);
                    if (response.equals("success")) {
                        Intent intent = new Intent(CustomerLoginActivity.this, Success.class);
                        startActivity(intent);
                        finish();

                    } else if (response.equals("failure")) {
                        Toast.makeText(CustomerLoginActivity.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CustomerLoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, CustomerRegisterActivity.class);
        startActivity(intent);
        finish();
    }
}