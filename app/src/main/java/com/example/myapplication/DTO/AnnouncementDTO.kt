package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class AnnouncementDTO(
    @JsonProperty("region")
    val region:Int?,

    @JsonProperty("start_date")
    val startDate:String?,

    @JsonProperty("end_date")
    val endDate:String?,

    @JsonProperty("status")
    val state : AnnouncementState?,

    @JsonProperty("price")
    val price : Long?,

    @JsonProperty("photoList")
    val photoList : String?
) : java.io.Serializable
