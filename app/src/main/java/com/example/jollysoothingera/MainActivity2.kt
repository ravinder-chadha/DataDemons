package com.example.jollysoothingera

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult

import java.util.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val button = findViewById<Button>(R.id.pickDateBtn)
        button.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                val text = findViewById<TextView>(R.id.textView5)
                text.setText("" + mDay + "/" + mMonth + "/" + mYear + "")
            }, year, month, day)
            dpd.show()
        }
        val button3 = findViewById<Button>(R.id.button3)
        val etUsername: EditText = findViewById(R.id.EmailAddress2)
        val etPhone: EditText = findViewById(R.id.editTextPhone)
        val etPassword: EditText = findViewById(R.id.editTextTextPassword2)
        button3.setOnClickListener {
            when {
                TextUtils.isEmpty(etUsername.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@MainActivity2,
                            "Please enter Email",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@MainActivity2,
                            "Please enter Password",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(etPhone.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@MainActivity2,
                            "Please enter Mobile number",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = etUsername.text.toString().trim { it <= ' ' }
                    val password: String = etPassword.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(
                                    OnCompleteListener<AuthResult> { task ->
                                        if (task.isSuccessful) {
                                            val firebaseUser: FirebaseUser = task.result!!.user!!
                                            Toast.makeText(
                                                    this@MainActivity2,
                                                    "Successfully Registered",
                                                    Toast.LENGTH_SHORT
                                            ).show()
                                            val intent = Intent(this@MainActivity2, MainActivity::class.java)
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            intent.putExtra("user_id", firebaseUser.uid)
                                            intent.putExtra("email_id", email)
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                    this@MainActivity2,
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