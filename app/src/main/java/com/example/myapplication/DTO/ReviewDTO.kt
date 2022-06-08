package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Builder
import java.io.Serializable

@Builder
data class ReviewDTO(

    @JsonProperty("description")
    var id: Long,

    @JsonProperty("description")
    var description: String
) : Serializable