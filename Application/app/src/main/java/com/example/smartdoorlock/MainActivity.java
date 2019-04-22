package com.example.smartdoorlock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button LogButton;
    Button PhotoButton;
    Button UserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
