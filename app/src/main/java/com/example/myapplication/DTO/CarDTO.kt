package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.ToString
import lombok.experimental.FieldDefaults
import lombok.extern.jackson.Jacksonized

data class CarDTO(
    val brand: String? = null,
    var model: String? = null,
    var transmission: String? = null,
    var gear: String? = null,
    var engineCapacity: Int? = null,
    var enginePower: Int? = null,
    var color: Int? = null,
    var mileage: Int? = null,
    var performance: String? = null,
    var vinNumber: Long? = null,
    val description: String? = null,
    val announcementDTO: AnnouncementDTO? = null
) : java.io.Serializable