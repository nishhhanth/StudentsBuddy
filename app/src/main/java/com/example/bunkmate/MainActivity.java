package com.example.bunkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText classesConductedInput, classesPresentInput;
    private RadioGroup percentageGroup;
    private TextView outputBox;
    private Button checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        classesConductedInput = findViewById(R.id.classes_conducted_input);
        classesPresentInput = findViewById(R.id.classes_present_input);
        percentageGroup = findViewById(R.id.percentage_group);
        outputBox = findViewById(R.id.output_box);
        checkButton = findViewById(R.id.buttonCheck);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAttendance();

            }
        });
    }

    private void calculateAttendance() {

        try {

            int classesConducted = Integer.parseInt(classesConductedInput.getText().toString());
            int classesPresent = Integer.parseInt(classesPresentInput.getText().toString());


            int requiredPercentage = getSelectedPercentage();


            double currentPercentage = ((double) classesPresent / classesConducted) * 100;
            int totalClasses = classesConducted;
            int attendedClasses = classesPresent;
            int bunk = 0;
            int extraAttend = 0;

            if(classesPresent>classesConducted){
                outputBox.setText(String.format("Classes conducted cannot be lesser than classes present\nPlease re-enter the values"));

            }

            else if (currentPercentage >= requiredPercentage) {

                while (currentPercentage >= requiredPercentage) {
                    bunk++;
                    totalClasses++;
                    currentPercentage = ((double) attendedClasses / totalClasses) * 100;
                }
                bunk--;
                totalClasses--;
                currentPercentage = ((double) attendedClasses / totalClasses) * 100;


                outputBox.setText(String.format("Hi\nYou can bunk %d classes.", bunk));
                outputBox.append(String.format("\nAfter that, your attendance will be %.2f%%.", currentPercentage));
            } else {

                while (currentPercentage < requiredPercentage) {
                    attendedClasses++;
                    extraAttend++;
                    totalClasses++;
                    currentPercentage = ((double) attendedClasses / totalClasses) * 100;
                }


                outputBox.setText(String.format("Hi\nOops! You will have to attend %d more classes.", extraAttend));
                outputBox.append(String.format("\nAfter that, your attendance will be %.2f%%.", currentPercentage));
            }

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }

}
    private int getSelectedPercentage() {
        int selectedId = percentageGroup.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(selectedId);

        switch (selectedButton.getText().toString()) {
            case "70%":
                return 70;
            case "75%":
                return 75;
            case "80%":
                return 80;
            case "85%":
                return 85;
            case "90%":
                return 90;
            default:
                return 75;
        }
    }

    public void next(View view) {
        Intent in=new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(in);
    }
}
