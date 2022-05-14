package com.example.myapplication.DTO

data class CreateCarAnnouncementsRequestDTO(
    val mail: String? = null,
    val brand: String? = null,
    val model: String? = null,
    val transmission: String? = null,
    val gear: String? = null,
    val engineCapacity: Int? = null,
    val enginePower: Int? = null,
    val color: String? = null,
    val mileage: Int? = null,
    val performance: String? = null,
    val vinNumber: Long? = null,
    val description: String? = null,
    val region: Int? = null,
    val price: Long? = null
)
