
package com.prasharrishi.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // class variables are also called fields
    private TextView resultText;
    private Button resultcalculate;
    private RadioButton resltmale;
    private RadioButton resltfemale;
    private EditText resultage;
    private EditText resultweight;
    private EditText resultfeet;
    private EditText resultinches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        extracted();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        resltmale = findViewById(R.id.radio_button_male);
        resltfemale = findViewById(R.id.radio_button_female);
        resultage = findViewById(R.id.edit_text_age);
        resultweight = findViewById(R.id.edit_text_weight);
        resultfeet = findViewById(R.id.edit_text_feet);
        resultinches = findViewById(R.id.edit_text_inches);
        resultcalculate = findViewById(R.id.button_calculate);

    }

    private void extracted() {
        resultcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();

                String ageText = resultage.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age <= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
                displayResult(bmiResult);
            }
        });
    }

    private double calculateBmi() {
        String feetText = resultfeet.getText().toString();
        String inchesText = resultinches.getText().toString();
        String weightText = resultweight.getText().toString();

        //Converting the number strings into Integer
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (bmi < 18.5) {
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight";

        } else {
            //Display Fit
            fullResultString = bmiTextResult + " - You are Healthy weight";

        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;

        if (resltmale.isChecked()) {
            //Display Boy Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for the healthy range for Boys ";
        } else if (resltfemale.isChecked()) {
            //Display Girl Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for the healthy range for Girls ";

        } else {
            //Display General Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for the healthy range";

        }
        resultText.setText(fullResultString);

    }


}

