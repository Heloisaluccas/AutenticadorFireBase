package com.example.afirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private val autentication by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnlogout= findViewById<Button>(R.id.btnlogout)

        val logmail = intent.getStringExtra("email")
        var TextView= findViewById<TextView>(R.id.txtLogin).apply {
        }
        val logsenha = intent.getStringExtra("senha")
        var TextView1 = findViewById<TextView>(R.id.txtSenha).apply {
        }
    }
    fun logout(){
        autentication.signOut()
        finish()
    }
}
