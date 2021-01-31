package com.example.jollysoothingera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Suggestion : AppCompatActivity() {
    lateinit var editTextTextPersonName3: EditText
    lateinit var editTextTextPersonName4: EditText
    lateinit var button4: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion)

        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3)
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4)
        button4 = findViewById(R.id.button4)

        button4.setOnClickListener{
            saveName()
        }
    }
    private fun saveName(){
        val name = editTextTextPersonName3.text.toString().trim()
        val suggestion= editTextTextPersonName4.text.toString()
        if(name.isEmpty()){
            editTextTextPersonName3.error = "Please enter a name"
            return
        }
        if(suggestion.isEmpty()){
            editTextTextPersonName4.error = "PLease enter your suggestion"
            return
        }
        val ref = FirebaseDatabase.getInstance().getReference("Suggesters")
        val uid = ref.push().key
        val suggester = uid?.let { Suggester(it, name, suggestion) }
        if (uid != null) {
            ref.child(uid).setValue(suggester).addOnCompleteListener{
                Toast.makeText(applicationContext,"Thank You For Your Suggestion", Toast.LENGTH_LONG).show()
            }
        }
    }
}