package com.example.progressbarthread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.progressbarthread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    HandlerThread handlerThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressBar.setMax(100);
        binding.progressBar.setProgress(0);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setProgress(0); // start 클릭하면 처음부터 시작
                if(handlerThread != null && handlerThread.isAlive()) {
                    return;
                }
                handlerThread = new HandlerThread("My Handler Thread");
                handlerThread.start();
                handler = new Handler(handlerThread.getLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if (msg.what == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.progressBar.incrementProgressBy(5);
                                }
                            });
                            if(binding.progressBar.getProgress()==100)
                                getLooper().quit();
                            Message m = Message.obtain();
                            m.what = 0;
                            sendMessageDelayed(m, 100);
                        }
                        else if(msg.what == -1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.progressBar.setProgress(0);
                                }
                            });
                            getLooper().quit();
                        }
                    }
                };
                Message msg = Message.obtain();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });

        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handlerThread != null && handlerThread.isAlive()) {
                    Message msg = Message.obtain();
                    msg.what = -1;
                    handler.sendMessage(msg);
                }
            }
        });
    }
}
