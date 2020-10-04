package com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    //Variables
    private EditText regName, regEmail, regUserNm, regPassword;
    private TextView regToLogIn;
    private Button regBttn;
    private ProgressBar progressBar;

   // User user;
    //long maxid = 0;

    //DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        //Hooks to all xml elements in activity_sign_up.xml
        regName = (EditText)findViewById(R.id.FullNameField);
        regEmail = (EditText)findViewById(R.id.emailField);
        regUserNm = (EditText)findViewById(R.id.usrNmField);
        regPassword = (EditText)findViewById(R.id.psswdField);

        regToLogIn = (TextView) findViewById(R.id.alreadyText);
        regToLogIn.setOnClickListener(this);

        regBttn = (Button) findViewById(R.id.regBtn);
        regBttn.setOnClickListener(this);

        progressBar = (ProgressBar)findViewById(R.id.progressBar1);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alreadyText:
                startActivity(new Intent(this,Login.class));
                break;
            case R.id.regBtn:
                rigisterUser();
                break;
        }
    }

    private void rigisterUser() {
        final String fullname = regName.getEditableText().toString().trim();
        final String email = regEmail.getEditableText().toString().trim();
        final String usernm = regUserNm.getEditableText().toString().trim();
        final String passwrd = regPassword.getEditableText().toString().trim();

        if (fullname.isEmpty()){
            regName.setError("Full Name is required");
            regName.requestFocus();
            return;
        }

        if (email.isEmpty()){
            regEmail.setError("Email is required");
            regEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regEmail.setError("Please provide a valid Email");
            regEmail.requestFocus();
            return;
        }

        if (usernm.isEmpty()){
            regUserNm.setError("Username is required");
            regUserNm.requestFocus();
            return;
        }

        if (passwrd.isEmpty()){
            regPassword.setError("Password is required");
            regPassword.requestFocus();
            return;
        }

        if (passwrd.length() < 6){
            regPassword.setError("Min password length is 6 characters!");
            regPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, passwrd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(fullname, email, usernm, passwrd);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUp.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(SignUp.this,Terms.class);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(SignUp.this,"Failed to register!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }


                                }
                            });

                        }else {
                            Toast.makeText(SignUp.this,"Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }







    /*user = new User();
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        regBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setName(regName.getEditableText().toString().trim());
                user.setEmail(regEmail.getEditableText().toString().trim());
                user.setUsrname(regUserNm.getEditableText().toString().trim());
                user.setPassword(regPassword.getEditableText().toString().trim());

                reference.child(String.valueOf(maxid+1)).setValue(user);
                Toast.makeText(SignUp.this,"Account Created Successfully", Toast.LENGTH_LONG).show();
            }
        });

        regToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });*/



}