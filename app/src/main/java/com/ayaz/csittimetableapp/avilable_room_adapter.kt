package com.ayaz.csittimetableapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class avilable_room_adapter(context: Context, val arraylist: ArrayList<avilable_room_datatype>):
    ArrayAdapter<avilable_room_datatype>(context,R.layout.avilable_room_item,arraylist) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.avilable_room_item, null)
        val time_view = view.findViewById<TextView>(R.id.time_duration)
        val room_no_view = view.findViewById<TextView>(R.id.room_name)
        time_view.text = arraylist[position].time_duration
        room_no_view.text = arraylist[position].room_name
        return view
    }
}