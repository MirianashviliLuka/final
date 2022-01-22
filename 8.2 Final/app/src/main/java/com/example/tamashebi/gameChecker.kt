package com.example.tamashebi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class gameChecker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_checker)
        supportActionBar!!.title = intent?.extras?.getString("title","game_title").toString()
        val passedCpu = intent?.extras?.getString("sendCpu","game_title").toString()
        val passedGpu = intent?.extras?.getString("sendGpu","game_title").toString()
        val passedRam = intent?.extras?.getString("sendRam","game_title").toString()
        val gameCpuScore = intent?.extras?.getString("gameCpuScore","game_title").toString()
        val gameGpuScore = intent?.extras?.getString("gameGpuScore","game_title").toString()
        val gameRam = intent?.extras?.getString("gameRam","game_title").toString()
        Toast.makeText(this,gameCpuScore+"  "+gameGpuScore+"  "+gameRam,Toast.LENGTH_SHORT).show()
        if(passedCpu.toInt()>=gameCpuScore.toInt() && passedGpu.toInt()>=gameGpuScore.toInt() && passedRam>= gameRam){
            findViewById<TextView>(R.id.finalAnswer).setText("You Can Run This Game")
        }
        else{
            findViewById<TextView>(R.id.finalAnswer).setText("You Can't Run This Game")
        }
    }
}