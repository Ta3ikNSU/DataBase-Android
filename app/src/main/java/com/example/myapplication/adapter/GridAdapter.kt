package com.example.myapplication.activities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.Entity.User
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import org.apache.log4j.LogManager
import org.apache.log4j.Logger


class AnnouncementsGridViewAdapter(
    private val mContext: Context,
    private val cars: ArrayList<CarDTO?>,
    private val user : User?
) : BaseAdapter() {
    private val log: Logger = LogManager.getLogger(this.javaClass.name)

    override fun getCount(): Int {
        return cars.size
    }

    override fun getItem(position: Int): Any {
        return cars[position]!!
    }

    override fun getItemId(position: Int): Long {
        return cars[position]!!.vinNumber!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val linearLayout = LayoutInflater.from(mContext).inflate(R.layout.ann_card, parent, false)
        val carPreview = linearLayout.findViewById<ImageView>(R.id.annCardImage)
        val title = linearLayout.findViewById<TextView>(R.id.annCardTitle)
        val price = linearLayout.findViewById<TextView>(R.id.annCardPrice)
        val date = linearLayout.findViewById<TextView>(R.id.annCardRegion)

        // пока так, в идеале добавить парс json'на
        val url = cars[position]!!.announcementDTO!!.photoList
        Picasso.get()
            .load(url)
            .placeholder(com.google.android.material.R.drawable.navigation_empty_icon)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(carPreview)

        title.text = cars[position]!!.brand + " " + cars[position]!!.model
        price.text = cars[position]!!.announcementDTO!!.price.toString() + " ₽"
        date.text = "Регион продажи: " + cars[position]!!.announcementDTO!!.region.toString()

        log.info("add car to grid = {${cars[position]}}")

        linearLayout.setOnClickListener {
            openAnnouncement(cars[position]!!)
        }
        return linearLayout
    }

    private fun openAnnouncement(car : CarDTO){
        val intent = Intent(mContext, AnnouncementActivity::class.java)
        intent.putExtra("CarDTO", car)
        intent.putExtra("user", user)
        mContext.startActivity(intent)
    }
}