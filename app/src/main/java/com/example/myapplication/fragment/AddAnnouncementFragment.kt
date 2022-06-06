package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.DTO.CreateCarAnnouncementsRequestDTO
import com.example.myapplication.DTO.CreateCarAnnouncementsResponseDTO
import com.example.myapplication.DTO.OkResponseDTO
import com.example.myapplication.Entity.User
import com.example.myapplication.R
import com.example.myapplication.RetrofitServices
import com.example.myapplication.retrofit.RetrofitClient
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddAnnouncementFragment(
    val user: User
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.create_announcemt,
            container, false
        )
    }

    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    val apiService: RetrofitServices =
        RetrofitClient.getClient().create(RetrofitServices::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val brand_create: TextView = view.findViewById(R.id.brand_create)
        val model_create: TextView = view.findViewById(R.id.model_create)
        val transmission_create: TextView = view.findViewById(R.id.transmission_create)
        val gear_create: TextView = view.findViewById(R.id.gear_create)
        val engineCapacity_create: TextView = view.findViewById(R.id.engineCapacity_create)
        val enginePower_create: TextView = view.findViewById(R.id.enginePower_create)
        val color_create: TextView = view.findViewById(R.id.color_create)
        val mileage_create: TextView = view.findViewById(R.id.mileage_create)
        val performance_create: TextView = view.findViewById(R.id.performance_create)
        val vinNumber_create: TextView = view.findViewById(R.id.vinNumber_create)
        val description_create: TextView = view.findViewById(R.id.description_create)
        val region_create: TextView = view.findViewById(R.id.region_create)
        val price_create: TextView = view.findViewById(R.id.price_create)

        val button_create: Button = view.findViewById(R.id.button_create_ann)

        button_create.setOnClickListener {
            if (brand_create.text.length == 0 ||
                model_create.text.length == 0 ||
                transmission_create.text.length == 0 ||
                gear_create.text.length == 0 ||
                engineCapacity_create.text.length == 0 ||
                enginePower_create.text.length == 0 ||
                mileage_create.text.length == 0 ||
                color_create.text.length == 0 ||
                performance_create.text.length == 0 ||
                vinNumber_create.text.length == 0 ||
                description_create.text.length == 0 ||
                region_create.text.length == 0 ||
                price_create.text.length == 0
            ) {
                activity?.runOnUiThread {
                    val text = "Заполните все поля"
                    Toast.makeText(this.context, text, text.length).show()
                }
            }else {
                val call =  apiService.createAnnouncement(CreateCarAnnouncementsRequestDTO(
                    user.mail,
                    brand_create.text.toString(),
                    model_create.text.toString(),
                    transmission_create.text.toString(),
                    gear_create.text.toString(),
                    engineCapacity_create.text.toString().toInt(),
                    enginePower_create.text.toString().toInt(),
                    color_create.text.toString(),
                    mileage_create.text.toString().toInt(),
                    performance_create.text.toString(),
                    vinNumber_create.text.toString().toLong(),
                    description_create.text.toString(),
                    region_create.text.toString().toInt(),
                    price_create.text.toString().toLong(),
                ))
                myExecutor.execute {
                    val responseDTO: CreateCarAnnouncementsResponseDTO = call.execute().body()!!
                    if(responseDTO.carDTO.vinNumber == vinNumber_create.text.toString().toLong()){
                        activity?.runOnUiThread {
                            val text = "Объявление успешно создано"
                            Toast.makeText(this.context, text, text.length).show()
                        }
                    }
                }
            }

        }
    }


}