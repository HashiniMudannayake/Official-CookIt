package com.example.officialcookit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.util.Strings;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Profile extends AppCompatActivity {

    TextView userName, email, location, password;

    //Global variables to hold user data inside this activity
    String user_username, user_email, user_location, user_password;

    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);

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

        userName.getText().setText(user_username);
        email.getText().setText(user_email);
        location.getText().setText(user_location);
        password.getText().setText(user_password);
    }

    //----Update details
    public void updateSaveChangers(View view){

        if (isUserNameChanged() || isPasswordChanged() || isLocationChanged() || isEmailChanged()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Data is the same can not be updated ", Toast.LENGTH_SHORT).show();
    }

    private boolean isEmailChanged () {
        if (user_email.equals(email.getEditText().toString())) {
            reference.child(user_email).child("Email").setValue(email.getEditText().toString());
            user_email = email.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isLocationChanged () {
        if (user_location.equals(location.getEditText().toString())) {
            reference.child(user_location).child("Location").setValue(location.getEditText().toString());
            user_location = location.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged () {
        if (user_password.equals(password.getEditText().toString())) {
            reference.child(user_password).child("Password").setValue(password.getEditText().toString());
            user_password = password.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isUserNameChanged () {
        if (user_username.equals(userName.getEditText().toString())) {
            reference.child(user_username).child("User Name").setValue(userName.getEditText().toString());
            user_username = userName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

}
