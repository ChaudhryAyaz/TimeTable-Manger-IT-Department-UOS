package com.ayaz.csittimetableapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class HomeFragment : Fragment() {
    private val filePath = File(Environment.getExternalStorageDirectory().toString() + "/Download/Timetable_v4.xlsx")
    var selectedclass :String?=""
    private lateinit var class_details_array : ArrayList<class_details_data>
    lateinit var listview : ListView
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val totalclass_view = view.findViewById<TextView>(R.id.totalclass_view)
        super.onViewCreated(view, savedInstanceState)
        val textView : TextView = view.findViewById(R.id.class_name)
        selectedclass = arguments?.getString("key")
        listview = view.findViewById(R.id.home_listview)
        textView.setOnClickListener {
            val fragmentmanger = activity?.supportFragmentManager
            val fragemettransaction = fragmentmanger?.beginTransaction()
            fragemettransaction?.replace(R.id.framelayout,classnamepicker())
            fragemettransaction?.commit()
        }
        var index  = 0
        var day_name_view = view.findViewById<TextView>(R.id.date_view)
        var day_name = Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.DAY_OF_WEEK)
        var day_name2= Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.DAY_OF_MONTH)
        var day_name3= Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.MONTH) + 1
        when (day_name) {

            1 -> {
                day_name_view.text = "Sunday$day_name2/$day_name3"
                index = 0
                totalclass_view.text = "Holiday"
                listview.visibility = GONE
            }
            2 -> {
                day_name_view.text = "Monday $day_name2/$day_name3"
                index = 0
            }
            3-> {
                day_name_view.text = "Tuesday $day_name2/$day_name3"
                index = 1
            }
            4 -> {
                day_name_view.text = "Wednesday $day_name2/$day_name3"
                index = 2
            }
            5 -> {
                day_name_view.text = "Thursday $day_name2/$day_name3"
                index = 3
            }
            6 -> {
                day_name_view.text = "Friday $day_name2/$day_name3"
                index = 4
            }
            7-> {
                day_name_view.text = "Saturday $day_name2/$day_name3"
                index = 0
                totalclass_view.text = "Holiday"
                listview.visibility = GONE

            }
            else -> {
                Toast.makeText(requireContext(), "There is problem Baby $day_name", Toast.LENGTH_SHORT).show()
            }
        }

        var starting_row  = 2
        var starting_cell  = 2
        var temp_text : String
        var text = ""
        var searchText = "BSCS 6th (Self)"
        if (selectedclass != null) {
            searchText = selectedclass.toString()
        }
        else{
            if (loaddata()!= "null")
            {
                searchText = loaddata().toString()
            }
        }
        textView.text = searchText
            val inputStream = FileInputStream(filePath)
            val xlWb = WorkbookFactory.create(inputStream)
            val xlWs = xlWb.getSheetAt(index)
            var class_detail = arrayOf<String?>()
            var Room_number = arrayOf<String?>()
            var Time_detail = arrayOf<String?>()
            class_details_array = ArrayList()
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                        text = temp_text
                        class_detail = append(class_detail, text)
                    }
                }
                starting_row++
            }
            starting_cell = 3
            starting_row = 2
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                        text = temp_text
                        class_detail = append(class_detail, text)
                    }

                }
                starting_row++
            }
            starting_cell = 4
            starting_row = 2
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                        text = temp_text
                        class_detail = append(class_detail, text)
                    }

                }
                starting_row++
            }
            starting_cell = 5
            starting_row = 2
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                        text = temp_text
                        class_detail = append(class_detail, text)
                    }
                }
                starting_row++
            }
            starting_cell = 6
            starting_row = 2
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                        text = temp_text
                        class_detail = append(class_detail, text)
                    }
                }
                starting_row++

            }
            starting_cell = 7
            starting_row = 2
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                        text = temp_text
                        class_detail = append(class_detail, text)
                    }

                }
                starting_row++

            }
            starting_cell = 8
            starting_row = 2
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                        text = temp_text
                        class_detail = append(class_detail, text)
                    }
                }
                starting_row++

            }

        for (i in class_detail.indices){
            val class_d = class_details_data(Time_detail[i].toString(),Room_number[i].toString() ,class_detail[i].toString())
            class_details_array.add(class_d)
        }
        if(day_name > 1 && day_name < 7 ) {
            totalclass_view.text = class_detail.size.toString()
            listview.adapter = class_details_adapter(requireContext(), class_details_array)
        }
        savedata()

    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun <T> append(arr: Array<T>, element: T): Array<T?> {
        val array = arr.copyOf(arr.size + 1)
        array[arr.size] = element
        return array
    }
     fun savedata()
    {
        val classname_viwe = view?.findViewById<TextView>(R.id.class_name)
        val classname = classname_viwe?.text.toString()

        val sheredperfernence : SharedPreferences? = this.activity?.getSharedPreferences("sheredPrefs",Context.MODE_PRIVATE)
        val editor = sheredperfernence?.edit()
        editor?.apply {
            putString("class_name",classname)

        }?.apply()

    }
    fun loaddata() : String?
    {
        val sheredperfernence : SharedPreferences? = this.activity?.getSharedPreferences("sheredPrefs",Context.MODE_PRIVATE)
        val savestring = sheredperfernence?.getString("class_name",null)
        return savestring.toString()
    }



}