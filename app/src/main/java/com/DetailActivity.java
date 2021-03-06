package com;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView foodDescription;
    ImageView foodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodDescription=(TextView)findViewById(R.id.txtDescription);
        foodImage=(ImageView)findViewById(R.id.ivImage2);


        Bundle mBundle=getIntent().getExtras();

        if (mBundle!=null)
        {
            foodDescription.setText(mBundle.getString("Description"));
            foodImage.setImageResource(mBundle.getInt("Image"));
        }

    }
}