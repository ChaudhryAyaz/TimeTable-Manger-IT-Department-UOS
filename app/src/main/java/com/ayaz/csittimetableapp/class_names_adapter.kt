package com.ayaz.csittimetableapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
class class_names_adapter(context : Context,val arraylist:ArrayList<class_name_dataType>):ArrayAdapter<class_name_dataType>(context,R.layout.classname_listview_item,arraylist){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater :LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.classname_listview_item,null)
        val textView : TextView = view.findViewById(R.id.class_names_textview)
        textView.text = arraylist[position].classes_names
        return view
    }
}
