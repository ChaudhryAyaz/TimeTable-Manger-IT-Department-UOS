package com.ayaz.csittimetableapp

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class classnamepicker : Fragment() {
    private val filePath = File(Environment.getExternalStorageDirectory().toString() + "/Download/Timetable_v4.xlsx")
    lateinit var class_name_pass: communication
    lateinit var classname_listview: ListView
    lateinit var classes_names_array: ArrayList<class_name_dataType>
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_classnamepicker, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        classes_names_array = ArrayList()
        classname_listview = view.findViewById(R.id.classes_name_listview)
        var tempstarting_row = 2
        var ending = 19
        var day_name = Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.DAY_OF_MONTH)



        var class_detail = arrayOf<String?>()
        var temp_text: String


        fun classesName (temp_starting_row:Int, ending: Int) {
            var inputStream = FileInputStream(filePath)
            var xlWb = WorkbookFactory.create(inputStream)
            var xlWs = xlWb.getSheetAt(0)
            var starting_row = temp_starting_row
            var starting_cell = 2


            while (starting_row != ending) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                var classdetail = temp_text.split("\n").toTypedArray()
                var temp_class_name = classdetail[0]
                class_detail = append(class_detail, temp_class_name)
                starting_row++

            }
            starting_cell = 3
            starting_row = temp_starting_row
            while (starting_row != ending) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                var classdetail = temp_text.split("\n").toTypedArray()
                var temp_class_name = classdetail[0]
                class_detail = append(class_detail, temp_class_name)

                starting_row++

            }
            starting_cell = 4
            starting_row = temp_starting_row
            while (starting_row != ending) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                var classdetail = temp_text.split("\n").toTypedArray()
                var temp_class_name = classdetail[0]
                class_detail = append(class_detail, temp_class_name)

                starting_row++

            }
            starting_cell = 5
            starting_row = temp_starting_row
            while (starting_row != ending) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                var classdetail = temp_text.split("\n").toTypedArray()
                var temp_class_name = classdetail[0]
                class_detail = append(class_detail, temp_class_name)
                starting_row++

            }
            starting_cell = 6
            starting_row = temp_starting_row
            while (starting_row != ending) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                var classdetail = temp_text.split("\n").toTypedArray()
                var temp_class_name = classdetail[0]
                class_detail = append(class_detail, temp_class_name)
                starting_row++

            }
            starting_cell = 7
            starting_row = temp_starting_row
            while (starting_row != ending) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                var classdetail = temp_text.split("\n").toTypedArray()
                var temp_class_name = classdetail[0]
                class_detail = append(class_detail, temp_class_name)
                starting_row++

            }
            starting_cell = 8
            starting_row = temp_starting_row
            while (starting_row != ending) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                var classdetail = temp_text.split("\n").toTypedArray()
                var temp_class_name = classdetail[0]
                class_detail = append(class_detail, temp_class_name)
                starting_row++
            }


            xlWb.close()
        }


        try {
            classes_names_array = getArrayList("aya")
        }
        catch (e:Exception)
        {
            classesName(2,19)
            classesName(22,39)
            classesName(42,59)
            classesName(62,79)
            classesName(82,99)
            class_detail.sort()
            class_detail = removedubplicate(class_detail)
            for (i in class_detail.indices) {
                val user = class_name_dataType(class_detail[i].toString())
                classes_names_array.add(user)
            }
        }

        classname_listview.isClickable = true
        saveArrayList(classes_names_array,"aya")
        classname_listview.adapter = class_names_adapter(requireContext(), classes_names_array)
        class_name_pass = activity as communication
        val progressBar2= view.findViewById<ProgressBar>(R.id.progressBar3)
        val textview  = view.findViewById<TextView>(R.id.textView3)

        classname_listview.setOnItemClickListener { parent, view, position, id ->
            progressBar2.visibility = View.VISIBLE
            classname_listview.visibility = View.GONE
            textview.text= "Please Wait... This may take Time"
            var selecteditem2 = classname_listview.getItemAtPosition(position) as class_name_dataType
            var selectedclass  =  selecteditem2.classes_names.toString()
            class_name_pass.passdata(selectedclass)
        }


    }

    fun saveArrayList(list: ArrayList<class_name_dataType>, key: String?) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String?): ArrayList<class_name_dataType> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val gson = Gson()
        val json: String = prefs.getString(key, null).toString()
        val type: Type = object : TypeToken<ArrayList<class_name_dataType>?>() {}.getType()
        return gson.fromJson(json, type)
    }


    fun <T> append(arr: Array<T>, element: T): Array<T?> {
        val array = arr.copyOf(arr.size + 1)
        array[arr.size] = element
        return array
    }

    fun removedubplicate(arr: Array<String?>): Array<String?> {
        return arr.distinct().toTypedArray()
    }

}