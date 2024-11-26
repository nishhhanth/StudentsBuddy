package com.example.bunkmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Target extends Fragment {
    private EditText currentAggregateInput, semestersCompletedInput,requiredAggregate;
    private TextView resultMessage;
    private double targetPercentage;
    private Button calculate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.target, container, false);
        currentAggregateInput = rootView.findViewById(R.id.current_aggregate_input);
        semestersCompletedInput = rootView.findViewById(R.id.semesters_completed_input);
        resultMessage = rootView.findViewById(R.id.output_box);
        requiredAggregate=rootView.findViewById(R.id.required_aggregate_input);
        calculate=rootView.findViewById(R.id.buttonCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateRequiredScore();
            }
        });
        return rootView;
    }
    private void calculateRequiredScore() {
        try {
            double currentAggregate = Double.parseDouble(currentAggregateInput.getText().toString());
            int semestersCompleted = Integer.parseInt(semestersCompletedInput.getText().toString());
            targetPercentage=Double.parseDouble(requiredAggregate.getText().toString());


            if (semestersCompleted <= 0 || semestersCompleted >= 8) {
                Toast.makeText(getContext(), "Please enter a valid number of semesters (1-7).", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getContext(), "Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

}
