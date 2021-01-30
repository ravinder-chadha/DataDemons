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

class Drawer : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val toggle =
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}