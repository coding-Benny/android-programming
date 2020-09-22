package com.example.mykotlinapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Title")
            builder.setMessage("Message")
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
                Toast.makeText(this, "Close Dialog", Toast.LENGTH_SHORT).show()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}
