package com.ayaz.csittimetableapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class CgpaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cgpa, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sem1 = view.findViewById<EditText>(R.id.sem1g)
        val sem1c = view.findViewById<EditText>(R.id.sem1c)
        val sem2 = view.findViewById<EditText>(R.id.sem2g)
        val sem2c = view.findViewById<EditText>(R.id.sem2c)
        val sem3 = view.findViewById<EditText>(R.id.sem3g)
        val sem3c = view.findViewById<EditText>(R.id.sem3c)
        val sem4 = view.findViewById<EditText>(R.id.sem4g)
        val sem4c = view.findViewById<EditText>(R.id.sem4c)
        val sem5 = view.findViewById<EditText>(R.id.sem5g)
        val sem5c = view.findViewById<EditText>(R.id.sem5c)
        val sem6 = view.findViewById<EditText>(R.id.sem6g)
        val sem6c = view.findViewById<EditText>(R.id.sem6c)
        val sem7 = view.findViewById<EditText>(R.id.sem7g)
        val sem7c = view.findViewById<EditText>(R.id.sem7c)
        val sem8 = view.findViewById<EditText>(R.id.sem8g)
        val sem8c = view.findViewById<EditText>(R.id.sem8c)
        val Res = view.findViewById<EditText>(R.id.resultcgpa)
        val button = view.findViewById<Button>(R.id.cgpa_cal)
        button.setOnClickListener {
            if (sem1.text.toString().isEmpty())
            {
                sem1.setError("This field is required or enter zero")

            }
            else if (sem1c.text.toString().isEmpty())
            {
                sem1c.setError("This field is required or enter zero")

            }else if (sem2.text.toString().isEmpty())
            {
                sem2.setError("This field is required or enter zero")

            }else if (sem2c.text.toString().isEmpty())
            {
                sem2c.setError("This field is required or enter zero")

            }else if (sem3.text.toString().isEmpty())
            {
                sem3.setError("This field is required or enter zero")

            }else if (sem3c.text.toString().isEmpty())
            {
                sem3c.setError("This field is required or enter zero")

            }else if (sem4.text.toString().isEmpty())
            {
                sem4.setError("This field is required or enter zero")

            }else if (sem4c.text.toString().isEmpty())
            {
                sem4c.setError("This field is required or enter zero")

            }else if (sem5.text.toString().isEmpty())
            {
                sem5.setError("This field is required or enter zero")

            }else if (sem5c.text.toString().isEmpty())
            {
                sem5c.setError("This field is required or enter zero")

            }else if (sem6.text.toString().isEmpty())
            {
                sem6.setError("This field is required or enter zero")

            }else if (sem6c.text.toString().isEmpty())
            {
                sem6c.setError("This field is required or enter zero")

            }else if (sem7.text.toString().isEmpty())
            {
                sem7.setError("This field is required or enter zero")

            }else if (sem7c.text.toString().isEmpty())
            {
                sem7c.setError("This field is required or enter zero")

            }else if (sem8.text.toString().isEmpty())
            {
                sem8.setError("This field is required or enter zero")

            }else if (sem8c.text.toString().isEmpty())
            {
                sem8c.setError("This field is required or enter zero")

            }
            else {
                val Gp_pts = sem1.text.toString().toDouble() * sem1c.text.toString()
                    .toDouble() + sem2.text.toString().toDouble() * sem2c.text.toString()
                    .toDouble() + sem3.text.toString().toDouble() * sem3c.text.toString()
                    .toDouble() + sem4.text.toString().toDouble() * sem4c.text.toString()
                    .toDouble() + sem5.text.toString().toDouble() * sem5c.text.toString()
                    .toDouble() + sem6.text.toString().toDouble() * sem6c.text.toString()
                    .toDouble() + sem7.text.toString().toDouble() * sem7c.text.toString()
                    .toDouble() + sem8.text.toString().toDouble() * sem8c.text.toString().toDouble()
                val Credits_t = sem1c.text.toString().toInt() + sem2c.text.toString()
                    .toInt() + sem3c.text.toString().toInt() + sem4c.text.toString()
                    .toInt() + sem5c.text.toString().toInt() + sem6c.text.toString()
                    .toInt() + sem7c.text.toString().toInt() + sem8c.text.toString().toInt()
                val CGPA = Gp_pts / Credits_t
                Res.setText(CGPA.toString())
            }
        }
    }
}