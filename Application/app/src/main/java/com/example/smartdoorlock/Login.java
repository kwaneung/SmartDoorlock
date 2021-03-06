package com.example.smartdoorlock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class Login extends AppCompatActivity {
    Button Login;
    EditText Domain;
    EditText UUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Domain = (EditText)findViewById(R.id.Domain);
        UUID = (EditText)findViewById(R.id.UUID);
        Login = (Button)findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URI uri ;
                String domain = "ws://" + Domain.getText().toString() + ":180";
                singleton.getInstance().URI = new String(domain);
                try{
                    uri = new URI(domain);
                } catch (URISyntaxException e){
                    e.printStackTrace();
                    return;
                }
                WebSocketClient webSocketClient = new WebSocketClient(uri,new Draft_17()) {
                    @Override
                    public void onOpen(ServerHandshake handshakedata) {
                        this.send("8");
                        this.send(UUID.getText().toString());
                    }

                    @Override
                    public void onMessage(final String message) {
                        if (!message.equals("false")) {
                            singleton.getInstance().role = new String(message);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override public void run() {
                                    Toast.makeText(Login.this, "UUID가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        this.close();
                    }

                    @Override
                    public void onClose(int code, String reason, boolean remote) {

                    }

                    @Override
                    public void onError(Exception ex) {
                        runOnUiThread(new Runnable() {
                            @Override public void run() {
                                Toast.makeText(Login.this, "도메인 접속 실패", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                };
                webSocketClient.connect();
            }

        });
    }
}
