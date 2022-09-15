package com.ayaz.csittimetableapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GpaFragment : Fragment() {
    // TODO: Rename and change types of parameters
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

        return inflater.inflate(R.layout.fragment_gpa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button_cal=view.findViewById<Button>(R.id.calculate)
        val s1m= view.findViewById(R.id.s1m) as EditText
        val s1c= view.findViewById(R.id.s1c) as EditText
        val s2m= view.findViewById(R.id.s2m) as EditText
        val s2c= view.findViewById(R.id.s2c) as EditText
        val s3m= view.findViewById(R.id.s3m) as EditText
        val s3c= view.findViewById(R.id.s3c) as EditText
        val s4m= view.findViewById(R.id.s4m) as EditText
        val s4c= view.findViewById(R.id.s4c) as EditText
        val s5m= view.findViewById(R.id.s5m) as EditText
        val s5c= view.findViewById(R.id.s5c) as EditText
        val s6m= view.findViewById(R.id.s6m) as EditText
        val s6c= view.findViewById(R.id.s6c) as EditText
        val s7m= view.findViewById(R.id.s7m) as EditText
        val s7c= view.findViewById(R.id.s7c) as EditText
        val s8m= view.findViewById(R.id.s8m) as EditText
        val s8c= view.findViewById(R.id.s8c) as EditText
        val GPA= view.findViewById(R.id.result) as EditText
        button_cal.setOnClickListener {

            if (s1m.text.toString().isEmpty())
            {
                s1m.setError("This field is required or enter zero")

            }
            else if (s1c.text.toString().isEmpty())
            {
                s1c.setError("This field is required or enter zero")

            }else if (s2m.text.toString().isEmpty())
            {
                s2m.setError("This field is required or enter zero")

            }else if (s2c.text.toString().isEmpty())
            {
                s2c.setError("This field is required or enter zero")

            }else if (s3m.text.toString().isEmpty())
            {
                s3m.setError("This field is required or enter zero")

            }else if (s3c.text.toString().isEmpty())
            {
                s3c.setError("This field is required or enter zero")

            }else if (s4m.text.toString().isEmpty())
            {
                s4m.setError("This field is required or enter zero")

            }else if (s4c.text.toString().isEmpty())
            {
                s4c.setError("This field is required or enter zero")

            }else if (s5m.text.toString().isEmpty())
            {
                s5m.setError("This field is required or enter zero")

            }else if (s5c.text.toString().isEmpty())
            {
                s5c.setError("This field is required or enter zero")

            }else if (s6m.text.toString().isEmpty())
            {
                s6m.setError("This field is required or enter zero")

            }else if (s6c.text.toString().isEmpty())
            {
                s6c.setError("This field is required or enter zero")

            }else if (s7m.text.toString().isEmpty())
            {
                s7m.setError("This field is required or enter zero")

            }else if (s7c.text.toString().isEmpty())
            {
                s7c.setError("This field is required or enter zero")

            }else if (s8m.text.toString().isEmpty())
            {
                s8m.setError("This field is required or enter zero")

            }else if (s8c.text.toString().isEmpty())
            {
                s8c.setError("This field is required or enter zero")

            }
            else {
                var s1mg: Double = gp_pints(s1m.text.toString().toInt())
                s1mg *= s1c.text.toString().toInt()
                var s2mg: Double = gp_pints(s2m.text.toString().toInt())
                s2mg *= s2c.text.toString().toInt()
                var s3mg: Double = gp_pints(s3m.text.toString().toInt())
                s3mg *= s3c.text.toString().toInt()
                var s4mg: Double = gp_pints(s4m.text.toString().toInt())
                s4mg *= s4c.text.toString().toInt()
                var s5mg: Double = gp_pints(s5m.text.toString().toInt())
                s5mg *= s5c.text.toString().toInt()
                var s6mg: Double = gp_pints(s6m.text.toString().toInt())
                s6mg *= s6c.text.toString().toInt()
                var s7mg: Double = gp_pints(s7m.text.toString().toInt())
                s7mg *= s7c.text.toString().toInt()
                var s8mg: Double = gp_pints(s8m.text.toString().toInt())
                s8mg *= s8c.text.toString().toInt()
                var s_sum: Double = s1mg + s2mg + s3mg + s4mg + s5mg + s6mg + s7mg + s8mg
                val c_sum =
                    s1c.text.toString().toInt() + s2c.text.toString().toInt() + s3c.text.toString()
                        .toInt() + s4c.text.toString().toInt() + s5c.text.toString()
                        .toInt() + s6c.text.toString().toInt() + s7c.text.toString()
                        .toInt() + s8c.text.toString().toInt()
                val gpa_is: Double = s_sum / c_sum
                GPA.setText("$gpa_is")
            }
        }




    }
    private fun gp_pints(Subjectmarks : Int): Double {
        when {
            Subjectmarks < 40 -> {
                return 0.00
            }
            Subjectmarks == 40 -> {
                return 1.00
            }
            Subjectmarks == 41 -> {
                return 1.10
            }
            Subjectmarks == 42 -> {
                return 1.20
            }
            Subjectmarks == 43 -> {
                return 1.30
            }
            Subjectmarks == 44 -> {
                return 1.40
            }
            Subjectmarks == 45 -> {
                return 1.50
            }
            Subjectmarks == 46 -> {
                return 1.60
            }
            Subjectmarks == 47 -> {
                return 1.70
            }
            Subjectmarks == 48 -> {
                return 1.80
            }
            Subjectmarks == 49 -> {
                return 1.90
            }
            Subjectmarks == 50 -> {
                return 2.00
            }
            Subjectmarks == 51 -> {
                return 2.06
            }
            Subjectmarks == 52 -> {
                return 2.12
            }
            Subjectmarks == 53 -> {
                return 2.18
            }
            Subjectmarks == 54 -> {
                return 2.24
            }
            Subjectmarks == 55 -> {
                return 2.30
            }
            Subjectmarks == 56 -> {
                return 2.36
            }
            Subjectmarks == 57 -> {
                return 2.43
            }
            Subjectmarks == 58 -> {
                return 2.50
            }
            Subjectmarks == 58 -> {
                return 2.50
            }
            Subjectmarks == 59 -> {
                return 2.57
            }
            Subjectmarks == 60 -> {
                return 2.64
            }
            Subjectmarks == 61 -> {
                return 2.70
            }
            Subjectmarks == 62 -> {
                return 2.78
            }
            Subjectmarks == 63 -> {
                return 2.85
            }
            Subjectmarks == 64 -> {
                return 2.92
            }
            Subjectmarks == 65 -> {
                return 3.00
            }
            Subjectmarks == 66 -> {
                return 3.07
            }
            Subjectmarks == 67 -> {
                return 3.14
            }
            Subjectmarks == 68 -> {
                return 3.20
            }
            Subjectmarks == 69 -> {
                return 3.27
            }
            Subjectmarks == 70 -> {
                return 3.34
            }
            Subjectmarks == 71 -> {
                return 3.40
            }
            Subjectmarks == 72 -> {
                return 3.47
            }
            Subjectmarks == 73 -> {
                return 3.54
            }
            Subjectmarks == 74 -> {
                return 3.54
            }
            Subjectmarks == 75 -> {
                return 3.67
            }
            Subjectmarks == 76 -> {
                return 3.74
            }
            Subjectmarks == 77 -> {
                return 3.80
            }
            Subjectmarks == 78 -> {
                return 3.87
            }
            Subjectmarks == 79 -> {
                return 3.94
            }
            Subjectmarks >= 80 -> {
                return 4.00
            }
            else -> {
                return 0.00
            }
        }
    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            GpaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}