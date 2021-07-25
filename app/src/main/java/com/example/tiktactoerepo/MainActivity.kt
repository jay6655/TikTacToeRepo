package com.example.tiktactoerepo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val singlePlayer =  findViewById<Button>(R.id.singlePlayer)
        singlePlayer.setOnClickListener {
            Log.e("AAA", "singlePlayer Clicked ")

            val intent = Intent(this@MainActivity,ComputerPlayingActivity::class.java)
            startActivity(intent)
        }

        val multiplePlayer =  findViewById<Button>(R.id.multiplePlayer)
        multiplePlayer.setOnClickListener {
            Log.e("AAA", "multiplePlayer Clicked ")
            val intent = Intent(this@MainActivity,PlayingActivity::class.java)
            startActivity(intent)
        }
    }
}