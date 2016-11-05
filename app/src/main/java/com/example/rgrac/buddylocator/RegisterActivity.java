package com.example.rgrac.buddylocator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

    EditText editAge, editName, editUsername, editPassword;
    String userID, name, username, userpassword, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editAge = (EditText) findViewById(R.id.editAge);
        editName= (EditText) findViewById(R.id.editName);
        editUsername= (EditText) findViewById(R.id.editUsername);
        editPassword= (EditText) findViewById(R.id.editPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);


    }

    public void userReg(View view)
    {

    }
}
