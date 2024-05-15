package com.example.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.restaurant.databinding.ActivityForgotPasswordScreenBinding
import com.example.restaurant.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordScreen : AppCompatActivity() {
    lateinit var email : EditText
    lateinit var button : Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var returntoBack : ImageView
    private lateinit var binding: ActivityForgotPasswordScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email = findViewById(R.id.resetemail)
        returntoBack = findViewById(R.id.returnToBack)
        button = findViewById(R.id.resetPasswordButton)
        firebaseAuth = FirebaseAuth.getInstance()
        button.setOnClickListener{
            if(email.text.toString().isNotEmpty()){
                val email = binding.resetemail.toString()
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    Toast.makeText(this,"Please check your email.",Toast.LENGTH_SHORT).show()
                }
                  //  .addOnFailureListener {
                   //     Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                   // }
            }
            else{
                Toast.makeText(this,"Please enter your email.",Toast.LENGTH_SHORT).show()
            }
        }

        returntoBack.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}