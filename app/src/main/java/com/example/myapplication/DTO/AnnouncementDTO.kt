package com.example.myapplication.DTO

import java.sql.Timestamp

data class AnnouncementDTO(
    val region:Int,
    val startDate:Timestamp,
    val endDate:Timestamp,
    val announcementState : AnnouncementState,
    val price : Long,
    val photoList : String
)
