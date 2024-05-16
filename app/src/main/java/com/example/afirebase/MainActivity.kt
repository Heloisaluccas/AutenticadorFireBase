package com.example.afirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    val COD_TELA = 2
    private val autentication by lazy {
        FirebaseAuth.getInstance()
    }
    data class User(val email:String, val senha:String)
    override fun onStart() {
        super.onStart()
        val user = autentication.currentUser
        if (user != null) {
            abrirInicio()
            verificarLogin()
        }
    }
    fun verificarLogin() {
        val user = autentication.currentUser
        if (user!=null){
            abrirInicio()
        }
    }
     fun abrirInicio() {
         var email = findViewById<EditText>(R.id.edtmail)
         var senha = findViewById<EditText>(R.id.edtpwd)
         var txtEmail = email?.text.toString()
         var txtSenha = senha?.text.toString()
         val usuario = User(txtEmail,txtSenha)
         val intent = Intent(this, Login::class.java).apply {
             putExtra("senha", usuario.senha)
             putExtra("email", usuario.email)
         }
         startActivityForResult(intent,COD_TELA)
         senha.setText("")
         email.setText("")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var email = findViewById<EditText>(R.id.edtmail)
        var senha = findViewById<EditText>(R.id.edtpwd)
        var btnlogin = findViewById<Button>(R.id.btnlogin)
        var btncadastro = findViewById<Button>(R.id.btncadastro)
        email = findViewById(R.id.edtmail)
        senha = findViewById(R.id.edtpwd)
    }
    fun cadastro() {
        var email = findViewById<EditText>(R.id.edtmail)
        var senha = findViewById<EditText>(R.id.edtpwd)
        var txtemail = email?.text.toString()
        var txtsenha = senha?.text.toString()
        autentication.createUserWithEmailAndPassword(
            txtemail, txtsenha
        ).addOnSuccessListener {
            Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "erro ao efetuar o cadastro", Toast.LENGTH_LONG).show()
        }
    }
    fun login() {
         val edtemail = findViewById<EditText>(R.id.edtmail)
         val edtsenha = findViewById<EditText>(R.id.edtpwd)
         var txtemail = edtemail.text.toString()
         var txtsenha = edtsenha.text.toString()
         autentication.signInWithEmailAndPassword(
             txtemail, txtsenha
         ).addOnSuccessListener {
             Toast.makeText(this, "logado com sucesso!", Toast.LENGTH_LONG).show()
             val intent = android.content.Intent(this, Login::class.java).apply {
                 putExtra("senha", txtsenha)
                 putExtra("email", txtemail)
             }
             startActivityForResult(intent, COD_TELA)
         }.addOnFailureListener {
             Toast.makeText(this, "erro ao efetuar o login", Toast.LENGTH_LONG).show()
             }
         }
   }

