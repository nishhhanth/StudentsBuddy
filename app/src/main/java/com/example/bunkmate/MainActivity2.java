package com.example.bunkmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButtonToggleGroup;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private EditText currentAggregateInput, semestersCompletedInput,requiredAggregate;
    private TextView resultMessage;
    private double targetPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        Intent in=getIntent();

        currentAggregateInput = findViewById(R.id.current_aggregate_input);
        semestersCompletedInput = findViewById(R.id.semesters_completed_input);
        resultMessage = findViewById(R.id.output_box);
        requiredAggregate=findViewById(R.id.required_aggregate_input);
    }

    public void back(View view) {
        Intent in=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
    }



    private void calculateRequiredScore() {
        try {
            double currentAggregate = Double.parseDouble(currentAggregateInput.getText().toString());
            int semestersCompleted = Integer.parseInt(semestersCompletedInput.getText().toString());
            targetPercentage=Double.parseDouble(requiredAggregate.getText().toString());


            if (semestersCompleted <= 0 || semestersCompleted >= 8) {
                Toast.makeText(this, "Please enter a valid number of semesters (1-7).", Toast.LENGTH_SHORT).show();
                return;
            }

            int totalSemesters = 8;
            int remainingSemesters = totalSemesters - semestersCompleted;
            double requiredScore = (targetPercentage * totalSemesters - currentAggregate * semestersCompleted) / remainingSemesters;

            if(requiredScore>100){
                resultMessage.setText(String.format("Hi \nYou have to score more than 100 in the next %d semesters to reach %.0f%%\nWhich is impossible", remainingSemesters, targetPercentage));

            }
            else if(requiredScore<=100){
                resultMessage.setText(String.format("Hi\nYou have to score %.2f in the next %d semesters to reach %.0f%%\nAll the best", requiredScore, remainingSemesters, targetPercentage));
            }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    public void Calculate(View view) {

        calculateRequiredScore();
    }
}