package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    Button mLoginButton;
    TextView mTextViewRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        mUsername = findViewById(R.id.edittext_username);
        mPassword = findViewById(R.id.edittext_password);
        mLoginButton = findViewById(R.id.button_Login);
        mTextViewRegister = findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                Boolean res = db.checkUser(username,password);
                if(res == true){
                    Toast.makeText(MainActivity.this, "Succesfully login",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "login UnSuccessFull! please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
