package com.example.resource;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.resource.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String[] animals;
    private ImageView imageAnimals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        animals = res.getStringArray(R.array.animals);
        imageAnimals = findViewById(R.id.imageAnimals);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.chooseAnimals) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Animals");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setItems(animals, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Animation anim = null;
                    switch (animals[which]) {
                        case "사자":
                            imageAnimals.setImageResource(R.drawable.lion);
                            break;
                        case "호랑이":
                            imageAnimals.setImageResource(R.drawable.tiger);
                            break;
                        case "표범":
                            imageAnimals.setImageResource(R.drawable.leopard);
                            break;
                    }
                    anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                    imageAnimals.startAnimation(anim);
                }
            });
            builder.create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
