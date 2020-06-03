package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mRegisterButton;
    TextView mTextViewRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mUsername = findViewById(R.id.edittext_username);
        mPassword = findViewById(R.id.edittext_password);
        mConfirmPassword = findViewById(R.id.edittext_cnf_password);
        mRegisterButton = findViewById(R.id.button_register);
        mTextViewRegister = findViewById(R.id.textview_register);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String cnf_password = mConfirmPassword.getText().toString().trim();

                if(password.equals(cnf_password)){
                    long val = db.addUser(username,password);
                    if(val>0) {
                        Toast.makeText(RegisterActivity.this, "You Have Register", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveToLogin);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registertion unsuccessfull please try again", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
