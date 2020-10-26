package com.example.firebasestorage

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.firebasestorage.databinding.ActivityRemoteConfigBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class RemoteConfigActivity : AppCompatActivity() {
    lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRemoteConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Firebase.auth.currentUser ?: finish()   // if not authenticated, finish this activity

        storage = Firebase.storage
        val storageRef = storage.reference  // reference to root

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1 // For test purpose only, 3600 seconds for production
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        fetchImage(remoteConfig, storageRef, binding)

        binding.buttonRefresh.setOnClickListener {
            fetchImage(remoteConfig, storageRef, binding)
        }
    }

    private fun fetchImage(remoteConfig: FirebaseRemoteConfig, storageRef: StorageReference, binding: ActivityRemoteConfigBinding) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) {
                val season = remoteConfig.getString("season")
                val imageRef = updateImage(season, storageRef)
                displayImageRef(imageRef, binding.imageView)
            }
    }

    private fun updateImage(season: String, storageRef: StorageReference): StorageReference {
        var imageRef = storageRef
        val springImage = storageRef.child("images/cherry-blossom.jpg")
        val summerImage = storageRef.child("images/summer-beach.jpg")
        val fallImage = storageRef.child("images/autumn-leaves.jpg")
        val winterImage = storageRef.child("images/snow.jpg")

        when (season) {
            "spring" -> {
                imageRef = springImage
            }
            "summer" -> {
                imageRef = summerImage
            }
            "fall" -> {
                imageRef = fallImage
            }
            "winter" -> {
                imageRef = winterImage
            }
        }
        return imageRef
    }

    private fun displayImageRef(imageRef: StorageReference?, view: ImageView) {
        imageRef?.getBytes(Long.MAX_VALUE)?.addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            view.setImageBitmap(bmp)
        }?.addOnFailureListener {
            // Failed to download the image
        }
    }
}