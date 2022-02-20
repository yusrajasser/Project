package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidproject.Adapter.RoomAdapter;
import com.example.androidproject.Entity.Room;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReceptionActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private List<Room> items = new ArrayList<>();
    private String BASE_URL = "http://10.0.2.2:80/hotel/getallrooms.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        recycler = findViewById(R.id.roomsrecycle);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
    }

    private void loadItems() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray array = new JSONArray(response);
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        String room_id = object.getString("room_id");
                                        String price = object.getString("price");
                                        String room_number = object.getString("room_number");
                                        String room_floor = object.getString("room_floor");
                                        String room_tel = object.getString("room_tel");
                                        String booked_condition = object.getString("booked_condition");
                                        String TV_number = object.getString("TV_number");
                                        String beds_number = object.getString("beds_number");
                                        String person_number = object.getString("person_number");
                                        String img = object.getString("img");
                                        String balcony_number = object.getString("balcony_number");
                                        Room room = new Room(Integer.parseInt(room_id), Integer.parseInt(price), Integer.parseInt(room_number), Integer.parseInt(room_floor),
                                                Integer.parseInt(room_tel), Integer.parseInt(booked_condition), Integer.parseInt(TV_number), Integer.parseInt(beds_number),
                                                Integer.parseInt(person_number), img, Integer.parseInt(balcony_number));
                                        items.add(room);
                                    }

                                } catch (Exception e) {

                                }

                                RoomAdapter adapter = new RoomAdapter(ReceptionActivity.this, items);
                                recycler.setAdapter(adapter);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ReceptionActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

                Volley.newRequestQueue(ReceptionActivity.this).add(stringRequest);

            }
        });
    }

    public void RoomSearch(View view) {
        Intent intent = new Intent(this, RoomTypesActivity.class);
        startActivity(intent);

    }


    public void BookforCustomer(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
