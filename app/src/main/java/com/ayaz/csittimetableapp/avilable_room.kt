package com.ayaz.csittimetableapp
import android.os.Bundle
import android.os.Environment
import android.text.Layout
import android.view.Gravity
import android.view.Gravity.CENTER
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.transition.Slide
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class avilable_room : Fragment() {
    private val filePath = File(Environment.getExternalStorageDirectory().toString() + "/Download/Timetable_v4.xlsx")
    private lateinit var avilableRoomlistview : ListView
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var class_details_array : ArrayList<avilable_room_datatype>
    private lateinit var class_details_array2 : ArrayList<avilable_room_datatype>
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
        return inflater.inflate(R.layout.fragment_avilable_room, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        avilableRoomlistview = view.findViewById(R.id.avilable_list_view)
        var starting_row  = 2
        var starting_cell  = 2
        var temp_text : String
        var index = 0
        val time = getCurrentDate()
        val time_array = time.split(":").toTypedArray()
        val hours = time_array[0].toInt()
        val mintues = time_array[1].toInt()
        if(hours in 1..8) //8:00 or 7:00
        {
            starting_cell = 2

        }
        if (hours == 9  && mintues < 30) // 9:10 or 9:20
        {
         starting_cell = 2
        }
        if(hours == 9 && mintues >= 30) // 9 : 40
        {
            starting_cell = 3
        }
        if (hours > 9  && hours < 11 ) // 10 : 20
        {
            starting_cell = 3
        }
        if(hours >= 11 && hours < 12)
        {
            starting_cell = 4
        }
        if (hours == 12  && mintues < 30) // 12:10 or 12:20
        {
            starting_cell = 4
        }
        if(hours == 12 && mintues >= 30) // 12 : 40
        {
            starting_cell = 5
        }
        if (hours >=13 && hours<14)
        {
            starting_cell = 5
        }
        if (hours >= 14 && hours < 15)
        {
            starting_cell= 6
        }
        if (hours == 15  && mintues < 30) // 12:10 or 12:20
        {
            starting_cell = 6
        }
        if(hours == 15 && mintues >= 30) // 12 : 40
        {
            starting_cell = 7
        }
        if (hours >= 16 && hours < 17 )
        {
            starting_cell = 7
        }
        if (hours == 17  && mintues < 30) // 12:10 or 12:20
        {
            starting_cell = 7
        }
        if(hours == 17 && mintues >= 30) // 12 : 40
        {
            starting_cell = 8
        }
        if (hours >= 18 && hours < 19 )
        {
            starting_cell = 8
        }
        else
        {

        }
        var temp = starting_cell
        var day_name = Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.DAY_OF_WEEK)
        when (day_name) {
            1 -> {
                index = 0
                avilableRoomlistview.visibility = View.GONE
            }
            2 -> {

                index = 0
            }
            3-> {

                index = 1
            }
            4 -> {

                index = 2
            }
            5 -> {

                index = 3
            }
            6 -> {

                index = 4
            }
            7-> {
                index = 0
                avilableRoomlistview.visibility = View.GONE
            }
            else -> {
                Toast.makeText(requireContext(), "There is problem Baby $day_name", Toast.LENGTH_SHORT).show()
            }
        }
        var searchText = ""
        val inputStream = FileInputStream(filePath)
        val xlWb = WorkbookFactory.create(inputStream)
        val xlWs = xlWb.getSheetAt(index)
        var Room_number = arrayOf<String?>()
        var Time_detail = arrayOf<String?>()

        class_details_array = ArrayList()
        class_details_array2 = ArrayList()
        var timefortext = " "

        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            if (temp_text.contains(searchText.toString())) {
                val class_conf = temp_text.split("\n").toTypedArray()
                if (searchText == class_conf[0] ) {
                    val time = xlWs.getRow(1).getCell(starting_cell).toString()
                    time.also { timefortext = it }
                    val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                    Time_detail = append(Time_detail, time)
                    Room_number = append(Room_number, cl_no)
                }
            }
            starting_row++
        }
        val textview_temp = view.findViewById<TextView>(R.id.temp)
        textview_temp.text = "Available Rooms between -> $timefortext"

        var listviewhead= view.findViewById<ListView>(R.id.listview_head)
        if(Time_detail.size > 0)
        {
            val toast = Toast.makeText(requireContext(), "The Room Avilable at this time is  ${Time_detail.size}", Toast.LENGTH_LONG)
//            toast.SetGravity(Gravity.CENTER,0,0)
            toast.show()
            for (i in Time_detail.indices){
                val class_d = avilable_room_datatype(Room_number[i].toString() ,Time_detail[i].toString())
                class_details_array2.add(class_d)
            }
            listviewhead.adapter = avilable_room_adapter(requireContext(),class_details_array2)
        }
        if(Time_detail.size == 0)
        {
            val textView_noclass= view.findViewById<TextView>(R.id.no_room_textview)
            textView_noclass.visibility  = View.VISIBLE
            textView_noclass.text = "No Room Avilable between " + (xlWs.getRow(1).getCell(starting_cell).toString())
            listviewhead.visibility = View.GONE
            val listView_underline = view.findViewById<View>(R.id.temp_view)
        }
        Room_number = arrayOf<String?>()
        Time_detail = arrayOf<String?>()
        starting_cell = 3
        starting_row = 2
        if( !(starting_cell ==  temp)) {
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())


                    }

                }
                starting_row++
            }
        }
        starting_cell = 4
        starting_row = 2
        if(starting_cell!=temp) {
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())


                    }

                }
                starting_row++
            }
        }
        starting_cell = 5
        starting_row = 2
        if(starting_cell!=temp) {
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())


                    }
                }
                starting_row++
            }
        }

        starting_cell = 6
        starting_row = 2
        if(starting_cell!=temp) {
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())


                    }
                }
                starting_row++

            }
        }
        starting_cell = 7
        starting_row = 2
        if(starting_cell!=temp) {
            while (starting_row != 21) {
                temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
                if (temp_text.contains(searchText.toString())) {
                    val class_conf = temp_text.split("\n").toTypedArray()
                    if (searchText == class_conf[0]) {
                        val time = xlWs.getRow(1).getCell(starting_cell).toString()
                        val cl_no = xlWs.getRow(starting_row).getCell(1).toString()
                        Time_detail = append(Time_detail, time)
                        Room_number = append(Room_number, cl_no.toString())
                    }
                }
                starting_row++
            }
        }
        for (i in Time_detail.indices){
            val class_d = avilable_room_datatype(Room_number[i].toString() ,Time_detail[i].toString())
            class_details_array.add(class_d)
        }
        avilableRoomlistview.adapter = avilable_room_adapter( requireContext(),class_details_array)

    }
    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat(getString(R.string.verible))
        return sdf.format(Date())
    }
    fun <T> append(arr: Array<T>, element: T): Array<T?> {
        val array = arr.copyOf(arr.size + 1)
        array[arr.size] = element
        return array
    }
}