package com.example.authemailfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    private lateinit var editEmail : EditText
    private lateinit var editPassword : EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var register : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        editEmail = findViewById(R.id.emailreg)
        editPassword = findViewById(R.id.passwordReg)
        register = findViewById(R.id.registerButton)
        register.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful){
                        val login = Intent(this@RegisterActivity,LoginActivity::class.java)
                        startActivity(login)
                    }else{
                        Toast.makeText(applicationContext,"gagal register",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}