package com.example.cheez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;



public class SettingsActivity extends AppCompatActivity {



    private Button ok_btn;
    private  Button back_btn;
    private EditText id_text;
    private EditText server_ip;


   @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
       SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
       final SharedPreferences.Editor editor = settings.edit();
       String id_key = settings.getString("ID_KEY", "введите ПИН");
       String srv_ip = settings.getString("SERVER_IP", "35.158.139.6");

        setContentView(R.layout.activity_settings);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        back_btn = (Button) findViewById(R.id.back_btn);
        id_text = (EditText) findViewById(R.id.id_text);
        server_ip = (EditText) findViewById(R.id.server_ip);




       id_text.setText(id_key);
       server_ip.setText(srv_ip);

       ok_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editor.putString("ID_KEY",id_text.getText().toString());
               editor.putString("SERVER_IP",server_ip.getText().toString());
               editor.apply();

               Intent intent1 = new Intent(SettingsActivity.this, MainActivity.class);
               startActivity(intent1);

           }
       });



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });




    }

}
