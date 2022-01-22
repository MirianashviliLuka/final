package com.example.tamashebi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
var names = arrayListOf<String>()
var setGpu: String = ""
var setCpu: String = ""
public var counter = 0
class GameDetails : AppCompatActivity() {
    lateinit var gameImage: ImageView
    lateinit var gameDate: TextView
    lateinit var gameDescription: TextView
    lateinit var gameCpu: TextView
    lateinit var gameGpu: TextView
    lateinit var gameRam: TextView
    lateinit var spinnerCpu: Spinner
    lateinit var spinnerGpu: Spinner
    lateinit var inputedRam: EditText
    lateinit var goToChecker: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)
        //initialize emelents here
        gameImage = findViewById(R.id.gameImage)
        gameDate = findViewById(R.id.gamesDate)
        gameDescription = findViewById(R.id.gamesDescription)
        gameCpu = findViewById(R.id.cpu)
        gameGpu = findViewById(R.id.gpu)
        gameRam = findViewById(R.id.ram)
        spinnerCpu = findViewById(R.id.spinnerCpu)
        spinnerGpu = findViewById(R.id.spinnerGpu)
        inputedRam = findViewById(R.id.ramInputed)
        goToChecker = findViewById(R.id.check)
        val getId = intent?.extras?.getString("title","game_title").toString()
        findViewById<TextView>(R.id.addTitle).setText("Can I Run "+getId)
        supportActionBar!!.title =getId
        getGameDetails(getId)
        for(i in allCpu){
            if(i == cpu[counter]){
                gameCpu.setText(i)
            }
        }
        for(j in allGpu){
            if(j == gpu[counter]){
                gameGpu.setText(j)
            }
        }
        gameRam.setText(ram[counter].toString()+"GB")
        gameDate.setText(realiseDate[counter].toString())
        gameDescription.setText(descriptions[counter].toString())
        Picasso.get().load(img[counter]).into(gameImage)



//        cpu Spinner
        val CpuAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, allCpu)
        spinnerCpu.adapter = CpuAdapter
        spinnerCpu.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                setCpu = allCpuScore[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
//        cpu spinner ends gere

//        gpu SPinner
        val GpuAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, allGpu)
        spinnerGpu.adapter = GpuAdapter
        spinnerGpu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                setGpu = allGpuScore[p2]
             //   Toast.makeText(this@GameDetails, setGpu,Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        goToChecker.setOnClickListener {
            val intent = Intent(this,gameChecker::class.java)
            intent.putExtra("sendCpu", setCpu)
            intent.putExtra("sendGpu", setGpu)
            intent.putExtra("sendRam",inputedRam.toString())
            intent.putExtra("gameCpuScore", allCpuScore[counter])
            intent.putExtra("gameGpuScore", allGpuScore[counter])
            intent.putExtra("gameRam",ram[counter])
            intent.putExtra("title",getId)
            startActivity(intent)
        }
    }
    fun getGameDetails(getId: String){
        val db = FirebaseFirestore.getInstance()

        db.collection("games").get().addOnCompleteListener {

            if(it.isSuccessful){

                for(document in it.result!!){
                    //get all the info down
                    names.add(document.data.getValue("name").toString())

                    //check until it doesnt get to needed position
                }

            }

        }


    }
}

