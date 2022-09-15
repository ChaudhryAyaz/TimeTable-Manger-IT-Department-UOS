package com.ayaz.csittimetableapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
class class_details_adapter(context: Context, val arraylist: ArrayList<class_details_data>):ArrayAdapter<class_details_data>(context,R.layout.listview_item,arraylist) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view:View =inflater.inflate(R.layout.listview_item,null)
        val time_view = view.findViewById<TextView>(R.id.time_view)
        val room_no_view = view.findViewById<TextView>(R.id.room_no_view)
        val class_detail_view = view.findViewById<TextView>(R.id.class_detail_view)
        time_view.text = arraylist[position].time
        room_no_view.text =   arraylist[position].room_no
        class_detail_view.text = arraylist[position].Class_detail_text
        return view
    }
}