package com.ayaz.csittimetableapp

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.activity.OnBackPressedDispatcher
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class classnamepicker : Fragment() {
    val fileurl =
        "https://drive.google.com/drive/folders/11gVZ2JMPXNWf4wr_2NXxzqOwObMMZBZU?usp=sharing"
    private val filePath =
        File(Environment.getExternalStorageDirectory().toString() + "/Download/Timetable_v4.xlsx")
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
        classname_listview = view.findViewById(R.id.classes_name_listview)
        var temp_text: String
        var starting_row = 2
        var starting_cell = 2
        var inputStream = FileInputStream(filePath)
        var xlWb = WorkbookFactory.create(inputStream)
        var xlWs = xlWb.getSheetAt(0)
        classes_names_array = ArrayList()
        var class_detail = arrayOf<String?>()
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 3
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)

            starting_row++

        }
        starting_cell = 4
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)

            starting_row++

        }
        starting_cell = 5
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 6
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 7
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 8
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++
        }
        class_detail.sort()
        class_detail = removedubplicate(class_detail)
        for (i in class_detail.indices) {
            val user = class_name_dataType(class_detail[i].toString())
            classes_names_array.add(user)
        }
        classname_listview.isClickable = true
        classname_listview.adapter = class_names_adapter(requireContext(), classes_names_array)
        class_name_pass = activity as communication

        starting_row = 2
        starting_cell = 2
        inputStream = FileInputStream(filePath)
        xlWb = WorkbookFactory.create(inputStream)
        xlWs = xlWb.getSheetAt(1)
        classes_names_array = ArrayList()
        class_detail = arrayOf<String?>()
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 3
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)

            starting_row++

        }
        starting_cell = 4
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)

            starting_row++

        }
        starting_cell = 5
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 6
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 7
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++

        }
        starting_cell = 8
        starting_row = 2
        while (starting_row != 21) {
            temp_text = xlWs.getRow(starting_row).getCell(starting_cell).toString()
            var classdetail = temp_text.split("\n").toTypedArray()
            var temp_class_name = classdetail[0]
            class_detail = append(class_detail, temp_class_name)
            starting_row++
        }
        class_detail.sort()
        class_detail = removedubplicate(class_detail)
        for (i in class_detail.indices) {
            val user = class_name_dataType(class_detail[i].toString())
            classes_names_array.add(user)
        }
        classname_listview.isClickable = true
        classname_listview.adapter = class_names_adapter(requireContext(), classes_names_array)
        class_name_pass = activity as communication

        classname_listview.setOnItemClickListener { parent, view, position, id ->
            val selectedclass = class_detail[position].toString()
            class_name_pass.passdata(selectedclass)
        }




    }

    fun <T> append(arr: Array<T>, element: T): Array<T?> {
        val array = arr.copyOf(arr.size + 1)
        array[arr.size] = element
        return array
    }


    companion object {
        fun newInstance(param1: String, param2: String) =
            classnamepicker().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun removedubplicate(arr: Array<String?>): Array<String?> {
        return arr.distinct().toTypedArray()
    }

}