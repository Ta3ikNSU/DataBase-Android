package com.example.myapplication


import com.example.myapplication.DTO.*
import com.google.rpc.context.AttributeContext
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {

    @POST("authorize/register")
    fun register(
        @Body registerRequestDTO: RegisterRequestDTO
    ):
            Call<OkResponseDTO>

    @POST("authorize/auth")
    fun auth(
        @Body registerRequestDTO: AuthRequestDTO
    ):
            Call<OkResponseDTO>

    @POST("car/announcements")
    fun getAnnouncements(
        @Body carAnnouncementsRequestDTO: CarAnnouncementsRequestDTO
    ):
            Call<CarAnnouncementsResponseDTO>

    @POST("car/announcements/{id}")
    fun getAnnouncementById(
        @Path("id") id: Long
    ):
            Call<CarDTO>

    @PUT("car/create")
    fun createAnnouncement(
        @Body createCarAnnouncementsRequestDTO: CreateCarAnnouncementsRequestDTO
    ): Call<CarAnnouncementsResponseDTO>
}