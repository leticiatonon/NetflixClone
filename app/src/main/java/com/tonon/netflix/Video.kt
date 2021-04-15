package com.tonon.netflix

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.tonon.netflix.databinding.ActivityVideoBinding

class Video : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val videourl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-clone-12c83.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=8e1a6add-a400-4486-a4a2-5e29784f6ad9")

        val video = binding.video
        video.setMediaController(MediaController(this))
        video.setVideoURI(videourl)
        video.requestFocus()
        video.start()
    }
}