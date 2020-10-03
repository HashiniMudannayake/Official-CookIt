package com.example.officialcookit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddDetails extends AppCompatActivity {

    //variables
    TextInputLayout addUserName, addEmail, addLocation, addPassword;
    Button addDetails;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        //Hook to all xml elements in activity_add_details.xml
        addUserName = findViewById(R.id.UserName);
        addEmail = findViewById(R.id.Email);
        addLocation = findViewById(R.id.Location);
        addPassword = findViewById(R.id.Password);
        addDetails = findViewById(R.id.addDetailsSubmit);


        //save data in firebase on button click
        addDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                //Get all the values
                String username = addUserName.getEditText().getText().toString();
                String email = addEmail.getEditText().getText().toString();
                String location = addLocation.getEditText().getText().toString();
                String password = addPassword.getEditText().getText().toString();
                UserHelperClass helperclass = new UserHelperClass(username, email, location, password);
                reference.child(username).setValue(helperclass);
            }

        });//Add details method end
        //onCreate method end
    }
}