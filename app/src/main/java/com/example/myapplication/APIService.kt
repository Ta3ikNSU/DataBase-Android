package com.example.myapplication


import com.example.myapplication.DTO.*
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
    ): Call<CreateCarAnnouncementsResponseDTO>

    @POST("user/favorites/{mail}/{announcement_id}")
    fun addFavoriteAnnouncement(
        @Path("mail") mail: String,
        @Path("announcement_id") announcement_id: Long
    ): Call<OkResponseDTO>

    @DELETE("user/favorites/{mail}/{announcement_id}")
    fun deleteFavoriteAnnouncement(
        @Path("mail") mail: String,
        @Path("announcement_id") announcement_id: Long
    ): Call<OkResponseDTO>
}