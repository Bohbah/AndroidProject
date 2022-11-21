package com.androidstudioprojects.grapevine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.androidstudioprojects.grapevine.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
bottomNav
        setContentView(R.layout.activity_main)

        /*
        Bottom Nav Bar navigation code
         */
        val fragmentManager: FragmentManager = supportFragmentManager
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->
            var fragmentToShow : Fragment? = null
            when(item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = HomeFragment()
                }
                R.id.action_calendar -> {
                    fragmentToShow = CalendarFragment()
                }
                R.id.action_compose -> {
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_chat -> {
                    fragmentToShow = ChatFragment()
                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                }
            }

            if (fragmentToShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }

            true //Not sure what this does but its necessary I promise
        }
        // Set default view
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
=======
        setContentView(R.layout.login_account)
master
    }
}
