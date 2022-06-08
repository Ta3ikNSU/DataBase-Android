package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class ProfileDTO(
    @JsonProperty("mail")
    val mail: String,

    @JsonProperty("nickname")
    val nickname: String,

    @JsonProperty("role")
    val role: String
) : java.io.Serializable