package com.example.learntogether;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Invitetutor extends AppCompatActivity {

    TextView na;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitetutor);
        na = (TextView) findViewById(R.id.NAame);

    }
}
