package com.polly.kotlinairhorn

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.RawRes
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addSound(R.id.airhorn_button, R.raw.airhorn)
        addSound(R.id.sadface_button, R.raw.sadtrombone)
        addSound(R.id.piano_button, R.raw.droppiano)
        addSound(R.id.johnson_button, R.raw.johnson)
    }

    private fun addSound(@IdRes buttonId: Int, @RawRes soundId: Int) {
        val button : ImageButton = findViewById(buttonId) as ImageButton
        button.setOnClickListener {
            MediaPlayer.create(this, soundId).apply {
                start()
                setOnCompletionListener { release() }
            }
        }
    }
}
