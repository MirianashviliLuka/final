package com.example.tamashebi

import android.accounts.AccountManager.get
import android.app.Activity
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.with

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.lang.System.load

import java.lang.reflect.Array.get
import kotlin.math.log
import kotlin.reflect.typeOf


var titles = arrayListOf<String>()
var descriptions = arrayListOf<String>()
var img = arrayListOf<String>()
var realiseDate = arrayListOf<String>()
var cpu = arrayListOf<String>()
var gpu = arrayListOf<String>()
var ram = arrayListOf<String>()
var allCpu = arrayListOf<String>()
var allGpu = arrayListOf<String>()
var allGpuScore = arrayListOf<String>()
var allCpuScore = arrayListOf<String>()

public var size = 0
class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var id : Int = 0









    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)


            itemView.setOnClickListener {
                val activity = itemView.context as Activity
                val position:Int = adapterPosition
                //Toast.makeText(itemView.context,"you clicked on"+ titles[position],Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, GameDetails::class.java)
                intent.putExtra("title", titles[position].toString())
                intent.putExtra("position",position.toInt())
                counter = position
                activity.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        var db = FirebaseFirestore.getInstance().collection("games").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        titles.add(document.data.getValue("name").toString())
                        descriptions.add(document.data.getValue("description").toString())
                        realiseDate.add(document.data.getValue("realeaseDate").toString())
                        cpu.add(document.data.getValue("cpu").toString())
                        gpu.add(document.data.getValue("gpu").toString())
                        ram.add(document.data.getValue("ram").toString())
                        img.add(document.data.getValue("img").toString())

                    }

                    holder.itemTitle.text = titles[position]
                    Picasso.get().load(img[position]).into(holder.itemImage)

                    holder.itemDetail.text = descriptions[position]
                }
            }
        var dbCpu = FirebaseFirestore.getInstance()
        dbCpu.collection("cpu").get().addOnCompleteListener {
            if(it.isSuccessful){
                for (document in it.result!!){
                    allCpu.add(document.data.getValue("name").toString())
                    allCpuScore.add(document.data.getValue("cpuScore").toString())
                }
            }

        }
        var dbGpu = FirebaseFirestore.getInstance()
        dbGpu.collection("gpu").get().addOnCompleteListener {
            if (it.isSuccessful){
                for(document in it.result!!){
                    allGpu.add(document.data.getValue("name").toString())
                    allGpuScore.add(document.data.getValue("gpuScore").toString())
                }
            }
        }
    }

    override fun getItemCount(): Int {

//  don't forget to change return function bellow
        return 3

    }
}