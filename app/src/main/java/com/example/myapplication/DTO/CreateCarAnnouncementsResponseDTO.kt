package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateCarAnnouncementsResponseDTO(
    @JsonProperty("carDTO")
    val carDTO: CarDTO
) : java.io.Serializable
