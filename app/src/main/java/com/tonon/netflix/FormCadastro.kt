package com.tonon.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.tonon.netflix.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        binding.btCadastrar.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.respostaErro

            if (email.isEmpty() || senha.isEmpty()) {
                mensagemErro.setText("Preencha todos os campos")
            } else {
                cadastrarUsuario()
            }
        }
    }
        private fun cadastrarUsuario(){
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.respostaErro

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    binding.editEmail.setText("")
                    binding.editSenha.setText("")
                    mensagemErro.setText("")
            }
        }.addOnFailureListener {

                var erro = it

                when{
                    erro is FirebaseAuthWeakPasswordException -> mensagemErro.setText("Digite uma senha com no mínimo 6 caracteres")
                    erro is FirebaseAuthUserCollisionException -> mensagemErro.setText("Essa conta já foi cadastrada")
                    erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a internet")
                    else -> mensagemErro.setText("Erro ao cadastrar usuário")
                }

            }

    }

    private fun Toolbar(){
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_logo))
    }
}