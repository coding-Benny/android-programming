package com.example.receiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;

import com.example.receiver.databinding.ActivityMainBinding;
import com.example.receiver.databinding.ItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int RC_SMS_RECEIVED = 1;
    private int idx = 0;
    private MySMSReceiver mySMSReceiver;
    private ArrayList<SMS> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SMSAdapter adapter = new SMSAdapter(messages);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        mySMSReceiver = new MySMSReceiver();
        mySMSReceiver.setOnSmsReceived(new MySMSReceiver.OnSmsReceived() {
            @Override
            public void onReceived(String msg) {
                messages.add(idx, new SMS(msg));
                adapter.notifyItemInserted(idx++);
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS}, RC_SMS_RECEIVED);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(mySMSReceiver, new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mySMSReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == RC_SMS_RECEIVED) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 있음
            }
            else {
                // 사용자에게 권한 필요성을 설명하는 다이얼로그
                // 다시 권한 요청..
                new AlertDialog.Builder(this).setTitle("Permission!")
                        .setMessage("RECEIVE_SMS permission is required to receive SMS.\nPress OK to grant the permission.")
                        .setPositiveButton("OK", ((dialog, which) -> ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.RECEIVE_SMS}, RC_SMS_RECEIVED)))
                        .setNegativeButton("Cancel", null)
                        .create().show();
            }
        }
    }
}
