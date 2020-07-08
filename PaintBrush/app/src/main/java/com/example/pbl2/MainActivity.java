package com.example.pbl2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    private MyView mMyView;
    private final String [] colors = {"빨간색", "노란색", "녹색", "파란색", "마젠타색"};
    private final String [] shapes = {"사각형", "원", "세모"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyView = findViewById(R.id.my_view);
        mRadioGroup = findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.button_red:
                        mMyView.setPaintColor(Color.RED);
                        break;
                    case R.id.button_green:
                        mMyView.setPaintColor(Color.GREEN);
                        break;
                    case R.id.button_blue:
                        mMyView.setPaintColor(Color.BLUE);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder;

        switch(item.getItemId()) {
            case R.id.color:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("브러쉬 색상을 선택하세요");
                builder.setSingleChoiceItems(colors, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(colors[which]) {
                            case "빨간색":
                                mMyView.setPaintColor(Color.RED);
                                break;
                            case "노란색":
                                mMyView.setPaintColor(Color.YELLOW);
                                break;
                            case "녹색":
                                mMyView.setPaintColor(Color.GREEN);
                                break;
                            case "파란색":
                                mMyView.setPaintColor(Color.BLUE);
                                break;
                            case "마젠타색":
                                mMyView.setPaintColor(Color.MAGENTA);
                                break;
                            default:
                                mMyView.setPaintColor(Color.BLACK);
                                break;
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                break;
            case R.id.shape:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("브러쉬 모양을 선택하세요");
                builder.setSingleChoiceItems(shapes, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMyView.setPaintShape(shapes[which]);
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
