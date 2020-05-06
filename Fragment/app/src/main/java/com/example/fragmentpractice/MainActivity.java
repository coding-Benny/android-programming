package com.example.fragmentpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    void switchFragment(int modeID) {
        if(modeID == R.id.listView) {
            getSupportFragmentManager().beginTransaction().replace(R.id.titles, new ListTitlesFragment()).addToBackStack(null).commit();

        }
        else if(modeID == R.id.buttons) {
            getSupportFragmentManager().beginTransaction().replace(R.id.titles, new ButtonTitlesFragment()).addToBackStack(null).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switchFragment(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
}
