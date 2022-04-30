package com.example.myapplication


import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL

fun getData(path: String): String {
    val address = "http://$ip:$host$path"
    print("Request to $address")
    try {
        val url = URL(address).openConnection()
        val bufferedReader = BufferedReader(InputStreamReader(url.getInputStream()))
        val stringBuffer = StringBuffer()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuffer.append(line)
        }
        return stringBuffer.toString()
    } catch (ex : Exception){
        ex.printStackTrace();
    }
    return "";
}