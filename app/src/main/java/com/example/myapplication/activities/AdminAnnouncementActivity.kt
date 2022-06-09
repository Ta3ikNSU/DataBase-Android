package com.example.myapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class AdminAnnouncementActivity : AppCompatActivity() {

    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cars: ArrayList<CarDTO> = this.intent.extras!!.getSerializable("cars") as ArrayList<CarDTO>
        val user: User = intent.extras!!.getSerializable("user") as User
        val inflater = LayoutInflater.from(this)
        val view : View = inflater.inflate(R.layout.admin_anns, null, false);

        setContentView(view)
        val listView : ListView  = findViewById(R.id.admin_anns_list)
        val backbutton: ImageButton = findViewById(R.id.act_ann_button_back_admin)
        backbutton.setOnClickListener {
            finish()
        }

        val cars_name = Array(cars.size) { "" }
        for (i in cars_name.indices) {
            cars_name.set(i, cars.get(i)!!.brand.toString() + cars.get(i)!!.model.toString())
        }

        listView.adapter = ArrayAdapter(this, R.layout.admin_anns, R.id.car_name, cars_name)

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(view.context, AnnouncementActivity::class.java)
            intent.putExtra("CarDTO", cars[position])
            intent.putExtra("user", user)
            view.context.startActivity(intent)
        }
    }
}

class AdminChangeableAnnouncement : AppCompatActivity() {
    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.admin_changeable_announcement)
        val nameTitle: TextView = findViewById(R.id.act_ann_textview_title_bar_name)
        val priceTitle: TextView = findViewById(R.id.act_ann_textview_title_bar_price)
        val nameFrame: TextView = findViewById(R.id.act_ann_image_frame_name)
        val priceFrame: TextView = findViewById(R.id.act_ann_image_frame_price)
        val description: TextView = findViewById(R.id.car_info_extra)
        val info: TextView = findViewById(R.id.downTitleText)

        val intent = this.intent

        val carDTO: CarDTO = intent.extras!!.getSerializable("CarDTO") as CarDTO

        val user: User = intent.extras!!.getSerializable("user") as User
        val mail: String = user.mail
        nameTitle.text = "${carDTO.brand} ${carDTO.model}"
        nameFrame.text = "${carDTO.brand} ${carDTO.model}"
        priceTitle.text = "${carDTO.announcementDTO?.price}"
        priceFrame.text = "${carDTO.announcementDTO?.price}"
        description.text = "${carDTO.description}"
        info.text = "${carDTO.vinNumber}"

        val recyclerView: RecyclerView = findViewById(R.id.car_info_list)
        recyclerView.adapter = CarParamsAdapter(carDtoToList(carDTO))

        val backbutton: ImageButton = findViewById(R.id.act_ann_button_back)
        backbutton.setOnClickListener {
            finish()
        }

        val deletebutton: Button = findViewById(R.id.delete)
        deletebutton.setOnClickListener {
            val apiService: RetrofitServices =
                RetrofitClient.getClient().create(RetrofitServices::class.java)
            val call = apiService.deleteReview(mail, carDTO.announcementDTO!!.id!!)
            myExecutor.execute {
                call.execute()
            }
            finish()
        }
    }
}