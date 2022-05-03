package com.example.myapplication.DTO

data class CreateCarAnnouncementsRequestDTO(
    val mail: String? = null,
    val brand: String? = null,
    val model: String? = null,
    val transmission: Int? = null,
    val gear: Int? = null,
    val engineCapacity: Int? = null,
    val enginePower: Int? = null,
    val color: String? = null,
    val mileage: String? = null,
    val performance: Int? = null,
    val vinNumber: Long? = null,
    val description: String? = null,
    val region: Int? = null,
    val price: Long? = null
)
