package com.example.myapplication


import com.example.myapplication.DTO.CarAnnouncementsRequestDTO
import com.example.myapplication.DTO.CarAnnouncementsResponseDTO
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.DTO.CreateCarAnnouncementsRequestDTO
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @POST("car/announcements")
    fun getAnnouncements(
        @Body carAnnouncementsRequestDTO: CarAnnouncementsRequestDTO
    ):
            Call<CarAnnouncementsResponseDTO>

    @POST("car/announcements/{id}")
    fun getAnnouncementById(
        @Body carAnnouncementsRequestDTO: CarAnnouncementsRequestDTO,
        @Path("id") id: String
    ):
            Call<CarDTO>

    @PUT("car/create")
    fun createAnnouncement(
        @Body createCarAnnouncementsRequestDTO: CreateCarAnnouncementsRequestDTO
    ): Call<CarAnnouncementsResponseDTO>
}