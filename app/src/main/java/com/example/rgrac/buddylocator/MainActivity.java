package com.example.rgrac.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editUsername,editPassword;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static Activity mainact;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUsername= (EditText) findViewById(R.id.username);
        editPassword= (EditText) findViewById(R.id.password);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        mainact=this;
        editor.commit();
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        final TextView registerLink = (TextView) findViewById(R.id.tvRegister);

        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

    }
    public void login(View view){
        String status="";
        Register register = new Register(getApplicationContext(),status);
        if(!editUsername.getText().toString().equals("")&&!editPassword.getText().toString().equals("")) {
            register.execute("login", editUsername.getText().toString(), editPassword.getText().toString());
            Log.d("STATUS", status);
            if (status.equals("Login Successful")) {
                SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("username", editUsername.getText().toString());
                editor.commit();
                SharedPreferences pref = this.getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                System.out.print("Shared Preferences"+pref.getString("username",null));
                Intent loginIntent = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(loginIntent);
            }
            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("username", editUsername.getText().toString());
            editor.commit();
            Intent in = new Intent(this, IndexActivity.class);
            startActivity(in);
            finish();
        }
    }
    public void SignUp(View view){
        Intent in=new Intent(this,RegisterActivity.class);
        startActivity(in);
    }
}
