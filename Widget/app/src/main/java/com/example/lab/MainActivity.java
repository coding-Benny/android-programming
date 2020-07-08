package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.lab.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = binding.editText1.getText().toString();
                String num2 = binding.editText2.getText().toString();
                int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
                int sub = Integer.parseInt(num1) - Integer.parseInt(num2);
                int mul = Integer.parseInt(num1) * Integer.parseInt(num2);
                double div = Double.parseDouble(num1) / Double.parseDouble(num2);

                binding.textView.setText(String.format("%d, %d %d, %.1f", sum, sub, mul, div));
            }
        });
    }
}
