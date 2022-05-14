package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.myapplication.DTO.CarAnnouncementsRequestDTO
import com.example.myapplication.DTO.CarAnnouncementsResponseDTO
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.R
import com.example.myapplication.RetrofitServices
import com.example.myapplication.activities.AnnouncementsGridViewAdapter
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class AnnouncementsFragment : Fragment() {

    var cars: ArrayList<CarDTO?> = ArrayList()

    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)

    lateinit var adapter : AnnouncementsGridViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.announcements_fragment,
            container, false
        )
    }

    private fun findAnnouncement(carAnnouncementsRequestDTO: CarAnnouncementsRequestDTO) {
        val apiService: RetrofitServices =
            RetrofitClient.getClient().create(RetrofitServices::class.java)
        val call = apiService.getAnnouncements(carAnnouncementsRequestDTO)
        myExecutor.execute {
            cars.addAll(call.execute().body()!!.cars)
            activity?.runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grid: GridView = view.findViewById(R.id.gridViewAnnouncement)
        adapter = AnnouncementsGridViewAdapter(view.context, cars)
        grid.adapter = adapter
        findAnnouncement(CarAnnouncementsRequestDTO())
        val button : Button = view.findViewById(R.id.button_filter)

    }
}