package com.example.myapplication.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class CarDTO {
    String brand;
    String model;
    Integer transmission;
    Integer gear;
    Integer engineCapacity;
    Integer enginePower;
    Integer color;
    String mileage;
    Integer performance;
    Long vinNumber;
    String description;
}
