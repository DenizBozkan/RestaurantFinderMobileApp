package com.example.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import com.example.restaurant.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {


    lateinit var firebaseAuth: FirebaseAuth
    lateinit var signuppage : TextView
    lateinit var forgotpassword : TextView
    lateinit var switch : Switch
    lateinit var password : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        switch = findViewById(R.id.showPassword)
        password = findViewById(R.id.password)
        firebaseAuth = FirebaseAuth.getInstance()
        signuppage = findViewById(R.id.signuppageText)
        signuppage.setOnClickListener {
            val intent= Intent(this,SignUpScreen::class.java)
            startActivity(intent)
        }
        forgotpassword = findViewById(R.id.forgotPasswordText)
        forgotpassword.setOnClickListener {
            val intent= Intent(this,ForgotPasswordScreen::class.java)
            startActivity(intent)
        }
        binding.loginButton.setOnClickListener {
            val email = binding.email1.text.toString()
            val password = binding.password.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Empty fiels are not allowed",Toast.LENGTH_SHORT)
                    .show()
            }
        }
        switch.setOnClickListener{
            if(switch.isChecked){
                password.inputType = 1
            }
            else{
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }
}