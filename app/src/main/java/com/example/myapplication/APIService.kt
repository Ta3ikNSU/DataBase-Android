package com.example.myapplication


import com.example.myapplication.DTO.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {

    @POST("/authorize/register")
    fun register(
        @Body registerRequestDTO: RegisterRequestDTO
    ): Call<ProfileDTO>

    @POST("/authorize/auth")
    fun auth(
        @Body registerRequestDTO: AuthRequestDTO
    ): Call<ProfileDTO>

    @POST("/car/announcements")
    fun getAnnouncements(
        @Body carAnnouncementsRequestDTO: CarAnnouncementsRequestDTO
    ): Call<CarAnnouncementsResponseDTO>

    @POST("/car/announcements/{id}")
    fun getAnnouncementById(
        @Path("id") id: Long
    ): Call<CarDTO>

    @PUT("car/create")
    fun createAnnouncement(
        @Body createCarAnnouncementsRequestDTO: CreateCarAnnouncementsRequestDTO
    ): Call<CreateCarAnnouncementsResponseDTO>

    @POST("/favorites/{mail}/{announcement_id}")
    fun addFavoriteAnnouncement(
        @Path("mail") mail: String,
        @Path("announcement_id") announcement_id: Long
    ): Call<OkResponseDTO>

    @DELETE("/user/favorites/{mail}/{announcement_id}")
    fun deleteFavoriteAnnouncement(
        @Path("mail") mail: String,
        @Path("announcement_id") announcement_id: Long
    ): Call<OkResponseDTO>

    @POST("/user/{mail}/{announcement_id}/{state}")
    fun updateAnnouncementStateById(
        @Path("mail") mail: String,
        @Path("announcement_id") announcement_id: Long,
        @Path("state") state: Int,
    ): Call<Nothing>

    @POST("/admin/{mail}/cars")
    fun getAdminAnnouncement(
        @Path("mail") mail: String,
    ): Call<CarAnnouncementsResponseDTO>

    @POST("/admin/{mail}/reviews")
    fun getReviews(
        @Path("mail") mail: String
    ): Call<ReviewsResponseDTO>

    @DELETE("/admin/{mail}/cars/{id}")
    fun deleteReview(
        @Path("mail") mail: String,
        @Path("id") id: Long,
    ): Call<Nothing>

    @POST("/admin/{mail}/review")
    fun updateAnnouncement(
        @Path("mail") mail: String,
        @Body reviewDTO: ReviewDTO
    ): Call<Nothing>

    @DELETE("/admin/{mail}/review")
    fun deleteReview(
        @Path("mail") mail: String,
        @Body reviewDTO: ReviewDTO
    ): Call<Nothing>
}