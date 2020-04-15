package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditNum1;
    private EditText mEditNum2;
    private RadioGroup mRadioGroup;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditNum1 = (EditText) findViewById(R.id.editNum1);
        mEditNum2 = (EditText) findViewById(R.id.editNum2);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.add) {
                    operator = "add";
                }
                else if(checkedId == R.id.sub) {
                    operator = "sub";
                }
                else if(checkedId == R.id.mul) {
                    operator = "mul";
                }
                else if(checkedId == R.id.div) {
                    operator = "div";
                }
            }
        });

    }

    public void mOnClick(View v) {
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra("num1", Integer.parseInt(mEditNum1.getText().toString()));
        intent.putExtra("num2", Integer.parseInt(mEditNum2.getText().toString()));
        intent.putExtra("op", operator);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK) {
            int result = data.getIntExtra("result", 0);
            Toast.makeText(this, "두 숫자의 연산 결과: " + result, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
