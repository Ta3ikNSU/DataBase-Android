package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.fragment.AnnouncementsFragment
import com.example.myapplication.fragment.DetailsFragment
import com.example.myapplication.fragment.MenuFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

const val ip: String = "localhost"
const val host: String = "8081"

class main : AppCompatActivity() {
    private val tag: String = this.javaClass.name

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chip: ChipNavigationBar = findViewById(R.id.bottom_menu)
        chip.setItemSelected(R.id.ann)
        supportFragmentManager.beginTransaction()
            .add(R.id.announcements_frame, AnnouncementsFragment()).commit()
        chip.setOnItemSelectedListener {
            when (it) {
                R.id.ann -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.announcements_frame, AnnouncementsFragment()).commit()
                    android.util.Log.i(tag, "User come to car announcements")
                }

                R.id.details -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.announcements_frame, DetailsFragment()).commit()
                    android.util.Log.i(tag, "User come to details announcements ")
                }
                R.id.menu -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.announcements_frame, MenuFragment()).commit()
                    android.util.Log.i(tag, "User come to menu")
                }
            }
        }

    }
}