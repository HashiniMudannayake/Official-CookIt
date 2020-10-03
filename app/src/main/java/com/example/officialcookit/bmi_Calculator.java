package com.example.officialcookit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class  bmi_Calculator extends AppCompatActivity {
    EditText weight,height;
    TextView resultText;
    String calculation,BMIresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resultText = findViewById(R.id.result);

    }

    public void calculateBMI(View view){
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();

        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) /100;

        float BMI = weightValue / (heightValue * heightValue);

        if(BMI<16){
            BMIresult = "Severely under weight";
        }else if (BMI>18.5){
            BMIresult = "Under weight";
        }else if(BMI>=18.5 && BMI<=24.9){
            BMIresult = "Normal weight";
        }else if(BMI>=25 && BMI<=29.9){
            BMIresult = "Over weight";
        }else{
            BMIresult ="Obese";
        }

        calculation = "Result :\n\n" + BMI + "\n" + BMIresult;
        resultText.setText(calculation);
    }
}