package com.tonon.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.tonon.netflix.Adapter.FilmesAdapter
import com.tonon.netflix.Model.addFilmes
import com.tonon.netflix.OnClick.OnItemClickListener
import com.tonon.netflix.OnClick.addOnItemClickListener
import com.tonon.netflix.databinding.ActivityListaFilmesBinding
import com.tonon.netflix.databinding.ActivityMainBinding

class ListaFilmes : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclefilmes = binding.recyclerView
        recyclefilmes.adapter = FilmesAdapter(addFilmes())
        recyclefilmes.layoutManager = GridLayoutManager(applicationContext,3)

        recyclefilmes.addOnItemClickListener(object : OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {

                when{
                    position == 0 -> DescricaoFilme()
                }
            }
        })

    }

    private fun DescricaoFilme(){
        val intent = Intent(this, DetalhesFilme::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.deslogar ->{
                FirebaseAuth.getInstance().signOut()
                VoltarTelaLogin()
            }
        }


        return super.onOptionsItemSelected(item)
    }

    private  fun VoltarTelaLogin(){
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}