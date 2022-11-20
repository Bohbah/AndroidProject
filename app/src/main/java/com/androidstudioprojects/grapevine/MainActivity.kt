package com.androidstudioprojects.grapevine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Bottom Nav Bar navigation code
         */
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->

            when(item.itemId) {
                R.id.action_home -> {
                    //TODO Navigate to home/feed fragment
                }
                R.id.action_calendar -> {
                    //TODO Navigate to calendar fragment
                }
                R.id.action_compose -> {
                    //TODO Navigate to compose fragment
                }
                R.id.action_chat -> {
                    //TODO Navigate to chat fragment
                }
                R.id.action_profile -> {
                    //TODO Navigate to profile fragment
                }
            }

            true //Not sure what this does but its necessary I promise
        }
    }
}
