package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.VerifiedInputEvent
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.activities.GridViewAdapter


class AnnouncementsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.announcements_fragment,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grid : GridView = view.findViewById(R.id.gridViewAnnouncement)
        grid.adapter = GridViewAdapter(this.context)
    }
}