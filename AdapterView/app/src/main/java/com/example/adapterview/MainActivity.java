package com.example.adapterview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.adapterview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Application> mApps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for(ApplicationInfo appInfo : packages) {
            CharSequence label = appInfo.loadLabel(pm);
            Drawable icon = appInfo.loadIcon(pm);
            String packageName = appInfo.packageName;
            mApps.add(new Application(icon, label, packageName));
        }

        ApplicationAdapter adapter = new ApplicationAdapter(mApps);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setHasFixedSize(true);
    }
}
