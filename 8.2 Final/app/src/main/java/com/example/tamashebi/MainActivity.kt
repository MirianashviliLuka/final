package com.example.tamashebi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    lateinit var getStuff: Button
    lateinit var printTo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getStuff = findViewById(R.id.getStuff)
        printTo = findViewById(R.id.desc)
        printTo.text = intent?.extras?.getString("title","game_title")

    }
}