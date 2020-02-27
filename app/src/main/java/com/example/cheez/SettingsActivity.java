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


   @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
       SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
       final SharedPreferences.Editor editor = settings.edit();
       int id_key = settings.getInt("ID_KEY", 0);

        setContentView(R.layout.activity_settings);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        back_btn = (Button) findViewById(R.id.back_btn);
        id_text = (EditText) findViewById(R.id.id_text);




       id_text.setText(Integer.toString(id_key));

       ok_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editor.putInt("ID_KEY",Integer.parseInt(id_text.getText().toString()));
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
