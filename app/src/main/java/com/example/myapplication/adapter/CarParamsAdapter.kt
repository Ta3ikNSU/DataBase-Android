package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DTO.CarDTO
import com.example.myapplication.R
import com.google.android.datatransport.runtime.time.TestClock

class CarParamsAdapter(
    private val params : ArrayList<Pair<String, String>>
) : RecyclerView.Adapter<CarParamsAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val brandView: TextView = itemView.findViewById(R.id.textViewParams)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.param, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.brandView.text =
            params[position].first + " " + params[position].second
    }

    override fun getItemCount(): Int {
        return params.size
    }
}