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

public class ModuloFragment extends Fragment {

    private EditText inputNumber1, inputNumber2;
    private Button buttonModulo, buttonClear;
    private TextView resultText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_modulo, container, false);

        // Initialize views
        inputNumber1 = rootView.findViewById(R.id.inputNumber1);
        inputNumber2 = rootView.findViewById(R.id.inputNumber2);
        buttonModulo = rootView.findViewById(R.id.buttonModulo);
        buttonClear = rootView.findViewById(R.id.buttonClear);
        resultText = rootView.findViewById(R.id.resultText);

        // Set up button click listeners
        buttonModulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateModulo();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });

        return rootView;
    }

    private void calculateModulo() {
        String num1Str = inputNumber1.getText().toString().trim();
        String num2Str = inputNumber2.getText().toString().trim();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(getContext(), "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);

            if (num2 == 0) {
                Toast.makeText(getContext(), "Cannot calculate modulus with zero", Toast.LENGTH_SHORT).show();
                return;
            }

            double result = num1 % num2;
            resultText.setText("Result: " + result);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        inputNumber1.setText("");
        inputNumber2.setText("");
        resultText.setText("Result: ");
    }
}

