package com.example.smartdoorlock;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LogLookUp extends AppCompatActivity {

    WebSocketClient mWebSocketClient;

    TextView start;
    TextView end;
    Button Search;
    int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_look_up);

        final DatePickerDialog.OnDateSetListener mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                start.setText(String.format("%d-%d-%d",mYear,mMonth +1, mDay));
            }
        };
        final DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                end.setText(String.format("%d-%d-%d",mYear,mMonth +1, mDay));
            }
        };

        start = (TextView) findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LogLookUp.this,mDateSetListener1, mYear, mMonth, mDay).show();
            }
        });

        end = (TextView) findViewById(R.id.End);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LogLookUp.this,mDateSetListener2, mYear, mMonth, mDay).show();
            }
        });

        Search = (Button) findViewById(R.id.LogSearch);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URI uri ;
                try{
                    uri = new URI("ws://3Nyang.gonetis.com:180");
                } catch (URISyntaxException e){
                    e.printStackTrace();
                    return;
                }
                    WebSocketClient webSocketClient = new WebSocketClient(uri,new Draft_17()) {
                        @Override
                        public void onOpen(ServerHandshake handshakedata) {
                            this.send(start.getText().toString() + '-' + end.getText().toString());

                        }

                        @Override
                        public void onMessage(String message) {


                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {

                        }

                        @Override
                        public void onError(Exception ex) {

                        }
                    };
                    webSocketClient.connect();
            }
        });
    }


}
