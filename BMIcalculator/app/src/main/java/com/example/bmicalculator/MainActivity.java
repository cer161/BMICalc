package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
public class MainActivity extends AppCompatActivity {

    public double height;
    public double weight;
    public static double bmi;
    public boolean isMetric;

    //GUI components
    public EditText heightInput;
    public EditText weightInput;
    public Button bmiButton;
    public Button adviceButton;
    public TextView bmiOutput;
    public RadioButton metric;
    public RadioButton english;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heightInput = (EditText) findViewById(R.id.heightInput);
        weightInput = (EditText) findViewById(R.id.weightInput);
        bmiOutput = (TextView) findViewById(R.id.bmiOutput);

        //The bmi button is pressed
        bmiButton = (Button) findViewById(R.id.bmiButton);
        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfUnitsSelected() == false || checkIfInputEntered() == false){
                    return;
                }
                calculateBMI();
            }
        });

        //The advice button is pressed
        adviceButton = (Button) findViewById(R.id.adviceButton);
        adviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfUnitsSelected() == false || checkIfInputEntered() == false){
                    return;
                }
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
            }
       });

        //The metric button is clicked
        metric = (RadioButton) findViewById(R.id.metric);
        metric.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setMetric();
            }
        });

        //The english button is clicked
        english = (RadioButton) findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setEnglish();
            }
        });

    }

    //calculates and prints bmi value to the screen
    public void calculateBMI() {
        bmi = 0;
        height = Double.valueOf(heightInput.getText().toString());
        weight = Double.valueOf(weightInput.getText().toString());
        if (isMetric == true) {
            bmi = (weight / height);
        } else {
            bmi = (weight * 703) / (height * height);
        }
        bmiOutput.setText(String.format("Your bmi is: %.2f", bmi));

    }

    public void setMetric() {
        if (english.isChecked()) {
            english.setChecked(false);
        }
        heightInput.setHint("Enter height in meters");
        weightInput.setHint("Enter weight in kilograms");
        isMetric = true;
    }

    public void setEnglish() {
        if (metric.isChecked()) {
            metric.setChecked(false);
        }
        heightInput.setHint("Enter height in inches");
        weightInput.setHint("Enter weight in pounds");
        isMetric = false;
    }

    public boolean checkIfUnitsSelected(){
        if (!english.isChecked() && !metric.isChecked()){
            Context context = getApplicationContext();
            CharSequence text = "Error: Select English or Metric units before " +
                    "calculating BMI or requesting advice";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return false;
        }
        return true;
    }

    public boolean checkIfInputEntered(){
        height = Double.valueOf(heightInput.getText().toString());
        weight = Double.valueOf(weightInput.getText().toString());
        if (height < 0.1 || weight < 0.1 ){
            Context context = getApplicationContext();
            CharSequence text = "Error: Please enter valid numeric input for Weight and Height";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return false;
        }
        return true;
    }

}
