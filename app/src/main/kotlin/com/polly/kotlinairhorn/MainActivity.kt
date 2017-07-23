package com.polly.kotlinairhorn

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val airhorn : ImageButton = findViewById(R.id.airhorn_button) as ImageButton
        airhorn.setOnClickListener {
            MediaPlayer.create(this, R.raw.airhorn).apply {
                start()
                setOnCompletionListener { release() }
            }
        }
    }
}
