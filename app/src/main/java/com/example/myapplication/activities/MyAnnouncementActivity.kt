package com.example.myapplication.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.Entity.User
import com.example.myapplication.R
import com.example.myapplication.RetrofitServices
import com.example.myapplication.adapter.CarParamsAdapter
import com.example.myapplication.carDtoToList
import com.example.myapplication.retrofit.RetrofitClient
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MyAnnouncementActivity : AppCompatActivity() {

    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)

    val apiService: RetrofitServices =
        RetrofitClient.getClient().create(RetrofitServices::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_myannouncement)
        val nameTitle: TextView = findViewById(R.id.my_act_ann_textview_title_bar_name)
        val priceTitle: TextView = findViewById(R.id.my_act_ann_textview_title_bar_price)
        val nameFrame: TextView = findViewById(R.id.my_act_ann_image_frame_name)
        val priceFrame: TextView = findViewById(R.id.my_act_ann_image_frame_price)
        val description: TextView = findViewById(R.id.my_car_info_extra)
        val info: TextView = findViewById(R.id.my_downTitleText)

        val intent = this.intent

        val carDTO: CarDTO = intent.extras!!.getSerializable("CarDTO") as CarDTO

        val user: User = intent.extras!!.getSerializable("user") as User
        val mail : String = user.mail
        nameTitle.text = "${carDTO.brand} ${carDTO.model}"
        nameFrame.text = "${carDTO.brand} ${carDTO.model}"
        priceTitle.text = "${carDTO.announcementDTO?.price}"
        priceFrame.text = "${carDTO.announcementDTO?.price}"
        description.text = "${carDTO.description}"
        info.text = "${carDTO.vinNumber}"

        val recyclerView: RecyclerView = findViewById(R.id.my_car_info_list)
        recyclerView.adapter = CarParamsAdapter(carDtoToList(carDTO))

        val backbutton: ImageButton = findViewById(R.id.my_act_ann_button_back)
        backbutton.setOnClickListener {
            finish()
        }
        val states = arrayOf("Открыто", "Продано", "Удалено")
        val spinner : Spinner = findViewById(R.id.my_stateSpinner)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(carDTO.announcementDTO!!.state!!.ordinal)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                apiService.updateAnnouncementStateById(mail, carDTO.announcementDTO!!.id!!, position)
            }
        }
    }
}