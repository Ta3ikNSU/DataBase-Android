package com.example.myapplication.activities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.squareup.picasso.Picasso
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

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val linearLayout = LayoutInflater.from(mContext).inflate(R.layout.ann_card, parent, false)
        val image = linearLayout.findViewById<ImageView>(R.id.annCardImage);
        val title = linearLayout.findViewById<TextView>(R.id.annCardTitle);
        val desc = linearLayout.findViewById<TextView>(R.id.annCardDesc);
//        Glide.with(mContext).load("http://goo.gl/gEgYUd").into(image);
        val url: String = "https://www.meme-arsenal.com/memes/8882e44a1bb04091839c8586160890b9.jpg"

        Picasso.get()
            .load(url)
            .placeholder(com.google.android.material.R.drawable.navigation_empty_icon)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(image);

        title.text = "Z V я люблю гавно"
        desc.text = "и обмазывать им сисечки"

//        price.text = "12341"
//        price.text = cars[position].price.toString()
//        card.addView(price)
//        return card
        return linearLayout
    }
}