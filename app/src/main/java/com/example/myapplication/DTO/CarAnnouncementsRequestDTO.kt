package com.example.myapplication.DTO

data class CarAnnouncementsRequestDTO(
    var brand: List<String>? = null,
    var model: List<String>? = null,
    var transmission: List<Int>? = null,
    var gear: List<Int>? = null,
    var minEngineCapacity: Int? = null,
    var maxEngineCapacity: Int? = null,
    var minEnginePower: Int? = null,
    var maxEnginePower: Int? = null,
    var color: List<Int>? = null,
    var mileage: List<String>? = null,
    var performance: List<Int>? = null,
    var description: List<String>? = null,
    val minPrice: Long? = null,
    val maxPrice: Long? = null,
    val state: AnnouncementState? = null
)
