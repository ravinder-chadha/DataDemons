package com.example.jollysoothingera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView3)
        val text = "Sign Up"
        val spannableString = SpannableString(text)
        val clickableSpan: ClickableSpan = object: ClickableSpan(){
            override fun onClick(widget: View) {
                val intent = Intent(this@MainActivity,MainActivity2::class.java)
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.setText(spannableString, TextView.BufferType.SPANNABLE)
        textView.movementMethod = LinkMovementMethod.getInstance()

        val etUsername: EditText = findViewById(R.id.editTextTextEmailAddress)
        val etPassword: EditText = findViewById(R.id.editTextTextPassword)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(){
            when {
                TextUtils.isEmpty(etUsername.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@MainActivity,
                            "Please enter Email",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@MainActivity,
                            "Please enter Password",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = etUsername.text.toString().trim { it <= ' ' }
                    val password: String = etPassword.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(
                                    OnCompleteListener<AuthResult> { task ->
                                        if (task.isSuccessful) {
                                            val firebaseUser: FirebaseUser = task.result!!.user!!
                                            Toast.makeText(
                                                    this@MainActivity,
                                                    "You are Logged In Successfully",
                                                    Toast.LENGTH_SHORT
                                            ).show()
                                            val intent = Intent(this@MainActivity, Drawer::class.java)
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                            intent.putExtra("email_id", email)
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                    this@MainActivity,
                                                    task.exception!!.message.toString(),
                                                    Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    })
                }
            }

        }

    }
}