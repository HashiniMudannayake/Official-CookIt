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

public class Login extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Custom comment.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        register = (TextView)findViewById(R.id.createText);
        register.setOnClickListener(this);

        signIn = (Button)findViewById(R.id.signInBtn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText)findViewById(R.id.logInemailField);
        editTextPassword = (EditText)findViewById(R.id.logInpsswdField);

        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createText:
                startActivity(new Intent(this,SignUp.class));
                break;
            case R.id.signInBtn:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getEditableText().toString().trim();
        String password = editTextPassword.getEditableText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid Email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent intent = new Intent(Login.this,UserProfile.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(Login.this,"Failed to Login! Please check credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}