package com.androidstudioprojects.grapevine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
        val username = findViewById<EditText>(R.id.etUsername).text.toString()
        val password = findViewById<EditText>(R.id.etPassword).text.toString()
        loginUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {

    }
}