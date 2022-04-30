package com.example.myapplication.activities

import android.R
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.getData
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.util.*


class GridViewAdapter(
    private val mContext : Context?
) : BaseAdapter() {
    var mapper = ObjectMapper()

    fun onCreateView(name: String, context: Context, attrs: AttributeSet) {
        val anns = getData("/car/announcements")
        print(anns)
        val cars: CarDTO? = mapper.readValue(anns, CarDTO::class.java)
        print(cars)
    }

    override fun getCount(): Int {
        return 256
    }

    override fun getItem(position: Int): Any {
        return Random().nextInt()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val card : LinearLayout = LinearLayout(mContext)
        val iv = ImageView(mContext)
        val tx = TextView(mContext)
        with(tx) {
            this.text = "гаввно"
        }
        card.addView(tx)
        card.addView(iv)
        return card
    }
}