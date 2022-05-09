package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
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
    lateinit var call : Call<CarAnnouncementsResponseDTO>

    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.announcements_fragment,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService: RetrofitServices =
            RetrofitClient.getClient().create(RetrofitServices::class.java)
        call = apiService.getAnnouncements(
            carAnnouncementsRequestDTO = CarAnnouncementsRequestDTO()
        )

        val grid: GridView = view.findViewById(R.id.gridViewAnnouncement)
        val adapter = AnnouncementsGridViewAdapter(view.context, cars)
        grid.adapter = adapter
        myExecutor.execute {
            cars.addAll(call.execute().body()!!.cars)
            activity?.runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }
}