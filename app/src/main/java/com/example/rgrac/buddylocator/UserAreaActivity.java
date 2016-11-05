package com.example.rgrac.buddylocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText editUsername= (EditText) findViewById(R.id.editUsername);
        final EditText editAge= (EditText) findViewById(R.id.editAge);

        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMSG);

    }
}
