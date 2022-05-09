package com.example.myapplication

import com.example.myapplication.DTO.CarDTO

fun carDtoToList(carDTO: CarDTO) : ArrayList<Pair<String, String>>{
    val list : ArrayList<Pair<String, String>> = ArrayList()
    list.add(Pair("Цвет: ", carDTO.color.toString()))
    list.add(Pair("Трансмиссия: ", carDTO.transmission.toString()))
    list.add(Pair("Объём двигателя: ", carDTO.engineCapacity.toString()))
    list.add(Pair("Мощность двигателя: ", carDTO.enginePower.toString()))
    list.add(Pair("vin: ", carDTO.vinNumber.toString()))
    list.add(Pair("Пробег: ", carDTO.mileage.toString()))
    list.add(Pair("Пробег: ", carDTO.performance.toString()))

    return list
}