package com.example.myapplication.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.DTO.CarAnnouncementsRequestDTO
import com.example.myapplication.DTO.CarAnnouncementsResponseDTO
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.R
import com.example.myapplication.RetrofitServices
import com.example.myapplication.retrofit.RetrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class GridViewAdapter(
    private val mContext: Context
) : BaseAdapter() {


    val myExecutor: ExecutorService = Executors.newCachedThreadPool()

    var cars: ArrayList<CarDTO?> = ArrayList()

    init {
        val apiService: RetrofitServices =
            RetrofitClient.getClient().create(RetrofitServices::class.java)
        val call: Call<CarAnnouncementsResponseDTO> = apiService.getAnnouncements(
            carAnnouncementsRequestDTO = CarAnnouncementsRequestDTO()
        )
        myExecutor.execute {
            cars.addAll(call.execute().body()!!.cars)
        }
    }

    override fun getCount(): Int {
        return cars.size
    }

    override fun getItem(position: Int): Any {
        return cars[position]!!
    }

    override fun getItemId(position: Int): Long {
        return cars[position]!!.vinNumber!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val linearLayout = LayoutInflater.from(mContext).inflate(R.layout.ann_card, parent, false)
        val carPreview = linearLayout.findViewById<ImageView>(R.id.annCardImage)
        val title = linearLayout.findViewById<TextView>(R.id.annCardTitle)
        val price = linearLayout.findViewById<TextView>(R.id.annCardPrice)
        val date = linearLayout.findViewById<TextView>(R.id.annCardDate)

        // пока так, в идеале добавить парс json'на
        val url = cars[position]!!.announcementDTO!!.photoList
        Picasso.get()
            .load(url)
            .placeholder(com.google.android.material.R.drawable.navigation_empty_icon)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(carPreview)

        title.text = cars[position]!!.brand + " " + cars[position]!!.model
        price.text = cars[position]!!.announcementDTO!!.price.toString()
        date.text = cars[position]!!.announcementDTO!!.startDate.toString()

        return linearLayout
    }
}