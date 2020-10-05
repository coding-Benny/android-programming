package com.example.notificationexample

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannels()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.noti1 -> showDefaultNotification()
            R.id.noti2 -> showNoticeNotification()
        }
        return super.onOptionsItemSelected(item)
    }

    private var myNotificationID = 1
    private val defaultChannelID = "default"
    private val noticeChannelID = "notice"

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {   // Android 8.0
            val defaultChannel = NotificationChannel(defaultChannelID, "default channel", NotificationManager.IMPORTANCE_DEFAULT)
            defaultChannel.description = "description text of default channel."
            val noticeChannel = NotificationChannel(noticeChannelID, "notice channel", NotificationManager.IMPORTANCE_DEFAULT)
            noticeChannel.description = "description text of notice channel."
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannels(listOf(defaultChannel, noticeChannel))
        }
    }

    private fun showDefaultNotification() {
        val intent = Intent(this, Noti2Activity::class.java)
        val pendingIntent = with(TaskStackBuilder.create(this)) {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.mumy)
        val builder = NotificationCompat.Builder(this, defaultChannelID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(bitmap)
            .setContentTitle("notification1")
            .setContentText("this is notification 1")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)    // auto remove this notification when user touches it
        NotificationManagerCompat.from(this).notify(myNotificationID++, builder.build())
    }

    private fun showNoticeNotification() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.mumy)
        val builder = NotificationCompat.Builder(this, noticeChannelID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(bitmap)
            .setContentTitle("notification2")
            .setContentText("this is notification 2")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(this).notify(myNotificationID++, builder.build())
    }
}