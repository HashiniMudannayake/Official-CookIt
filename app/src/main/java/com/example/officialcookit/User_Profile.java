package com.example.officialcookit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Profile extends AppCompatActivity {

    TextView userName, email, location, password;

    //Global variables to hold user data inside this activity
    String user_username,user_email,user_location,user_password;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        reference = FirebaseDatabase.getInstance().getReference("Users");

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
        user_username = intent.getStringExtra("User Name");
        user_email = intent.getStringExtra("Email");
        user_location = intent.getStringExtra("Location");
        user_password = intent.getStringExtra("Password");

        userName.getEditText().setText(user_username);
        email.getEditText().setText(user_email);
        location.getEditText().setText(user_location);
        password.getEditText().setText(user_password);
    }

}