/**
 This file defines the Second Activity, which is only accessible through the Main Activity. This screen displays advice based on the users BMI.
 @author Caitlyn Romano, Rose Sirohi
 */

package com.example.bmicalc;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    public static final double UNDERWEIGHT = 18.4;
    public static final double OVERWEIGHT = 25;
    public static final double OBESE = 30;
    public TextView textBox;
    public  TextView adviceBox;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageView);
        textBox = (TextView) findViewById(R.id.textBox);
        adviceBox = (TextView) findViewById(R.id.adviceBox);

        if (MainActivity.bmi <= UNDERWEIGHT) {
            textBox.setText(String.format("UNDERWEIGHT"));
            adviceBox.setText(String.format("Increase your calorie intake per day. A good way to do this is to eat 2-4 high protein snacks in between meals."));
            imageView.setImageResource(R.drawable.underweighttwo);
        }
        else if (MainActivity.bmi > UNDERWEIGHT && MainActivity.bmi < OVERWEIGHT) {
            textBox.setText(String.format("NORMAL WEIGHT"));
            adviceBox.setText(String.format("You have a healthy weight! Keep a balanced diet rich in fruits and vegetables and get adequate exercise daily."));
            imageView.setImageResource(R.drawable.normalweight);
        }
        else if (MainActivity.bmi >= OVERWEIGHT && MainActivity.bmi < OBESE) {
            textBox.setText(String.format("OVERWEIGHT"));
            adviceBox.setText(String.format("Decrease your calorie intake per day. Focus on a diet rich in fruits and vegetables and exercise for 30 minutes daily."));
            imageView.setImageResource(R.drawable.overweight);
        }
        if (MainActivity.bmi >= OBESE) {
            textBox.setText(String.format("OBESE"));
            adviceBox.setText(String.format("Decrease your calorie intake per day. Focus on a diet rich in fruits and vegetables and exercise for 30 minutes daily. Limit your sugar intake as sugar slows your metabolism."));
        }
    }
}

