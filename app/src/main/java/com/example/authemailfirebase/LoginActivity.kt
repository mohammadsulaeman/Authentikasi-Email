package com.example.authemailfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity()
{
    private lateinit var mAuth: FirebaseAuth
    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var registerBtn : TextView
    private lateinit var lupapassBtn : TextView
    private lateinit var loginBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.emailLog)
        edtPassword = findViewById(R.id.passwordLog)
        registerBtn = findViewById(R.id.registerButton)
        loginBtn = findViewById(R.id.loginButton)
        lupapassBtn = findViewById(R.id.lupapassBtn)

        lupapassBtn.setOnClickListener {
            val forget = Intent(this@LoginActivity,ForgotPassActivity::class.java)
            startActivity(forget)
        }
        registerBtn.setOnClickListener {

            val regis  = Intent(this@LoginActivity, RegisterActivity::class.java)
            regis.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(regis)
        }

        loginBtn.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString()

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful){
                        val home = Intent(this@LoginActivity,MainActivity::class.java)
                        startActivity(home)
                    }else{
                        Toast.makeText(applicationContext,"Gagal Login",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}