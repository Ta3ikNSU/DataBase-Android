package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.ToString
import lombok.experimental.FieldDefaults
import lombok.extern.jackson.Jacksonized

@Jacksonized
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
data class CarDTO (
    var brand: String? = null,
    var model: String? = null,
    var transmission: Int? = null,
    var gear: Int? = null,
    var engineCapacity: Int? = null,
    var enginePower: Int? = null,
    var color: Int? = null,
    var mileage: String? = null,
    var performance: Int? = null,
    var vinNumber: Long? = null,
    var description: String? = null,
    val price: Long? = null
){
}