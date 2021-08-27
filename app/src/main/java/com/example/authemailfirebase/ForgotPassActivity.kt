package com.example.authemailfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassActivity : AppCompatActivity()
{
    private lateinit var editEmail : EditText
    private lateinit var lupasBtn : Button
    private lateinit var loginBtn : TextView
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        mAuth = FirebaseAuth.getInstance()
        editEmail = findViewById(R.id.emailPass)
        lupasBtn = findViewById(R.id.lupapassBtn)
        loginBtn = findViewById(R.id.loginbutton)
        loginBtn.setOnClickListener {
            val login = Intent(applicationContext,LoginActivity:: class.java)
            startActivity(login)
        }
        lupasBtn.setOnClickListener {
            val email = editEmail.text.toString()

            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val login = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(login)
                    }else{
                        Toast.makeText(applicationContext,"gagal melakukan proses reset password", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}