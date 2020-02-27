package com.example.cheez;
import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    public String result = "Не смог распознать - какая-то ерунда";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        String prefix = "trash";

        SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
        //final SharedPreferences.Editor editor = settings.edit();
        int id_key = settings.getInt("ID_KEY", 0);
        if (id_key !=0) {prefix = Integer.toString(id_key);}

        String start_url = "http://35.158.139.6:8080/direct/";
        String qr = rawResult.getText();
        String qr_query = null;
        try {
            qr_query = URLEncoder.encode(qr, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String full_url = start_url+prefix+"/"+qr_query;



        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, full_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MainActivity.textView.setText(response.toString());
                        onBackPressed();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String error_msg = error.toString();
                MainActivity.textView.setText("Нет соединения с сервером обмена");
                onBackPressed();

            }
        });



        queue.add(stringRequest);

        //MainActivity.textView.setText(result);
        //onBackPressed();

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}