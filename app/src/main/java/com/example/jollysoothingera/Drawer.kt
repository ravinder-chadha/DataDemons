package com.example.jollysoothingera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.drawer.*

class Drawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var suggestion: Suggestion
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val toggle =
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

         nav_menu.setNavigationItemSelectedListener(this)
    }
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            // Handle navigation view item clicks here.
            when (item.itemId) {
                R.id.Feed -> {
                    Toast.makeText(this, "You are in feed tab", Toast.LENGTH_SHORT).show()
                }
                R.id.Donate -> {
                    Toast.makeText(this, "You are in donate tab", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Donate::class.java))
                }
                R.id.Suggestions -> {
                    Toast.makeText(this, "You are in suggestion tab", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Suggestion::class.java))
                }
                R.id.About -> {
                    Toast.makeText(this, "You are in about tab", Toast.LENGTH_SHORT).show()
                }
                R.id.RateUs -> {
                    Toast.makeText(this, "You are in RateUs tab", Toast.LENGTH_SHORT).show()
                }
            }
            return true
        }
    }