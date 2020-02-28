package com.example.cheez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


    private Button btn;
    private Button set_btn;
    public static TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        set_btn  = findViewById(R.id.set_btn);
        textView= findViewById(R.id.textView);


        // -------- make internet connection check ------------ //

        String full_url = "http://google.com";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, full_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String response_str = response.toString();
                        MainActivity.textView.setText("Соединение установлено");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String error_msg = error.toString();
                MainActivity.textView.setText("Нет соединения с Интернетом");


            }
        });



        queue.add(stringRequest);
        //-------------------------------------------------------------------//


        set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent1);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
                //final SharedPreferences.Editor editor = settings.edit();
                String id_key = settings.getString("ID_KEY", "trash");
                if (id_key == "trash") {
                    textView.setText("не задан пин-код");
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
