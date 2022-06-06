package com.example.myapplication.activities

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.DTO.OkResponseDTO
import com.example.myapplication.Entity.User
import com.example.myapplication.R
import com.example.myapplication.RetrofitServices
import com.example.myapplication.adapter.CarParamsAdapter
import com.example.myapplication.carDtoToList
import com.example.myapplication.retrofit.RetrofitClient
import com.google.android.material.button.MaterialButton
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class AnnouncementActivity : AppCompatActivity() {

    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService: RetrofitServices =
            RetrofitClient.getClient().create(RetrofitServices::class.java)

        setContentView(R.layout.activity_announcement)
        val nameTitle: TextView = findViewById(R.id.act_ann_textview_title_bar_name)
        val priceTitle: TextView = findViewById(R.id.act_ann_textview_title_bar_price)
        val nameFrame: TextView = findViewById(R.id.act_ann_image_frame_name)
        val priceFrame: TextView = findViewById(R.id.act_ann_image_frame_price)
        val description: TextView = findViewById(R.id.car_info_extra)
        val callButton: MaterialButton = findViewById(R.id.call_button)
        val info: TextView = findViewById(R.id.downTitleText)

        val intent = this.intent

        val carDTO: CarDTO = intent.extras!!.getSerializable("CarDTO") as CarDTO

        val user: User = intent.extras!!.getSerializable("user") as User

        nameTitle.text = "${carDTO.brand} ${carDTO.model}"
        nameFrame.text = "${carDTO.brand} ${carDTO.model}"
        priceTitle.text = "${carDTO.announcementDTO?.price}"
        priceFrame.text = "${carDTO.announcementDTO?.price}"
        description.text = "${carDTO.description}"
        callButton.text = "${carDTO.announcementDTO?.region}"
        info.text = "${carDTO.vinNumber}"

        val recyclerView: RecyclerView = findViewById(R.id.car_info_list)
        recyclerView.adapter = CarParamsAdapter(carDtoToList(carDTO))

        val backbutton: ImageButton = findViewById(R.id.act_ann_button_back)
        backbutton.setOnClickListener {
            finish()
        }

        val star: CheckBox = findViewById(R.id.checkBox)
        star.setOnClickListener {
            if (user.isAuth) {
                if (!star.isChecked) {
                    val call =
                        apiService.deleteFavoriteAnnouncement(
                            user.mail,
                            carDTO.announcementDTO!!.id!!
                        )
                    myExecutor.execute {
                        val response: OkResponseDTO? = call.execute().body()
                        if (response!!.success) {
                            runOnUiThread {
                                star.isChecked = false
                            }
                        }
                    }
                } else {
                    val call =
                        apiService.addFavoriteAnnouncement(user.mail, carDTO.announcementDTO!!.id!!)
                    myExecutor.execute {
                        val response: OkResponseDTO? = call.execute().body()
                        if (response!!.success) {
                            runOnUiThread {
                                star.isChecked = true
                            }
                        }
                    }
                }
            } else {
                runOnUiThread {
                    val text = "Войдите в своей аккаунт, чтобы добавить объявление в избранное"
                    Toast.makeText(this.baseContext, text, text.length).show()
                    star.isChecked = false
                }
            }
        }
    }

}