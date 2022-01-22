package com.example.tamashebi

import com.google.firebase.firestore.FirebaseFirestore

class info(){

    fun getGameList(){
        var titles = arrayListOf<String>()
        var descriptions = arrayListOf<String>()
        var db = FirebaseFirestore.getInstance()
        db.collection("games").get().addOnCompleteListener {
            if(it.isSuccessful){
                for(document in it.result!!){
                    titles.add(document.data.getValue("name").toString())
                    descriptions.add(document.data.getValue("description").toString())
                }

            }
        }
    }

}
