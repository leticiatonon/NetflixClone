package com.tonon.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import com.tonon.netflix.Adapter.FilmesAdapter
import com.tonon.netflix.Model.addFilmes
import com.tonon.netflix.databinding.ActivityDetalhesFilmeBinding

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        val recycler_outrosfilmes = binding.recyclerOutrosfilmes
        recycler_outrosfilmes.adapter = FilmesAdapter(addFilmes())
        recycler_outrosfilmes.layoutManager = GridLayoutManager(applicationContext,3)

        val capaVideo = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-12c83.appspot.com/o/video.jpg?alt=media&token=5613d354-9103-408e-8cbb-9420fd1a3bdd"
        Picasso.get().load(capaVideo).fit().into(binding.capa)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
        }
    }

    private fun Toolbar(){
        val toobar_detalhes = binding.toolbarDetalhes
        toobar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toobar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}