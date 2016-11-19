package com.example.rgrac.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText editfname, editsname, editUsername, editPassword,editmail,editPassword2;
    String fname, sname, uname, userpassword, email;
    public static Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editfname = (EditText) findViewById(R.id.fname);
        editsname= (EditText) findViewById(R.id.sname);
        editUsername= (EditText) findViewById(R.id.editUsername);
        editPassword= (EditText) findViewById(R.id.editPassword);
        editPassword2= (EditText) findViewById(R.id.editPassword);
        editmail= (EditText) findViewById(R.id.email);
       // final Button bRegister = (Button) findViewById(R.id.bRegister);
        act=this;
       
    }
	public void register(View view){
	    sname=editsname.getText().toString();
        fname=editfname.getText().toString();
        uname=editUsername.getText().toString();
        email=editmail.getText().toString();
        userpassword=editPassword.getText().toString();
		String status="";
        if(editPassword.getText().toString().equals(editPassword2.getText().toString())) {
            Register register = new Register(getApplicationContext(), status);
            register.execute("register", uname, userpassword, email);
            System.out.println("STATUS" + status);
            Log.d("STATUS", status);
            if (status.equals("Register Successful")) {
                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        }
        else{
            Toast toast=Toast.makeText(this,"Passwords don't match!", Toast.LENGTH_SHORT);
            toast.show();
        }
	}
}
