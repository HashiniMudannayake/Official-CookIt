package com.example.officialcookit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class User_Profile extends AppCompatActivity {

    TextView userName, email, location, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        //hooks
        userName = findViewById(R.id.UserName);
        email = findViewById(R.id.Email);
        location = findViewById(R.id.Location);
        password = findViewById(R.id.Password);

        //ShowAllData
        showUserData();
    }

    private void showUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("User Name");
        String user_email = intent.getStringExtra("Email");
        String user_location = intent.getStringExtra("Location");
        String user_password = intent.getStringExtra("Password");

        userName.getEditText().setText(user_username);
        email.getEditText().setText(user_email);
        location.getEditText().setText(user_location);
        password.getEditText().setText(user_password);
    }

}