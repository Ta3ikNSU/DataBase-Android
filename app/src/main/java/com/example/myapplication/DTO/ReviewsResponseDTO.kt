package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Builder
import java.io.Serializable

@Builder
data class ReviewsResponseDTO (
    @JsonProperty("reviews")
    var reviewDTOList: List<ReviewDTO>
) : Serializable