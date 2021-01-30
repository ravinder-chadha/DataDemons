package com.example.jollysoothingera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.drawer.*

class MainActivity3 : AppCompatActivity()  {
    lateinit var textView7: TextView
    lateinit var textView8: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")
        textView7 = findViewById(R.id.textView7)
        textView8 = findViewById(R.id.textView8)
        textView7.text = "User ID :: $userId"
        textView8.text = "Email ID :: $emailId"

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity3, MainActivity::class.java))
            finish()
        }
    }
}