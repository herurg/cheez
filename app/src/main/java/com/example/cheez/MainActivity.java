package com.example.cheez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                int id_key = settings.getInt("ID_KEY", 0);
                if (id_key == 0) {
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
