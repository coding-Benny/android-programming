package com.example.shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopping.databinding.ActivityFirestoreBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirestoreBinding
    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("items")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itemID = intent.getCharSequenceExtra("id").toString()
        binding.name.setText(intent.getCharSequenceExtra("name"))
        binding.price.setText(intent.getCharSequenceExtra("price"))
        val isCartIn = intent.getBooleanExtra("cart", true)
        if (isCartIn) {
            binding.button.setText("REMOVE FROM CART")
        }
        else
            binding.button.setText("ADD TO CART")
        binding.button.setOnClickListener {
            if (isCartIn) {
                itemsCollectionRef.document(itemID).update("cart", false).addOnSuccessListener {
                    binding.button.setText("ADD TO CART")
                }.addOnFailureListener {  }
            }
            else {
                itemsCollectionRef.document(itemID).update("cart", true).addOnSuccessListener {
                    binding.button.setText("REMOVE FROM CART")
                }.addOnFailureListener {  }
            }
        }
    }
}