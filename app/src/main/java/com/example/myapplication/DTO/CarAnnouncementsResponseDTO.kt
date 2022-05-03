package com.example.myapplication.DTO

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.ToString
import lombok.experimental.FieldDefaults
import lombok.extern.jackson.Jacksonized


data class CarAnnouncementsResponseDTO(
    var cars: List<CarDTO?>
)