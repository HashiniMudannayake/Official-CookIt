package com.example.officialcookit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ValidateDetails extends AppCompatActivity {
    public Button callUserProfile;

    Button validate;
    ImageView image;
    TextView validateName, validatePassword;
    TextInputLayout inputValidateName, inputValidatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_details);

        //--- calling user Profile
        callUserProfile = (Button) findViewById(R.id.editProfileBtn);
        callUserProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ValidateDetails.this,User_Profile.class);
                startActivity(intent);
            }
        });//-----end calling userprofile

        private Boolean validateUserName () {
            String val = validateName.getEditText().getText().toString();
            if (val.isEmpty()) {
                validateName.setError("Field cannot be empty");
                return false;
            } else {
                validateName.setError(null);
                validateName.setErrorEnable(false);
                return true;
            }
        }

        private final Boolean validatePassword () {
            String val = validatePassword.getEditText().getText().toString();
            if (val.isEmpty()) {
                validatePassword.setError("Field cannot be empty");
                return false;
            } else if (!val.matches(String.valueOf(inputValidatePassword))) {
                validatePassword.setError("Password is too weak");
                return false;
            } else {
                validatePassword.setError(null);
                validatePassword.setErrorEnabled(false);
                return true;
            }

        }

        public void AddDetails (View view){
            //validate add details
            if (!validateUserName() | !validatePassword()) {
                return;
            } else {
                isUser();
            }
        }

        private void isUser () {
            final String userEnteredUserName = validateName.getEditText().getText().toString().trim();
            final String userEnteredPassword = validatePassword.getEditText().getText().toString().trim();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            Query checkUser = reference.orderByChild("User Name").equalTo(userEnteredUserName);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    DataSnapshot dataSnapshot = null;
                    if (dataSnapshot.exists()) {

                        validateName.setError(null);
                        validateName.setErrorEnabled(false);

                        String passwordFromDB = dataSnapshot.child(userEnteredPassword).child("Password").getValue(String.class);

                        if (passwordFromDB.equals(userEnteredPassword)) {
                            validateName.setError(null);
                            validateName.setErrorEnabled(false);

                            String usernameFromDB = dataSnapshot.child(userEnteredUserName).child("User Name").getValue(String.class);
                            String locationFromDB = dataSnapshot.child(userEnteredUserName).child("Location").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(userEnteredUserName).child("Email").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), User_Profile.class);
                            intent.putExtra("name", usernameFromDB);
                            intent.putExtra("location", locationFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("password", passwordFromDB);

                            startActivity(intent);
                        } else {
                            validatePassword.setError("Wrong Password");
                            validatePassword.requestFocus();
                        }

                    } else {
                        validateName.setError("No such user exist");
                        validateName.requestFocus();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
