package com.androidstudioprojects.grapevine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_account)

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val name = findViewById<EditText>(R.id.tiName).text.toString()
            val school = findViewById<EditText>(R.id.tiSchool).text.toString()
            val org = findViewById<EditText>(R.id.tiOrg).text.toString()
            val joined = findViewById<EditText>(R.id.tiJoinedIn).text.toString()
            val position = findViewById<EditText>(R.id.tiPosition).text.toString()
            val username = findViewById<EditText>(R.id.tiUsername).text.toString()
            val password = findViewById<EditText>(R.id.tiPassword).text.toString()
            signupUser(name, school, org, joined, position, username, password)
        }
    }

    private fun signupUser(name: String, school: String, org: String, joined: String, position: String, username: String, password: String) {
        val user = ParseUser()

        // Set fields for the user to be created
        user.put("name", name)
        user.put("school", school)
        user.put("org", org)
        user.put("joined", joined)
        user.put("role", position)
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                Log.i(TAG, "Successfully created new user.")
                goToMainActivity()
            } else {
                Toast.makeText(this, "error making new user", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
    private fun goToMainActivity() {
        val intent = Intent(this@SignupActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        val TAG = "SignupActivity"
    }

}