package com.example.catal.cardposting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.catal.cardposting.DAO.Database;
import com.google.android.gms.common.api.Api;

public class MainActivity extends AppCompatActivity implements MainNavigator{
    Button server;
    Button client;
    String deckType;
    RadioGroup type;
    Database db;
    EditText et;
    RadioButton initBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        server = findViewById(R.id.bt_server);
        client = findViewById(R.id.bt_client);
//        deckType = "traditional";
        type = findViewById(R.id.radioGroup);
        initBtn = findViewById(R.id.radioButton);
        //et = findViewById(R.id.et);
        //db = new Database(this);


        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startServer();
            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClient();
            }
        });

        initBtn.toggle();
    }

    public void startServer() {
        Intent intent = new Intent(this, ServerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isOtherHand", true);
        int id = type.getCheckedRadioButtonId();
        RadioButton aux = findViewById(id);
        deckType = aux.getText().toString();
        bundle.putString("deckType", deckType);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startClient() {
        Intent intent = new Intent(this, ClientActivity.class);
        Bundle bundle = new Bundle();
        int id = type.getCheckedRadioButtonId();
        RadioButton aux = findViewById(id);
        deckType = aux.getText().toString();
        bundle.putString("deckType", deckType);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void displayServerError()
    {

    }

    public void displayClientError()
    {

    }
}
