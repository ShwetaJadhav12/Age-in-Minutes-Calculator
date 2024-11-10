package com.example.ageinmincalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView? = null
    private var ageInMinutes : TextView? = null
// not initialised at this time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val selectDate = findViewById<Button>(R.id.selectDate)
        val date = findViewById<TextView>(R.id.date)
        val age=findViewById<TextView>(R.id.age)
        selectedDate=findViewById(R.id.date)
        ageInMinutes=findViewById(R.id.age)
        val clr=findViewById<Button>(R.id.clr)

    clr.setOnClickListener {
        selectedDate?.text=""
        ageInMinutes?.text=""
        date.text=""
        age.text=""
    }
        selectDate.setOnClickListener {
            datePicker()




        }

    }
    fun datePicker(){
        val myCalendar=Calendar.getInstance()
//        access the date month year
        val myDay=myCalendar.get(Calendar.DAY_OF_MONTH)
        val myMonth=myCalendar.get(Calendar.MONTH)
        val myYear=myCalendar.get(Calendar.YEAR)

        DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            val dateSelected="$dayOfMonth/${month+1}/$year"
            selectedDate?.text=dateSelected
            val sdf=SimpleDateFormat("dd/MM/yyyy",java.util.Locale.ENGLISH)
//            define a pattern in which we want to use our date
            val dateObj=sdf.parse(dateSelected)
            val selectedDateInMin=dateObj.time/60000
            val currentdate=sdf.parse(sdf.format(System.currentTimeMillis()))
//            this will give us the time in milliseconds from 1 jan 1970 in millisec
            val differenceInMin=(currentdate.time/60000)-selectedDateInMin
            ageInMinutes?.text=differenceInMin.toString()





        },
            myYear,
            myMonth,
            myDay
        ).show()



        }

    }
