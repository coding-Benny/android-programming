package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class SubActivity extends AppCompatActivity {

    private int num1;
    private int num2;
    private String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView textNum1 = (TextView) findViewById(R.id.textNum1);
        TextView textNum2 = (TextView) findViewById(R.id.textNum2);

        Intent intent = getIntent();

        num1 = intent.getIntExtra("num1", -1);
        num2 = intent.getIntExtra("num2", -1);
        op = intent.getStringExtra("op");
        if(num1 != -1 && num2 != -1) {
            textNum1.setText(String.format(Locale.KOREAN, "숫자1: %d", num1));
            textNum2.setText(String.format(Locale.KOREAN, "숫자2: %d", num2));
        }
    }

    public void mOnClick(View v) {
        Intent data = new Intent();
        switch (op) {
            case "add":
                data.putExtra("result", num1 + num2);
                break;
            case "sub":
                data.putExtra("result", num1 - num2);
                break;
            case "mul":
                data.putExtra("result", num1 * num2);
                break;
            case "div":
                data.putExtra("result", num1 / num2);
        }
        setResult(RESULT_OK, data);
        finish();
    }
}
