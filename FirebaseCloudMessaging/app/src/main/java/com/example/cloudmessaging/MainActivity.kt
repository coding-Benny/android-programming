package com.example.cloudmessaging

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cloudmessaging.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            binding.fcmToken.text = if (it.isSuccessful) it.result else "Token Error!"

            // copy FCM token to clipboard
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("FCM Token", binding.fcmToken.text)
            clipboard.setPrimaryClip(clip)

            // write to logcat
            Log.d(MyFirebaseMessagingService.TAG, "FCM token: ${binding.fcmToken.text}")
        }

        val msgBody = intent.getCharSequenceExtra("message")
        if (msgBody != null)
            binding.msgBody.text = msgBody
    }
}