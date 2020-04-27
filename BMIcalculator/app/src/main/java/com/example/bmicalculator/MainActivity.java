/**
 This file defines the Main Activity, which is the default activity. The user inputs their height and weight and is able to calculate their BMI as well as get advice.
 @author Caitlyn Romano, Rose Sirohi
 */

package com.example.bmicalc;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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


        // The bmi button is pressed
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
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main2Activity.class);
                view.getContext().startActivity(intent);
            }
        });

        //The metric button is pressed
        metric = (RadioButton) findViewById(R.id.metric);
        metric.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setMetric();
            }
        });

        //The english button is pressed
        english = (RadioButton) findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setEnglish();
            }
        });

    }

    /**
     * Method to calculate and display the BMI
     */
    public void calculateBMI() {
        bmi = 0;
        height = Double.valueOf(heightInput.getText().toString());
        weight = Double.valueOf(weightInput.getText().toString());
        if (isMetric == true) {
            bmi = (weight / height);
        } else {
            bmi = (weight * 703) / (height * height);
        }
        bmiOutput.setText(String.format("Your bmi is %.2f", bmi));

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

    /**
     * Method to check if the user selects a unit and display an error message if not.
     * @return true if the user selects a unit, false otherwise
     */
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

    /**
     * Method to check if the user inputs a weight and height and display an error message if not.
     * @return true if the user enters a non-zero weight and height, false otherwise
     */
    public boolean checkIfInputEntered(){
        if(TextUtils.isEmpty(heightInput.getText())==false && TextUtils.isEmpty(weightInput.getText())==false) {
            height = Double.valueOf(heightInput.getText().toString());
            weight = Double.valueOf(weightInput.getText().toString());
            if (height == 0 || weight == 0 ){
                Context context = getApplicationContext();
                CharSequence text = "Error: Please enter valid numeric input for Weight and Height";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return false;
            }
            return true;
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Error: Please enter valid numeric input for Weight and Height";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return false;
        }
    }


}

