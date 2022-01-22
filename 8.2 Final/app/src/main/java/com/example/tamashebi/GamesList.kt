package com.example.tamashebi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
public var sizeof = 0
class GamesList : AppCompatActivity() {
    var layoutManager: LayoutManager? = null

    var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)
        supportActionBar!!.title="Games"
        layoutManager = LinearLayoutManager(this)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        getSize()

    }
    fun getSize(){
        var counter = 0
        var db = FirebaseFirestore.getInstance()
        db.collection("games").get().addOnCompleteListener {
            if(it.isSuccessful){
            for(document in it.result!!){
                counter+=1

            }
                //Toast.makeText(this,""+counter,Toast.LENGTH_SHORT).show()
            }
        }


    }
}