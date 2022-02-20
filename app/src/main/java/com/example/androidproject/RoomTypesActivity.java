package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class RoomTypesActivity extends AppCompatActivity {

    ListView listView;
    int[] arrimage = {R.drawable.singleroom, R.drawable.doubleroom, R.drawable.tripleroom, R.drawable.kingroom, R.drawable.suitroom, R.drawable.mastersuitroom};
    String[] arrname = {"Single Room", "Double Room", "Triple Room", "King Room", "Suit Room", "Master Suit Room"};
    String[] arrdescription = {"A room assigned to one person. That have one bed." ,
            "A room assigned to two people. That have two beds." ,
            "A room that can accommodate three persons and has been fitted with three twin beds",
            " A room with a king-sized bed. May be occupied by one or more people.",
            "A parlour or living room connected with to one or more bedrooms.",
            "The most expensive room provided by a hotel. Usually, only one president suite is available in one single hotel property."};
    String[] arrprice = {"80 $", "130 $", "170 $", "150 $", "170 $", "200 $"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_types);
        listView = findViewById(R.id.listview);

        listAdapter adapter = new listAdapter(this, arrname, arrdescription, arrprice, arrimage);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RoomTypesActivity.this, RoomActivity.class);
                intent.putExtra("imageId", arrimage[position]);
                intent.putExtra("name", arrname[position]);
                intent.putExtra("description", arrdescription[position]);
                intent.putExtra("price", arrprice[position]);
                startActivity(intent);
            }
        });

    }

    class listAdapter extends ArrayAdapter<String> {
        Context context;
        String rname[];
        String rdesc[];
        String rprice[];
        int rimg[];

        public listAdapter(Context context, String rname[], String rdesc[], String rprice[], int rimg[]) {
            super(context, R.layout.roomitem, rname);

            this.rname = rname;
            this.rdesc = rdesc;
            this.rimg = rimg;
            this.rprice = rprice;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.roomitem, parent,false);

            CircleImageView img = view.findViewById(R.id.image);
            TextView name = view.findViewById(R.id.name);
            name.setText(rname[position]);
            img.setImageResource(rimg[position]);

            return view;
        }
    }
//
//    private void getRooms(){
//
//        StringRequest stringReuest= new StringRequest(Request.Method.GET, BASE_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try{
//                            JSONArray array = new JSONArray(response);
//                            for(int i=0; i<array.length(); i++){
//                                JSONObject object = array.getJSONObject(i);
//                                String name = object.getString("name");
//                                String description = object.getString("description");
//                                String image = object.getString("image");
//                                double price = object.getDouble("price");
//
//                            }
//                        }catch (Exception e){
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity2.this, error.toString(),Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}