package com.polly.kotlinairhorn

import android.content.Context
import android.media.AudioManager
import android.media.AudioManager.*
import android.media.MediaPlayer
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.RawRes
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        setContentView(R.layout.activity_main)
        addSound(R.id.airhorn_button, R.raw.airhorn, AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
        addSound(R.id.sadface_button, R.raw.sadtrombone, AUDIOFOCUS_GAIN_TRANSIENT)
        addSound(R.id.piano_button, R.raw.droppiano, AUDIOFOCUS_GAIN)
        addSound(R.id.johnson_button, R.raw.johnson, AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
    }

    private fun addSound(@IdRes buttonId: Int, @RawRes soundId: Int, audioFocusMode: Int) {
        val button = findViewById<ImageButton>(buttonId)
        button.setOnClickListener {
            Log.d("polly", "request audio focus")
            audioManager.requestAudioFocus(null, STREAM_MUSIC, audioFocusMode)
            playSound(soundId)
        }
    }

    private fun playSound(soundId: Int) {
        MediaPlayer.create(this, soundId).apply {
            start()
            setOnCompletionListener {
                Log.d("polly", "abandon audio focus")
                audioManager.abandonAudioFocus(null)
                release()
            }
        }
    }
}
