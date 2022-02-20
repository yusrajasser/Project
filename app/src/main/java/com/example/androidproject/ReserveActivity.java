package com.example.androidproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

public class ReserveActivity extends AppCompatActivity {
    Button reserve, startdate, enddate;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private static String start = "";
    private static String end = "";
    private static String room_id = "";
    private static String user_id = "";

    private String BASE_URL = "http://10.0.2.2:80/hotel/addReserve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        intiDatePicker();
        intiDatePicker1();
        reserve = findViewById(R.id.reserve);
        startdate = findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        room_id = getIntent().getStringExtra("room_id");
        user_id = "1"; // hay mn intent lazm

    }

    private void intiDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                start = makeDateString(day, month, year);
                startdate.setText(start);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void intiDatePicker1() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                end = makeDateString(day, month, year);
                enddate.setText(end);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getmonthformate(month) + " " + day + " " + year;
    }

    private String getmonthformate(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";
    }

    public void opendatepicker(View view) {
        datePickerDialog.show();
    }

    public void opendatepicker1(View view) {
        datePickerDialog1.show();
    }

    public  void Reserve(View view){
        Intent intent = new Intent(this, Success.class);
        startActivity(intent);
    }

    public void reserve(View view) throws UnsupportedEncodingException {


        String user = URLEncoder.encode(user_id, "UTF8");
        String room = URLEncoder.encode(room_id, "UTF8");
        String start1 = URLEncoder.encode(start, "UTF8");
        String end1 = URLEncoder.encode(end, "UTF8");
        String url = BASE_URL + "?room_id=" + room + "&user_id=" + user + "&start=" + start1 + "&end=" + end1;
        Log.d("url is: ", url);
        if (!start1.equals("") || !end1.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(ReserveActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ReserveActivity.this, "error" + e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ReserveActivity.this, "error" + error.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
            );
            RequestQueue requestQueue = Volley.newRequestQueue(ReserveActivity.this);
            requestQueue.add(stringRequest);


        }else {
            Toast.makeText(ReserveActivity.this,"please fill start and end date", Toast.LENGTH_LONG).show();
        }
    }
}