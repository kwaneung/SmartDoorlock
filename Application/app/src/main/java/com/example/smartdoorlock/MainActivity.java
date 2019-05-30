package com.example.smartdoorlock;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class MainActivity extends AppCompatActivity {
    Button LogButton;
    Button PhotoButton;
    Button UserButton;
    Button DoorOpen;
    TextView role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        role = (TextView)findViewById(R.id.Role);
        role.setText(singleton.getInstance().role);
        LogButton = (Button)findViewById(R.id.Log);
        LogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogLookUp.class);
                startActivity(intent);
            }
        });
        PhotoButton = (Button)findViewById(R.id.Photo);
        PhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhotoLoopUp.class);
                startActivity(intent);
            }
        });
        UserButton = (Button)findViewById(R.id.User);
        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserLookUp.class);
                startActivity(intent);
            }
        });

        StrictMode.ThreadPolicy ourPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(ourPolicy);
        // 위에 두 문장을 추가해 줘야지 동작함. 아마도 권한, 정책관련 옵션같음.
        DoorOpen = (Button)findViewById(R.id.DoorOpen);
        DoorOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DoorOpen.class);
//                startActivity(intent);
                try {
                    HttpClient client = new DefaultHttpClient();
                    String getURL = "http://192.168.35.208/on";
                    HttpGet get = new HttpGet(getURL);
                    HttpResponse responseGet = client.execute(get);
                    HttpEntity resEntityGet = responseGet.getEntity();
                    if (resEntityGet != null) {
                        // 결과를 처리합니다.
                        Log.i("RESPONSE", EntityUtils.toString(resEntityGet));
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                }
            }
        });
    }
}
