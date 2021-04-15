package com.tonon.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.tonon.netflix.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        verificarUsuarioLogado()

        binding.textTelaCadastro.setOnClickListener {

            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btEntrar.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.mensagemErro

            if (email.isEmpty() || senha.isEmpty()) {
                mensagemErro.setText("Preencha todos os campos")
            } else {
                autenticarUsuario()
            }
        }
    }

    private fun autenticarUsuario(){

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagemErro = binding.mensagemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Login efetuado com sucesso",Toast.LENGTH_SHORT).show()
                teladeFilmes()
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> mensagemErro.setText("Email ou senha incorretos")
                erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a internet")
                else -> mensagemErro.setText("erro ao logar usuário")
            }

        }

    }

    private fun verificarUsuarioLogado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if(usuarioLogado != null){
            teladeFilmes()
        }
    }

    private fun teladeFilmes(){
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }

}
