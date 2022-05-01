package com.example.myapplication.activities

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import com.bumptech.glide.Glide
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.getData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.android.material.card.MaterialCardView
import java.util.*


class GridViewAdapter(
    private val mContext: Context
) : BaseAdapter() {
    private var mapper = jacksonObjectMapper()
//    private val cars: List<CarDTO> = mapper.readValue(getData("/car/announcements"))

    override fun getCount(): Int {
        return 256
//        return cars.size
    }

    override fun getItem(position: Int): Any {
//        return cars[position]

    return Random().nextInt()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val linearLayout = LinearLayout(mContext)
        val card = MaterialCardView(mContext)
        val image = ImageView(mContext)

        val insideLinearLayout = LinearLayout(mContext);
        Glide.with(mContext).load("http://goo.gl/gEgYUd").into(image);

        val title = TextView(mContext)
        val descText = TextView(mContext)
        title.text = "Z V я люблю гавно"

        descText.text = "и обмазывать им сисечки"
        
        insideLinearLayout.addView(image)
        insideLinearLayout.addView(title)
        insideLinearLayout.addView(descText)
        card.addView(insideLinearLayout)
//        card.layoutParams = ViewGroup.LayoutParams(parent!!.layoutParams.width, 150)
        linearLayout.addView(card)
//        price.text = "12341"
//        price.text = cars[position].price.toString()
//        card.addView(price)
//        return card
        return linearLayout
    }
}