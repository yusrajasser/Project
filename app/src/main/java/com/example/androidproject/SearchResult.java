package com.example.androidproject;

import android.os.Bundle;
import android.os.Handler;
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

public class SearchResult extends AppCompatActivity {
    private RecyclerView recycler;
    private List<Room> items = new ArrayList<>();
    private  String BASE_URL = "http://10.0.2.2:80/hotel/getrooms.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        String numOfPerson = getIntent().getStringExtra("data") ;
        BASE_URL = BASE_URL+"?numOfPerson="+numOfPerson ;
        recycler = findViewById(R.id.roomsrecycle);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
    }

    private void loadItems() {
        Handler handler = new Handler() ;
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
                                        Room room = new Room(Integer.parseInt(room_id),Integer.parseInt(price),Integer.parseInt(room_number),Integer.parseInt(room_floor),
                                                Integer.parseInt(room_tel),Integer.parseInt(booked_condition),Integer.parseInt(TV_number),Integer.parseInt(beds_number),
                                                Integer.parseInt(person_number),img,Integer.parseInt(balcony_number));
                                        items.add(room);
                                    }

                                } catch (Exception e) {

                                }

                                RoomAdapter adapter = new RoomAdapter(SearchResult.this, items);
                                recycler.setAdapter(adapter);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchResult.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

                Volley.newRequestQueue(SearchResult.this).add(stringRequest);

            }
        });
    }


}