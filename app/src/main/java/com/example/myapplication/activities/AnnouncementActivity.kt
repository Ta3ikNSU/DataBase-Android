package com.example.myapplication.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.R
import com.example.myapplication.adapter.CarParamsAdapter
import com.example.myapplication.carDtoToList
import com.google.android.material.button.MaterialButton


class AnnouncementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement)
        val nameTitle : TextView = findViewById(R.id.act_ann_textview_title_bar_name)
        val priceTitle : TextView = findViewById(R.id.act_ann_textview_title_bar_price)
        val nameFrame : TextView = findViewById(R.id.act_ann_image_frame_name)
        val priceFrame : TextView = findViewById(R.id.act_ann_image_frame_price)
        val description : TextView = findViewById(R.id.car_info_extra)
        val callButton : MaterialButton = findViewById(R.id.call_button)
        val info : TextView = findViewById(R.id.downTitleText)

        val intent = this.intent

//        val carDTO : CarDTO = intent.extras!!.get("CarDTO") as CarDTO
        val carDTO : CarDTO = intent.extras!!.getSerializable("CarDTO") as CarDTO

        nameTitle.text = "${carDTO.brand} ${carDTO.model}"
        nameFrame.text = "${carDTO.brand} ${carDTO.model}"
        priceTitle.text = "${carDTO.announcementDTO?.price}"
        priceFrame.text = "${carDTO.announcementDTO?.price}"
        description.text = "${carDTO.description}"
        callButton.text = "${carDTO.announcementDTO?.region}"
        info.text = "${carDTO.vinNumber}"

        val recyclerView : RecyclerView = findViewById(R.id.car_info_list)
        recyclerView.adapter = CarParamsAdapter(carDtoToList(carDTO))

        val backbutton : ImageButton = findViewById(R.id.act_ann_button_back)
        backbutton.setOnClickListener {
            finish()
        }
    }

}