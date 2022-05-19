package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class OkResponseDTO(

    @JsonProperty("success")
    val success : Boolean
)
