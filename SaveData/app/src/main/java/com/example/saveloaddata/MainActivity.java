package com.example.saveloaddata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.example.saveloaddata.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(v -> save());
        binding.btnLoad.setOnClickListener(v -> load());
    }

    void save() {
        if(isExternalStorageMounted()) {
            File file = new File(getExternalFilesDir(null), "test.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(binding.editText.getText().toString().getBytes());
                fos.close();

                Toast.makeText(this, "File saved!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(this, "Cannot write to external storage..", Toast.LENGTH_SHORT).show();
        }
    }

    void load() {
        if(isExternalStorageMounted()) {
            File file = new File(getExternalFilesDir(null), "test.txt");
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                binding.editText.setText(new String(data));
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isExternalStorageMounted() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}