package com.example.restaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.restaurant.databinding.ActivityMainBinding
import com.example.restaurant.databinding.ActivitySignUpScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpScreen : AppCompatActivity(){
    lateinit var sbinding: ActivitySignUpScreenBinding
    lateinit var loginpage : TextView
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var switch : Switch
    lateinit var passwordconfirm : EditText
    lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sbinding= ActivitySignUpScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(sbinding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.email)
        password = findViewById(R.id.passwordsup)
        passwordconfirm = findViewById(R.id.passwordConfirm)
        loginpage = findViewById(R.id.returnToLoginPageText)
        switch = findViewById(R.id.showPasswordSignUp)
        loginpage.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        sbinding.singupButton.setOnClickListener{
            val email = sbinding.email.text.toString()
            val password = sbinding.passwordsup.text.toString()
            val passwordc = sbinding.passwordConfirm.text.toString()
            if(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
             if(password.isNotEmpty() && passwordc.isNotEmpty()){
                 if(password == passwordc){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Passwords is not matching",Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else{
                Toast.makeText(this,"Empty fiels are not allowed",Toast.LENGTH_SHORT)
                    .show()
            }
        } else{
                Toast.makeText(this,"Invalid Email Address", Toast.LENGTH_SHORT).show();
            }
        }
        switch.setOnClickListener{
            if(switch.isChecked){
                password.inputType = 1
                passwordconfirm.inputType = 1
            }
            else{
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                passwordconfirm.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }
}