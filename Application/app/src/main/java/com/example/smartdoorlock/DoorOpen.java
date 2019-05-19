package com.example.smartdoorlock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class DoorOpen extends AppCompatActivity {
    Button Open;
    EditText WebDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_open);

        WebDomain = (EditText)findViewById(R.id.WebDomain);
        Open = (Button)findViewById(R.id.open);
        Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    URL url = new URL("http://" + WebDomain.getText().toString() + "/on");
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();

                    con.setRequestMethod("GET");
                    con.setDoInput(true);

                    con.getResponseCode();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                }
            }
        });
    }
}
