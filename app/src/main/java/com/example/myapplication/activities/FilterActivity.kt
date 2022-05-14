package com.example.myapplication.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class FilterActivity : AppCompatActivity() {

    private val mGroupsArray = arrayOf("Марка", "Мощность двигателя", "Объём двигателя", "Пробег", "Цена")

    private val mMarksArray = arrayOf("Audi", "BMW", "Kia", "Ford", "Nissan", "Mitsubishi")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filter_cars)
        val expandableListView : ExpandableListView = findViewById(R.id.expListView)

    }
}