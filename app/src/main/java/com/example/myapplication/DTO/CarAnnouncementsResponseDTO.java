package com.example.myapplication.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
public class CarAnnouncementsResponseDTO {
    List<CarDTO> cars;
}
